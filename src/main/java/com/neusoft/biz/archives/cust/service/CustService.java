package com.neusoft.biz.archives.cust.service;

import com.neusoft.base.service.BaseService;
import com.neusoft.biz.archives.cust.model.Cust;

public interface CustService extends BaseService<Cust> {

    void copyUser(String custCode);
}