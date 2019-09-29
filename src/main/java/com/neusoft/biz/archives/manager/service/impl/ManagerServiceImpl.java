package com.neusoft.biz.archives.manager.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neusoft.base.comm.GlobalConst;
import com.neusoft.base.service.AbstractBaseService;
import com.neusoft.base.utils.ShiroUtils;
import com.neusoft.biz.archives.manager.dao.ManagerMapper;
import com.neusoft.biz.archives.manager.model.Manager;
import com.neusoft.biz.archives.manager.service.ManagerService;
import com.neusoft.biz.console.sys.coding.dao.CodingMapper;
import com.neusoft.biz.console.sys.user.dao.UserMapper;
import com.neusoft.biz.console.sys.user.model.User;

@Service
public class ManagerServiceImpl extends AbstractBaseService<ManagerMapper, Manager> implements ManagerService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CodingMapper codingMapper;

    @Override
    public void insert(Manager t) {
        t.setManagerCode(codingMapper.getCodingNextVal(GlobalConst.CODING_MANAGER));
        baseMapper.insert(t);

        User f = new User();
        f.setUserCode(t.getManagerCode());
        f.setName(t.getManagerName());
        f.setPassword(ShiroUtils.encrypt(GlobalConst.JQUERY_MD5_DEF, t.getManagerCode()));
        f.setRoleType(GlobalConst.ROLETYPE_MANAGER);
        f.setIsValid(t.getIsValid());
        userMapper.insert(f);
        userMapper.insertUserRole(f);
    }

}