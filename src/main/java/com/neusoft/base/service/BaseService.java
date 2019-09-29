package com.neusoft.base.service;

import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Workbook;

/**
 * Service接口
 *
 * @author：yu8home
 * @date：2017年9月8日 下午9:04:05
 */
@SuppressWarnings({ "rawtypes" })
public interface BaseService<T> {

    List qryForList(Map<String, Object> condition);

    List<T> qryForList(T t);

    T getInfo(T t);

    void insert(T t);

    void update(T t);

    void delete(T t);

    String excelImport(Workbook wb);
}