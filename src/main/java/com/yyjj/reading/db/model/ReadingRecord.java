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
public class ReadingRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 记录id
     */
    private Integer id;

    /**
     * 书籍id
     */
    private Integer bookId;

    /**
     * 书籍名称
     */
    private String bookName;

    /**
     * 书籍封面
     */
    private String bookCover;

    /**
     * 书籍作者
     */
    private String bookAuthor;

    /**
     * 章节id
     */
    private Integer chapterId;

    /**
     * 章节名称
     */
    private String chapterName;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 记录时间
     */
    private LocalDateTime redcordTime;

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
    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
    public String getBookCover() {
        return bookCover;
    }

    public void setBookCover(String bookCover) {
        this.bookCover = bookCover;
    }
    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }
    public Integer getChapterId() {
        return chapterId;
    }

    public void setChapterId(Integer chapterId) {
        this.chapterId = chapterId;
    }
    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public LocalDateTime getRedcordTime() {
        return redcordTime;
    }

    public void setRedcordTime(LocalDateTime redcordTime) {
        this.redcordTime = redcordTime;
    }

    @Override
    public String toString() {
        return "ReadingRecord{" +
            "id=" + id +
            ", bookId=" + bookId +
            ", bookName=" + bookName +
            ", bookCover=" + bookCover +
            ", bookAuthor=" + bookAuthor +
            ", chapterId=" + chapterId +
            ", chapterName=" + chapterName +
            ", userId=" + userId +
            ", redcordTime=" + redcordTime +
        "}";
    }
}
