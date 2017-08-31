package com.lix.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

public class BaseService<T> {

    @Autowired
    private Mapper<T> mapper;

    /**
     * 根据id查询数据
     * 
     * @param id
     * @return
     */
    public T queryById(Integer id) {
        return this.mapper.selectByPrimaryKey(id);
    }

    /**
     * 查询所有数据
     * 
     * @return
     */
    public List<T> queryAll() {
        return this.mapper.select(null);
    }

    /**
     * 根据条件查询一条数据,如果有多条数据会抛出异常
     * 
     * @param record
     * @return
     */
    public T queryOne(T record) {
        return this.mapper.selectOne(record);
    }

    /**
     * 根据条件查询数据列表
     * 
     * @param record
     * @return
     */
    public List<T> queryListByWhere(T record) {
        return this.mapper.select(record);
    }

    /**
     * 分页查询
     * 
     * @param page 页数
     * @param rows 一页查询多少条
     * @param record
     * @return
     */
    public PageInfo<T> queryPageListByWhere(Integer page, Integer rows, T record) {
        // 设置分页条件 page页数 rows一页查询多少条
        PageHelper.startPage(page, rows);
        List<T> list = this.mapper.select(record);
        return new PageInfo<T>(list);
    }

    /**
     * 新增数据,返回成功的条数
     * 
     * @param record
     * @return
     */
    public Integer save(T record) {
//        record.setCreated(new Date());
//        record.setUpdated(record.getCreated());
        return this.mapper.insert(record);
    }

    /**
     * 新增数据，使用不为null的字段，返回成功的条数
     * 
     * @param record
     * @return
     */
    public Integer saveSelective(T record) {
//        record.setCreated(new Date());
//        record.setUpdated(record.getCreated());
        return this.mapper.insertSelective(record);
    }

    /**
     * 更新数据,返回成功的条数
     * 
     * @param record
     * @return
     */
    public Integer update(T record) {
//        record.setUpdated(new Date());
        return this.mapper.updateByPrimaryKey(record);
    }

    /**
     * 更新数据,使用不为null的字段,返回成功的条数
     * 
     * @param record
     * @return
     */
    public Integer updateSelective(T record) {
//        record.setUpdated(new Date());
//        record.setCreated(null); // 强制设置创建时间为空,永远不会被更新
        return this.mapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 根据id删除数据（物理删除）
     * 
     * @param id
     * @return
     */
    public Integer deleteById(Integer id) {
        return this.mapper.deleteByPrimaryKey(id);
    }

    /**
     * 批量删除（物理删除）
     * 
     * @param clazz
     * @param property
     * @param values
     * @return
     */
    public Integer deleteByIds(Class<T> clazz, String property, List<Object> ids) {
        Example example = new Example(clazz);
        example.createCriteria().andIn(property, ids);
        return this.mapper.deleteByExample(example);
    }

    /**
     * 根据条件删除（物理删除）
     * 
     * @param record
     * @return
     */
    public Integer deleteByWhere(T record) {
        return this.mapper.delete(record);
    }
}
