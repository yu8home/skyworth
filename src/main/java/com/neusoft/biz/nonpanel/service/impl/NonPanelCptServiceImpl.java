package com.neusoft.biz.nonpanel.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neusoft.base.service.AbstractBaseService;
import com.neusoft.base.utils.CalendarUtils;
import com.neusoft.base.utils.PoiUtils;
import com.neusoft.base.utils.ShiroUtils;
import com.neusoft.biz.console.sys.user.model.User;
import com.neusoft.biz.nonpanel.dao.NonPanelCptMapper;
import com.neusoft.biz.nonpanel.model.NonPanelCpt;
import com.neusoft.biz.nonpanel.service.NonPanelCptService;
import com.neusoft.biz.order.model.Order;
import com.neusoft.biz.order.service.OrderService;

@Service
public class NonPanelCptServiceImpl extends AbstractBaseService<NonPanelCptMapper, NonPanelCpt> implements NonPanelCptService {
    @Autowired
    private OrderService orderService;

    @Override
    public String excelImport(Workbook wb) {
        User u = ShiroUtils.getUser();
        Integer userId = u.getUserId();
        String name = u.getName();

        Sheet sheet = wb.getSheetAt(0);
        int cnt = sheet.getPhysicalNumberOfRows();

        List<NonPanelCpt> nonPanelList = new ArrayList<NonPanelCpt>();
        List<String> errList = new ArrayList<String>();
        NonPanelCpt dt;
        Row row;
        int k;
        for (int i = 2; i < cnt; i++) {
            dt = new NonPanelCpt();
            row = sheet.getRow(i);
            k = 0;
            dt.setComplaintNo(PoiUtils.getCellStringValue(row.getCell(k++), errList, "客诉单号"));
            dt.setReceiveTime(CalendarUtils.parseStringSEC(PoiUtils.getCellCalendarSEC(row.getCell(k++), errList, "反馈时间")));
            dt.setOrderNo(PoiUtils.getCellStringValue(row.getCell(k++), errList, "订单号"));
            dt.setSrc(PoiUtils.getCellStringValue(row.getCell(k++), errList, "客诉来源"));
            dt.setComponentEditing(PoiUtils.getCellStringValue(row.getCell(k++), errList, "部件物编"));
            dt.setPartOrderQty(PoiUtils.getCellIntegerValue(row.getCell(k++), errList, "部件订单量"));
            dt.setNumberOfComplaints(PoiUtils.getCellIntegerValue(row.getCell(k++), errList, "投诉数量"));
            dt.setBadPhase(PoiUtils.getCellStringValue(row.getCell(k++), errList, "不良阶段"));
            dt.setRmaFailureType(PoiUtils.getCellStringNull(row.getCell(k++), errList, "RMA故障现象类型"));
            dt.setNonRmaFailureType(PoiUtils.getCellStringNull(row.getCell(k++), errList, "非RMA故障现象类型"));
            dt.setFaultremark(PoiUtils.getCellStringNull(row.getCell(k++), errList, "故障现象描述"));
            dt.setPartName(PoiUtils.getCellStringValue(row.getCell(k++), errList, "部件名称"));
            dt.setComponentName(PoiUtils.getCellStringNull(row.getCell(k++), errList, "元器件名称"));
            dt.setInitialType(PoiUtils.getCellStringValue(row.getCell(k++), errList, "初审意见类型"));
            dt.setInitialResponseTime(CalendarUtils.parseStringSEC(PoiUtils.getCellCalendarSEC(row.getCell(k++), errList, "初审意见时间")));
            dt.setTemporaryMeasureType(PoiUtils.getCellStringValue(row.getCell(k++), errList, "临时措施类型"));
            dt.setTemporaryMeasuresremark(PoiUtils.getCellStringValue(row.getCell(k++), errList, "临时措施描述"));
            dt.setReserveLossOrder(PoiUtils.getCellStringNull(row.getCell(k++), errList, "备损订单号"));
            dt.setReserveLossAmount(PoiUtils.getCellDoubleNull(row.getCell(k++), errList, "备损订单金额"));
            dt.setDamageLossShipping(PoiUtils.getCellStringNull(row.getCell(k++), errList, "备损出货方式"));
            dt.setTrackingNumber(PoiUtils.getCellStringNull(row.getCell(k++), errList, "追溯单号"));
            dt.setRepairFees(PoiUtils.getCellDoubleNull(row.getCell(k++), errList, "维修费用"));
            dt.setCompensationFee(PoiUtils.getCellDoubleNull(row.getCell(k++), errList, "赔偿费用"));
            dt.setConfirmationSolution(PoiUtils.getCellStringNull(row.getCell(k++), errList, "确认解决方案"));
            dt.setConfirmSolutionTime(CalendarUtils.parseStringSEC(PoiUtils.getCellCalendarSECNull(row.getCell(k++), errList, "确认解决方案时间")));
            dt.setQualityFileFeedbackTime(CalendarUtils.parseStringSEC(PoiUtils.getCellCalendarSECNull(row.getCell(k++), errList, "质量文件反馈时间")));
            dt.setQualitydocRecipient(PoiUtils.getCellStringNull(row.getCell(k++), errList, "质量文件接收人"));
            dt.setReportReceivingTime(CalendarUtils.parseStringSEC(PoiUtils.getCellCalendarSECNull(row.getCell(k++), errList, "8D报告接收时间")));
            dt.setReportForwardingTime(CalendarUtils.parseStringSEC(PoiUtils.getCellCalendarSECNull(row.getCell(k++), errList, "8D报告转发技术组时间")));
            dt.setTechnicalRespondedTime(CalendarUtils.parseStringSEC(PoiUtils.getCellCalendarSECNull(row.getCell(k++), errList, "技术组回复审核意见时间")));
            dt.setReportRespondsTime(CalendarUtils.parseStringSEC(PoiUtils.getCellCalendarSECNull(row.getCell(k++), errList, "8D报告回复客户时间")));
            dt.setClosedRemark(PoiUtils.getCellStringNull(row.getCell(k++), errList, "投诉闭环措施"));
            dt.setClosedTime(CalendarUtils.parseStringSEC(PoiUtils.getCellCalendarSECNull(row.getCell(k++), errList, "投诉闭环时间")));
            dt.setPrimaryToDotype(PoiUtils.getCellStringNull(row.getCell(k++), errList, "首要待办事项类型"));
            dt.setEstimatedFinishTime(CalendarUtils.parseStringSEC(PoiUtils.getCellCalendarSECNull(row.getCell(k++), errList, "预计完成时间")));

            nonPanelList.add(dt);
        }

        if (CollectionUtils.isEmpty(errList)) {
            if (CollectionUtils.isEmpty(nonPanelList)) {
                errList.add("excel数据为空");
            } else {
                // 校验订单号是否存在
                List<String> orderNos = new ArrayList<String>();
                List<String> existsOrderNos = new ArrayList<String>();
                nonPanelList.forEach(m -> {
                    orderNos.add(m.getOrderNo());
                });
                List<Order> orderList = orderService.qryExistsOrderNo(null, String.join(",", orderNos));
                if (CollectionUtils.isNotEmpty(orderList)) {
                    for (Order m : orderList) {
                        // 获取不存在的订单号
                        if (orderNos.contains(m.getOrderNo())) {
                            existsOrderNos.add(m.getOrderNo());
                        }
                    }
                    orderNos.removeAll(existsOrderNos);
                }
                if (CollectionUtils.isNotEmpty(orderNos)) {
                    errList.add("不存在的订单号【" + String.join("、", orderNos) + "】");
                }
                if (CollectionUtils.isEmpty(errList)) {
                    nonPanelList.forEach(m -> {
                        m.setCreateUserId(userId);
                        m.setCs(name);
                        baseMapper.insert(m);
                    });
                }
            }
        }
        return String.join("，", errList);
    }

}