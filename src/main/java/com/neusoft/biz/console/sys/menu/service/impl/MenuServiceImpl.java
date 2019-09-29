package com.neusoft.biz.console.sys.menu.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.neusoft.base.service.AbstractBaseService;
import com.neusoft.biz.console.sys.menu.dao.MenuMapper;
import com.neusoft.biz.console.sys.menu.model.Menu;
import com.neusoft.biz.console.sys.menu.service.MenuService;

@Service
public class MenuServiceImpl extends AbstractBaseService<MenuMapper, Menu> implements MenuService {

    @Override
    public Menu qryMenuByUserId(Integer userId) {
        Menu root = null;
        List<Menu> rsList = baseMapper.qryMenuByUserId(userId);
        if (CollectionUtils.isNotEmpty(rsList)) {
            rsList = this.setMenuI18n(rsList);

            root = rsList.get(0);
            root.setId(root.getMenuId());
            for (Menu m : rsList) {
                m.setId(m.getMenuId());
                this.addMenu(m, root);
            }
        }
        return root;
    }

    @Override
    public Menu qryMenuTree(Menu t) {
        Menu root = null;
        List<Menu> rsList = baseMapper.qryMenuTree(t);
        if (CollectionUtils.isNotEmpty(rsList)) {
            rsList = this.setMenuI18n(rsList);

            root = rsList.get(0);
            root.setId(root.getMenuId());
            for (Menu m : rsList) {
                m.setId(m.getMenuId());
                this.addMenu(m, root);
            }
        }
        return root;
    }

    private boolean addMenu(Menu m, Menu dt) {
        boolean flag = false;
        List<Menu> children = dt.getChildren();
        if (m.getPid().equals(dt.getMenuId())) {
            dt.setState("closed");
            if (children == null) {
                children = new ArrayList<Menu>();
                dt.setChildren(children);
            }
            children.add(m);
            flag = true;
        }
        if (!flag && CollectionUtils.isNotEmpty(children)) {
            for (Menu t : children) {
                if (this.addMenu(m, t)) {
                    break;
                }
            }
        }
        return flag;
    }

    @Override
    public Menu qryMenuAuthTree(String roleType) {
        Menu root = null;
        List<Menu> rsList = baseMapper.qryMenuAuthTree(roleType);
        if (CollectionUtils.isNotEmpty(rsList)) {
            rsList = this.setMenuI18n(rsList);

            root = rsList.get(0);
            root.setId(root.getMenuId());
            for (Menu m : rsList) {
                m.setId(m.getMenuId());
                this.addMenu(m, root);
            }
        }
        return root;
    }

    /**
     * 菜单-国际化
     */
    private List<Menu> setMenuI18n(List<Menu> rsList) {
        Locale lc = LocaleContextHolder.getLocale();
        boolean flag = lc.toString().equals(Locale.CHINA.toString());

        rsList.forEach(m -> {
            String menuName = m.getText();
            if (menuName.contains("#")) {
                if (flag) {
                    m.setText(menuName.substring(0, menuName.indexOf("#")));
                } else {
                    m.setText(menuName.substring(menuName.indexOf("#") + 1));
                }
            }
        });
        return rsList;
    }

}