package com.neusoft.biz.archives.cust.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.base.comm.GlobalConst;
import com.neusoft.base.controller.AbstractBaseController;
import com.neusoft.base.controller.R;
import com.neusoft.biz.archives.cust.model.Cust;
import com.neusoft.biz.archives.cust.service.CustService;
import com.neusoft.biz.console.sys.user.service.UserService;

/**
 * 客户
 *
 * @author：yu8home
 * @date：2018年6月28日 下午1:25:27
 */
@RestController
@RequestMapping("/archives/cust")
public class CustController extends AbstractBaseController<CustService, Cust> {
    @Autowired
    private UserService userService;

    @Override
    @RequestMapping("/update")
    public R update(Cust t) {
        if (t.getIsValid() == GlobalConst.NO) {
            int cnt = userService.getUnclosedCntByUser(GlobalConst.ROLETYPE_CUSTOMER, t.getCustCode());
            if (cnt > 0) {
                return R.fail(i18n.getMessage("user.have.unclosed.form"));
            }
        }
        return super.update(t);
    }

    @RequestMapping("/copyUser")
    public void copyUser(String custCode) {
        baseService.copyUser(custCode);
    }

}