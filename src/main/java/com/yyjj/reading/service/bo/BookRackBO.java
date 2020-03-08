package com.yyjj.reading.service.bo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.oudot.asset.manage.db.model.model.BookRack;
import com.oudot.asset.manage.service.bo.base.IBaseBO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookRackBO implements IBaseBO<BookRack>{
	
	@Override
	public void accpet(QueryWrapper<BookRack> queryWrapper) {
		
	}
	
	public  BookRack convert() {
  		 BookRack  bookrack = new  BookRack();
  	  BeanUtils.copyProperties(this,  bookrack);
  	  return  bookrack;
  	}
}
    