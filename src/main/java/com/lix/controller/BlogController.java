package com.lix.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lix.lucene.BlogIndex;
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

    // 博客索引
    private BlogIndex blogIndex = new BlogIndex();

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
     * 根据关键字查询相关博客信息
     * 
     * @param q
     * @return
     * @throws Exception
     */
    @RequestMapping("/q")
    public String search(@RequestParam(value = "q", required = false) String q,
            @RequestParam(value = "page", required = false) String page, ModelMap modelMap,
            HttpServletRequest request) throws Exception {
        if (StringUtils.isEmpty(page)) {
            page = "1";
        }
        modelMap.addAttribute("mainPage", "foreground/blog/result.jsp");
        List<Blog> blogList = blogIndex.searchBlog(q.trim());
        Integer toIndex = blogList.size() >= Integer.parseInt(page) * 10 ? Integer.parseInt(page) * 10
                : blogList.size();
        modelMap.addAttribute("blogList", blogList.subList((Integer.parseInt(page) - 1) * 10, toIndex));
        modelMap.addAttribute("pageCode", this.genUpAndDownPageCode(Integer.parseInt(page), blogList.size(),
                q, 10, request.getServletContext().getContextPath()));
        modelMap.addAttribute("q", q);
        modelMap.addAttribute("resultTotal", blogList.size());
        modelMap.addAttribute("pageTitle", "搜索关键字'" + q + "'结果页面_Java博客系统");
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

    /**
     * 获取上一页，下一页代码 查询博客用到
     * 
     * @param page 当前页
     * @param totalNum 总记录数
     * @param q 查询关键字
     * @param pageSize 每页大小
     * @param projectContext
     * @return
     */
    private String genUpAndDownPageCode(Integer page, Integer totalNum, String q, Integer pageSize,
            String projectContext) {
        long totalPage = totalNum % pageSize == 0 ? totalNum / pageSize : totalNum / pageSize + 1;
        StringBuffer pageCode = new StringBuffer();
        if (totalPage == 0) {
            return "";
        } else {
            pageCode.append("<nav>");
            pageCode.append("<ul class='pager' >");
            if (page > 1) {
                pageCode.append("<li><a href='" + projectContext + "/blog/q.html?page=" + (page - 1) + "&q="
                        + q + "'>上一页</a></li>");
            } else {
                pageCode.append("<li class='disabled'><a href='#'>上一页</a></li>");
            }
            if (page < totalPage) {
                pageCode.append("<li><a href='" + projectContext + "/blog/q.html?page=" + (page + 1) + "&q="
                        + q + "'>下一页</a></li>");
            } else {
                pageCode.append("<li class='disabled'><a href='#'>下一页</a></li>");
            }
            pageCode.append("</ul>");
            pageCode.append("</nav>");
        }
        return pageCode.toString();
    }
}
