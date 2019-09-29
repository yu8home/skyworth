package com.neusoft.biz.panel.vendorcpt.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.base.comm.GlobalConst;
import com.neusoft.base.controller.AbstractBaseController;
import com.neusoft.base.utils.CommUtils;
import com.neusoft.biz.panel.form.model.Form;
import com.neusoft.biz.panel.form.service.FormService;
import com.neusoft.biz.panel.vendorcpt.model.VendorConfirmLog;
import com.neusoft.biz.panel.vendorcpt.service.VendorConfirmLogService;

/**
 * 供应商工单确认信息日志
 *
 * @author：yu8home
 * @date：2018年7月22日 上午11:20:59
 */
@RestController
@RequestMapping("/panel/vendorcpt")
public class VendorConfirmLogController extends AbstractBaseController<VendorConfirmLogService, VendorConfirmLog> {
    @Autowired
    private FormService formService;

    // 导出
    @RequestMapping("/excelExport")
    public void excelExport(HttpServletResponse response, Form t) {
        InputStream in = null;
        HSSFWorkbook wb = null;
        try {
            File f = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "public/xls/suplierCptExport.xls");
            in = new FileInputStream(f);
            wb = new HSSFWorkbook(in);
            HSSFSheet sheet = wb.getSheetAt(0);

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
                    r.createCell(i++).setCellValue(dt.getStatusName());
                    r.createCell(i++).setCellValue(dt.getFeedbackCsTime());
                    r.createCell(i++).setCellValue(dt.getFeedbackSupplierTime());
                    r.createCell(i++).setCellValue(dt.getCloseSupplierTime());
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
                    // 供应商
                    r.createCell(i++).setCellValue(dt.getVendorName());
                    r.createCell(i++).setCellValue(dt.getConfirmRs());
                    r.createCell(i++).setCellValue(dt.getRmaNo());
                    r.createCell(i++).setCellValue(dt.getLogRemark());
                }
            }
            response.setContentType(GlobalConst.CONTENTYPE_XLS);
            response.setHeader("content-disposition", "attachment;filename=suplierCptExport.xls");
            wb.write(response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CommUtils.close(wb, in);
        }
    }

}