package com.yyjj.reading.service.bo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yyjj.reading.db.model.Chapter;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
public class ChapterBO implements IBaseBO<Chapter>{
	
	@Override
	public void accpet(QueryWrapper<Chapter> queryWrapper) {
		
	}
	
	public  Chapter convert() {
  		 Chapter  chapter = new  Chapter();
  	  BeanUtils.copyProperties(this,  chapter);
  	  return  chapter;
  	}
}
    