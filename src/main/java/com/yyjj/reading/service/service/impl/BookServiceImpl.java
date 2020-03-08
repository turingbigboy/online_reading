package com.yyjj.reading.service.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyjj.reading.db.dao.BookMapper;
import com.yyjj.reading.db.model.Book;
import com.yyjj.reading.service.service.BookService;
import org.springframework.stereotype.Service;

/**
 * 
 * @author yml
 *
 */
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book>
implements BookService
{
	

}
