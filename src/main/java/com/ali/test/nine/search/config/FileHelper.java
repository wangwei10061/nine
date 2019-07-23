package com.ali.test.nine.search.config;

import java.util.ArrayList;
import java.util.List;

public class FileHelper {

    public static List<String> fileList() {
        List<String> filePath = new ArrayList<>();
        filePath.add("file/section-01/section.rst.txt");
        filePath.add("file/section-01/subsection-01.rst.txt");
        filePath.add("file/section-01/subsection-02.rst.txt");
        filePath.add("file/section-01/subsection-03.rst.txt");

        filePath.add("file/section-02/section.rst.txt");
        filePath.add("file/section-02/subsection-01.rst.txt");
        filePath.add("file/section-02/subsection-02.rst.txt");

        filePath.add("file/index.rst.txt");
        return filePath;
    }
}
