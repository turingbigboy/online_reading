package com.yyjj.reading.api.vo;

import com.yyjj.reading.db.model.Comment;
import com.yyjj.reading.service.bo.CommentBO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * <p>
 * 
 * </p>
 *
 * @author yml
 * 
 */
@Getter
@Setter
public class CommentVO implements Serializable {

    private static final long serialVersionUID = 1L;
	private Integer id;

	/**
	 * 评论用户
	 */
	private Integer userId;

	/**
	 * 书籍ID
	 */
	private Integer bookId;

	/**
	 * 评论内容
	 */
	private String content;

	/**
	 * 评论**数量
	 */
	private Integer level;
	private LocalDateTime createTime;

	private BookVO book;

	private UserVO user;
     
    public static CommentVO newInstance(Comment comment) {
        if(Objects.isNull( comment)) {
  	    return null;
  	  }        
         CommentVO commentVO = new CommentVO();
        BeanUtils.copyProperties( comment,  commentVO);
        return  commentVO;
  	}
    
    public static CommentVO newInstance(CommentBO comment) {
        if(Objects.isNull( comment)) {
  	    return null;
  	  }        
         CommentVO commentVO = new CommentVO();
        BeanUtils.copyProperties( comment,  commentVO);
        return  commentVO;
  	}
  	    
  	public  Comment convert() {
  		 Comment  comment = new  Comment();
  	  BeanUtils.copyProperties(this,  comment);
  	  return  comment;
  	}
  	
  	public  CommentBO  CommentBO() {
  		 CommentBO  comment = new  CommentBO();
  	  BeanUtils.copyProperties(this,  comment);
  	  return  comment;
  	}
  	
  }