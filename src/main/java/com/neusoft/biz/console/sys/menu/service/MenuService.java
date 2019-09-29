package com.neusoft.biz.console.sys.menu.service;

import com.neusoft.base.service.BaseService;
import com.neusoft.biz.console.sys.menu.model.Menu;

public interface MenuService extends BaseService<Menu> {

    /**
     * 导航栏菜单
     *
     * @author：yu8home
     * @date：2018年1月23日 下午5:49:00
     */
    Menu qryMenuByUserId(Integer userId);

    /**
     * 菜单树
     *
     * @author：yu8home
     * @date：2018年1月23日 下午5:49:16
     */
    Menu qryMenuTree(Menu t);

    /**
     * 菜单权限树：角色管理
     *
     * @author：yu8home
     * @date：2018年1月29日 上午11:41:14
     */
    Menu qryMenuAuthTree(String roleType);
}