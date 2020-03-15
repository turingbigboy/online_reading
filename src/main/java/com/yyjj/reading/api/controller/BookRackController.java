package com.yyjj.reading.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yyjj.reading.api.vo.BookRackVO;
import com.yyjj.reading.api.vo.BookVO;
import com.yyjj.reading.db.model.BookRack;
import com.yyjj.reading.domain.context.AjaxResult;
import com.yyjj.reading.domain.service.BasePage;
import com.yyjj.reading.service.service.BookRackService;
import com.yyjj.reading.service.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 *书架
 * @author yml
 *
 */
@RestController
@RequestMapping("/BookRack")
public class BookRackController {
		
	@Autowired
	BookRackService bookrackService;

	@Autowired
	BookService bookService;
	/**
	 * 获取我的书架的所有书籍
	 * @param vo
	 * @return
	 */
	@GetMapping("{userId:\\d+}")
	public AjaxResult<BookRackVO> listBasePage(@PathVariable Integer userId, BookRackVO vo){
		vo.setUserId(null);
		return AjaxResult.success("",bookrackService.listPage(new QueryWrapper<BookRack>(vo.convert()).lambda().eq(BookRack::getUserId,userId)).converterAll(this::convert));
	}
	
	
	/**
	 * 加入书架
	 * @param vo userId,bookId 必传
	 * @return
	 * 
	 */
	@PostMapping
	public AjaxResult<BookRackVO> add(@RequestBody @Validated BookRackVO vo) {
		BookRack br = bookrackService.lambdaQuery().eq(BookRack::getUserId,vo.getUserId()).eq(BookRack::getBookId,vo.getBookId()).one();
		if(Objects.nonNull(br)){
			return  AjaxResult.failed("书架中已存在该书籍");
		}
		BookRack bookRack = vo.convert();
		Boolean result = bookrackService.save(bookRack);
		if(result){
			return AjaxResult.success("成功加入书架",convert(bookrackService.getById(bookRack.getId())));
		}else{
			return	AjaxResult.failed("加入书架失败");
		}
	}

	
	/**
	 * 移出书架
	 * @param id
	 */
	@DeleteMapping("/{id:\\d+}")
	public AjaxResult remove(@PathVariable Integer id) {
		Boolean result = bookrackService.removeById(id);
		if(result){
			return  AjaxResult.success("移除成功");
		}else {
			return AjaxResult.failed("移除失败");
		}
	}
	
	private BasePage<BookRackVO> convert(BasePage<BookRack> basePage) {
		List<BookRackVO> resultList = new ArrayList<BookRackVO>();
		for (BookRack bookrack : basePage.getRecords()) {
			resultList.add(convert(bookrack));
		}
		BasePage<BookRackVO> result = new BasePage<BookRackVO>(basePage.getPage(),
				basePage.getPageSize(), basePage.getCurrent(), basePage.getTotal(), resultList);
		return result;
	}

	private BookRackVO convert(BookRack bookRack){
		BookRackVO vo =	BookRackVO.newInstance(bookRack);
		vo.setBook(BookVO.newInstance(bookService.getById(vo.getBookId())));
		return vo;
	}
}
