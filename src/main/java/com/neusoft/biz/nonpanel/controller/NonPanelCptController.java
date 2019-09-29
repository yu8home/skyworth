package com.neusoft.biz.nonpanel.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.base.comm.GlobalConst;
import com.neusoft.base.controller.AbstractBaseController;
import com.neusoft.base.controller.R;
import com.neusoft.base.utils.CommUtils;
import com.neusoft.biz.console.sys.user.model.User;
import com.neusoft.biz.nonpanel.model.NonPanelCpt;
import com.neusoft.biz.nonpanel.service.NonPanelCptService;

/**
 * 非屏体工单
 *
 * @author：yu8home
 * @date：2018年6月28日 下午2:06:42
 */
@RestController
@RequestMapping("/nonpanel")
public class NonPanelCptController extends AbstractBaseController<NonPanelCptService, NonPanelCpt> {

    @RequestMapping("/add")
    public R add(NonPanelCpt t) {
        User u = this.getUser();
        t.setCs(u.getName());
        t.setCreateUserId(u.getUserId());
        return super.add(t);
    }

    @RequestMapping("/update")
    public R update(NonPanelCpt t) {
        t.setModUserId(this.getUserId());
        return super.update(t);
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

    @RequestMapping("/excelExport")
    public void excelExport(HttpServletResponse response, NonPanelCpt t) {
        InputStream in = null;
        HSSFWorkbook wb = null;
        try {
            File f = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "public/xls/nonPanelCptExport.xls");
            in = new FileInputStream(f);
            wb = new HSSFWorkbook(in);
            HSSFSheet sheet = wb.getSheetAt(0);

            List<NonPanelCpt> rsList = this.baseService.qryForList(t);
            if (CollectionUtils.isNotEmpty(rsList)) {
                NonPanelCpt dt;
                Row r;
                int k;
                for (int i = 0; i < rsList.size(); i++) {
                    dt = rsList.get(i);
                    r = sheet.createRow(i + 2);
                    k = 0;
                    r.createCell(k++).setCellValue(dt.getComplaintNo());
                    r.createCell(k++).setCellValue(dt.getReceiveTime());
                    r.createCell(k++).setCellValue(dt.getCs());
                    r.createCell(k++).setCellValue(dt.getOrderNo());
                    r.createCell(k++).setCellValue(dt.getCustName());
                    r.createCell(k++).setCellValue(dt.getBrand());
                    r.createCell(k++).setCellValue(dt.getDeliveryForm());
                    r.createCell(k++).setCellValue(dt.getMovement());
                    r.createCell(k++).setCellValue(dt.getOurModel());
                    r.createCell(k++).setCellValue(dt.getCustModel());
                    r.createCell(k++).setCellValue(dt.getSoldToCountry());
                    r.createCell(k++).setCellValue(dt.getSrc());
                    r.createCell(k++).setCellValue(dt.getComponentEditing());
                    r.createCell(k++).setCellValue(dt.getPartOrderQty());
                    r.createCell(k++).setCellValue(dt.getNumberOfComplaints());
                    r.createCell(k++).setCellValue(dt.getNonPerformingRate());
                    r.createCell(k++).setCellValue(dt.getBadPhase());
                    r.createCell(k++).setCellValue(dt.getRmaFailureType());
                    r.createCell(k++).setCellValue(dt.getNonRmaFailureType());
                    r.createCell(k++).setCellValue(dt.getFaultremark());
                    r.createCell(k++).setCellValue(dt.getPartName());
                    r.createCell(k++).setCellValue(dt.getComponentName());
                    r.createCell(k++).setCellValue(dt.getInitialType());
                    r.createCell(k++).setCellValue(dt.getInitialResponseTime());
                    r.createCell(k++).setCellValue(dt.getTemporaryMeasureType());
                    r.createCell(k++).setCellValue(dt.getTemporaryMeasuresremark());
                    r.createCell(k++).setCellValue(dt.getReserveLossOrder());
                    r.createCell(k++).setCellValue(dt.getReserveLossAmount());
                    r.createCell(k++).setCellValue(dt.getDamageLossShipping());
                    r.createCell(k++).setCellValue(dt.getTrackingNumber());
                    r.createCell(k++).setCellValue(dt.getRepairFees());
                    r.createCell(k++).setCellValue(dt.getCompensationFee());
                    r.createCell(k++).setCellValue(dt.getConfirmationSolution());
                    r.createCell(k++).setCellValue(dt.getConfirmSolutionTime());
                    r.createCell(k++).setCellValue(dt.getQualityFileFeedbackTime());
                    r.createCell(k++).setCellValue(dt.getQualitydocRecipient());
                    r.createCell(k++).setCellValue(dt.getReportReceivingTime());
                    r.createCell(k++).setCellValue(dt.getReportForwardingTime());
                    r.createCell(k++).setCellValue(dt.getTechnicalRespondedTime());
                    r.createCell(k++).setCellValue(dt.getReportRespondsTime());
                    r.createCell(k++).setCellValue(dt.getClosedRemark());
                    r.createCell(k++).setCellValue(dt.getClosedTime());
                    r.createCell(k++).setCellValue(dt.getPrimaryToDotype());
                    r.createCell(k++).setCellValue(dt.getEstimatedFinishTime());
                }
            }
            response.setContentType(GlobalConst.CONTENTYPE_XLS);
            response.setHeader("content-disposition", "attachment;filename=nonPanelCptExport.xls");
            wb.write(response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CommUtils.close(wb, in);
        }
    }

}