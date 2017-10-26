package com.lix.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lix.service.BlogService;

/**
 * 主页控制器
 * 
 * @author lix
 *
 */
@RequestMapping("/")
@Controller
public class IndexController {
    
    @Autowired
    private BlogService blogService;

    @RequestMapping("/index")
    public String index(ModelMap map, @RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "typeId", required = false) String typeId,
            @RequestParam(value = "releaseDateStr", required = false) String releaseDateStr,
            ModelMap modelMap, HttpServletRequest request) {
        
        if(StringUtils.isEmpty(page)){
            page = "1";
        }
        
        return null;
    }

}
