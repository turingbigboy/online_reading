package com.yyjj.reading.service.bo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.oudot.asset.manage.db.model.model.ReadingRecord;
import com.oudot.asset.manage.service.bo.base.IBaseBO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReadingRecordBO implements IBaseBO<ReadingRecord>{
	
	@Override
	public void accpet(QueryWrapper<ReadingRecord> queryWrapper) {
		
	}
	
	public  ReadingRecord convert() {
  		 ReadingRecord  readingrecord = new  ReadingRecord();
  	  BeanUtils.copyProperties(this,  readingrecord);
  	  return  readingrecord;
  	}
}
    