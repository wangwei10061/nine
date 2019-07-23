package com.ali.test.nine.search.controller;

import com.ali.test.nine.search.bean.ResultBean;
import com.ali.test.nine.search.service.SearchKeywordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class SearchKeywordController {

    @Autowired
    SearchKeywordService searchKeywordService;

    @RequestMapping("/search")
    public List<ResultBean> searchKeyword(@RequestParam(name = "keyword") String keyword) {
        return searchKeywordService.getSearchResult(keyword);
    }

}
