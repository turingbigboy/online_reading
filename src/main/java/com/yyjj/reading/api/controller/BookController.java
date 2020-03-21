package com.yyjj.reading.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yyjj.reading.api.vo.BookVO;
import com.yyjj.reading.db.model.Book;
import com.yyjj.reading.db.model.Chapter;
import com.yyjj.reading.domain.context.AjaxResult;
import com.yyjj.reading.domain.service.BasePage;
import com.yyjj.reading.service.service.BookService;
import com.yyjj.reading.service.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * Book
 * @author yml
 *
 */
@RestController
@RequestMapping("/book")
public class BookController {
		
	@Autowired
	BookService bookService;

	@Value("${upload.filePath}")
	String filePath;
	@Value("${upload.imagePath}")
	String imgPath;
	@Autowired
	ChapterService chapterService;
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
	 *获取指定书籍
	 * @param id Bookid
	 * @return
	 */
	@GetMapping("/{id:\\d+}")
	public AjaxResult<BookVO> Detail(@PathVariable Integer id) {
		return AjaxResult.success("",BookVO.newInstance(bookService.getById(id)));
	}
	
	
	/**
	 * 新增书籍
	 * @param vo
	 * @return
	 * 
	 */
	@PostMapping
	public AjaxResult<BookVO> add(MultipartFile image,@RequestBody @Validated BookVO vo) throws IOException {
		int nano = LocalDateTime.now().getNano();
		if(Objects.nonNull(image)) {
			image.transferTo(new File(filePath+ File.separator+"cover"+File.separator+nano+image.getOriginalFilename()));
			vo.setCover(imgPath+File.separator+"cover"+File.separator+nano+image.getOriginalFilename());
		}
		Book book = vo.convert();
		Boolean result = bookService.save(book);
		if(result){
			return AjaxResult.success("添加成功",bookService.getById(book.getId()));
		}else {
			return AjaxResult.failed("添加失败");
		}
	}


	/**
	 * 修改书籍信息
	 * @param vo
	 * @return
	 * 
	 */
	@PutMapping
	public AjaxResult modify(MultipartFile image,@RequestBody @Validated BookVO vo) throws IOException {
		if(Objects.nonNull(image)) {
			int nano = LocalDateTime.now().getNano();
			image.transferTo(new File(filePath+ File.separator+"cover"+File.separator+nano +image.getOriginalFilename()));
			vo.setCover(imgPath+File.separator+"cover"+File.separator+nano+image.getOriginalFilename());
		}
		Book book = vo.convert();
		Boolean result = bookService.save(book);
		if(result){
			return AjaxResult.success("添加成功",bookService.getById(book.getId()));
		}else {
			return AjaxResult.failed("添加失败");
		}
	}
	
	/**
	 * 删除书籍（会将章节同时删除）
	 * @param id
	 */
	@DeleteMapping("/{id:\\d+}")
	public AjaxResult remove(@PathVariable Integer id) {

		chapterService.remove(new QueryWrapper<Chapter>().lambda().eq(Chapter::getBookId,id));
		Boolean result = bookService.removeById(id);
		if(result){
			return AjaxResult.success("删除成功");
		}else {
			return AjaxResult.failed("删除失败");
		}
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
