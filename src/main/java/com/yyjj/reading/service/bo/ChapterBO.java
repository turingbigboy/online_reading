package com.yyjj.reading.service.bo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.oudot.asset.manage.db.model.model.Chapter;
import com.oudot.asset.manage.service.bo.base.IBaseBO;

import lombok.Getter;
import lombok.Setter;

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
    