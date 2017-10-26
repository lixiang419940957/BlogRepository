package com.lix.dao;

import java.util.List;

import com.lix.pojo.BlogType;

import tk.mybatis.mapper.common.Mapper;

public interface BlogTypeDao extends Mapper<BlogType> {
    /**
     * 获取所有博客类型名称及其相关数量，并按照博客类别排序编码正序排序
     * 
     * @return
     */
    public List<BlogType> queryCountList();

    /**
     * 通过id查询博客类型
     * 
     * @return
     */
    public BlogType queryBlogTypeById();

}
