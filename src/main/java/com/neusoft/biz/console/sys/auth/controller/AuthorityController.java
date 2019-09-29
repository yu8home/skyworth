package com.neusoft.biz.console.sys.auth.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.base.controller.AbstractBaseController;
import com.neusoft.biz.console.sys.auth.model.Authority;
import com.neusoft.biz.console.sys.auth.service.AuthorityService;
import com.neusoft.biz.console.sys.resource.model.Resource;

/**
 * 权限
 *
 * @author：yu8home
 * @date：2017年12月3日 下午10:50:25
 */
@RestController
@RequestMapping("/console/sys/authority")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class AuthorityController extends AbstractBaseController<AuthorityService, Authority> {

    @RequestMapping("qryAuthRec")
    public List<Resource> qryAuthRec(Integer authId) {
        return baseService.qryAuthRec(authId);
    }

    @RequestMapping("qryUnAuthRec")
    public Map qryUnAuthRec(@RequestParam Map t, int page, int rows) {
        PageHelper.startPage(page, rows);
        List<Resource> rsList = baseService.qryUnAuthRec(t);
        PageInfo<Resource> pageInfo = new PageInfo<Resource>(rsList);

        Map rsMap = new HashMap();
        rsMap.put("total", pageInfo.getTotal());
        rsMap.put("rows", pageInfo.getList());
        return rsMap;
    }

    @RequestMapping("addAuthRec")
    public void addAuthRec(@RequestParam Map t) {
        baseService.addAuthRec(t);
    }

    @RequestMapping("deleteAuthRec")
    public void deleteAuthRec(@RequestParam Map t) {
        baseService.deleteAuthRec(t);
    }

}