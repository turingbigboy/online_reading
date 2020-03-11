package com.yyjj.reading.api.vo;

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