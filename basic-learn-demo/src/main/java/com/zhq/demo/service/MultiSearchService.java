package com.zhq.demo.service;

/**
 * @author : ZHQ
 * @date : 2020/5/20
 */
public interface MultiSearchService {
    // 同步执行MultiSearchRequest
    public void executeMultiSearchRequest(String field, String[] keywords);
}
