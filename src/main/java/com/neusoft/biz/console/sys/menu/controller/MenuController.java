package com.neusoft.biz.console.sys.menu.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.base.comm.GlobalConst;
import com.neusoft.base.controller.AbstractBaseController;
import com.neusoft.base.utils.ShiroUtils;
import com.neusoft.biz.console.sys.menu.model.Menu;
import com.neusoft.biz.console.sys.menu.service.MenuService;

/**
 * 菜单
 *
 * @author：yu8home
 * @String：2017年12月3日 下午10:24:51
 */
@RestController
@RequestMapping("/console/sys/menu")
public class MenuController extends AbstractBaseController<MenuService, Menu> {

    // 导航栏菜单
    @RequestMapping("qryMenuByUserId")
    public List<Menu> qryMenuByUserId() {
        Menu root = this.baseService.qryMenuByUserId(ShiroUtils.getUserId());
        return root.getChildren();
    }

    // 菜单树：不包含跟节点
    @RequestMapping("qryMenuTree")
    public List<Menu> qryMenuTree(Menu t) {
        t.setUserId(ShiroUtils.getUserId());
        t.setIsValid(GlobalConst.YES);
        Menu root = this.baseService.qryMenuTree(t);
        return root.getChildren();
    }

    // 菜单管理treegrid
    @RequestMapping("/qryMenuTreeGrid")
    public List<Menu> qryMenuTreeGrid(Menu t) {
        List<Menu> rsMenu = new ArrayList<Menu>();
        Menu root = this.baseService.qryMenuTree(t);
        if (root != null) {
            root.setState("open");// 默认展开
            rsMenu.add(root);
        }
        return rsMenu;
    }

    // 菜单权限树：角色管理
    @RequestMapping("qryMenuAuthTree")
    public List<Menu> qryMenuAuthTree(String roleType) {
        Menu root = this.baseService.qryMenuAuthTree(roleType);
        return root.getChildren();
    }

}