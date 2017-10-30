package com.lix.utils;

import java.util.ArrayList;
import java.util.List;

public class StringUtils {
    /**
     * 过滤掉集合里的空格
     * 
     * @param list
     * @return
     */
    public static List<String> filterWhite(List<String> list) {
        List<String> resultList = new ArrayList<String>();
        for (String l : list) {
            if (org.apache.commons.lang3.StringUtils.isNotBlank(l)) {
                resultList.add(l);
            }
        }
        return resultList;
    }
}
