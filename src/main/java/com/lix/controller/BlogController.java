package com.lix.controller;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lix.pojo.Blog;
import com.lix.service.BlogService;

/**
 * 博客控制层
 * 
 * @author lix
 *
 */
@RequestMapping("/blog")
@Controller
public class BlogController {

    @Autowired
    private BlogService blogService;

    @RequestMapping("/articles/{id}")
    public String details(@PathVariable("id") String id, ModelMap modelMap, HttpServletRequest request) {

        Blog blog = this.blogService.queryBlogById(id);
        String keyWords = blog.getKeyWord();
        if (StringUtils.isNotEmpty(keyWords)) {
            String[] keyWordsArr = keyWords.split(" ");
            modelMap.addAttribute("keyWords",
                    com.lix.utils.StringUtils.filterWhite(Arrays.asList(keyWordsArr)));
        } else {
            modelMap.addAttribute("keyWords", null);
        }
        modelMap.addAttribute("blog", blog);
        blog.setClickHit(blog.getClickHit() + 1);
        this.blogService.updateBlog(blog);
        modelMap.addAttribute("pageCode", this.genUpAndDownPageCode(
                this.blogService.queryLastBlog(blog.getId()), this.blogService.queryNextBlog(blog.getId()),
                request.getServletContext().getContextPath()));
        modelMap.addAttribute("mainPage", "foreground/blog/view.jsp");
        modelMap.addAttribute("pageTitle", blog.getTitle() + "_Java博客系统");
        return "main";
    }

    /**
     * 获取下一篇博客和下一篇博客代码
     * 
     * @param lastBlog
     * @param nextBlog
     * @return
     */
    private String genUpAndDownPageCode(Blog lastBlog, Blog nextBlog, String projectContext) {
        StringBuffer pageCode = new StringBuffer();
        if (lastBlog == null || lastBlog.getId() == null) {
            pageCode.append("<p>上一篇：没有了</p>");
        } else {
            pageCode.append("<p>上一篇：<a href='" + projectContext + "/blog/articles/" + lastBlog.getId()
                    + ".html'>" + lastBlog.getTitle() + "</a></p>");
        }
        if (nextBlog == null || nextBlog.getId() == null) {
            pageCode.append("<p>下一篇：没有了</p>");
        } else {
            pageCode.append("<p>下一篇：<a href='" + projectContext + "/blog/articles/" + nextBlog.getId()
                    + ".html'>" + nextBlog.getTitle() + "</a></p>");
        }
        return pageCode.toString();
    }
}
