package com.yyjj.reading.api.vo;

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
public class BookVO implements Serializable {

    private static final long serialVersionUID = 1L;
    
     
    public static BookVO newInstance(Book  book) {
        if(Objects.isNull( book)) {
  	    return null;
  	  }        
         BookVO bookVO = new BookVO();
        BeanUtils.copyProperties( book,  bookVO);
        return  bookVO;
  	}
    
    public static BookVO newInstance(BookBO  book) {
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