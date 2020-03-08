package com.yyjj.reading.service.bo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.oudot.asset.manage.db.model.model.Nodes;
import com.oudot.asset.manage.service.bo.base.IBaseBO;

import lombok.Getter;
import lombok.Setter;

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
    