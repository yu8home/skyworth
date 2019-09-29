package com.neusoft.biz.archives.custspc.service;

import java.util.List;
import java.util.Map;

import com.neusoft.base.service.BaseService;
import com.neusoft.biz.archives.custspc.model.CustSpc;
import com.neusoft.biz.console.sys.user.model.User;

@SuppressWarnings({ "rawtypes" })
public interface CustSpcService extends BaseService<CustSpc> {

    List<User> qryRelUser(Map t);

    List<User> qryUnRelUser(Map t);

    void addRelUser(Map t);

    void deleteRelUser(Map t);
}