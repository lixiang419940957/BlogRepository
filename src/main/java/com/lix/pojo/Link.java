package com.lix.pojo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "t_link")
public class Link {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 编号

    @Column(name = "linkName")
    private String linkName; // 链接名称

    @Column(name = "linkUrl")
    private String linkUrl; // 链接地址

    @Column(name = "orderNo")
    private Integer orderNo; // 排序序号 从小到大排序

    
    public Link() {
        super();
    }

    public Link(Integer id, String linkName, String linkUrl, Integer orderNo) {
        super();
        this.id = id;
        this.linkName = linkName;
        this.linkUrl = linkUrl;
        this.orderNo = orderNo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    @Override
    public String toString() {
        return "Link [id=" + id + ", linkName=" + linkName + ", linkUrl=" + linkUrl + ", orderNo=" + orderNo
                + "]";
    }

}
