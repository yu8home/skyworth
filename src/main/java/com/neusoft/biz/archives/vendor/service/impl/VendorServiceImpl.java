package com.neusoft.biz.archives.vendor.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neusoft.base.comm.GlobalConst;
import com.neusoft.base.service.AbstractBaseService;
import com.neusoft.base.utils.ShiroUtils;
import com.neusoft.biz.archives.vendor.dao.VendorMapper;
import com.neusoft.biz.archives.vendor.model.Vendor;
import com.neusoft.biz.archives.vendor.service.VendorService;
import com.neusoft.biz.console.sys.coding.dao.CodingMapper;
import com.neusoft.biz.console.sys.user.dao.UserMapper;
import com.neusoft.biz.console.sys.user.model.User;

@Service
public class VendorServiceImpl extends AbstractBaseService<VendorMapper, Vendor> implements VendorService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CodingMapper codingMapper;

    @Override
    public void insert(Vendor t) {
        t.setVendorCode(codingMapper.getCodingNextVal(GlobalConst.CODING_VENDOR));
        baseMapper.insert(t);

        User f = new User();
        f.setUserCode(t.getVendorCode());
        f.setName(t.getVendorName());
        f.setPassword(ShiroUtils.encrypt(GlobalConst.JQUERY_MD5_DEF, t.getVendorCode()));
        f.setRoleType(GlobalConst.ROLETYPE_VENDOR);
        f.setIsValid(t.getIsValid());
        userMapper.insert(f);
        userMapper.insertUserRole(f);
    }

}