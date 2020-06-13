package com.zhq.demo.controller;

import com.zhq.demo.service.RankEvalService;

import org.elasticsearch.common.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : ZHQ
 * @date : 2020/5/20
 */
@RestController
@RequestMapping("/springboot/es/ranksearch")
public class RankEvalController {

    @Autowired
    private RankEvalService rankEvalService;

    // 同步方式执行MultiSearchRequest
    @RequestMapping("/sr")
    public String executeRankEvalRequest(String indexName, String document, String field,
                    String content) {
        // 参数校验
        if (Strings.isNullOrEmpty(indexName) || Strings.isNullOrEmpty(document)
                        || Strings.isNullOrEmpty(field) || Strings.isNullOrEmpty(content)) {
            return "Parameters are wrong!";
        }

        rankEvalService.executeRankEvalRequest(indexName, document, field, content);

        return "Execute RankEvalRequest success!";
    }
}
