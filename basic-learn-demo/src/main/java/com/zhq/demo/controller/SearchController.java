package com.zhq.demo.controller;

import com.zhq.demo.service.SearchService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : ZHQ
 * @date : 2020/5/19
 */
@RestController
@RequestMapping("/springboot/es/search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    // 同步方式执行SearchRequest
    @RequestMapping("/sr")
    public String executeSearchRequest() {
        searchService.executeSearchRequest();

        return "Execute SearchRequest success!";
    }


}
