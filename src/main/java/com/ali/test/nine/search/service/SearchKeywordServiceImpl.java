package com.ali.test.nine.search.service;


import com.ali.test.nine.search.bean.ResultBean;
import com.ali.test.nine.search.config.FileHelper;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class SearchKeywordServiceImpl implements SearchKeywordService {

    //文件路径的列表

    @Override
    public List<ResultBean> getSearchResult(String keyword) {

        //最后结果列表
        List<ResultBean> results = new ArrayList<>();

        for (String filePath : FileHelper.fileList()) {
            String fileContext = loadFileContext(filePath);
            ResultBean resultBean = new ResultBean();

            if (fileContext.contains(keyword)) {
                //返回文件标题
                resultBean.setTitle(findFileTitle(filePath));
                //返回文件上下文内容
                resultBean.setRelateContext(findFileRelateContext(filePath, keyword));
                //返回文件路径
                resultBean.setFilePath(filePath);
                resultBean.setKeyword(keyword);

                results.add(resultBean);
            }

        }

        return results;
    }

    private String findFileRelateContext(String path, String keyword) {
        StringBuilder sb = new StringBuilder();
        sb.append("……");
        try {
            InputStream is = SearchKeywordServiceImpl.class.getClassLoader()
                    .getResourceAsStream(path);
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains(keyword)) {
                    sb.append(line);
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        sb.append("……");
        return sb.toString();
    }

    private String findFileTitle(String path) {
        StringBuilder sb = new StringBuilder();
        try {
            InputStream is = SearchKeywordServiceImpl.class.getClassLoader()
                    .getResourceAsStream(path);
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
                break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

    private String loadFileContext(String path) {
        StringBuilder sb = new StringBuilder();
        try {
            InputStream is = SearchKeywordServiceImpl.class.getClassLoader()
                    .getResourceAsStream(path);
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sb.toString();
    }
}
