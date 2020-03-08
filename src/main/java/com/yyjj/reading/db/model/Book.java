package com.yyjj.reading.db.model;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author yml
 * @since 2020-03-08
 */
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 书籍id
     */
    private Integer id;

    /**
     * 书籍名称
     */
    private String name;

    /**
     * 作者
     */
    private String author;

    private String publishingHouse;

    private LocalDateTime publicationTime;

    /**
     * 简介
     */
    private String synopsis;

    /**
     * 字数
     */
    private Integer numberWorders;

    /**
     * 封面
     */
    private String cover;

    /**
     * 书籍状态
     */
    private Integer status;

    private LocalDateTime createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    public String getPublishingHouse() {
        return publishingHouse;
    }

    public void setPublishingHouse(String publishingHouse) {
        this.publishingHouse = publishingHouse;
    }
    public LocalDateTime getPublicationTime() {
        return publicationTime;
    }

    public void setPublicationTime(LocalDateTime publicationTime) {
        this.publicationTime = publicationTime;
    }
    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }
    public Integer getNumberWorders() {
        return numberWorders;
    }

    public void setNumberWorders(Integer numberWorders) {
        this.numberWorders = numberWorders;
    }
    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Book{" +
            "id=" + id +
            ", name=" + name +
            ", author=" + author +
            ", publishingHouse=" + publishingHouse +
            ", publicationTime=" + publicationTime +
            ", synopsis=" + synopsis +
            ", numberWorders=" + numberWorders +
            ", cover=" + cover +
            ", status=" + status +
            ", createTime=" + createTime +
        "}";
    }
}
