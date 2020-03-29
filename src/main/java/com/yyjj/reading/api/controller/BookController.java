package com.yyjj.reading.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.yyjj.reading.api.vo.BookTypeVO;
import com.yyjj.reading.api.vo.BookVO;
import com.yyjj.reading.api.vo.TypeVO;
import com.yyjj.reading.db.model.Book;
import com.yyjj.reading.db.model.BookRack;
import com.yyjj.reading.db.model.BookType;
import com.yyjj.reading.db.model.Chapter;
import com.yyjj.reading.domain.context.AjaxResult;
import com.yyjj.reading.domain.context.BasePageVOContextHolder;
import com.yyjj.reading.domain.service.BasePage;
import com.yyjj.reading.domain.service.BasePageVO;
import com.yyjj.reading.service.bo.BookBO;
import com.yyjj.reading.service.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
	@Autowired
	BookRackService bookrackService;
	@Autowired
	BookTypeService bookTypeService;
	@Autowired
	TypeService typeService;
	/**
	 * 获取所有书籍
	 * @param vo
	 * @return
	 */
	@GetMapping
	public AjaxResult listBasePage(BookVO vo){
		BookBO bo = vo.BookBO();
		bo.setType(vo.getType());
		bo.setName(vo.getName());
		return AjaxResult.success("",bookService.listSearch(new Book(),new BookBO()).converterAll(this::convert));
	}
	@GetMapping("/{bookId:\\d+}/types")
    public AjaxResult bookTypeList(@PathVariable Integer bookId){
		List<BookType> bookTypes =  bookTypeService.lambdaQuery().eq(BookType::getBookId,bookId).list();
		List<BookTypeVO> bookTypeVOs = new ArrayList<>();
		for(BookType bt : bookTypes){
			BookTypeVO vo = BookTypeVO.newInstance(bt);
			vo.setBook(BookVO.newInstance(bookService.getById(bt.getBookId())));
			vo.setType(TypeVO.newInstance(typeService.getById(bt.getTypeId())));
			bookTypeVOs.add(vo);
		}

		/*if(!CollectionUtils.isEmpty(bookTypes)) {
		return AjaxResult.success("",typeService.lambdaQuery().in(Type::getId, bookTypes.stream().map(BookType::getTypeId).collect(Collectors.toList())).list());
		}*/
		return AjaxResult.success("",bookTypeVOs);
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
	 * 新书推荐
	 * @return
	 */
	@GetMapping("newbook-recommend")
	public AjaxResult<BookVO> newBook(){
		BasePageVO vo = new BasePageVO();
		vo.setPage((long)1);
		vo.setPageSize((long)3);
		BasePageVOContextHolder.setBasePageVO(vo);
		return AjaxResult.success("",bookService.listPage(new QueryWrapper<Book>().orderByDesc("create_time")).converterAll(this::convert));
	}

	/**
	 * top榜
	 * @return
	 */
	@GetMapping("top")
	public AjaxResult<BookVO> top(){
		BasePageVO vo = new BasePageVO();
		vo.setPage((long)1);
		vo.setPageSize((long)3);
		BookBO bo = new BookBO();
		bo.setTypeName("TOP");
		return AjaxResult.success("",bookService.listSearch(new Book(),new BookBO(),vo).converterAll(this::convert));
	}


	/**
	 * 新增书籍
	 * @param vo
	 * @return
	 * 
	 */
	@PostMapping
	public AjaxResult<BookVO> add(MultipartFile file,BookVO vo) throws IOException {
			int nano = LocalDateTime.now().getNano();
		if(Objects.nonNull(file)) {
			file.transferTo(new File(filePath+ File.separator+"cover"+File.separator+nano+file.getOriginalFilename()));
			vo.setCover(imgPath+File.separator+"cover"+File.separator+nano+file.getOriginalFilename());
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
	 * 下架书籍
	 * @param bookId
	 * @return
	 *
	 */
	@PutMapping("/disable/{bookId:\\d+}")
	public AjaxResult disable(@PathVariable Integer bookId) throws IOException {

		Boolean result = bookService.update(new UpdateWrapper<Book>().lambda().eq(Book::getId,bookId).set(Book::getStatus,0));
		if(result){
			return AjaxResult.success("下架成功",bookService.getById(bookId));
		}else {
			return AjaxResult.failed("下架失败");
		}
	}

	/**
	 * 上架书籍
	 * @param bookId
	 * @return
	 *
	 */
	@PutMapping("/enable/{bookId:\\d+}")
	public AjaxResult enable(@PathVariable Integer bookId) throws IOException {
		Boolean result = bookService.update(new UpdateWrapper<Book>().lambda().eq(Book::getId,bookId).set(Book::getStatus,1));
		if(result){
			return AjaxResult.success("上架成功",bookService.getById(bookId));
		}else {
			return AjaxResult.failed("上架失败");
		}
	}

	/**
	 * 修改书籍信息
	 * @param vo
	 * @return
	 * 
	 */
	@PutMapping
	public AjaxResult modify(MultipartFile file,BookVO vo) throws IOException {
		if(Objects.nonNull(file)) {
			int nano = LocalDateTime.now().getNano();
			file.transferTo(new File(filePath+ File.separator+"cover"+File.separator+nano +file.getOriginalFilename()));
			vo.setCover(imgPath+File.separator+"cover"+File.separator+nano+file.getOriginalFilename());
		}
		Book book = vo.convert();
		Boolean result = bookService.updateById(book);
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
		bookrackService.remove(new QueryWrapper<BookRack>().lambda().eq(BookRack::getBookId,id));
		bookTypeService.remove(new QueryWrapper<BookType>().lambda().eq(BookType::getBookId,id));
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
