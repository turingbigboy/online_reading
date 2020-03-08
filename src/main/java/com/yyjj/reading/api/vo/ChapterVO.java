package com.yyjj.reading.api.vo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
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
    
     
    public static ChapterVO newInstance(Chapter  chapter) {
        if(Objects.isNull( chapter)) {
  	    return null;
  	  }        
         ChapterVO chapterVO = new ChapterVO();
        BeanUtils.copyProperties( chapter,  chapterVO);
        return  chapterVO;
  	}
    
    public static ChapterVO newInstance(ChapterBO  chapter) {
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