package com.neusoft.biz.console.sys.dict.service.impl;

import org.springframework.stereotype.Service;

import com.neusoft.base.service.AbstractBaseService;
import com.neusoft.biz.console.sys.dict.dao.DataDictMapper;
import com.neusoft.biz.console.sys.dict.model.DataDict;
import com.neusoft.biz.console.sys.dict.service.DataDictService;
import com.neusoft.base.comm.Dict;
import com.neusoft.base.comm.GlobalConst;

@Service
public class DataDictServiceImpl extends AbstractBaseService<DataDictMapper, DataDict> implements DataDictService {

    @Override
    public void loadDict() {
        DataDict t = new DataDict();
        t.setIsValid(GlobalConst.YES);
        Dict.loadDict(baseMapper.qryForList(t));
    }

}