package com.neusoft.biz.panel.custcpt.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFPicture;
import org.apache.poi.xssf.usermodel.XSSFPictureData;
import org.apache.poi.xssf.usermodel.XSSFShape;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.neusoft.base.annotation.SystemLog;
import com.neusoft.base.comm.GlobalConst;
import com.neusoft.base.controller.AbstractBaseController;
import com.neusoft.base.controller.R;
import com.neusoft.base.utils.CalendarUtils;
import com.neusoft.base.utils.CommUtils;
import com.neusoft.base.utils.PoiUtils;
import com.neusoft.biz.console.sys.coding.service.CodingService;
import com.neusoft.biz.panel.custcpt.service.CustCptService;
import com.neusoft.biz.panel.form.model.Form;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.exceptions.ExceptionUtil;

/**
 * 客户工单
 *
 * @author：yu8home
 * @date：2018年7月13日 下午5:01:12
 */
@RestController
@RequestMapping("/panel/custcpt")
public class CustCptController extends AbstractBaseController<CustCptService, Form> {
    @Autowired
    private CodingService codingService;

    @Override
    @SystemLog("工单新建")
    @RequestMapping("/add")
    public R add(Form t) {
        int cnt = baseService.checkFormSn(t);
        if (cnt > 0) {
            return R.fail(i18n.getMessage("sn.exists"));
        }
        return super.add(t);
    }

    @Override
    @RequestMapping("/update")
    public R update(Form t) {
        int cnt = baseService.checkFormSn(t);
        if (cnt > 0) {
            return R.fail(i18n.getMessage("sn.exists"));
        }
        return super.update(t);
    }

    @RequestMapping("/batchSubmit")
    public R batchSubmit(Form t) {
        t.setCreateUserId(this.getUserId());
        this.baseService.batchSubmit(t);
        return R.success();
    }

    // 上传图片
    @RequestMapping(value = "/upImg")
    public R upImg(List<MultipartFile> fileName, String complaintNo, String imgType) {
        List<String> rsList = new ArrayList<String>();
        String cl = CalendarUtils.parseString(Calendar.getInstance(), DatePattern.PURE_DATETIME_PATTERN);
        try {
            File dir = new File(GlobalConst.UPLOAD_IMG + complaintNo);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            MultipartFile mf;
            File f;
            for (int i = 0; i < fileName.size(); ++i) {
                mf = fileName.get(i);
                f = new File(dir, imgType + "_" + cl + "_" + i + mf.getOriginalFilename().substring(mf.getOriginalFilename().lastIndexOf(".")));
                mf.transferTo(f);
                rsList.add(f.getAbsolutePath());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return R.success(rsList);
    }

    // 回显图片
    @RequestMapping("/showImg")
    public void showImg(HttpServletRequest request, HttpServletResponse response, @RequestParam String filePath) throws IOException {
        InputStream in = null;
        OutputStream out = null;
        try {
            in = new FileInputStream(URLDecoder.decode(filePath, "UTF-8"));
            out = response.getOutputStream();

            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = in.read(bytes)) > 0) {
                out.write(bytes, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CommUtils.close(in, out);
        }
    }

    @RequestMapping(value = "/cloned")
    public R cloned(Integer complaintId) {
        baseService.cloned(complaintId);
        return R.success();
    }

    @RequestMapping(value = "/confirm")
    public R confirm(Form t) {
        baseService.confirm(t);
        return R.success();
    }

    @SystemLog("客户确认闭环")
    @RequestMapping(value = "/confirmClosed")
    public R confirmClosed(Form t) {
        t.setCreateUserId(this.getUserId());
        baseService.confirmClosed(t);
        return R.success();
    }

    // excel模板文件下载
    @RequestMapping("/xlsDownload/{fileName}")
    public void xlsDownload(HttpServletRequest request, HttpServletResponse response, @PathVariable("fileName") String fileName) {
        InputStream in = null;
        XSSFWorkbook wb = null;
        try {
            File f = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "public/xls/" + fileName);
            in = new FileInputStream(f);
            wb = new XSSFWorkbook(in);
            response.setContentType(GlobalConst.CONTENTYPE_XLSX);
            response.setHeader("content-disposition", "attachment;filename=" + fileName);
            wb.write(response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CommUtils.close(wb, in);
        }
    }

    // excel导入
    @Override
    @SystemLog("工单批量导入")
    @RequestMapping("/excelImport")
    public R excelImport(@RequestParam(value = "fileName") MultipartFile file) {
        InputStream in = null;
        XSSFWorkbook wb = null;
        R r = null;

        try {
            in = file.getInputStream();
            wb = new XSSFWorkbook(in);
            XSSFSheet sheet = wb.getSheetAt(0);
            int cnt = sheet.getPhysicalNumberOfRows();

            // 解析excel图片
            List<XSSFPictureData> pcs = wb.getAllPictures();
            if (CollectionUtils.isEmpty(pcs)) {
                return R.fail(i18n.getMessage("excel.pic.null"));
            }
            Map<String, XSSFPictureData> picMap = new HashMap<String, XSSFPictureData>();
            List<XSSFShape> hsList = sheet.getDrawingPatriarch().getShapes();
            for (XSSFShape shape : hsList) {
                if (shape instanceof XSSFPicture) {
                    XSSFPicture pic = (XSSFPicture) shape;
                    XSSFClientAnchor anchor = pic.getClientAnchor();
                    picMap.put(anchor.getRow1() + "_" + anchor.getCol1(), pic.getPictureData());
                }
            }

            List<Form> rsList = new ArrayList<Form>();
            List<String> i18nErrList = new ArrayList<String>();
            List<String> errList = new ArrayList<String>();
            Form dt;
            String complaintNo;
            Row row;
            for (int i = 2; i < cnt; i++) {
                row = sheet.getRow(i);
                dt = new Form();
                complaintNo = codingService.getCodingNextVal(GlobalConst.CODING_CPT);
                int k = 0;
                dt.setCustComplaintNo(PoiUtils.getCellStringNull(row.getCell(k++), errList, "custComplaintNo"));// 客户投诉号
                dt.setOrderNo(PoiUtils.getCellStringNull(row.getCell(k++), errList, "orderNo"));// 订单号
                dt.setCustOrderNo(PoiUtils.getCellStringNull(row.getCell(k++), errList, "cust.orderNo"));// 客户订单号
                dt.setCbuSn(PoiUtils.getCellStringNull(row.getCell(k++), errList, "cbu.sn"));// 整机序列号
                dt.setBadPhase(PoiUtils.getCellStringValue(row.getCell(k++), errList, "bad.phase"));// 不良阶段
                dt.setFailureType(PoiUtils.getCellStringNull(row.getCell(k++), errList, "phenomenon.desc"));// 故障现象类型
                dt.setPartName(PoiUtils.getCellStringNull(row.getCell(k++), errList, "part.name"));// 部件名称
                dt.setPanelModuleModel(PoiUtils.getCellStringValue(row.getCell(k++), errList, "panel.module.model"));// 屏体模组型号
                dt.setPanelModuleSn(PoiUtils.getCellStringValue(row.getCell(k++), errList, "panel.module.sn"));// 屏体模组序列号
                dt.setOcModel(PoiUtils.getCellStringValue(row.getCell(k++), errList, "oc.model"));// 玻璃型号
                dt.setOcSn(PoiUtils.getCellStringValue(row.getCell(k++), errList, "oc.pcbi.sn"));// 玻璃序列号
                dt.setFailureRemark(PoiUtils.getCellStringValue(row.getCell(k++), errList, "fault.remark"));// 故障现象描述
                dt.setExchangeTestRs(PoiUtils.getCellStringValue(row.getCell(k++), errList, "mura.test.result"));// 对调试验/MURA测试结果
                dt.setOtherCheckRs(PoiUtils.getCellStringNull(row.getCell(k++), errList, "other.checking.result"));

                File dir = new File(GlobalConst.UPLOAD_IMG + complaintNo);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                dt.setCbuSnImg1(createImg(picMap, dir, "cbuSnImg", i + "_" + (k++)));// 整机序列号
                dt.setCbuSnImg2(createImg(picMap, dir, "cbuSnImg", i + "_" + (k++)));
                dt.setCbuSnImg3(createImg(picMap, dir, "cbuSnImg", i + "_" + (k++)));
                dt.setBadPhenImg1(createImg(picMap, dir, "badPhenImg", i + "_" + (k++)));// 不良阶段
                dt.setBadPhenImg2(createImg(picMap, dir, "badPhenImg", i + "_" + (k++)));
                dt.setBadPhenImg3(createImg(picMap, dir, "badPhenImg", i + "_" + (k++)));
                dt.setPanelModuleSnImg1(createImg(picMap, dir, "panelModuleSnImg", i + "_" + (k++)));// 屏体模组序列号
                dt.setPanelModuleSnImg2(createImg(picMap, dir, "panelModuleSnImg", i + "_" + (k++)));
                dt.setPanelModuleSnImg3(createImg(picMap, dir, "panelModuleSnImg", i + "_" + (k++)));
                dt.setOcSnImg1(createImg(picMap, dir, "ocSnImg", i + "_" + (k++)));// 玻璃序列号
                dt.setOcSnImg2(createImg(picMap, dir, "ocSnImg", i + "_" + (k++)));
                dt.setOcSnImg3(createImg(picMap, dir, "ocSnImg", i + "_" + (k++)));
                dt.setScreenFacPackingImg1(createImg(picMap, dir, "screenFacPackingImg", i + "_" + (k++)));// 屏厂包装箱贴纸
                dt.setScreenFacPackingImg2(createImg(picMap, dir, "screenFacPackingImg", i + "_" + (k++)));
                dt.setScreenFacPackingImg3(createImg(picMap, dir, "screenFacPackingImg", i + "_" + (k++)));
                dt.setOutsidePackingImg1(createImg(picMap, dir, "outsidePackingImg", i + "_" + (k++)));// 包装箱外部状态
                dt.setOutsidePackingImg2(createImg(picMap, dir, "outsidePackingImg", i + "_" + (k++)));
                dt.setOutsidePackingImg3(createImg(picMap, dir, "outsidePackingImg", i + "_" + (k++)));
                dt.setInsidePackingImg1(createImg(picMap, dir, "insidePackingImg", i + "_" + (k++)));// 包装箱内部状态
                dt.setInsidePackingImg2(createImg(picMap, dir, "insidePackingImg", i + "_" + (k++)));
                dt.setInsidePackingImg3(createImg(picMap, dir, "insidePackingImg", i + "_" + (k++)));
                dt.setScrappedImg1(createImg(picMap, dir, "scrappedImg", i + "_" + (k++)));// 报废
                dt.setScrappedImg2(createImg(picMap, dir, "scrappedImg", i + "_" + (k++)));
                dt.setScrappedImg3(createImg(picMap, dir, "scrappedImg", i + "_" + (k++)));

                if (dt.getBadPhenImg1() == null && dt.getBadPhenImg2() == null && dt.getBadPhenImg3() == null) {
                    String msg = "img.isNull.phenomenon.pic";
                    if (!errList.contains(msg)) {
                        errList.add(msg);
                    }
                }
                if (dt.getOcSnImg1() == null && dt.getOcSnImg2() == null && dt.getOcSnImg3() == null) {
                    String msg = "img.isNull.oc.pcbi.label";
                    if (!errList.contains(msg)) {
                        errList.add(msg);
                    }
                }

                dt.setComplaintNo(complaintNo);
                rsList.add(dt);
            }

            if (CollectionUtils.isEmpty(errList)) {
                if (CollectionUtils.isEmpty(rsList)) {
                    i18nErrList.add(i18n.getMessage("excel.data.null"));
                } else {
                    errList.forEach(m -> {
                        i18nErrList.add(i18n.getMessage(m));// 国际化转换
                    });
                    this.checkSn(rsList, errList, i18nErrList);
                }
            }

            // 数据转换无误：保存
            if (CollectionUtils.isEmpty(i18nErrList)) {
                baseService.batchInsert(rsList);
                r = R.success();
            } else {
                String rstr = String.join("<br/>", i18nErrList);
                r = R.fail(rstr);
            }
        } catch (Exception e) {
            r = R.fail(e.getMessage());
            e.printStackTrace();
            logger.error(ExceptionUtil.stacktraceToString(e));
        } finally {
            CommUtils.close(wb, in);
        }
        return r;
    }

    /**
     * 生成图片并返回路径
     */
    private String createImg(Map<String, XSSFPictureData> picMap, File dir, String imgType, String key) {
        String filePath = null;
        XSSFPictureData pic = picMap.get(key);
        if (pic == null) {
            return filePath;
        }

        FileOutputStream out = null;
        try {
            File f = new File(dir, imgType + "_" + key + "." + pic.suggestFileExtension());
            out = new FileOutputStream(f);
            out.write(pic.getData());
            filePath = f.getAbsolutePath().replace("\\", "/");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CommUtils.close(out);
        }
        return filePath;
    }

    /**
     * 校验sn：excel数据重复、与数据库记录重复
     */
    private void checkSn(List<Form> rsList, List<String> errList, List<String> i18nErrList) {
        List<String> ocSn_existsList = new ArrayList<String>();
        List<String> moduleSn_existsList = new ArrayList<String>();

        List<String> ocSn_needChckList = new ArrayList<String>();
        List<String> moduleSn_needChckList = new ArrayList<String>();
        boolean ocsnExists;
        boolean moduleSnExists;
        Form m;
        Form n;
        for (int i = 0; i < rsList.size(); i++) {
            m = rsList.get(i);
            ocsnExists = false;
            moduleSnExists = false;
            String ocSn = m.getOcSn();
            String moduleSn = m.getPanelModuleSn();

            for (int j = i + 1; j < rsList.size(); j++) {
                n = rsList.get(j);
                if (StringUtils.isNotBlank(ocSn) && StringUtils.isNotBlank(n.getOcSn()) && ocSn.equals(n.getOcSn())) {
                    ocsnExists = true;
                }
                if (StringUtils.isNotBlank(moduleSn) && StringUtils.isNotBlank(n.getPanelModuleSn()) && moduleSn.equals(n.getPanelModuleSn())) {
                    moduleSnExists = true;
                }
            }
            if (StringUtils.isNotBlank(ocSn)) {
                if (ocsnExists) {
                    ocSn_existsList.add(ocSn);
                } else {
                    ocSn_needChckList.add(ocSn);
                }
            }
            if (StringUtils.isNotBlank(moduleSn)) {
                if (moduleSnExists) {
                    moduleSn_existsList.add(moduleSn);
                } else {
                    moduleSn_needChckList.add(moduleSn);
                }
            }
        }

        ocSn_existsList.addAll(baseService.checkOcSn(ocSn_needChckList));
        if (CollectionUtils.isNotEmpty(ocSn_existsList)) {
            i18nErrList.add(i18n.getMessage("ocsn.exists") + StringUtils.join(ocSn_existsList.toArray(), "、"));
        }
        moduleSn_existsList.addAll(baseService.checkModuleSn(moduleSn_needChckList));
        if (CollectionUtils.isNotEmpty(moduleSn_existsList)) {
            i18nErrList.add(i18n.getMessage("panelmodulesn.exists") + StringUtils.join(moduleSn_existsList.toArray(), "、"));
        }
    }

}