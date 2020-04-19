package com.yyjj.reading.db.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * <p>
 * 
 * </p>
 *
 * @author yml
 * @since 2020-03-08
 */
public class Chapter implements Serializable,Comparable<Chapter> {

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
     * 章节标题
     */
    private String title;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
            ", createTime=" + createTime + ", title=" + title +
        "}";
    }

    @Override
    public int compareTo(Chapter o) {
        if(this.sort.equals("") || Objects.isNull(this.sort)){
            return -1;
        }
        if(o.getSort().equals("") || Objects.isNull(o.getSort())){
            return 1;
        }
        Integer sort1 =  Integer.parseInt(this.sort);
        Integer sort2 = Integer.parseInt(o.getSort());
        return sort1.compareTo(sort2);
    }
}
