package com.neusoft.biz.panel.form.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.base.comm.Dict;
import com.neusoft.base.comm.GlobalConst;
import com.neusoft.base.controller.AbstractBaseController;
import com.neusoft.base.controller.R;
import com.neusoft.biz.console.sys.user.model.User;
import com.neusoft.biz.panel.form.model.Form;
import com.neusoft.biz.panel.form.service.FormService;

/**
 * 屏体工单：公共部分
 *
 * @author：yu8home
 * @date：2018年7月12日 下午1:12:28
 */
@RestController
@RequestMapping("/form")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class FormController extends AbstractBaseController<FormService, Form> {

    @Override
    @RequestMapping("/qryForPage")
    public Map qryForPage(Form t, int page, int rows) {
        User u = getUser();
        String roleType = u.getRoleType();
        if (roleType.equals(GlobalConst.ROLETYPE_CUSTOMER)) {
            t.setCreateUserId(u.getUserId());
        } else if (roleType.equals(GlobalConst.ROLETYPE_CS)) {
            t.setCsCode(u.getUserCode());
        } else if (roleType.equals(GlobalConst.ROLETYPE_VENDOR)) {
            t.setVendorCode(u.getUserCode());
        }
        t.setRoleType(roleType);

        PageHelper.startPage(page, rows);
        List<Form> rsList = this.baseService.qryForList(t);
        PageInfo<Form> pageInfo = new PageInfo<Form>(rsList);
        // 大数据量影响查询速度：避免在SQL语句中使用子查询
        List<Form> forms = pageInfo.getList();
        if (CollectionUtils.isNotEmpty(forms)) {
            forms.forEach(m -> {
                m.setStatusName(Dict.getDictName(Dict.CPT_STATUS, m.getStatus()));
                m.setPriorityName(Dict.getDictName(Dict.PRIORITY, m.getPriority()));
                m.setVendorFormStatusName(Dict.getDictName(Dict.VENDOR_FORM_STATUS, m.getVendorFormStatus()));
            });
        }

        Map rsMap = new HashMap();
        rsMap.put("total", pageInfo.getTotal());
        rsMap.put("rows", forms);
        return rsMap;
    }

    @RequestMapping(value = "/qryFormLog")
    public List qryFormLog(Form t) {
        return baseService.qryFormLog(t);
    }

    @RequestMapping(value = "/getAppClosedReason")
    public String getAppClosedReason(Integer complaintId) {
        return baseService.getAppClosedReason(complaintId);
    }

    @RequestMapping(value = "/saveFormRemark")
    public R saveFormRemark(Form t) {
        baseService.saveFormRemark(t);
        return R.success();
    }

}