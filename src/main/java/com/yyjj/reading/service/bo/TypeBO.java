package com.yyjj.reading.service.bo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yyjj.reading.db.model.Type;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
public class TypeBO implements IBaseBO<Type>{
	
	@Override
	public void accpet(QueryWrapper<Type> queryWrapper) {
		
	}
	
	public  Type convert() {
  		 Type  type = new  Type();
  	  BeanUtils.copyProperties(this,  type);
  	  return  type;
  	}
}
    