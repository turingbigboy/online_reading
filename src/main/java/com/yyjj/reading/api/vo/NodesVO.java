package com.yyjj.reading.api.vo;

import com.yyjj.reading.db.model.Nodes;
import com.yyjj.reading.service.bo.NodesBO;
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
public class NodesVO implements Serializable {

    private static final long serialVersionUID = 1L;
    
     
    public static NodesVO newInstance(Nodes nodes) {
        if(Objects.isNull( nodes)) {
  	    return null;
  	  }        
         NodesVO nodesVO = new NodesVO();
        BeanUtils.copyProperties( nodes,  nodesVO);
        return  nodesVO;
  	}
    
    public static NodesVO newInstance(NodesBO nodes) {
        if(Objects.isNull( nodes)) {
  	    return null;
  	  }        
         NodesVO nodesVO = new NodesVO();
        BeanUtils.copyProperties( nodes,  nodesVO);
        return  nodesVO;
  	}
  	    
  	public  Nodes convert() {
  		 Nodes  nodes = new  Nodes();
  	  BeanUtils.copyProperties(this,  nodes);
  	  return  nodes;
  	}
  	
  	public  NodesBO  NodesBO() {
  		 NodesBO  nodes = new  NodesBO();
  	  BeanUtils.copyProperties(this,  nodes);
  	  return  nodes;
  	}
  	
  }