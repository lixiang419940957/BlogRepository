package com.lix.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lix.pojo.Blog;
import com.lix.service.BlogService;
import com.lix.utils.PageBean;
import com.lix.utils.PageUtil;

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
    public String index(@RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "typeId", required = false) String typeId,
            @RequestParam(value = "releaseDateStr", required = false) String releaseDateStr,
            ModelMap modelMap, HttpServletRequest request) {

        if (StringUtils.isEmpty(page)) {
            page = "1";
        }
        PageBean pageBean = new PageBean(Integer.parseInt(page), 10);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());
        map.put("typeId", typeId);
        map.put("releaseDateStr", releaseDateStr);
        List<Blog> blogList = this.blogService.queryBlogList(map);
        for (Blog blog : blogList) {
            List<String> imagesList = blog.getImagesList();
            String blogInfo = blog.getContent();
            Document doc = Jsoup.parse(blogInfo);
            Elements jpgs = doc.select("img[src$=.jpg]"); // 　查找扩展名是jpg的图片
            for (int i = 0; i < jpgs.size(); i++) {
                Element jpg = jpgs.get(i);
                imagesList.add(jpg.toString());
                if (i == 2) {
                    break;
                }
            }
        }
        modelMap.addAttribute("blogList", blogList);
        StringBuffer param = new StringBuffer(); // 查询参数
        if (StringUtils.isNotEmpty(typeId)) {
            param.append("typeId=" + typeId + "&");
        }
        if (StringUtils.isNotEmpty(releaseDateStr)) {
            param.append("releaseDateStr=" + releaseDateStr + "&");
        }
        modelMap.addAttribute("pageCode", PageUtil.genPagination(request.getContextPath() + "/index.html",
                blogService.queryBlogListTotal(map), Integer.parseInt(page), 10, param.toString()));
        modelMap.addAttribute("mainPage", "foreground/blog/list.jsp");
        modelMap.addAttribute("pageTitle", "Java开源博客系统");
        return "main";
    }

}
