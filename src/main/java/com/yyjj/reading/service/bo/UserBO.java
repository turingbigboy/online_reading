package com.yyjj.reading.service.bo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.oudot.asset.manage.db.model.model.User;
import com.oudot.asset.manage.service.bo.base.IBaseBO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserBO implements IBaseBO<User>{
	
	@Override
	public void accpet(QueryWrapper<User> queryWrapper) {
		
	}
	
	public  User convert() {
  		 User  user = new  User();
  	  BeanUtils.copyProperties(this,  user);
  	  return  user;
  	}
}
    