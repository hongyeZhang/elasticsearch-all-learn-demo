package com.zhq.demo.service;

/**
 * @author : ZHQ
 * @date : 2020/5/21
 */
public interface IndexService {
    // 同步执行AnalyzeRequest
    void executeAnalyzeRequest(String text);

    // 同步执行创建索引的请求
    void executeIndexRequest(String index);

    // 同步执行GetIndexRequest
    void excuteGetIndexRequest(String index);

    // 同步执行DeleteIndexRequest
    void executeDeleteIndexRequest(String index);

    // 同步执行索引存在验证请求
    void executeExistsIndexRequest(String index);

    // 同步方式执行打开索引OpenIndexRequest
    void executeOpenIndexRequest(String index);

    // 以同步方式执行关闭索引请求CloseIndexRequest
    void executeCloseIndexRequest(String index);

    // 同步执行ResizeRequest
    void executeResizeRequest(String sourceIndex, String targetIndex);

    // 同步执行拆分索引的ResizeRequest
    void executeSplitRequest(String sourceIndex, String targetIndex);

    // 同步方式执行RefreshRequest
    void executeRefreshRequest(String index);

    // 同步方式执行FlushRequest
    void executeFlushRequest(String index);

    // 同步方式执行SyncedFlushRequest
    void executeSyncedFlushRequest(String index);

    // 同步方式执行ClearIndicesCacheRequest
    void executeClearIndicesCacheRequest(String index);

    // 同步执行ForceMergeRequest
    void executeForceMergeRequest(String index);

    // 同步执行RolloverRequest
    void executeRolloverRequest(String index);

    // 同步执行IndicatesAliasesRequest
    void executeIndicatesAliasesRequest(String index, String indexAlias);

    // 同步执行GetAliasesRequest
    void executeGetAliasesRequest(String indexAlias);

    // 同步执行GetAliasesRequest,获取索引别名
    void executeGetAliasesRequestForAliases(String indexAlias);
}
