package com.yyjj.reading.api.controller;

import com.yyjj.reading.api.vo.BookTypeVO;
import com.yyjj.reading.db.model.BookType;
import com.yyjj.reading.domain.service.BasePage;
import com.yyjj.reading.service.service.BookTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


/**
 * BookType
 * @author yml
 *
 */
@RestController
@RequestMapping("/BookType")
public class BookTypeController {
		
	@Autowired
	BookTypeService booktypeService;
	
	/**
	 * ${controllerName}
	 * @param vo
	 * @return
	 */
	@GetMapping
	public BasePage<BookTypeVO> listBasePage(BookTypeVO vo){
		return null;
	}
	
	/**
	 *${controllerName}
	 * @param id BookTypeid
	 * @return
	 */
	@GetMapping("/{id:\\d+}")
	public BookTypeVO Detail(@PathVariable Integer id) {
		
		return null;
	}
	
	
	/**
	 * BookType
	 * @param vo
	 * @return
	 * 
	 */
	@PostMapping
	public BookTypeVO add(@RequestBody @Validated BookTypeVO vo) {
		return null;	
	}
	
	/**
	 * BookType
	 * @param vo
	 * @return
	 * 
	 */
	@PutMapping
	public BookTypeVO modify(@RequestBody @Validated BookTypeVO vo) {
		return null;	
	}
	
	/**
	 * BookType
	 * @param id
	 */
	@DeleteMapping("/{id:\\d+}")
	public void remove(@PathVariable Integer id) {
		
	}
	
	private BasePage<BookTypeVO> convert(BasePage<BookType> basePage) {
		List<BookTypeVO> resultList = new ArrayList<BookTypeVO>();
					
		for (BookType booktype : basePage.getRecords()) {
			resultList.add(BookTypeVO.newInstance(booktype));
		}
		BasePage<BookTypeVO> result = new BasePage<BookTypeVO>(basePage.getPage(),
				basePage.getPageSize(), basePage.getCurrent(), basePage.getTotal(), resultList);
		return result;
	} 
	
	
}
