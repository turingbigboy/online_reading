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
	private Integer id;

	/**
	 * 书籍id
	 */
	private Integer bookId;

	/**
	 * 章节id
	 */
	private Integer chapterId;

	/**
	 * 笔记起始位置
	 */
	private Integer nodeBeginLocation;

	/**
	 * 笔记结束位置
	 */
	private Integer nodeEndLocation;

	/**
	 * 笔记内容
	 */
	private String nodeContent;

	private String comment;

	/**
	 * 用户id
	 */
	private Integer userId;
     
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