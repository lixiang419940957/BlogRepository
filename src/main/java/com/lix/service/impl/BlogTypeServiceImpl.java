package com.lix.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tk.mybatis.mapper.entity.Example;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lix.dao.BlogTypeDao;
import com.lix.pojo.BlogType;
import com.lix.service.BlogTypeService;
import com.lix.utils.EasyUIResult;

@Service
public class BlogTypeServiceImpl extends BaseService<BlogType> implements BlogTypeService {

    @Autowired
    private BlogTypeDao blogTypeDao;

    public EasyUIResult queryBlogTypeList(Integer page, Integer rows) {

        // 设置分页参数
        PageHelper.startPage(page, rows);

        Example example = new Example(BlogType.class);
        // 按照创建时间排序
        example.setOrderByClause("orderNum ASC");
        List<BlogType> list = this.blogTypeDao.selectByExample(example);

        PageInfo<BlogType> pageInfo = new PageInfo<BlogType>(list);

        return new EasyUIResult(pageInfo.getTotal(), pageInfo.getList());
    }

    public Boolean saveBlogType(BlogType blogType) {

        Integer count = super.save(blogType);

        return count == 1;
    }

    public Boolean updateBlogType(BlogType blogType) {
        Integer count = super.updateSelective(blogType);
        return count == 1;
    }

    public Boolean deleteBlogType(Integer id) {
        Integer count = super.deleteById(id);
        return count == 1;
    }

    public BlogType queryBlogTypeById(Integer id) {
        return super.queryById(id);
    }

    public List<BlogType> queryBlogTypeComboBox() {

        Example example = new Example(BlogType.class);
        example.setOrderByClause("orderNum ASC");

        return this.blogTypeDao.selectByExample(example);
    }

	@Override
	public List<BlogType> queryCountList() {
		return this.blogTypeDao.queryCountList();
	}

}
