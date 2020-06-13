package com.zhq.demo.service;

/**
 * @author : ZHQ
 * @date : 2020/5/20
 */
public interface RankEvalService {
    // 同步执行RankEvalRequest
    public void executeRankEvalRequest(String index, String documentId, String field, String content);
}
