package com.yyjj.reading.service.bo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yyjj.reading.db.model.Comment;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
public class CommentBO implements IBaseBO<Comment>{
	
	@Override
	public void accpet(QueryWrapper<Comment> queryWrapper) {
		
	}
	
	public  Comment convert() {
  		 Comment  comment = new  Comment();
  	  BeanUtils.copyProperties(this,  comment);
  	  return  comment;
  	}
}
    