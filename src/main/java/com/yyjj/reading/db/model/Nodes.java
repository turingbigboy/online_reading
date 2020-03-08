package com.yyjj.reading.db.model;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author yml
 * @since 2020-03-08
 */
public class Nodes implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 书籍id
     */
    private Integer bookId;

    /**
     * 章节id
     */
    private Integer chapter;

    /**
     * 笔记起始位置
     */
    private Integer nodeBeginLocation;

    /**
     * 笔记结束位置
     */
    private Integer nodeEndLocation;

    /**
     * 笔记内容
     */
    private String nodeContent;

    private String comment;

    /**
     * 用户id
     */
    private Integer userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }
    public Integer getChapter() {
        return chapter;
    }

    public void setChapter(Integer chapter) {
        this.chapter = chapter;
    }
    public Integer getNodeBeginLocation() {
        return nodeBeginLocation;
    }

    public void setNodeBeginLocation(Integer nodeBeginLocation) {
        this.nodeBeginLocation = nodeBeginLocation;
    }
    public Integer getNodeEndLocation() {
        return nodeEndLocation;
    }

    public void setNodeEndLocation(Integer nodeEndLocation) {
        this.nodeEndLocation = nodeEndLocation;
    }
    public String getNodeContent() {
        return nodeContent;
    }

    public void setNodeContent(String nodeContent) {
        this.nodeContent = nodeContent;
    }
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Nodes{" +
            "id=" + id +
            ", bookId=" + bookId +
            ", chapter=" + chapter +
            ", nodeBeginLocation=" + nodeBeginLocation +
            ", nodeEndLocation=" + nodeEndLocation +
            ", nodeContent=" + nodeContent +
            ", comment=" + comment +
            ", userId=" + userId +
        "}";
    }
}
