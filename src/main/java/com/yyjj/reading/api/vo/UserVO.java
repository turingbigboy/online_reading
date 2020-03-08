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
public class UserVO implements Serializable {

    private static final long serialVersionUID = 1L;
    
     
    public static UserVO newInstance(User  user) {
        if(Objects.isNull( user)) {
  	    return null;
  	  }        
         UserVO userVO = new UserVO();
        BeanUtils.copyProperties( user,  userVO);
        return  userVO;
  	}
    
    public static UserVO newInstance(UserBO  user) {
        if(Objects.isNull( user)) {
  	    return null;
  	  }        
         UserVO userVO = new UserVO();
        BeanUtils.copyProperties( user,  userVO);
        return  userVO;
  	}
  	    
  	public  User convert() {
  		 User  user = new  User();
  	  BeanUtils.copyProperties(this,  user);
  	  return  user;
  	}
  	
  	public  UserBO  UserBO() {
  		 UserBO  user = new  UserBO();
  	  BeanUtils.copyProperties(this,  user);
  	  return  user;
  	}
  	
  }