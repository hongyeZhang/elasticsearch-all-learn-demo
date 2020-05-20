package com.zhq.demo.service;

/**
 * @author : ZHQ
 * @date : 2020/5/19
 */
public interface ScrollSearchService {
    // 构建SearchRequest:带有滚动搜索参数
    public void buildAndExecuteScrollSearchRequest(String indexName, int size);
}
