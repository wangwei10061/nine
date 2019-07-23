package com.ali.test.nine.search.service;


import com.ali.test.nine.search.bean.ResultBean;

import java.util.List;

public interface SearchKeywordService {
    List<ResultBean> getSearchResult(String keyword);
}
