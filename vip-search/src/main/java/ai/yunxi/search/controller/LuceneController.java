package ai.yunxi.search.controller;

import ai.yunxi.search.model.FileInfo;
import ai.yunxi.search.service.LuceneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class LuceneController {
    @Autowired
    private LuceneService service;

    @RequestMapping(value = "/hello")
    public String hello() {
        return "index";
    }

    @RequestMapping(value = "/search")
    public String search(String keyword, Model model) {
        int num = 10;
        List<FileInfo> list = service.getDocs("content", keyword, 15, num);
        model.addAttribute("docList", list);
        return "result";
    }
}
