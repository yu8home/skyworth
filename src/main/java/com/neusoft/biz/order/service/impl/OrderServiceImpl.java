package com.neusoft.biz.order.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import com.neusoft.base.service.AbstractBaseService;
import com.neusoft.base.utils.CalendarUtils;
import com.neusoft.base.utils.CommUtils;
import com.neusoft.base.utils.PoiUtils;
import com.neusoft.base.utils.ShiroUtils;
import com.neusoft.biz.order.dao.OrderMapper;
import com.neusoft.biz.order.model.Order;
import com.neusoft.biz.order.service.OrderService;

@Service
public class OrderServiceImpl extends AbstractBaseService<OrderMapper, Order> implements OrderService {

    @Override
    public String excelImport(Workbook wb) {
        Integer userId = ShiroUtils.getUser().getUserId();

        List<Order> rsList = new ArrayList<Order>();
        List<String> errList = new ArrayList<String>();
        Order dt;
        Row row;

        Sheet sheet = wb.getSheetAt(0);
        int k = sheet.getPhysicalNumberOfRows();
        for (int i = 1; i < k; i++) {
            row = sheet.getRow(i);
            dt = new Order();
            dt.setOrderNo(PoiUtils.getCellStringValue(row.getCell(0), errList, "订单号"));
            dt.setCustOrderNo(PoiUtils.getCellStringValue(row.getCell(1), errList, "客户订单号"));
            dt.setPrefix(PoiUtils.getCellStringValue(row.getCell(2), errList, "序列号前缀", true));
            dt.setOrderDate(CalendarUtils.parseString(PoiUtils.getCellCalendarValue(row.getCell(3), errList, "下单日期")));
            dt.setMonth(PoiUtils.getCellStringValue(row.getCell(4), errList, "月份"));
            dt.setSaleOrderNum(PoiUtils.getCellStringValue(row.getCell(5), errList, "销单号"));
            dt.setOrderType(PoiUtils.getCellStringValue(row.getCell(6), errList, "订单类型"));
            dt.setDeliveryForm(PoiUtils.getCellStringValue(row.getCell(7), errList, "走货方式"));
            dt.setPid(PoiUtils.getCellStringValue(row.getCell(8), errList, "PID号"));
            dt.setScreen(PoiUtils.getCellStringValue(row.getCell(9), errList, "配屏"));
            dt.setSize(PoiUtils.getCellIntegerValue(row.getCell(10), errList, "尺寸"));
            dt.setMovement(PoiUtils.getCellStringValue(row.getCell(11), errList, "机芯"));
            dt.setOurModel(PoiUtils.getCellStringValue(row.getCell(12), errList, "我司型号"));
            dt.setCustModel(PoiUtils.getCellStringValue(row.getCell(13), errList, "买家型号"));
            dt.setOrderQty(PoiUtils.getCellIntegerValue(row.getCell(14), errList, "订单量"));
            dt.setBrand(PoiUtils.getCellStringValue(row.getCell(15), errList, "品牌"));
            dt.setSoldToCountry(PoiUtils.getCellStringValue(row.getCell(16), errList, "销往国家"));
            dt.setCustName(PoiUtils.getCellStringValue(row.getCell(17), errList, "客户名称"));
            dt.setReceiver(PoiUtils.getCellStringValue(row.getCell(18), errList, "接单人"));
            dt.setBusinessLeader(PoiUtils.getCellStringValue(row.getCell(19), errList, "业务区负责人"));
            dt.setBusinessGroup(PoiUtils.getCellStringValue(row.getCell(20), errList, "业务组"));
            dt.setPaymentMethod(PoiUtils.getCellStringValue(row.getCell(21), errList, "付款方式"));
            dt.setNationalStandardsCertification(PoiUtils.getCellStringValue(row.getCell(22), errList, "国家标准及认证", true));
            dt.setIsCheck(CommUtils.getYesNoValue(PoiUtils.getCellStringValue(row.getCell(23), errList, "是否验货")));
            dt.setIsPaidOptionalDamage(CommUtils.getYesNoValue(PoiUtils.getCellStringValue(row.getCell(24), errList, "付费自选备损")));
            dt.setFreePreparationLoss(PoiUtils.getCellStringValue(row.getCell(25), errList, "免费备损", true));
            dt.setStandardAllowanceAmount(PoiUtils.getCellDoubleValue(row.getCell(26), errList, "标准备损金额", true));
            dt.setIsRiskOrder(CommUtils.getYesNoValue(PoiUtils.getCellStringValue(row.getCell(27), errList, "是否风险订单", true)));
            dt.setRiskCauseAnalysis(PoiUtils.getCellStringValue(row.getCell(28), errList, "风险原因分析", true));
            dt.setConstructionDate(CalendarUtils.parseString(PoiUtils.getCellCalendarValue(row.getCell(29), errList, "建销单日期")));
            dt.setOneReviewPeriod(PoiUtils.getCellStringValue(row.getCell(30), errList, "一次评审完成期", true));
            dt.setTransferOrderDate(CalendarUtils.parseString(PoiUtils.getCellCalendarValue(row.getCell(31), errList, "转工单日期", true)));
            dt.setReceiptOrderDate(CalendarUtils.parseString(PoiUtils.getCellCalendarValue(row.getCell(32), errList, "接单走货日期")));
            dt.setDeliveryPeriod(PoiUtils.getCellStringValue(row.getCell(33), errList, "走货期"));
            dt.setDeliveryChangeReason(PoiUtils.getCellStringValue(row.getCell(34), errList, "交期更改原因", true));
            dt.setReceiverDays(PoiUtils.getCellIntegerValue(row.getCell(35), errList, "接单天数"));
            dt.setDeliveryChangePeriod(PoiUtils.getCellIntegerValue(row.getCell(36), errList, "走货期更改天数"));
            dt.setTotalDays(PoiUtils.getCellIntegerValue(row.getCell(37), errList, "合计天数"));
            dt.setMovementModel(PoiUtils.getCellStringValue(row.getCell(38), errList, "机芯\\机型", true));
            dt.setArea(PoiUtils.getCellStringValue(row.getCell(39), errList, "片区", true));
            dt.setProcessingMethods(PoiUtils.getCellStringValue(row.getCell(40), errList, "加工方式", true));
            dt.setInterTradeTerms(PoiUtils.getCellStringValue(row.getCell(41), errList, "国际贸易条款"));
            dt.setInterTradeRemark(PoiUtils.getCellStringValue(row.getCell(42), errList, "国际贸易备注", true));
            dt.setCompany(PoiUtils.getCellStringValue(row.getCell(43), errList, "公司", true));
            dt.setRemark(PoiUtils.getCellStringValue(row.getCell(44), errList, "备注", true));

            rsList.add(dt);
        }

        if (CollectionUtils.isEmpty(errList)) {
            if (CollectionUtils.isEmpty(rsList)) {
                errList.add("excel数据为空");
            } else {
                List<String> orderNos = new ArrayList<String>();
                rsList.forEach(m -> {
                    orderNos.add(m.getOrderNo());
                });

                List<String> existsOrderNos = new ArrayList<String>();
                List<Order> orderList = baseMapper.qryExistsOrderNo(null, String.join(",", orderNos));
                if (CollectionUtils.isEmpty(orderList)) {
                    rsList.forEach(m -> {
                        m.setCreateUserId(userId);
                        baseMapper.insert(m);
                    });
                } else {
                    orderList.forEach(m -> {
                        existsOrderNos.add(m.getOrderNo());
                    });
                    errList.add("已存在的订单号【" + String.join("、", existsOrderNos) + "】");
                }
            }
        }
        return String.join("，", errList);
    }

    @Override
    public List<Order> qryExistsOrderNo(Integer orderId, String orderNo) {
        return baseMapper.qryExistsOrderNo(orderId, orderNo);
    }

    @Override
    public List<String> qryUsedOrder(String ids) {
        return baseMapper.qryUsedOrder(ids);
    }

}