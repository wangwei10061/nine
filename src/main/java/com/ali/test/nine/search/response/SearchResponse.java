package com.ali.test.nine.search.response;



import com.ali.test.nine.search.bean.ResultBean;

import java.util.List;

public class SearchResponse {
    List<ResultBean> data;

    public List<ResultBean> getData() {
        return data;
    }

    public void setData(List<ResultBean> data) {
        this.data = data;
    }
}
