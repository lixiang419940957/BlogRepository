package com.lix.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lix.pojo.Blog;
import com.lix.service.BlogService;
import com.lix.util.EasyUIResult;
import com.lix.util.PageBean;

@RequestMapping("/admin/blog")
@Controller
public class BlogAdminController {

    @Autowired
    private BlogService blogService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> save(Blog blog) {
        Boolean flag = null; // 保存或更新成功标识
        if (blog.getId() == null) {
            flag = this.blogService.saveBlog(blog);
        } else {
            flag = this.blogService.updateBlog(blog);
        }
        HashMap<String, Object> result = new HashMap<String, Object>();
        if (flag) {
            result.put("success", true);
        } else {
            result.put("false", false);
        }

        return result;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public EasyUIResult queryBlogList(@RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "rows", required = false) String rows, Blog s_blog) {

        PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("title", s_blog.getTitle());
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());
        List<Blog> list = this.blogService.queryBlogList(map);
        Integer total = this.blogService.queryBlogListTotal(map);

        return new EasyUIResult(total, list);
    }
    
    @RequestMapping(value = "/queryBlogById", method = RequestMethod.GET)
    @ResponseBody
    public Blog queryBlogById(@RequestParam(value = "id", required = false) String id){
        Blog blog = this.blogService.queryBlogById(id);
        return blog;
    }
    
}
