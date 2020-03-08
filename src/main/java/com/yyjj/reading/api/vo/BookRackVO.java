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
public class BookRackVO implements Serializable {

    private static final long serialVersionUID = 1L;
    
     
    public static BookRackVO newInstance(BookRack  bookrack) {
        if(Objects.isNull( bookrack)) {
  	    return null;
  	  }        
         BookRackVO bookrackVO = new BookRackVO();
        BeanUtils.copyProperties( bookrack,  bookrackVO);
        return  bookrackVO;
  	}
    
    public static BookRackVO newInstance(BookRackBO  bookrack) {
        if(Objects.isNull( bookrack)) {
  	    return null;
  	  }        
         BookRackVO bookrackVO = new BookRackVO();
        BeanUtils.copyProperties( bookrack,  bookrackVO);
        return  bookrackVO;
  	}
  	    
  	public  BookRack convert() {
  		 BookRack  bookrack = new  BookRack();
  	  BeanUtils.copyProperties(this,  bookrack);
  	  return  bookrack;
  	}
  	
  	public  BookRackBO  BookRackBO() {
  		 BookRackBO  bookrack = new  BookRackBO();
  	  BeanUtils.copyProperties(this,  bookrack);
  	  return  bookrack;
  	}
  	
  }