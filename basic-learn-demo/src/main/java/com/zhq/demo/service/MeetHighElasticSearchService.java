package com.zhq.demo.service;

/**
 * @author : ZHQ
 * @date : 2020/5/17
 */
public interface MeetHighElasticSearchService {
    /**
     * 本部分用于介绍如何与ElasticSearch构建连接和关闭连接
     */
    void initEs();

    void closeEs();

    // 索引文档
    void indexDocuments(String indexName, String document);

    // 同步方式获取索引文档
    void getIndexDocuments(String indexName, String document);

    // 同步方式校验索引文档是否存在
    void checkExistIndexDocuments(String indexName, String document);

    // 同步方式删除索引文档
    void deleteIndexDocuments(String indexName, String document);

    // 同步方式更新索引文档
    void updateIndexDocuments(String indexName, String document);

    // 同步执行TermVectorsRequest请求
    void exucateTermVectorsRequest(String indexName, String document, String field);

    // 同步方式执行BulkRequest
    void executeBulkRequest(String indexName, String field);

    // 用BulkProcessor在同步方式下 执行BulkRequest
    void executeBulkRequestWithBulkProcessor(String indexName, String field);

    // 同步执行MultiGetRequest
    void executeMultiGetRequest(String indexName, String[] documentIds);

    // 同步执行ReindexRequest
    void executeReindexRequest(String fromIndex, String toIndex);

    // 同步执行UpdateByQueryRequest
    void executeUpdateByQueryRequest(String indexName);

    // 同步执行 DeleteByQueryRequest
    void executeDeleteByQueryRequest(String indexName);

    // 同步执行MultiTermVectorsRequest
    void executeMultiTermVectorsRequest(String indexName, String[] documentIds, String field);
}
