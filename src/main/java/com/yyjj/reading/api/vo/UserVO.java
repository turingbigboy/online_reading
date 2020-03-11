package com.yyjj.reading.api.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.yyjj.reading.db.model.User;
import com.yyjj.reading.service.bo.UserBO;
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
	/**
	 * 用户id
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	/**
	 * 用户姓名
	 */
	private String name;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 用户账号
	 */
	private String account;

	/**
	 * 用户头像
	 */
	private String icon;

	/**
	 * 用户身份
	 */
	private Integer identity;
     
    public static UserVO newInstance(User user) {
        if(Objects.isNull( user)) {
  	    return null;
  	  }        
         UserVO userVO = new UserVO();
        BeanUtils.copyProperties( user,  userVO);
        return  userVO;
  	}
    
    public static UserVO newInstance(UserBO user) {
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