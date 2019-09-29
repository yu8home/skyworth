package com.neusoft.biz.console.sys.menu.model;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * “菜单、权限按钮”共用“类、表”
 *
 * @author：yu8home
 * @date：2018年2月2日 下午2:47:19
 */
@Getter
@Setter
public class Menu implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer menuId;
    private String text;
    private String url;// 叶子菜单url
    private String iconCls;// 图标
    private Integer isLeaf;// 是否为叶子菜单：1是、0否
    private Integer pid;
    private Integer orderNo;
    private Integer isValid;

    private Integer userId;// 根据userId获取菜单树
    // 菜单树节点
    private Integer id;
    private String state;
    private boolean checked = false;
    private List<Menu> children;
}