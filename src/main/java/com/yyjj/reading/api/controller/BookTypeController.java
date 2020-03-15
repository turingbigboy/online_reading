package com.yyjj.reading.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yyjj.reading.api.vo.BookTypeVO;
import com.yyjj.reading.api.vo.BookVO;
import com.yyjj.reading.api.vo.TypeVO;
import com.yyjj.reading.db.model.BookType;
import com.yyjj.reading.domain.context.AjaxResult;
import com.yyjj.reading.domain.service.BasePage;
import com.yyjj.reading.service.service.BookService;
import com.yyjj.reading.service.service.BookTypeService;
import com.yyjj.reading.service.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * 分类书籍
 * @author yml
 *
 */
@RestController
@RequestMapping("/book-type")
public class BookTypeController {
		
	@Autowired
	BookTypeService booktypeService;

	@Autowired
	BookService bookService;

	@Autowired
	TypeService typeService;
	/**
	 * 分页获取指定分类下的书籍
	 * @param vo
	 * @return
	 */
	@GetMapping("{id:\\d+}")
	public AjaxResult<BookTypeVO> listBasePage(@PathVariable Integer id,BookTypeVO vo){
		return 	AjaxResult.success("",booktypeService.listPage(new QueryWrapper<BookType>(vo.convert()).lambda().eq(BookType::getTypeId,id)).converterAll(this::convert));

	}
	

	
	/**
	 * 新增分类书籍关联
	 * @param vo
	 * @return
	 * 
	 */
	@PostMapping
	public AjaxResult<BookTypeVO> add(@RequestBody @Validated BookTypeVO vo) {
		BookType by = booktypeService.lambdaQuery().eq(BookType::getTypeId,vo.getTypeId()).eq(BookType::getBookId,vo.getBookId()).one();
		if(Objects.nonNull(by)){
			return AjaxResult.failed("分类书籍已经存在");
		}
		BookType bookType = vo.convert();
		Boolean result = booktypeService.save(bookType);
		if(result){
			return AjaxResult.success("添加成功",booktypeService.getById(bookType.getId()));
		}else {
			return AjaxResult.failed("添加失败");
		}
	}

	/**
	 * 删除分类关联
	 * @param id 关联id
	 */
	@DeleteMapping("/{id:\\d+}")
	public AjaxResult remove(@PathVariable Integer id) {
		Boolean result = booktypeService.removeById(id);
		if(result){
			return AjaxResult.success("删除成功");
		}else {
			return AjaxResult.failed("删除失败");
		}
	}
	
	private BasePage<BookTypeVO> convert(BasePage<BookType> basePage) {
		List<BookTypeVO> resultList = new ArrayList<BookTypeVO>();
					
		for (BookType booktype : basePage.getRecords()) {
			resultList.add(convert(booktype));
		}
		BasePage<BookTypeVO> result = new BasePage<BookTypeVO>(basePage.getPage(),
				basePage.getPageSize(), basePage.getCurrent(), basePage.getTotal(), resultList);
		return result;
	} 
	private BookTypeVO convert(BookType bookType){
		BookTypeVO vo = BookTypeVO.newInstance(bookType);
		vo.setBook(BookVO.newInstance(bookService.getById(vo.getBookId())));
		vo.setType(TypeVO.newInstance(typeService.getById(vo.getTypeId())));
		return vo;
	}
	
}
