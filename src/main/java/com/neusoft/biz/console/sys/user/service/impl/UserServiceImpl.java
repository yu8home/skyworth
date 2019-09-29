package com.neusoft.biz.console.sys.user.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.neusoft.base.service.AbstractBaseService;
import com.neusoft.biz.console.sys.resource.model.Resource;
import com.neusoft.biz.console.sys.user.dao.UserMapper;
import com.neusoft.biz.console.sys.user.model.User;
import com.neusoft.biz.console.sys.user.service.UserService;

@Service
@SuppressWarnings({ "rawtypes" })
public class UserServiceImpl extends AbstractBaseService<UserMapper, User> implements UserService {

    @Override
    public int getUnclosedCntByUser(String roleType, String userCode) {
        return baseMapper.getUnclosedCntByUser(roleType, userCode);
    }

    @Override
    public List<Map> optionalRole(User t) {
        return baseMapper.optionalRole(t);
    }

    @Override
    public List<Map> selectedRole(User t) {
        return baseMapper.selectedRole(t);
    }

    @Override
    public int isExistsUserCode(User t) {
        return baseMapper.isExistsUserCode(t);
    }

    @Override
    public void updatePwd(User t) {
        baseMapper.updatePwd(t);
    }

    @Override
    public Set<String> qryUserRoles(Integer userId) {
        return baseMapper.qryUserRoles(userId);
    }

    @Override
    public List<Resource> qryUserPerms(Integer userId) {
        return baseMapper.qryUserPerms(userId);
    }

    @Override
    public int isUsed(String userCode) {
        return baseMapper.isUsed(userCode);
    }

}