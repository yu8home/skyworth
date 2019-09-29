package com.neusoft.base.service;

import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.neusoft.base.dao.BaseMapper;
import com.neusoft.base.spring.I18N;

/**
 * Service抽象类
 *
 * @author：yu8home
 * @date：2017年9月8日 下午9:04:22
 */
@SuppressWarnings({ "rawtypes" })
public abstract class AbstractBaseService<M extends BaseMapper<T>, T> implements BaseService<T> {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    protected M baseMapper;
    @Autowired
    protected I18N i18n;

    public List qryForList(Map condition) {
        return baseMapper.qryForList(condition);
    }

    public List<T> qryForList(T t) {
        return baseMapper.qryForList(t);
    }

    public T getInfo(T t) {
        return baseMapper.getInfo(t);
    }

    public void insert(T t) {
        baseMapper.insert(t);
    }

    public void update(T t) {
        baseMapper.update(t);
    }

    public void delete(T t) {
        baseMapper.delete(t);
    }

    public String excelImport(Workbook wb) {
        return null;
    }

}