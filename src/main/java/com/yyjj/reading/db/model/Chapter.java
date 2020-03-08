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
public class Chapter implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 章节表
     */
    private Integer id;

    /**
     * 书籍名称
     */
    private String bookName;

    /**
     * 书籍id
     */
    private Integer bookId;

    /**
     * 字数
     */
    private String numberWorders;

    /**
     * 章节序号
     */
    private String sort;

    /**
     * 章节内容
     */
    private String content;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }
    public String getNumberWorders() {
        return numberWorders;
    }

    public void setNumberWorders(String numberWorders) {
        this.numberWorders = numberWorders;
    }
    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Chapter{" +
            "id=" + id +
            ", bookName=" + bookName +
            ", bookId=" + bookId +
            ", numberWorders=" + numberWorders +
            ", sort=" + sort +
            ", content=" + content +
            ", createTime=" + createTime +
        "}";
    }
}
