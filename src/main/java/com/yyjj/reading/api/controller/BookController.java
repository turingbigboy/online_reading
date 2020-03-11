package com.yyjj.reading.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yyjj.reading.api.vo.BookVO;
import com.yyjj.reading.db.model.Book;
import com.yyjj.reading.domain.context.AjaxResult;
import com.yyjj.reading.domain.service.BasePage;
import com.yyjj.reading.service.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


/**
 * Book
 * @author yml
 *
 */
@RestController
@RequestMapping("/Book")
public class BookController {
		
	@Autowired
	BookService bookService;
	
	/**
	 * 获取所有书籍
	 * @param vo
	 * @return
	 */
	@GetMapping
	public AjaxResult listBasePage(BookVO vo){
		return AjaxResult.success("",bookService.listPage(new QueryWrapper<Book>(vo.convert())).converterAll(this::convert));
	}
	
	/**
	 *${controllerName}
	 * @param id Bookid
	 * @return
	 */
	@GetMapping("/{id:\\d+}")
	public AjaxResult Detail(@PathVariable Integer id) {
		
		return null;
	}
	
	
	/**
	 * Book
	 * @param vo
	 * @return
	 * 
	 */
	@PostMapping
	public AjaxResult add(@RequestBody @Validated BookVO vo) {
		return null;	
	}
	
	/**
	 * Book
	 * @param vo
	 * @return
	 * 
	 */
	@PutMapping
	public AjaxResult modify(@RequestBody @Validated BookVO vo) {
		return null;	
	}
	
	/**
	 * Book
	 * @param id
	 */
	@DeleteMapping("/{id:\\d+}")
	public AjaxResult remove(@PathVariable Integer id) {
		return null;
	}
	
	private BasePage<BookVO> convert(BasePage<Book> basePage) {
		List<BookVO> resultList = new ArrayList<BookVO>();
					
		for (Book book : basePage.getRecords()) {
			resultList.add(BookVO.newInstance(book));
		}
		BasePage<BookVO> result = new BasePage<BookVO>(basePage.getPage(),
				basePage.getPageSize(), basePage.getCurrent(), basePage.getTotal(), resultList);
		return result;
	} 
	
	
}
