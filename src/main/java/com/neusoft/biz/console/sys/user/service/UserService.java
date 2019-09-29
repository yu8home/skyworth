package com.neusoft.biz.console.sys.user.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.neusoft.base.service.BaseService;
import com.neusoft.biz.console.sys.resource.model.Resource;
import com.neusoft.biz.console.sys.user.model.User;

@SuppressWarnings({ "rawtypes" })
public interface UserService extends BaseService<User> {

    /**
     * 失效用户前校验是否有未闭环工单
     */
    int getUnclosedCntByUser(String roleType, String userCode);

    /**
     * 可选角色
     */
    List<Map> optionalRole(User t);

    /**
     * 已选角色
     */
    List<Map> selectedRole(User t);

    /**
     * 校验用户编码是否已存在
     */
    int isExistsUserCode(User t);

    /**
     * 修改密码
     */
    void updatePwd(User t);

    /**
     * realm-用户角色：超级管理员-所有角色
     */
    Set<String> qryUserRoles(Integer userId);

    /**
     * realm-用户权限：超级管理员-所有权限
     */
    List<Resource> qryUserPerms(Integer userId);

    /**
     * 是否在用：是否有该人员对应的在途工单
     */
    int isUsed(String userCode);
}