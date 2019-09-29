package com.neusoft.biz.archives.cust.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neusoft.base.comm.GlobalConst;
import com.neusoft.base.service.AbstractBaseService;
import com.neusoft.base.utils.ShiroUtils;
import com.neusoft.biz.archives.cust.dao.CustMapper;
import com.neusoft.biz.archives.cust.model.Cust;
import com.neusoft.biz.archives.cust.service.CustService;
import com.neusoft.biz.console.sys.coding.dao.CodingMapper;
import com.neusoft.biz.console.sys.user.dao.UserMapper;
import com.neusoft.biz.console.sys.user.model.User;

@Service
public class CustServiceImpl extends AbstractBaseService<CustMapper, Cust> implements CustService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CodingMapper codingMapper;

    @Override
    public void insert(Cust t) {
        t.setCustCode(codingMapper.getCodingNextVal(GlobalConst.CODING_CUST));
        baseMapper.insert(t);

        User f = new User();
        f.setUserCode(t.getCustCode());
        f.setName(t.getCustName());
        f.setPassword(ShiroUtils.encrypt(GlobalConst.JQUERY_MD5_DEF, t.getCustCode()));
        f.setRoleType(GlobalConst.ROLETYPE_CUSTOMER);
        f.setIsValid(t.getIsValid());
        userMapper.insert(f);
        userMapper.insertUserRole(f);
    }

    @Override
    public void copyUser(String custCode) {
        String newCustCode = codingMapper.getCodingNextVal(GlobalConst.CODING_CUST);
        String pwd = ShiroUtils.encrypt(GlobalConst.JQUERY_MD5_DEF, newCustCode);
        baseMapper.copyUser(custCode, newCustCode, pwd);
    }

}