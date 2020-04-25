package com.yyjj.reading.service.bo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.yyjj.reading.db.model.Book;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
public class BookBO implements IBaseBO<Book>{
	/**
	 * 书籍id
	 */
	private Integer id;

	/**
	 * 书籍名称
	 */
	private String name;

	/**
	 * 作者
	 */
	private String author;
	/**
	 * 出版社
	 */
	private String publishingHouse;

	/**
	 * 出版时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime publicationTime;

	/**
	 * 简介
	 */
	private String synopsis;

	/**
	 * 字数
	 */
	private Integer numberWorders;

	/**
	 * 封面
	 */
	private String cover;

	/**
	 * 书籍状态
	 */
	private Integer status;
	/**
	 * 创建时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createTime;

	private Integer type ;
	private String typeName;

	@Override
	public void accpet(QueryWrapper<Book> queryWrapper) {
		queryWrapper.lambda().orderByDesc(Book::getCreateTime);
		if(!StringUtils.isEmpty(typeName)){
			queryWrapper.lambda().inSql(Book::getId,"select book_id from book_rack GROUP BY book_id  ORDER BY  count(*) DESC");
		}
		if(Objects.nonNull(type)){
			queryWrapper.lambda().inSql(Book::getId," select book_id from book_type where type_id = " +type);
		}
		if(Objects.nonNull(name)){
			queryWrapper.lambda().like(Book::getName,name).or().like(Book::getAuthor,name);
		}
	}
	
	public  Book convert() {
  		 Book  book = new  Book();
  	  BeanUtils.copyProperties(this,  book);
  	  return  book;
  	}
}