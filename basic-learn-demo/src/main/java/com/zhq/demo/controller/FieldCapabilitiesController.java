package com.zhq.demo.controller;

import com.google.common.base.Splitter;

import com.zhq.demo.service.FieldCapabilitiesService;

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
@RequestMapping("/springboot/es/fieldsearch")
public class FieldCapabilitiesController {

    @Autowired
    private FieldCapabilitiesService fieldCapabilitiesService;

    // 同步方式执行MultiSearchRequest
    @RequestMapping("/sr")
    public String executeFieldSearchRequest(String field, String indices) {
        // 参数校验
        if (Strings.isNullOrEmpty(field) || Strings.isNullOrEmpty(indices)) {
            return "Parameters are wrong!";
        }

        // 将英文逗号分隔的字符串切分成数组
        List<String> indicesList = Splitter.on(",").splitToList(indices);
        fieldCapabilitiesService.executeFieldCapabilitiesRequest(field,
                        indicesList.toArray(new String[indicesList.size()]));
        return "Execute FieldSearchRequest success!";
    }
}
