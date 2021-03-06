package com.yyjj.reading.api.vo;

import com.yyjj.reading.db.model.Chapter;
import com.yyjj.reading.service.bo.ChapterBO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
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
public class ChapterVO implements Serializable,Comparable<ChapterVO> {

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
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	private LocalDateTime createTime;
	/**
	 * 笔记
	 */
	List<NodesVO> nodes;

	private Long page;
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

	@Override
	public int compareTo(ChapterVO o) {

    		if(this.sort.equals("") || Objects.isNull(this.sort)){
    			return -1;
			}
    		if(o.getSort().equals("") || Objects.isNull(o.getSort())){
    			return 1;
			}
			Integer sort1 =  Integer.parseInt(this.sort);
			Integer sort2 = Integer.parseInt(o.getSort());


		return sort1.compareTo(sort2);
	}
}