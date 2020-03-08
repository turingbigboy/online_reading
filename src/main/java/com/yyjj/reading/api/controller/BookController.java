package com.yyjj.reading.api.controller;

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
	 * ${controllerName}
	 * @param vo
	 * @return
	 */
	@GetMapping
	public BasePage<BookVO> listBasePage(BookVO vo){
		return null;
	}
	
	/**
	 *${controllerName}
	 * @param id Bookid
	 * @return
	 */
	@GetMapping("/{id:\\d+}")
	public BookVO Detail(@PathVariable Integer id) {
		
		return null;
	}
	
	
	/**
	 * Book
	 * @param vo
	 * @return
	 * 
	 */
	@PostMapping
	public BookVO add(@RequestBody @Validated BookVO vo) {
		return null;	
	}
	
	/**
	 * Book
	 * @param vo
	 * @return
	 * 
	 */
	@PutMapping
	public BookVO modify(@RequestBody @Validated BookVO vo) {
		return null;	
	}
	
	/**
	 * Book
	 * @param id
	 */
	@DeleteMapping("/{id:\\d+}")
	public void remove(@PathVariable Integer id) {
		
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
