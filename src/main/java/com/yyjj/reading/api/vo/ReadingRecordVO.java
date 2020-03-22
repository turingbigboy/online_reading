package com.yyjj.reading.api.vo;

import com.yyjj.reading.db.model.ReadingRecord;
import com.yyjj.reading.service.bo.ReadingRecordBO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

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
public class ReadingRecordVO implements Serializable {

    private static final long serialVersionUID = 1L;
	/**
	 * 记录id
	 */
	private Integer id;

	/**
	 * 书籍id
	 */
	private Integer bookId;

	/**
	 * 书籍名称
	 */
	private String bookName;

	/**
	 * 书籍封面
	 */
	private String bookCover;

	/**
	 * 书籍作者
	 */
	private String bookAuthor;

	/**
	 * 章节id
	 */
	private Integer chapterId;

	/**
	 * 章节名称
	 */
	private String chapterName;

	/**
	 * 用户id
	 */
	private Integer userId;

	/**
	 * 记录时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	private LocalDateTime redcordTime;
     
    public static ReadingRecordVO newInstance(ReadingRecord readingrecord) {
        if(Objects.isNull( readingrecord)) {
  	    return null;
  	  }        
         ReadingRecordVO readingrecordVO = new ReadingRecordVO();
        BeanUtils.copyProperties( readingrecord,  readingrecordVO);
        return  readingrecordVO;
  	}
    
    public static ReadingRecordVO newInstance(ReadingRecordBO readingrecord) {
        if(Objects.isNull( readingrecord)) {
  	    return null;
  	  }        
         ReadingRecordVO readingrecordVO = new ReadingRecordVO();
        BeanUtils.copyProperties( readingrecord,  readingrecordVO);
        return  readingrecordVO;
  	}
  	    
  	public  ReadingRecord convert() {
  		 ReadingRecord  readingrecord = new  ReadingRecord();
  	  BeanUtils.copyProperties(this,  readingrecord);
  	  return  readingrecord;
  	}
  	
  	public  ReadingRecordBO  ReadingRecordBO() {
  		 ReadingRecordBO  readingrecord = new  ReadingRecordBO();
  	  BeanUtils.copyProperties(this,  readingrecord);
  	  return  readingrecord;
  	}
  	
  }