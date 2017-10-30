package com.lix.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import tk.mybatis.mapper.common.Mapper;

import com.lix.pojo.Blog;

public interface BlogDao extends Mapper<Blog> {

	public Integer queryBlogCountByTypeId(Integer BlogTypeId);

	public Integer saveBlog(Blog blog);

	public List<Blog> queryBlogList(Map<String, Object> map);

	public Integer queryBlogListTotal(Map<String, Object> map);

	public Blog queryBlogById(@Param("id") String id);

	public Integer updateBlog(Blog blog);

	public List<Blog> queryCountList();

	public Blog queryLastBlog(@Param("id") Integer id);

	public Blog queryNextBlog(@Param("id") Integer id);

}
