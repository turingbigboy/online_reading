package com.yyjj.reading.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yyjj.reading.api.vo.ChapterVO;
import com.yyjj.reading.db.model.Book;
import com.yyjj.reading.db.model.Chapter;
import com.yyjj.reading.db.model.Nodes;
import com.yyjj.reading.db.model.ReadingRecord;
import com.yyjj.reading.domain.context.AjaxResult;
import com.yyjj.reading.domain.service.BasePage;
import com.yyjj.reading.service.service.BookService;
import com.yyjj.reading.service.service.ChapterService;
import com.yyjj.reading.service.service.NodesService;
import com.yyjj.reading.service.service.ReadingRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
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

	@Autowired
	NodesService nodesService;
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
	@GetMapping("/read/{userId:\\d+}/{bookId:\\d+}")
	public AjaxResult<ChapterVO> readingDetail(@PathVariable Integer userId,Integer chapterId,@PathVariable Integer bookId) {
		//根据章节ID阅读章节
		ChapterVO vo = null;
		if (chapterId != null) {
			//BasePage<Chapter> pages = chapterService.listPage(new QueryWrapper<Chapter>().lambda().eq(Chapter::getBookId,bookId).orderByAsc(Chapter::getSort));
			List<Chapter> chapters = chapterService.lambdaQuery().eq(Chapter::getBookId, bookId).orderByAsc(Chapter::getSort).list();
			if (!CollectionUtils.isEmpty(chapters)) {
				Long num = (long)0;
				for (Chapter chap : chapters) {
					num++;
					if (chap.getId().equals(chapterId)) {
						vo = convert(chap);
						vo.setPage(num);
					}
				}
			}
		} else {
			BasePage<Chapter> pages = chapterService.listPage(new QueryWrapper<Chapter>().lambda().eq(Chapter::getBookId, bookId).orderByAsc(Chapter::getSort));
			List<Chapter> list = pages.getRecords();
			if (!CollectionUtils.isEmpty(list)) {
				Chapter chapter1 = list.get(0);
				vo = convert(chapter1);
				vo.setPage(pages.getPage());
			}
		}
		if(Objects.isNull(vo)) {
			return AjaxResult.failed("没有下一章");
		}

		//一条新的阅读记录
		ReadingRecord rr = new ReadingRecord();
		rr.setUserId(userId);
		rr.setRedcordTime(LocalDateTime.now());
		rr.setChapterName(vo.getTitle());
		Book book = bookService.getById(vo.getBookId());
		rr.setBookId(vo.getBookId());
		rr.setBookName(book.getName());
		rr.setBookAuthor(book.getAuthor());
		rr.setBookCover(book.getCover());
		rr.setChapterId(vo.getId());
		ReadingRecord readingrecord = readingrecordService.lambdaQuery().eq(ReadingRecord::getUserId, userId).eq(ReadingRecord::getBookId, vo.getBookId()).one();
		//一条新的阅读记录
		if (Objects.isNull(readingrecord)) {
			readingrecordService.save(rr);
		} else {
			//更新阅读记录
			readingrecord.setChapterId(vo.getId());
			readingrecord.setChapterName(vo.getTitle());
			readingrecord.setRedcordTime(LocalDateTime.now());
			readingrecordService.updateById(readingrecord);
		}


		Nodes node = nodesService.lambdaQuery().eq(Nodes::getChapterId, vo.getId()).eq(Nodes::getUserId, userId).one();
		if (Objects.nonNull(node)) {
			vo.setContent(node.getNodeContent());
		} else {
			Nodes nodes = new Nodes();
			nodes.setChapterId(vo.getId());
			nodes.setUserId(userId);
			nodes.setBookId(book.getId());
			nodes.setNodeContent(vo.getContent());
			nodesService.save(nodes);
		}
		  return AjaxResult.success("", vo);
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
		nodesService.remove(new QueryWrapper<Nodes>().lambda().eq(Nodes::getChapterId,vo.getId()));
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
        nodesService.remove(new QueryWrapper<Nodes>().lambda().eq(Nodes::getChapterId,id));
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

			return vo;
	}
	
}
