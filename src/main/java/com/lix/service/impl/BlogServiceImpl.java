package com.lix.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lix.dao.BlogDao;
import com.lix.pojo.Blog;
import com.lix.service.BlogService;

@Service
public class BlogServiceImpl extends BaseService<Blog> implements BlogService {

    @Autowired
    private BlogDao blogDao;

    public Integer queryBlogCountByTypeId(Integer BlogTypeId) {

        Integer count = this.blogDao.queryBlogCountByTypeId(BlogTypeId);

        return count;
    }

    public Boolean saveBlog(Blog blog) {

        Integer count = this.blogDao.saveBlog(blog);
        return count == 1;
    }

    public List<Blog> queryBlogList(Map<String, Object> map) {

        List<Blog> list = this.blogDao.queryBlogList(map);

        return list;
    }

    public Integer queryBlogListTotal(Map<String, Object> map) {
        Integer count = this.blogDao.queryBlogListTotal(map);
        return count;
    }

    public Blog queryBlogById(String id) {
        Blog blog = this.blogDao.queryBlogById(id);
        return blog;
    }

    public Boolean updateBlog(Blog blog) {
        Integer count = this.blogDao.updateBlog(blog);
        return count == 1;
    }

    public Boolean deleteBlog(String[] ids) {
        List<Object> list = new ArrayList<Object>();
        for (int i = 0; i < ids.length; i++) {
            list.add(ids[i]);
        }
        Integer count = super.deleteByIds(Blog.class, "id", list);
        return count == ids.length;
    }
}
