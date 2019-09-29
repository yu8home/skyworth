package com.neusoft.biz.console.sys.user.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.neusoft.base.dao.BaseMapper;
import com.neusoft.biz.console.sys.resource.model.Resource;
import com.neusoft.biz.console.sys.user.model.User;

@Mapper
@SuppressWarnings({ "rawtypes" })
public interface UserMapper extends BaseMapper<User> {

    int getUnclosedCntByUser(@Param("roleType") String roleType, @Param("userCode") String userCode);

    void insertUserRole(User t);

    List<Map> optionalRole(User t);

    List<Map> selectedRole(User t);

    int isExistsUserCode(User t);

    void updatePwd(User t);

    Set<String> qryUserRoles(@Param("userId") Integer userId);

    List<Resource> qryUserPerms(@Param("userId") Integer userId);

    int isUsed(String userCode);
}