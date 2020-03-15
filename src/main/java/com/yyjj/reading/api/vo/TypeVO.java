package com.yyjj.reading.api.vo;

import com.yyjj.reading.db.model.Type;
import com.yyjj.reading.service.bo.TypeBO;
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
public class TypeVO implements Serializable {

    private static final long serialVersionUID = 1L;
	/**
	 * 类型id
	 */
	private Integer id;

	/**
	 * 类型名称
	 */
	private String typeName;
     
    public static TypeVO newInstance(Type type) {
        if(Objects.isNull( type)) {
  	    return null;
  	  }        
         TypeVO typeVO = new TypeVO();
        BeanUtils.copyProperties( type,  typeVO);
        return  typeVO;
  	}
    
    public static TypeVO newInstance(TypeBO type) {
        if(Objects.isNull( type)) {
  	    return null;
  	  }        
         TypeVO typeVO = new TypeVO();
        BeanUtils.copyProperties( type,  typeVO);
        return  typeVO;
  	}
  	    
  	public  Type convert() {
  		 Type  type = new  Type();
  	  BeanUtils.copyProperties(this,  type);
  	  return  type;
  	}
  	
  	public  TypeBO  TypeBO() {
  		 TypeBO  type = new  TypeBO();
  	  BeanUtils.copyProperties(this,  type);
  	  return  type;
  	}
  	
  }