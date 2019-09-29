package com.neusoft.biz.console.sys.auth.service;

import java.util.List;
import java.util.Map;

import com.neusoft.base.service.BaseService;
import com.neusoft.biz.console.sys.auth.model.Authority;
import com.neusoft.biz.console.sys.resource.model.Resource;

@SuppressWarnings({ "rawtypes" })
public interface AuthorityService extends BaseService<Authority> {

    /**
     * 查询已授权资源
     *
     * @author：yu8home
     * @date：2018年6月21日 下午12:21:15
     */
    List<Resource> qryAuthRec(Integer authId);

    /**
     * 查询未授权资源
     *
     * @author：yu8home
     * @date：2018年6月11日 下午12:21:35
     */
    List<Resource> qryUnAuthRec(Map t);

    /**
     * 新建授权资源
     *
     * @author：yu8home
     * @date：2018年6月11日 下午12:21:53
     */
    void addAuthRec(Map t);

    /**
     * 删除授权资源
     *
     * @author：yu8home
     * @date：2018年6月11日 下午12:22:10
     */
    void deleteAuthRec(Map t);
}