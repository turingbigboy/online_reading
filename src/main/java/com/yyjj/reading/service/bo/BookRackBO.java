package com.yyjj.reading.service.bo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yyjj.reading.db.model.BookRack;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

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
    