package com.yyjj.reading.service.bo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yyjj.reading.db.model.Nodes;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
public class NodesBO implements IBaseBO<Nodes>{
	
	@Override
	public void accpet(QueryWrapper<Nodes> queryWrapper) {
		
	}
	
	public  Nodes convert() {
  		 Nodes  nodes = new  Nodes();
  	  BeanUtils.copyProperties(this,  nodes);
  	  return  nodes;
  	}
}
    