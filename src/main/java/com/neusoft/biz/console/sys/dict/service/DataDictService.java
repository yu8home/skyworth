package com.neusoft.biz.console.sys.dict.service;

import com.neusoft.base.service.BaseService;
import com.neusoft.biz.console.sys.dict.model.DataDict;

public interface DataDictService extends BaseService<DataDict> {

    /**
     * 加载数据字典到缓存中去
     *
     * @author：yu8home
     * @date：2017年12月3日 下午10:32:21
     */
    void loadDict();
}