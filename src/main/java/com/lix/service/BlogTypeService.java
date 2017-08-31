package com.lix.service;

import java.util.List;

import com.lix.pojo.BlogType;
import com.lix.util.EasyUIResult;

public interface BlogTypeService {

    public EasyUIResult queryBlogTypeList(Integer page, Integer rows);
    
    public Boolean saveBlogType(BlogType blogType);
    
    public Boolean updateBlogType(BlogType blogType);
    
    public Boolean deleteBlogType(Integer id);
    
    public BlogType queryBlogTypeById(Integer id);

    public List<BlogType> queryBlogTypeComboBox();
}
