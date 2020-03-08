package com.yyjj.reading.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


/**
 * BookRack
 * @author yml
 *
 */
@RestController
@RequestMapping("/BookRack")
public class BookRackController {
		
	@Autowired
	BookRackService bookrackService;
	
	/**
	 * ${controllerName}
	 * @param vo
	 * @return
	 */
	@GetMapping
	public BasePage<BookRackVO> listBasePage(BookRackVO vo){
		return null;
	}
	
	/**
	 *${controllerName}
	 * @param id BookRackid
	 * @return
	 */
	@GetMapping("/{id:\\d+}")
	public BookRackVO Detail(@PathVariable Integer id) {
		
		return null;
	}
	
	
	/**
	 * BookRack
	 * @param vo
	 * @return
	 * 
	 */
	@PostMapping
	public BookRackVO add(@RequestBody @Validated BookRackVO vo) {
		return null;	
	}
	
	/**
	 * BookRack
	 * @param vo
	 * @return
	 * 
	 */
	@PutMapping
	public BookRackVO modify(@RequestBody @Validated BookRackVO vo) {
		return null;	
	}
	
	/**
	 * BookRack
	 * @param id
	 */
	@DeleteMapping("/{id:\\d+}")
	public void remove(@PathVariable Integer id) {
		
	}
	
	private BasePage<BookRackVO> convert(BasePage<BookRack> basePage) {
		List<BookRackVO> resultList = new ArrayList<BookRackVO>();
					
		for (BookRack bookrack : basePage.getRecords()) {
			resultList.add(BookRackVO.newInstance(bookrack));
		}
		BasePage<BookRackVO> result = new BasePage<BookRackVO>(basePage.getPage(),
				basePage.getPageSize(), basePage.getCurrent(), basePage.getTotal(), resultList);
		return result;
	} 
	
	
}
