package com.lix.service;

import java.util.List;
import java.util.Map;

import com.lix.pojo.Blog;

public interface BlogService {

    public Integer queryBlogCountByTypeId(Integer BlogTypeId);

    public Boolean saveBlog(Blog blog);

    public List<Blog> queryBlogList(Map<String, Object> map);

    public Integer queryBlogListTotal(Map<String, Object> map);

    public Blog queryBlogById(String id);

    public Boolean updateBlog(Blog blog);

    public Boolean deleteBlog(String[] ids);
    
    public List<Blog> queryCountList();
    
}
