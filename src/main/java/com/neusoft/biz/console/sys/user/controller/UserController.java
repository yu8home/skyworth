package com.neusoft.biz.console.sys.user.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.base.comm.GlobalConst;
import com.neusoft.base.controller.AbstractBaseController;
import com.neusoft.base.controller.R;
import com.neusoft.base.utils.ShiroUtils;
import com.neusoft.biz.console.sys.user.model.User;
import com.neusoft.biz.console.sys.user.service.UserService;

/**
 * 用户
 *
 * @author：yu8home
 * @date：2018年2月23日 下午2:14:57
 */
@RestController
@RequestMapping("/console/sys/user")
@SuppressWarnings({ "rawtypes" })
public class UserController extends AbstractBaseController<UserService, User> {

    @Override
    @RequestMapping("/add")
    public R add(User t) {
        t.setPassword(ShiroUtils.encrypt(GlobalConst.JQUERY_MD5_DEF, t.getUserCode()));
        return super.add(t);
    }

    @Override
    @RequestMapping("/update")
    public R update(User t) {
        return super.update(t);
    }

    @RequestMapping("/updatePwd")
    public R updatePwd(String password) {
        User t = this.getUser();
        t.setPassword(ShiroUtils.encrypt(password, t.getUserCode()));
        this.baseService.updatePwd(t);
        return R.success();
    }

    @RequestMapping("/resetPwd")
    public R resetPwd(User t) {
        t.setPassword(ShiroUtils.encrypt(GlobalConst.JQUERY_MD5_DEF, t.getUserCode()));
        this.baseService.updatePwd(t);
        return R.success();
    }

    @RequestMapping("optionalRole")
    public List<Map> optionalRole(User t) {
        return baseService.optionalRole(t);
    }

    @RequestMapping("selectedRole")
    public List<Map> selectedRole(User t) {
        return baseService.selectedRole(t);
    }

    @RequestMapping("isExistsUserCode")
    public int isExistsUserCode(User t) {
        return baseService.isExistsUserCode(t);
    }

    @RequestMapping("/isUsed")
    public int isUsed(String userCode) {
        return baseService.isUsed(userCode);
    }

}