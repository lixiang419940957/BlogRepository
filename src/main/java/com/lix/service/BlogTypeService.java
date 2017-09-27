package com.lix.service;

import java.util.List;

import com.lix.pojo.BlogType;
import com.lix.utils.EasyUIResult;

public interface BlogTypeService {

	public EasyUIResult queryBlogTypeList(Integer page, Integer rows);

	public Boolean saveBlogType(BlogType blogType);

	public Boolean updateBlogType(BlogType blogType);

	public Boolean deleteBlogType(Integer id);

	public BlogType queryBlogTypeById(Integer id);

	public List<BlogType> queryBlogTypeComboBox();

	/**
	 * 获取所有博客类型名称及其相关数量，并按照博客类别排序编码正序排序
	 * 
	 * @return
	 */
	public List<BlogType> queryCountList();
}
