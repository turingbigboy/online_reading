package com.yyjj.reading.api.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.yyjj.reading.db.model.BookType;
import com.yyjj.reading.service.bo.BookTypeBO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
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
public class BookTypeVO implements Serializable {

    private static final long serialVersionUID = 1L;

	/**
	 * 书籍类型id
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	/**
	 * 书籍id
	 */
	private Integer bookId;

	/**
	 * 书籍
	 */
	private BookVO book;

	/**
	 * 类型
	 */
	private TypeVO type;
	/**
	 * 类型id
	 */
	private Integer typeId;
    public static BookTypeVO newInstance(BookType booktype) {
        if(Objects.isNull( booktype)) {
  	    return null;
  	  }        
         BookTypeVO booktypeVO = new BookTypeVO();
        BeanUtils.copyProperties( booktype,  booktypeVO);
        return  booktypeVO;
  	}
    
    public static BookTypeVO newInstance(BookTypeBO booktype) {
        if(Objects.isNull( booktype)) {
  	    return null;
  	  }        
         BookTypeVO booktypeVO = new BookTypeVO();
        BeanUtils.copyProperties( booktype,  booktypeVO);
        return  booktypeVO;
  	}
  	    
  	public  BookType convert() {
  		 BookType  booktype = new  BookType();
  	  BeanUtils.copyProperties(this,  booktype);
  	  return  booktype;
  	}
  	
  	public  BookTypeBO  BookTypeBO() {
  		 BookTypeBO  booktype = new  BookTypeBO();
  	  BeanUtils.copyProperties(this,  booktype);
  	  return  booktype;
  	}
  	
  }