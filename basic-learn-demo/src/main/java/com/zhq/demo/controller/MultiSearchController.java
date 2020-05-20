package com.zhq.demo.controller;

import com.google.common.base.Splitter;

import com.zhq.demo.service.MultiSearchService;

import org.elasticsearch.common.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : ZHQ
 * @date : 2020/5/20
 */
@RestController
@RequestMapping("/springboot/es/multisearch")
public class MultiSearchController {

    @Autowired
    private MultiSearchService multiSearchService;

    // 同步方式执行MultiSearchRequest
    @RequestMapping("/sr")
    public String executeMultiSearchRequest(String field, String keywords) {
        // 参数校验
        if (Strings.isNullOrEmpty(field) || Strings.isNullOrEmpty(keywords)) {
            return "Parameters are wrong!";
        }

        // 将英文逗号分隔的字符串切分成数组
        List<String> keywordsList = Splitter.on(",").splitToList(keywords);
        multiSearchService.executeMultiSearchRequest(field,
                        keywordsList.toArray(new String[keywordsList.size()]));
        return "Execute MultiSearchRequest success!";
    }
}

