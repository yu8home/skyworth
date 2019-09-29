package com.neusoft.biz.console.sys.resource.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.neusoft.base.comm.GlobalConst;
import com.neusoft.base.service.AbstractBaseService;
import com.neusoft.biz.console.sys.resource.dao.ResourceMapper;
import com.neusoft.biz.console.sys.resource.model.Resource;
import com.neusoft.biz.console.sys.resource.service.ResourceService;

@Service
public class ResourceServiceImpl extends AbstractBaseService<ResourceMapper, Resource> implements ResourceService {

    @Override
    public Map<String, String> getFilterChainDefinitionMap() {
        Map<String, String> rsMap = new LinkedHashMap<String, String>();
        Resource t = new Resource();
        t.setIsValid(GlobalConst.YES);
        List<Resource> rsList = this.qryForList(t);

        rsList.forEach(m -> {
            String auth = m.getAuth();
            if (StringUtils.isBlank(auth)) {
                String[] url = m.getUrl().split("/");
                int k = url.length;
                auth = "kickout,perms[" + url[k - 2] + ":" + url[k - 1] + "]";
            }
            rsMap.put(m.getUrl(), auth);
        });
        return rsMap;
    }

}