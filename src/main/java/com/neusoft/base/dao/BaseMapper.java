package com.neusoft.base.dao;

import java.util.List;
import java.util.Map;

/**
 * Mapper接口
 *
 * @author：yu8home
 * @date：2017年9月8日 下午9:05:59
 */
@SuppressWarnings({ "rawtypes" })
public interface BaseMapper<T> {

    List qryForList(Map condition);

    List<T> qryForList(T t);

    T getInfo(T t);

    Integer insert(T t);

    void update(T t);

    void delete(T t);
}