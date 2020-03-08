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
public class BookRack implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 书架id
     */
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 书籍id
     */
    private Integer bookId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    @Override
    public String toString() {
        return "BookRack{" +
            "id=" + id +
            ", userId=" + userId +
            ", bookId=" + bookId +
        "}";
    }
}
