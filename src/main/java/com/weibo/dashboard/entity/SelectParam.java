package com.weibo.dashboard.entity;

/**
 * 查询参数封装
 * Created by 韩亚辉 on 2016/3/31.
 */
public class SelectParam extends BaseEntity{
    private String key;
    private String operation;
    private Object value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
