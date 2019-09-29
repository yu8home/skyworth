package com.neusoft.biz.panel.cs.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.neusoft.base.comm.Dict;
import com.neusoft.base.comm.GlobalConst;
import com.neusoft.base.controller.AbstractBaseController;
import com.neusoft.base.controller.R;
import com.neusoft.base.utils.CalendarUtils;
import com.neusoft.base.utils.CommUtils;
import com.neusoft.biz.console.sys.user.model.User;
import com.neusoft.biz.panel.cs.service.CsCptService;
import com.neusoft.biz.panel.custcpt.service.CustCptService;
import com.neusoft.biz.panel.form.model.Form;
import com.neusoft.biz.panel.form.model.FormVendorChg;
import com.neusoft.biz.panel.form.service.FormService;

import cn.hutool.core.date.DatePattern;

/**
 * 客户专员工单
 *
 * @author：yu8home
 * @date：2018年7月15日 下午1:28:34
 */
@RestController
@RequestMapping("/panel/cscpt")
@SuppressWarnings({ "rawtypes" })
public class CsCptController extends AbstractBaseController<CsCptService, Form> {
    @Autowired
    private CustCptService custCptService;
    @Autowired
    private FormService formService;

    @Override
    @RequestMapping("/update")
    public R update(Form t) {
        int cnt = custCptService.checkFormSn(t);
        if (cnt > 0) {
            return R.fail(i18n.getMessage("sn.exists"));
        }
        t.setModUserId(getUserId());
        return super.update(t);
    }

    @RequestMapping(value = "/updatePriority")
    public R updatePriority(Form t) {
        baseService.updatePriority(t);
        return R.success();
    }

    @RequestMapping(value = "/initial")
    public R initial(Form t) {
        baseService.initial(t);
        return R.success();
    }

    @RequestMapping(value = "/solution")
    public R solution(Form t) {
        baseService.solution(t);
        return R.success();
    }

    @RequestMapping(value = "/solutionExt")
    public R solutionExt(Form t) {
        baseService.solutionExt(t);
        return R.success();
    }

    @RequestMapping(value = "/feedbackSupplier")
    public R feedbackSupplier(FormVendorChg t) {
        baseService.feedbackSupplier(t);
        return R.success();
    }

    @RequestMapping(value = "/closedVendorFormStatus")
    public R closedVendorFormStatus(Form t) {
        baseService.closedVendorFormStatus(t);
        return R.success();
    }

    @RequestMapping(value = "/qryVendorChg")
    public List<Map> qryVendorChg(FormVendorChg t) {
        User u = getUser();
        if (u.getRoleType().equals(GlobalConst.ROLETYPE_VENDOR)) {
            t.setVendorCode(u.getUserCode());
        }
        return baseService.qryVendorChg(t);
    }

    // 上传附件
    @RequestMapping(value = "/fileUpload")
    public R fileUpload(MultipartFile fileName) {
        String cl = CalendarUtils.parseString(Calendar.getInstance(), DatePattern.PURE_DATETIME_MS_PATTERN);
        String f = fileName.getOriginalFilename();
        String rs = cl + "_" + f.substring(f.lastIndexOf(File.separator) + 1);
        try {
            fileName.transferTo(new File(GlobalConst.UPLOAD_ATTACHMENT + rs));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return R.success(rs);
    }

    // 导出
    @RequestMapping("/excelExport")
    public void excelExport(HttpServletResponse response, Form t) {
        InputStream in = null;
        XSSFWorkbook wb = null;

        try {
            File f = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "public/xls/csCptExport.xlsx");
            in = new FileInputStream(f);
            wb = new XSSFWorkbook(in);
            XSSFSheet sheet = wb.getSheetAt(0);
            XSSFDrawing hp = sheet.createDrawingPatriarch();

            Form dt = formService.getInfo(t);
            int k = 0;
            Row r = sheet.getRow(2);
            r.getCell(k++).setCellValue(dt.getComplaintNo());
            r.getCell(k++).setCellValue(dt.getStatusName());
            r.getCell(k++).setCellValue(dt.getCustComplaintNo());
            r.getCell(k++).setCellValue(dt.getCbuSn());
            r.getCell(k++).setCellValue(dt.getBadPhase());
            r.getCell(k++).setCellValue(dt.getFailureType());
            r.getCell(k++).setCellValue(dt.getPartName());
            r.getCell(k++).setCellValue(dt.getPanelModuleModel());
            r.getCell(k++).setCellValue(dt.getPanelModuleSn());
            r.getCell(k++).setCellValue(dt.getOcModel());
            r.getCell(k++).setCellValue(dt.getOcSn());
            r.getCell(k++).setCellValue(dt.getFailureRemark());
            r.getCell(k++).setCellValue(dt.getExchangeTestRs());
            r.getCell(k++).setCellValue(dt.getOtherCheckRs());
            // 订单
            k = 0;
            r = sheet.getRow(6);
            r.getCell(k++).setCellValue(dt.getOrderNo());
            r.getCell(k++).setCellValue(dt.getCustOrderNo());
            r.getCell(k++).setCellValue(dt.getMovement());
            r.getCell(k++).setCellValue(dt.getOurModel());
            r.getCell(k++).setCellValue(dt.getCustModel());
            r.getCell(k++).setCellValue(dt.getScreen());
            r.getCell(k++).setCellValue(dt.getOrderBrand());
            r.getCell(k++).setCellValue(dt.getOrderQty());
            r.getCell(k++).setCellValue(dt.getCustName());
            r.getCell(k++).setCellValue(dt.getSoldToCountry());
            r.getCell(k++).setCellValue(dt.getOrderDate());
            r.getCell(k++).setCellValue(dt.getDeliveryPeriod());
            r.getCell(k++).setCellValue(dt.getDeliveryForm());
            // 客户、供应商
            k = 0;
            r = sheet.getRow(10);
            r.getCell(k++).setCellValue(dt.getCreateUserName());
            r.getCell(k++).setCellValue(dt.getBelongEnd());
            r.getCell(k++).setCellValue(dt.getCsCodeName());
            r.getCell(k++).setCellValue(dt.getVendorName());
            r.getCell(k++).setCellValue(dt.getConfirmRs());
            r.getCell(k++).setCellValue(dt.getRmaNo());
            r.getCell(k++).setCellValue(dt.getLogRemark());
            // 时间节点
            k = 0;
            r = sheet.getRow(14);
            r.getCell(k++).setCellValue(dt.getCreateTime());
            r.getCell(k++).setCellValue(dt.getFeedbackCsTime());
            r.getCell(k++).setCellValue(dt.getFeedbackCsTime2());
            r.getCell(k++).setCellValue(dt.getFeedbackSupplierTime());
            r.getCell(k++).setCellValue(dt.getCloseSupplierTime());
            r.getCell(k++).setCellValue(dt.getCurInitialTime());
            r.getCell(k++).setCellValue(dt.getFirstSolutionTime());
            r.getCell(k++).setCellValue(dt.getFinalSolutionTime());
            r.getCell(k++).setCellValue(dt.getCloseTime());
            r.getCell(k++).setCellValue(dt.getConfirmCloseTime());
            // 解决方案
            k = 0;
            r = sheet.getRow(18);
            r.getCell(k++).setCellValue(dt.getCurInitialType());
            r.getCell(k++).setCellValue(dt.getSolutionType());
            r.getCell(k++).setCellValue(dt.getSpfNo());
            r.getCell(k++).setCellValue(dt.getSpfValueAmount() == null ? "" : dt.getSpfValueAmount().toString());
            r.getCell(k++).setCellValue(dt.getEsShipmentDate());
            r.getCell(k++).setCellValue(dt.getShipmentDate());
            r.getCell(k++).setCellValue(dt.getListProvideTime());
            r.getCell(k++).setCellValue(dt.getSpfDeliveredType());
            r.getCell(k++).setCellValue(dt.getTrackingNo());
            r.getCell(k++).setCellValue(dt.getReimAmount() == null ? "" : dt.getReimAmount().toString());
            r.getCell(k++).setCellValue(dt.getReimAppConfirmTime());
            r.getCell(k++).setCellValue(dt.getSolutionDesc());
            // 填充图片
            int rowi = 20;
            setImgToCell(wb, hp, dt.getCbuSnImg1(), rowi, 1);
            setImgToCell(wb, hp, dt.getCbuSnImg2(), rowi, 2);
            setImgToCell(wb, hp, dt.getCbuSnImg3(), rowi, 3);
            rowi++;
            setImgToCell(wb, hp, dt.getBadPhenImg1(), rowi, 1);
            setImgToCell(wb, hp, dt.getBadPhenImg2(), rowi, 2);
            setImgToCell(wb, hp, dt.getBadPhenImg3(), rowi, 3);
            rowi++;
            setImgToCell(wb, hp, dt.getPanelModuleSnImg1(), rowi, 1);
            setImgToCell(wb, hp, dt.getPanelModuleSnImg2(), rowi, 2);
            setImgToCell(wb, hp, dt.getPanelModuleSnImg3(), rowi, 3);
            rowi++;
            setImgToCell(wb, hp, dt.getOcSnImg1(), rowi, 1);
            setImgToCell(wb, hp, dt.getOcSnImg2(), rowi, 2);
            setImgToCell(wb, hp, dt.getOcSnImg3(), rowi, 3);
            rowi++;
            setImgToCell(wb, hp, dt.getScreenFacPackingImg1(), rowi, 1);
            setImgToCell(wb, hp, dt.getScreenFacPackingImg2(), rowi, 2);
            setImgToCell(wb, hp, dt.getScreenFacPackingImg3(), rowi, 3);
            rowi++;
            setImgToCell(wb, hp, dt.getOutsidePackingImg1(), rowi, 1);
            setImgToCell(wb, hp, dt.getOutsidePackingImg2(), rowi, 2);
            setImgToCell(wb, hp, dt.getOutsidePackingImg3(), rowi, 3);
            rowi++;
            setImgToCell(wb, hp, dt.getInsidePackingImg1(), rowi, 1);
            setImgToCell(wb, hp, dt.getInsidePackingImg2(), rowi, 2);
            setImgToCell(wb, hp, dt.getInsidePackingImg3(), rowi, 3);
            rowi++;
            setImgToCell(wb, hp, dt.getScrappedImg1(), rowi, 1);
            setImgToCell(wb, hp, dt.getScrappedImg2(), rowi, 2);
            setImgToCell(wb, hp, dt.getScrappedImg3(), rowi, 3);

            response.setContentType(GlobalConst.CONTENTYPE_XLSX);
            response.setHeader("content-disposition", "attachment;filename=csCptExport.xlsx");
            wb.write(response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CommUtils.close(wb, in);
        }
    }

    // 批量导出
    @RequestMapping("/batchExcelExport")
    public void batchExcelExport(HttpServletResponse response, Form t) {
        InputStream in = null;
        HSSFWorkbook wb = null;
        try {
            File f = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "public/xls/batchCsCptExport.xls");
            in = new FileInputStream(f);
            wb = new HSSFWorkbook(in);
            HSSFSheet sheet = wb.getSheetAt(0);

            User u = getUser();
            String roleType = u.getRoleType();
            if (roleType.equals(GlobalConst.ROLETYPE_CUSTOMER)) {
                t.setCreateUserId(u.getUserId());
            } else if (roleType.equals(GlobalConst.ROLETYPE_CS)) {
                t.setCsCode(u.getUserCode());
            } else if (roleType.equals(GlobalConst.ROLETYPE_VENDOR)) {
                t.setVendorCode(u.getUserCode());
            }
            t.setRoleType(roleType);
            List<Form> rsList = formService.qryForListExcludeImg(t);
            if (CollectionUtils.isNotEmpty(rsList)) {
                Form dt;
                Row r;
                int i;
                for (int j = 0; j < rsList.size(); j++) {
                    dt = rsList.get(j);
                    r = sheet.createRow(j + 2);
                    i = 0;
                    r.createCell(i++).setCellValue(dt.getComplaintNo());
                    r.createCell(i++).setCellValue(Dict.getDictName(Dict.CPT_STATUS, dt.getStatus()));
                    r.createCell(i++).setCellValue(dt.getCustComplaintNo());
                    r.createCell(i++).setCellValue(dt.getCbuSn());
                    r.createCell(i++).setCellValue(dt.getBadPhase());
                    r.createCell(i++).setCellValue(dt.getFailureType());
                    r.createCell(i++).setCellValue(dt.getPartName());
                    r.createCell(i++).setCellValue(dt.getPanelModuleModel());
                    r.createCell(i++).setCellValue(dt.getPanelModuleSn());
                    r.createCell(i++).setCellValue(dt.getOcModel());
                    r.createCell(i++).setCellValue(dt.getOcSn());
                    r.createCell(i++).setCellValue(dt.getFailureRemark());
                    r.createCell(i++).setCellValue(dt.getExchangeTestRs());
                    r.createCell(i++).setCellValue(dt.getOtherCheckRs());
                    // 订单
                    r.createCell(i++).setCellValue(dt.getOrderNo());
                    r.createCell(i++).setCellValue(dt.getCustOrderNo());
                    r.createCell(i++).setCellValue(dt.getMovement());
                    r.createCell(i++).setCellValue(dt.getOurModel());
                    r.createCell(i++).setCellValue(dt.getCustModel());
                    r.createCell(i++).setCellValue(dt.getScreen());
                    r.createCell(i++).setCellValue(dt.getOrderBrand());
                    r.createCell(i++).setCellValue(dt.getOrderQty() == null ? "" : dt.getOrderQty().toString());
                    r.createCell(i++).setCellValue(dt.getCustName());
                    r.createCell(i++).setCellValue(dt.getSoldToCountry());
                    r.createCell(i++).setCellValue(dt.getOrderDate());
                    r.createCell(i++).setCellValue(dt.getDeliveryPeriod());
                    r.createCell(i++).setCellValue(dt.getDeliveryForm());
                    // 客户、供应商
                    r.createCell(i++).setCellValue(dt.getCreateUserName());
                    r.createCell(i++).setCellValue(dt.getBelongEnd());
                    r.createCell(i++).setCellValue(dt.getCsCodeName());
                    r.createCell(i++).setCellValue(dt.getVendorName());
                    r.createCell(i++).setCellValue(dt.getConfirmRs());
                    r.createCell(i++).setCellValue(dt.getRmaNo());
                    r.createCell(i++).setCellValue(dt.getLogRemark());
                    // 时间节点
                    r.createCell(i++).setCellValue(dt.getCreateTime());
                    r.createCell(i++).setCellValue(dt.getFeedbackCsTime());
                    r.createCell(i++).setCellValue(dt.getFeedbackCsTime2());
                    r.createCell(i++).setCellValue(dt.getFeedbackSupplierTime());
                    r.createCell(i++).setCellValue(dt.getCloseSupplierTime());
                    r.createCell(i++).setCellValue(dt.getCurInitialTime());
                    r.createCell(i++).setCellValue(dt.getFirstSolutionTime());
                    r.createCell(i++).setCellValue(dt.getFinalSolutionTime());
                    r.createCell(i++).setCellValue(dt.getCloseTime());
                    r.createCell(i++).setCellValue(dt.getConfirmCloseTime());
                    // 解决方案
                    r.createCell(i++).setCellValue(dt.getCurInitialType());
                    r.createCell(i++).setCellValue(dt.getSolutionType());
                    r.createCell(i++).setCellValue(dt.getSpfNo());
                    r.createCell(i++).setCellValue(dt.getSpfValueAmount() == null ? "" : dt.getSpfValueAmount().toString());
                    r.createCell(i++).setCellValue(dt.getEsShipmentDate());
                    r.createCell(i++).setCellValue(dt.getShipmentDate());
                    r.createCell(i++).setCellValue(dt.getListProvideTime());
                    r.createCell(i++).setCellValue(dt.getSpfDeliveredType());
                    r.createCell(i++).setCellValue(dt.getTrackingNo());
                    r.createCell(i++).setCellValue(dt.getReimAmount() == null ? "" : dt.getReimAmount().toString());
                    r.createCell(i++).setCellValue(dt.getReimAppConfirmTime());
                    r.createCell(i++).setCellValue(dt.getSolutionDesc());
                }
            }
            response.setContentType(GlobalConst.CONTENTYPE_XLS);
            response.setHeader("content-disposition", "attachment;filename=batchCsCptExport.xls");
            wb.write(response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CommUtils.close(wb, in);
        }
    }

    // 包含图片的批量导出
    @RequestMapping("/batchExcelExportPic")
    public void batchExcelExportPic(HttpServletResponse response, Form t) {
        InputStream in = null;
        XSSFWorkbook wb = null;
        int MAX_SHEET = 5;// 导出最大记录

        try {
            File f = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "public/xls/csCptExportPic.xlsx");
            in = new FileInputStream(f);
            wb = new XSSFWorkbook(in);

            String[] ids = t.getIds().split(",");
            // 删除多余的sheet
            for (int i = MAX_SHEET - 1; i >= ids.length; i--) {
                wb.removeSheetAt(i);
            }
            for (int i = 0; i < ids.length; i++) {
                Form frm = new Form();
                frm.setComplaintId(Integer.parseInt(ids[i]));
                Form dt = formService.getInfo(frm);

                wb.setSheetName(i, dt.getComplaintNo());
                XSSFSheet sheet = wb.getSheetAt(i);
                XSSFDrawing hp = sheet.createDrawingPatriarch();

                int k = 0;
                Row r = sheet.getRow(2);
                r.getCell(k++).setCellValue(dt.getComplaintNo());
                r.getCell(k++).setCellValue(dt.getStatusName());
                r.getCell(k++).setCellValue(dt.getCustComplaintNo());
                r.getCell(k++).setCellValue(dt.getCbuSn());
                r.getCell(k++).setCellValue(dt.getBadPhase());
                r.getCell(k++).setCellValue(dt.getFailureType());
                r.getCell(k++).setCellValue(dt.getPartName());
                r.getCell(k++).setCellValue(dt.getPanelModuleModel());
                r.getCell(k++).setCellValue(dt.getPanelModuleSn());
                r.getCell(k++).setCellValue(dt.getOcModel());
                r.getCell(k++).setCellValue(dt.getOcSn());
                r.getCell(k++).setCellValue(dt.getFailureRemark());
                r.getCell(k++).setCellValue(dt.getExchangeTestRs());
                r.getCell(k++).setCellValue(dt.getOtherCheckRs());
                // 订单
                k = 0;
                r = sheet.getRow(6);
                r.getCell(k++).setCellValue(dt.getOrderNo());
                r.getCell(k++).setCellValue(dt.getCustOrderNo());
                r.getCell(k++).setCellValue(dt.getMovement());
                r.getCell(k++).setCellValue(dt.getOurModel());
                r.getCell(k++).setCellValue(dt.getCustModel());
                r.getCell(k++).setCellValue(dt.getScreen());
                r.getCell(k++).setCellValue(dt.getOrderBrand());
                r.getCell(k++).setCellValue(dt.getOrderQty());
                r.getCell(k++).setCellValue(dt.getCustName());
                r.getCell(k++).setCellValue(dt.getSoldToCountry());
                r.getCell(k++).setCellValue(dt.getOrderDate());
                r.getCell(k++).setCellValue(dt.getDeliveryPeriod());
                r.getCell(k++).setCellValue(dt.getDeliveryForm());
                // 客户、供应商
                k = 0;
                r = sheet.getRow(10);
                r.getCell(k++).setCellValue(dt.getCreateUserName());
                r.getCell(k++).setCellValue(dt.getBelongEnd());
                r.getCell(k++).setCellValue(dt.getCsCodeName());
                r.getCell(k++).setCellValue(dt.getVendorName());
                r.getCell(k++).setCellValue(dt.getConfirmRs());
                r.getCell(k++).setCellValue(dt.getRmaNo());
                r.getCell(k++).setCellValue(dt.getLogRemark());
                // 时间节点
                k = 0;
                r = sheet.getRow(14);
                r.getCell(k++).setCellValue(dt.getCreateTime());
                r.getCell(k++).setCellValue(dt.getFeedbackCsTime());
                r.getCell(k++).setCellValue(dt.getFeedbackCsTime2());
                r.getCell(k++).setCellValue(dt.getFeedbackSupplierTime());
                r.getCell(k++).setCellValue(dt.getCloseSupplierTime());
                r.getCell(k++).setCellValue(dt.getCurInitialTime());
                r.getCell(k++).setCellValue(dt.getFirstSolutionTime());
                r.getCell(k++).setCellValue(dt.getFinalSolutionTime());
                r.getCell(k++).setCellValue(dt.getCloseTime());
                r.getCell(k++).setCellValue(dt.getConfirmCloseTime());
                // 解决方案
                k = 0;
                r = sheet.getRow(18);
                r.getCell(k++).setCellValue(dt.getCurInitialType());
                r.getCell(k++).setCellValue(dt.getSolutionType());
                r.getCell(k++).setCellValue(dt.getSpfNo());
                r.getCell(k++).setCellValue(dt.getSpfValueAmount() == null ? "" : dt.getSpfValueAmount().toString());
                r.getCell(k++).setCellValue(dt.getEsShipmentDate());
                r.getCell(k++).setCellValue(dt.getShipmentDate());
                r.getCell(k++).setCellValue(dt.getListProvideTime());
                r.getCell(k++).setCellValue(dt.getSpfDeliveredType());
                r.getCell(k++).setCellValue(dt.getTrackingNo());
                r.getCell(k++).setCellValue(dt.getReimAmount() == null ? "" : dt.getReimAmount().toString());
                r.getCell(k++).setCellValue(dt.getReimAppConfirmTime());
                r.getCell(k++).setCellValue(dt.getSolutionDesc());
                // 填充图片
                int rowi = 20;
                setImgToCell(wb, hp, dt.getCbuSnImg1(), rowi, 1);
                setImgToCell(wb, hp, dt.getCbuSnImg2(), rowi, 2);
                setImgToCell(wb, hp, dt.getCbuSnImg3(), rowi, 3);
                rowi++;
                setImgToCell(wb, hp, dt.getBadPhenImg1(), rowi, 1);
                setImgToCell(wb, hp, dt.getBadPhenImg2(), rowi, 2);
                setImgToCell(wb, hp, dt.getBadPhenImg3(), rowi, 3);
                rowi++;
                setImgToCell(wb, hp, dt.getPanelModuleSnImg1(), rowi, 1);
                setImgToCell(wb, hp, dt.getPanelModuleSnImg2(), rowi, 2);
                setImgToCell(wb, hp, dt.getPanelModuleSnImg3(), rowi, 3);
                rowi++;
                setImgToCell(wb, hp, dt.getOcSnImg1(), rowi, 1);
                setImgToCell(wb, hp, dt.getOcSnImg2(), rowi, 2);
                setImgToCell(wb, hp, dt.getOcSnImg3(), rowi, 3);
                rowi++;
                setImgToCell(wb, hp, dt.getScreenFacPackingImg1(), rowi, 1);
                setImgToCell(wb, hp, dt.getScreenFacPackingImg2(), rowi, 2);
                setImgToCell(wb, hp, dt.getScreenFacPackingImg3(), rowi, 3);
                rowi++;
                setImgToCell(wb, hp, dt.getOutsidePackingImg1(), rowi, 1);
                setImgToCell(wb, hp, dt.getOutsidePackingImg2(), rowi, 2);
                setImgToCell(wb, hp, dt.getOutsidePackingImg3(), rowi, 3);
                rowi++;
                setImgToCell(wb, hp, dt.getInsidePackingImg1(), rowi, 1);
                setImgToCell(wb, hp, dt.getInsidePackingImg2(), rowi, 2);
                setImgToCell(wb, hp, dt.getInsidePackingImg3(), rowi, 3);
                rowi++;
                setImgToCell(wb, hp, dt.getScrappedImg1(), rowi, 1);
                setImgToCell(wb, hp, dt.getScrappedImg2(), rowi, 2);
                setImgToCell(wb, hp, dt.getScrappedImg3(), rowi, 3);
            }
            response.setContentType(GlobalConst.CONTENTYPE_XLSX);
            response.setHeader("content-disposition", "attachment;filename=csCptExportPic.xlsx");
            wb.write(response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CommUtils.close(wb, in);
        }
    }

    /**
     * 设置图片到excel
     */
    private void setImgToCell(XSSFWorkbook wb, XSSFDrawing hp, String url, int row, int col) {
        if (StringUtils.isBlank(url)) {
            return;
        }
        ByteArrayOutputStream out = null;
        try {
            out = new ByteArrayOutputStream();
            BufferedImage img = ImageIO.read(new File(URLDecoder.decode(url, "UTF-8")));
            ImageIO.write(img, "png", out);

            XSSFClientAnchor anchor = new XSSFClientAnchor(0, 0, 0, 0, (short) col, row, (short) (col + 1), row + 1);
            hp.createPicture(anchor, wb.addPicture(out.toByteArray(), XSSFWorkbook.PICTURE_TYPE_PNG));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CommUtils.close(out);
        }
    }

}