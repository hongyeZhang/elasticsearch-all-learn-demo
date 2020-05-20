package com.zhq.demo.service;

/**
 * @author : ZHQ
 * @date : 2020/5/20
 */
public interface FieldCapabilitiesService {
    // 同步方式执行FieldCapabilitiesRequest，跨索引字段搜索请求
    public void executeFieldCapabilitiesRequest(String field, String[] indices);
}
