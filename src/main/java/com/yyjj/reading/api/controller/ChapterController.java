package com.yyjj.reading.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yyjj.reading.api.vo.ChapterVO;
import com.yyjj.reading.db.model.Book;
import com.yyjj.reading.db.model.Chapter;
import com.yyjj.reading.db.model.ReadingRecord;
import com.yyjj.reading.domain.context.AjaxResult;
import com.yyjj.reading.domain.service.BasePage;
import com.yyjj.reading.service.service.BookService;
import com.yyjj.reading.service.service.ChapterService;
import com.yyjj.reading.service.service.ReadingRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * 章节
 * @author yml
 *
 */
@RestController
@RequestMapping("/chapter")
public class ChapterController {
		
	@Autowired
	ChapterService chapterService;
	@Autowired
	ReadingRecordService readingrecordService;

	@Autowired
	BookService bookService;
	/**
	 * 获取书籍的章节列表
	 * @param vo
	 * @param bookId 书籍id
	 * @return
	 */
	@GetMapping("{bookId:\\d+}")
	public AjaxResult<ChapterVO> listBasePage(@PathVariable Integer bookId, ChapterVO vo){
		return AjaxResult.success("",chapterService.listPage(new QueryWrapper<Chapter>().lambda()
				.eq(Chapter::getBookId,bookId).orderByAsc(Chapter::getSort)).converterAll(this::convert));
	}
	
	/**
	 *获取指定章节
	 * @param ChapterId Chapterid
	 * @return
	 */
	@GetMapping("/detail/{ChapterId:\\d+}")
	public AjaxResult<ChapterVO> Detail(@PathVariable Integer ChapterId) {
		return AjaxResult.success("",convert(chapterService.getById(ChapterId)));
	}

	/**
	 *阅读指定章节
	 * @param userId 用户ID
	 * @return
	 */
	@GetMapping("/read/{userId:\\d+}/{chapterId:\\d+}")
	public AjaxResult<ChapterVO> readingDetail(@PathVariable Integer userId,@PathVariable Integer chapterId) {
		//一条新的阅读记录
		ReadingRecord rr = new ReadingRecord();
		rr.setUserId(userId);
		rr.setRedcordTime(LocalDateTime.now());
		Chapter chapter = chapterService.getById(chapterId);
		rr.setChapterName(chapter.getTitle());
		Book book = bookService.getById(chapter.getBookId());
		rr.setBookId(chapter.getBookId());
		rr.setBookName(book.getName());
		rr.setBookAuthor(book.getAuthor());
		rr.setBookCover(book.getCover());
		ReadingRecord readingrecord = readingrecordService.lambdaQuery().eq(ReadingRecord::getUserId,userId).eq(ReadingRecord::getBookId,chapter.getBookId()).one();
		//一条新的阅读记录
		if(Objects.isNull(readingrecord)){
			readingrecordService.save(rr);
		}else{
			//更新阅读记录
			readingrecord.setChapterId(chapterId);
			readingrecord.setChapterName(chapter.getTitle());
			readingrecord.setRedcordTime(LocalDateTime.now());
			readingrecordService.updateById(readingrecord);
		}
		return AjaxResult.success("",convert(chapterService.getById(chapterId)));
	}
	
	/**
	 * 新增章节
	 * @param vo 必传BookId
	 * @return
	 * 
	 */
	@PostMapping
	public AjaxResult<ChapterVO> add(@RequestBody @Validated ChapterVO vo) {
		Chapter chapter = vo.convert();
		Boolean result = chapterService.save(chapter);
		if(result){
			return  AjaxResult.success("新增成功",convert(chapterService.getById(chapter.getId())));
		}else{
			return  AjaxResult.failed("新增失败");
		}
	}
	
	/**
	 * 修改章节
	 * @param vo
	 * @return
	 * 
	 */
	@PutMapping
	public AjaxResult<ChapterVO> modify(@RequestBody @Validated ChapterVO vo) {
		Chapter chapter = vo.convert();
		Boolean result = chapterService.updateById(chapter);
		if(result){
			return  AjaxResult.success("修改成功",convert(chapterService.getById(chapter.getId())));
		}else{
			return  AjaxResult.failed("修改失败");
		}
	}
	
	/**
	 * 删除章节
	 * @param id 章节id
	 */
	@DeleteMapping("/{id:\\d+}")
	public AjaxResult remove(@PathVariable Integer id) {
		Boolean result = chapterService.removeById(id);
		if(result){
			return  AjaxResult.success("删除成功");
		}else {
			return AjaxResult.failed("删除失败");
		}
	}
	
	private BasePage<ChapterVO> convert(BasePage<Chapter> basePage) {
		List<ChapterVO> resultList = new ArrayList<ChapterVO>();
					
		for (Chapter chapter : basePage.getRecords()) {
			resultList.add(convert(chapter));
		}
		BasePage<ChapterVO> result = new BasePage<ChapterVO>(basePage.getPage(),
				basePage.getPageSize(), basePage.getCurrent(), basePage.getTotal(), resultList);
		return result;
	}
	private ChapterVO convert(Chapter chapter){
			ChapterVO vo = ChapterVO.newInstance(chapter);
			//TODO
			return vo;
	}
	
}
