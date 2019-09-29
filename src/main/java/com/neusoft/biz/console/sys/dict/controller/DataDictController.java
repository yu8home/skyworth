package com.neusoft.biz.console.sys.dict.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.base.comm.Dict;
import com.neusoft.base.controller.AbstractBaseController;
import com.neusoft.base.controller.R;
import com.neusoft.biz.console.sys.dict.model.DataDict;
import com.neusoft.biz.console.sys.dict.service.DataDictService;

/**
 * 数据字典
 *
 * @author：yu8home
 * @String：2017年12月3日 下午10:31:29
 */
@RestController
@RequestMapping("/console/sys/dict")
public class DataDictController extends AbstractBaseController<DataDictService, DataDict> {

    @RequestMapping("/selDict/{dictCode}")
    public List<DataDict> selDict(@PathVariable Integer dictCode) {
        return Dict.getSubDict(dictCode);
    }

    @RequestMapping("/selDictEx/{dictCode}/{excludeArr}")
    public List<DataDict> selDictEx(@PathVariable Integer dictCode, @PathVariable String excludeArr) {
        List<DataDict> rs = new ArrayList<DataDict>();
        List<String> ex = Arrays.asList(excludeArr.split(","));
        Dict.getSubDict(dictCode).forEach(m -> {
            if (!ex.contains(m.getValue())) {
                rs.add(m);
            }
        });
        return rs;
    }

    // 增加“空项”，相当于“请选择、全选”
    @RequestMapping("/selectDict/{dictCode}")
    public List<DataDict> selectDict(@PathVariable Integer dictCode) {
        List<DataDict> rs = new ArrayList<DataDict>();
        DataDict t = new DataDict();
        t.setValue("");
        t.setText(i18n.getMessage("please.select"));
        rs.add(0, t);
        rs.addAll(Dict.getSubDict(dictCode));
        return rs;
    }

    // 增加“空项”，相当于“请选择、全选”，并增加“排除项”
    @RequestMapping("/selectDictEx/{dictCode}/{excludeArr}")
    public List<DataDict> selectDictEx(@PathVariable Integer dictCode, @PathVariable String excludeArr) {
        List<DataDict> rs = new ArrayList<DataDict>();
        DataDict t = new DataDict();
        t.setValue("");
        t.setText(i18n.getMessage("please.select"));
        rs.add(0, t);

        List<String> ex = Arrays.asList(excludeArr.split(","));
        Dict.getSubDict(dictCode).forEach(m -> {
            if (!ex.contains(m.getValue())) {
                rs.add(m);
            }
        });
        return rs;
    }

    @RequestMapping("/selDictByPid/{pid}")
    public List<DataDict> selDictByPid(@PathVariable Integer pid) {
        return Dict.getDictByParentId(pid);
    }

    // 增加“空项”，相当于“请选择、全选”
    @RequestMapping("/selectDictByPid/{pid}")
    public List<DataDict> selectDictByPid(@PathVariable Integer pid) {
        List<DataDict> rs = new ArrayList<DataDict>();
        DataDict t = new DataDict();
        t.setValue("");
        t.setText(i18n.getMessage("please.select"));
        rs.add(0, t);
        rs.addAll(Dict.getDictByParentId(pid));
        return rs;
    }

    @Override
    @RequestMapping("/add")
    public R add(DataDict t) {
        this.baseService.insert(t);
        this.baseService.loadDict();
        return R.success();
    }

    @Override
    @RequestMapping("/update")
    public R update(DataDict t) {
        this.baseService.update(t);
        this.baseService.loadDict();
        return R.success();
    }

}