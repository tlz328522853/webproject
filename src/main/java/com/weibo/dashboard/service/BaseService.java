package com.weibo.dashboard.service;

import java.util.List;
import java.util.Map;

import com.weibo.dashboard.entity.BaseEntity;
import com.weibo.dashboard.entity.SelectParam;
import com.weibo.util.LarPager;

/**
 * 基础的service
 * Created by 韩亚辉 on 2016/3/22.
 */
public interface BaseService<T extends BaseEntity> {
    LarPager<T> findAll(LarPager<T> larPager);

    Boolean save(T t);

    Boolean update(T t);

    Boolean delete(Long id);

    Long totalCount(LarPager<T> var1);

    LarPager<T> findByOrgIds(LarPager<T> larPager, List<Long> ids);

    Long countByOrgIds(LarPager<T> var1, List<Long> ids);

    Boolean exist(Map<String, String> map, Long id);

    //批量删除
    Boolean batchDelete(String ids);

    LarPager<T> selectData(List<SelectParam> list);

    T getById(Long id, Map<String, Object> map);
}
