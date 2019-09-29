package com.neusoft.biz.archives.custspc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.base.comm.GlobalConst;
import com.neusoft.base.controller.AbstractBaseController;
import com.neusoft.base.controller.R;
import com.neusoft.biz.archives.custspc.model.CustSpc;
import com.neusoft.biz.archives.custspc.service.CustSpcService;
import com.neusoft.biz.console.sys.user.model.User;
import com.neusoft.biz.console.sys.user.service.UserService;

/**
 * 客服专员
 *
 * @author：yu8home
 * @date：2018年6月28日 下午1:24:27
 */
@RestController
@RequestMapping("/archives/custspc")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class CustSpcController extends AbstractBaseController<CustSpcService, CustSpc> {
    @Autowired
    private UserService userService;

    @RequestMapping("qryRelUser")
    public List<User> qryRelUser(@RequestParam Map t) {
        return baseService.qryRelUser(t);
    }

    @RequestMapping("qryUnRelUser")
    public Map qryUnRelUser(@RequestParam Map t, int page, int rows) {
        PageHelper.startPage(page, rows);
        List<User> rsList = baseService.qryUnRelUser(t);
        PageInfo<User> pageInfo = new PageInfo<User>(rsList);

        Map rsMap = new HashMap();
        rsMap.put("total", pageInfo.getTotal());
        rsMap.put("rows", pageInfo.getList());
        return rsMap;
    }

    @RequestMapping("addRelUser")
    public void addRelUser(@RequestParam Map t) {
        baseService.addRelUser(t);
    }

    @Override
    @RequestMapping("/update")
    public R update(CustSpc t) {
        if (t.getIsValid() == GlobalConst.NO) {
            int cnt = userService.getUnclosedCntByUser(GlobalConst.ROLETYPE_CS, t.getCustServCode());
            if (cnt > 0) {
                return R.fail(i18n.getMessage("user.have.unclosed.form"));
            }
        }
        return super.update(t);
    }

    @RequestMapping("deleteRelUser")
    public void deleteRelUser(@RequestParam Map t) {
        baseService.deleteRelUser(t);
    }

}