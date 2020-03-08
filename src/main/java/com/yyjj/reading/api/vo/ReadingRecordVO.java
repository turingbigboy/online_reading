package com.yyjj.reading.api.vo;

import com.yyjj.reading.db.model.ReadingRecord;
import com.yyjj.reading.service.bo.ReadingRecordBO;
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
public class ReadingRecordVO implements Serializable {

    private static final long serialVersionUID = 1L;
    
     
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