package com.neusoft.biz.order.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.base.comm.GlobalConst;
import com.neusoft.base.controller.AbstractBaseController;
import com.neusoft.base.controller.R;
import com.neusoft.base.utils.CommUtils;
import com.neusoft.biz.order.model.Order;
import com.neusoft.biz.order.service.OrderService;

import cn.hutool.core.collection.CollectionUtil;

/**
 * 订单
 *
 * @author：yu8home
 * @date：2018年7月4日 上午8:57:20
 */
@RestController
@RequestMapping("/order")
public class OrderController extends AbstractBaseController<OrderService, Order> {

    @RequestMapping("/add")
    public R add(Order t) {
        List<Order> rsList = baseService.qryExistsOrderNo(null, t.getOrderNo());
        if (CollectionUtils.isNotEmpty(rsList)) {
            return R.fail("订单号已存在！");
        }
        t.setCreateUserId(this.getUserId());
        return super.add(t);
    }

    @RequestMapping("/update")
    public R update(Order t) {
        List<Order> rsList = baseService.qryExistsOrderNo(t.getOrderId(), t.getOrderNo());
        if (CollectionUtils.isNotEmpty(rsList)) {
            return R.fail("订单号已存在！");
        }
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

    @RequestMapping("/delete")
    @Override
    protected R delete(Order t) {
        List<String> orderNos = this.baseService.qryUsedOrder(t.getIds());
        if (CollectionUtil.isNotEmpty(orderNos)) {
            return R.fail("删除失败，已生成工单的订单号：" + String.join("、", orderNos));
        } else {
            super.delete(t);
        }
        return R.success();
    }

}