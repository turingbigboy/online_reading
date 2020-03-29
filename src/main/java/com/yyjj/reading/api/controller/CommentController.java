package com.yyjj.reading.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yyjj.reading.api.vo.BookVO;
import com.yyjj.reading.api.vo.CommentVO;
import com.yyjj.reading.api.vo.UserVO;
import com.yyjj.reading.db.model.Comment;
import com.yyjj.reading.db.model.User;
import com.yyjj.reading.domain.context.AjaxResult;
import com.yyjj.reading.domain.service.BasePage;
import com.yyjj.reading.service.service.BookService;
import com.yyjj.reading.service.service.CommentService;
import com.yyjj.reading.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * Comment
 * @author yml
 *
 */
@RestController
@RequestMapping("/comment")
public class CommentController {
		
	@Autowired
	CommentService commentService;

	@Autowired
	BookService bookService;

	@Autowired
	UserService userService;
	/**
	 * 获取书籍所有评论
	 * @param vo
	 * @return
	 */
	@GetMapping("/{bookId:\\d+}")
	public AjaxResult<CommentVO> listBasePage(@PathVariable Integer bookId, CommentVO vo){
		return AjaxResult.success("",
				commentService.listPage(new QueryWrapper<Comment>().lambda()
						.eq(Comment::getBookId,bookId).orderByAsc(Comment::getCreateTime)).converterAll(this::convert));
	}
	
	/**
	 *${controllerName}
	 * @param id Commentid
	 * @return
	 */
//	@GetMapping("/{id:\\d+}")
//	public AjaxResult<CommentVO> Detail(@PathVariable Integer id) {
//
//		return null;
//	}
	
	
	/**
	 * 新增评论
	 * @param vo
	 * @return
	 * 
	 */
	@PostMapping
	public AjaxResult<CommentVO> add(@RequestBody @Validated CommentVO vo, HttpServletRequest request, HttpServletResponse response) throws IOException {
		if(Objects.isNull(vo.getUserId())){
			User user = (User)request.getSession().getAttribute("user");
			if(Objects.isNull(user)){
				response.sendError(401, "请登录");
			}else{
				vo.setUserId(user.getId());
			}
		}
		Comment comment = vo.convert();
		Boolean result = commentService.save(comment);
		if(result){
			return AjaxResult.success("评论成功",convert(commentService.getById(comment.getId())));
		}else {
			return AjaxResult.failed("添加失败");
		}
	}
	
	/**
	 * Comment
	 * @param vo
	 * @return
	 * 
	 */
	@PutMapping
	public AjaxResult<CommentVO> modify(@RequestBody @Validated CommentVO vo) {
		return null;	
	}
	
	/**
	 * Comment
	 * @param id
	 */
	@DeleteMapping("/{id:\\d+}")
	public AjaxResult remove(@PathVariable Integer id) {
		return null;
	}
	
	private BasePage<CommentVO> convert(BasePage<Comment> basePage) {
		List<CommentVO> resultList = new ArrayList<CommentVO>();
					
		for (Comment comment : basePage.getRecords()) {
			resultList.add(convert(comment));
		}
		BasePage<CommentVO> result = new BasePage<CommentVO>(basePage.getPage(),
				basePage.getPageSize(), basePage.getCurrent(), basePage.getTotal(), resultList);
		return result;
	}
	private CommentVO convert(Comment comment){
			CommentVO vo = CommentVO.newInstance(comment);
			vo.setBook(BookVO.newInstance(bookService.getById(vo.getBookId())));
			vo.setUser(UserVO.newInstance(userService.getById(vo.getUserId())));
			return vo;
	}
	
}
