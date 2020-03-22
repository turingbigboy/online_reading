package com.yyjj.reading.api.vo;

import com.yyjj.reading.db.model.Book;
import com.yyjj.reading.service.bo.BookBO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * <p>
 * 
 * </p>
 *
 * @author yml
 * 
 */
@Getter
@Setter
public class BookVO implements Serializable {

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
	/**
	 * 出版社
	 */
	private String publishingHouse;

	/**
	 * 出版时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
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
	/**
	 * 创建时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	private LocalDateTime createTime;
     
    public static BookVO newInstance(Book book) {
        if(Objects.isNull( book)) {
  	    return null;
  	  }        
         BookVO bookVO = new BookVO();
        BeanUtils.copyProperties( book,  bookVO);
        return  bookVO;
  	}
    
    public static BookVO newInstance(BookBO book) {
        if(Objects.isNull( book)) {
  	    return null;
  	  }        
         BookVO bookVO = new BookVO();
        BeanUtils.copyProperties( book,  bookVO);
        return  bookVO;
  	}
  	    
  	public  Book convert() {
  		 Book  book = new  Book();
  	  BeanUtils.copyProperties(this,  book);
  	  return  book;
  	}
  	
  	public  BookBO  BookBO() {
  		 BookBO  book = new  BookBO();
  	  BeanUtils.copyProperties(this,  book);
  	  return  book;
  	}
  	
  }