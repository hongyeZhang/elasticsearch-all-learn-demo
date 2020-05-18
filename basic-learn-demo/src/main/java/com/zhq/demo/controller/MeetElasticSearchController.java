package com.zhq.demo.controller;

import com.zhq.demo.service.MeetElasticSearchService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : ZHQ
 * @date : 2020/5/16
 */
@RestController
@RequestMapping("/springboot/es")
public class MeetElasticSearchController {

    @Autowired
    private MeetElasticSearchService meetElasticSearchService;

    @RequestMapping("/init")
    public String initElasticSearch() {
        meetElasticSearchService.initEs();
        return "Init ElasticSearch Over!";
    }

    @RequestMapping("/buildRequest")
    public String executeRequestForElasticSearch() {
        return meetElasticSearchService.executeRequest();
    }

    @RequestMapping("/parseEsResponse")
    public String parseElasticSearchResponse() {
        meetElasticSearchService.parseElasticSearchResponse();
        return "Parse ElasticSearch Response Is  Over!";
    }
}
