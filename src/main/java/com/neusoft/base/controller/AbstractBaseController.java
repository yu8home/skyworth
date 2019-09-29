package com.neusoft.base.controller;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.base.service.BaseService;
import com.neusoft.base.spring.I18N;
import com.neusoft.base.utils.CommUtils;
import com.neusoft.base.utils.ShiroUtils;
import com.neusoft.biz.console.sys.user.model.User;

/**
 * Controller抽象类
 *
 * @author：yu8home
 * @date：2017年9月4日 下午3:02:09
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public abstract class AbstractBaseController<M extends BaseService<T>, T> {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    protected M baseService;
    @Autowired
    protected I18N i18n;

    protected User getUser() {
        return ShiroUtils.getUser();
    }

    protected Integer getUserId() {
        return this.getUser().getUserId();
    }

    // 查询条件只包含“单个”bean属性
    @RequestMapping("/qryForPage")
    protected Map qryForPage(T t, int page, int rows) {
        PageHelper.startPage(page, rows);
        List<T> rsList = this.baseService.qryForList(t);
        PageInfo<T> pageInfo = new PageInfo<T>(rsList);

        Map rsMap = new HashMap();
        rsMap.put("total", pageInfo.getTotal());
        rsMap.put("rows", pageInfo.getList());
        return rsMap;
    }

    // 查询条件包含“多个”bean属性
    @RequestMapping("/queryForPage")
    protected Map queryForPage(@RequestParam Map t, int page, int rows) {
        PageHelper.startPage(page, rows);
        List<T> rsList = this.baseService.qryForList(t);
        PageInfo<T> pageInfo = new PageInfo<T>(rsList);

        Map rsMap = new HashMap();
        rsMap.put("total", pageInfo.getTotal());
        rsMap.put("rows", pageInfo.getList());
        return rsMap;
    }

    @RequestMapping("/qryForList")
    protected List qryForList(T t) {
        return baseService.qryForList(t);
    }

    @RequestMapping("/queryForList")
    protected List queryForList(@RequestParam Map t) {
        return baseService.qryForList(t);
    }

    @RequestMapping("/add")
    protected R add(T t) {
        this.baseService.insert(t);
        return R.success();
    }

    @RequestMapping("/edit")
    protected T edit(T t) {
        return this.baseService.getInfo(t);
    }

    @RequestMapping("/update")
    protected R update(T t) {
        this.baseService.update(t);
        return R.success();
    }

    @RequestMapping("/delete")
    protected R delete(T t) {
        this.baseService.delete(t);
        return R.success();
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // excel导入
    @RequestMapping("/excelImport")
    protected R excelImport(@RequestParam(value = "fileName") MultipartFile file) {
        InputStream in = null;
        Workbook wb = null;

        R r = null;
        try {
            in = file.getInputStream();
            wb = new XSSFWorkbook(in);
            String rstr = baseService.excelImport(wb);
            if (StringUtils.isBlank(rstr)) {
                r = R.success();
            } else {
                r = R.fail(rstr);
            }
        } catch (Exception e) {
            r = R.fail(e.getMessage());
            e.printStackTrace();
        } finally {
            CommUtils.close(wb, in);
        }
        return r;
    }

}