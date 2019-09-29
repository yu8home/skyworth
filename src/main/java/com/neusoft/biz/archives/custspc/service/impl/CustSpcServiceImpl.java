package com.neusoft.biz.archives.custspc.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neusoft.base.comm.GlobalConst;
import com.neusoft.base.service.AbstractBaseService;
import com.neusoft.base.utils.ShiroUtils;
import com.neusoft.biz.archives.custspc.dao.CustSpcMapper;
import com.neusoft.biz.archives.custspc.model.CustSpc;
import com.neusoft.biz.archives.custspc.service.CustSpcService;
import com.neusoft.biz.console.sys.coding.dao.CodingMapper;
import com.neusoft.biz.console.sys.user.dao.UserMapper;
import com.neusoft.biz.console.sys.user.model.User;

@Service
@SuppressWarnings({ "rawtypes" })
public class CustSpcServiceImpl extends AbstractBaseService<CustSpcMapper, CustSpc> implements CustSpcService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CodingMapper codingMapper;

    @Override
    public void insert(CustSpc t) {
        t.setCustServCode(codingMapper.getCodingNextVal(GlobalConst.CODING_CUSTSERV));
        baseMapper.insert(t);

        User f = new User();
        f.setUserCode(t.getCustServCode());
        f.setName(t.getCustServName());
        f.setPassword(ShiroUtils.encrypt(GlobalConst.JQUERY_MD5_DEF, t.getCustServCode()));
        f.setRoleType(GlobalConst.ROLETYPE_CS);
        f.setIsValid(t.getIsValid());
        userMapper.insert(f);
        userMapper.insertUserRole(f);
    }

    @Override
    public List<User> qryRelUser(Map t) {
        return baseMapper.qryRelUser(t);
    }

    @Override
    public List<User> qryUnRelUser(Map t) {
        return baseMapper.qryUnRelUser(t);
    }

    @Override
    public void addRelUser(Map t) {
        baseMapper.addRelUser(t);
    }

    @Override
    public void deleteRelUser(Map t) {
        baseMapper.deleteRelUser(t);
    }

}