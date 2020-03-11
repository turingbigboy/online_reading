package com.yyjj.reading.service.bo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yyjj.reading.db.model.BookType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
public class BookTypeBO implements IBaseBO<BookType>{
	
	@Override
	public void accpet(QueryWrapper<BookType> queryWrapper) {
		
	}
	
	public  BookType convert() {
  		 BookType  booktype = new  BookType();
  	  BeanUtils.copyProperties(this,  booktype);
  	  return  booktype;
  	}
}
    