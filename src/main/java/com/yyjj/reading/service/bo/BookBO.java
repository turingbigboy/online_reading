package com.yyjj.reading.service.bo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yyjj.reading.db.model.Book;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
public class BookBO implements IBaseBO<Book>{
	
	@Override
	public void accpet(QueryWrapper<Book> queryWrapper) {
		queryWrapper.lambda().inSql(Book::getId,"select book_id from book_rack GROUP BY book_id  ORDER BY  count(*) DESC");
	}
	
	public  Book convert() {
  		 Book  book = new  Book();
  	  BeanUtils.copyProperties(this,  book);
  	  return  book;
  	}
}
    