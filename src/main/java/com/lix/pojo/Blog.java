package com.lix.pojo;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name = "t_blog")
public class Blog {

    @Column(name = "id")
    private Integer id; // 编号

    @Column(name = "title")
    private String title; // 博客标题

    @Column(name = "summary")
    private String summary; // 摘要

    @Column(name = "releaseDate")
    private Date releaseDate; // 发布日期

    @Column(name = "clickHit")
    private Integer clickHit; // 查看次数

    @Column(name = "replyHit")
    private Integer replyHit; // 回复次数

    @Column(name = "content")
    private String content; // 博客内容

    @Transient
    private String contentNoTag; // 博客内容 无网页标签 Lucene分词用

    @Transient
    private BlogType blogType; // 博客类型

    @Transient
    private Integer blogCount; // 博客数量 非博客实际属性，主要是 根据发布日期归档查询博客数量用

    @Transient
    private String releaseDateStr; // 发布日期字符串 只取年和月

    @Transient
    private String keyWord; // 关键字 空格隔开

    @Transient
    private List<String> imagesList = new LinkedList<String>(); // 博客里存在的图片 主要用于列表展示显示缩略图

    public Blog() {
        super();
    }

    public Blog(Integer id, String title, String summary, Date releaseDate, Integer clickHit,
            Integer replyHit, String content, String contentNoTag, BlogType blogType, Integer blogCount,
            String releaseDateStr, String keyWord, List<String> imagesList) {
        super();
        this.id = id;
        this.title = title;
        this.summary = summary;
        this.releaseDate = releaseDate;
        this.clickHit = clickHit;
        this.replyHit = replyHit;
        this.content = content;
        this.contentNoTag = contentNoTag;
        this.blogType = blogType;
        this.blogCount = blogCount;
        this.releaseDateStr = releaseDateStr;
        this.keyWord = keyWord;
        this.imagesList = imagesList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getClickHit() {
        return clickHit;
    }

    public void setClickHit(Integer clickHit) {
        this.clickHit = clickHit;
    }

    public Integer getReplyHit() {
        return replyHit;
    }

    public void setReplyHit(Integer replyHit) {
        this.replyHit = replyHit;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentNoTag() {
        return contentNoTag;
    }

    public void setContentNoTag(String contentNoTag) {
        this.contentNoTag = contentNoTag;
    }

    public BlogType getBlogType() {
        return blogType;
    }

    public void setBlogType(BlogType blogType) {
        this.blogType = blogType;
    }

    public Integer getBlogCount() {
        return blogCount;
    }

    public void setBlogCount(Integer blogCount) {
        this.blogCount = blogCount;
    }

    public String getReleaseDateStr() {
        return releaseDateStr;
    }

    public void setReleaseDateStr(String releaseDateStr) {
        this.releaseDateStr = releaseDateStr;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public List<String> getImagesList() {
        return imagesList;
    }

    public void setImagesList(List<String> imagesList) {
        this.imagesList = imagesList;
    }

}
