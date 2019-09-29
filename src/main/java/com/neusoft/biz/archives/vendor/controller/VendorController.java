package com.neusoft.biz.archives.vendor.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.base.comm.GlobalConst;
import com.neusoft.base.controller.AbstractBaseController;
import com.neusoft.base.controller.R;
import com.neusoft.biz.archives.vendor.model.Vendor;
import com.neusoft.biz.archives.vendor.service.VendorService;
import com.neusoft.biz.console.sys.user.service.UserService;

/**
 * 供应商
 *
 * @author：yu8home
 * @date：2018年6月28日 下午1:17:05
 */
@RestController
@RequestMapping("/archives/vendor")
public class VendorController extends AbstractBaseController<VendorService, Vendor> {
    @Autowired
    private UserService userService;

    @RequestMapping("/selVendor")
    public List<Vendor> selVendor(Vendor t) {
        return baseService.qryForList(t);
    }

    @RequestMapping("/selectVendor")
    public List<Vendor> selectVendor(Vendor t) {
        List<Vendor> rs = new ArrayList<Vendor>();
        Vendor tt = new Vendor();
        tt.setVendorCode("");
        tt.setVendorName(i18n.getMessage("please.select"));
        rs.add(0, tt);
        rs.addAll(baseService.qryForList(t));
        return rs;
    }

    @Override
    @RequestMapping("/update")
    public R update(Vendor t) {
        if (t.getIsValid() == GlobalConst.NO) {
            int cnt = userService.getUnclosedCntByUser(GlobalConst.ROLETYPE_VENDOR, t.getVendorCode());
            if (cnt > 0) {
                return R.fail(i18n.getMessage("user.have.unclosed.form"));
            }
        }
        return super.update(t);
    }

}