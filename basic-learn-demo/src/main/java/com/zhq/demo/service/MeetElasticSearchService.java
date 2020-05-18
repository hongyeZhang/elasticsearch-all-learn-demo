package com.zhq.demo.service;

/**
 * @author : ZHQ
 * @date : 2020/5/16
 */
public interface MeetElasticSearchService {

    /**
     * 本部分用于介绍如何与ElasticSearch构建连接和关闭连接
     */
    void initEs();

    /**
     * 关闭
     */
    void closeEs();

    /**
     * 本部分用于介绍如何构建对ElasticSearch服务的请求
     */
    String executeRequest();

    /**
     * 本部分用于介绍如何解析ElasticSearch服务的返回结果
     */
    void parseElasticSearchResponse();

}
