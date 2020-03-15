package com.yyjj.reading.api.vo;

import com.yyjj.reading.db.model.Chapter;
import com.yyjj.reading.service.bo.ChapterBO;
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
public class ChapterVO implements Serializable {

    private static final long serialVersionUID = 1L;

	/**
	 * 章节表
	 */
	private Integer id;

	/**
	 * 书籍名称
	 */
	private String bookName;

	/**
	 * 书籍id
	 */
	private Integer bookId;

	/**
	 * 字数
	 */
	private String numberWorders;
	/**
	 * 章节标题
	 */
	private String title;
	/**
	 * 章节序号
	 */
	private String sort;

	/**
	 * 章节内容
	 */
	private String content;

	/**
	 * 创建时间
	 */
	private LocalDateTime createTime;
     
    public static ChapterVO newInstance(Chapter chapter) {
        if(Objects.isNull( chapter)) {
  	    return null;
  	  }        
         ChapterVO chapterVO = new ChapterVO();
        BeanUtils.copyProperties( chapter,  chapterVO);
        return  chapterVO;
  	}
    
    public static ChapterVO newInstance(ChapterBO chapter) {
        if(Objects.isNull( chapter)) {
  	    return null;
  	  }        
         ChapterVO chapterVO = new ChapterVO();
        BeanUtils.copyProperties( chapter,  chapterVO);
        return  chapterVO;
  	}
  	    
  	public  Chapter convert() {
  		 Chapter  chapter = new  Chapter();
  	  BeanUtils.copyProperties(this,  chapter);
  	  return  chapter;
  	}
  	
  	public  ChapterBO  ChapterBO() {
  		 ChapterBO  chapter = new  ChapterBO();
  	  BeanUtils.copyProperties(this,  chapter);
  	  return  chapter;
  	}
  	
  }