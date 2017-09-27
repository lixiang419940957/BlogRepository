package com.lix.pojo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

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

	// 博客数量
	@Transient
	private Integer blogCount;

	public BlogType() {
		super();
	}

	public BlogType(Integer id, String typeName, Integer orderNum, Integer blogCount) {
		super();
		this.id = id;
		this.typeName = typeName;
		this.orderNum = orderNum;
		this.blogCount = blogCount;
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

	public Integer getBlogCount() {
		return blogCount;
	}

	public void setBlogCount(Integer blogCount) {
		this.blogCount = blogCount;
	}

	@Override
	public String toString() {
		return "BlogType [id=" + id + ", typeName=" + typeName + ", orderNum=" + orderNum + ", blogCount=" + blogCount
				+ "]";
	}

}
