package com.lix.pojo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "t_blogtype")
public class BlogType {

    // 博客类别Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // 博客类别名称
    @Column(name = "typeName")
    private String typeName;

    // 博客排序编号
    @Column(name = "orderNum")
    private Integer orderNum;

    public BlogType() {
        super();
    }

    public BlogType(Integer id, String typeName, Integer orderNum) {
        super();
        this.id = id;
        this.typeName = typeName;
        this.orderNum = orderNum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    @Override
    public String toString() {
        return "BlogType [id=" + id + ", typeName=" + typeName + ", orderNum=" + orderNum + "]";
    }

}
