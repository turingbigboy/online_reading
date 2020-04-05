package com.yyjj.reading.api.controller;

import com.yyjj.reading.api.vo.NodesVO;
import com.yyjj.reading.db.model.Nodes;
import com.yyjj.reading.domain.context.AjaxResult;
import com.yyjj.reading.domain.service.BasePage;
import com.yyjj.reading.service.service.NodesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * 笔记
 * @author yml
 *
 */
@RestController
@RequestMapping("/nodes")
public class NodesController {
		
	@Autowired
	NodesService nodesService;
	
	/**
	 * ${controllerName}
	 * @param vo
	 * @return
	 */
	@GetMapping
	public BasePage<NodesVO> listBasePage(NodesVO vo){
		return null;
	}
	
	/**
	 *${controllerName}
	 * @param id Nodesid
	 * @return
	 */
	@GetMapping("/{id:\\d+}")
	public NodesVO Detail(@PathVariable Integer id) {
		
		return null;
	}
	
	
	/**
	 * 新增笔记
	 * @param vo
	 * @return
	 * 
	 */
	@PostMapping
	public AjaxResult<NodesVO> add(@RequestBody @Validated NodesVO vo) {
	    Nodes nodes  = nodesService.lambdaQuery().eq(Nodes::getChapterId,vo.getChapterId()).eq(Nodes::getUserId,vo.getUserId()).one();
        boolean result = false;
	    if(Objects.nonNull(nodes)){
	        nodes.setNodeContent(vo.getNodeContent());
            result =  nodesService.updateById(nodes);
        }else {
            result =  nodesService.save(vo.convert());
        }
		if(result){
			return  AjaxResult.success("新增成功");
		}else {
			return AjaxResult.failed("新增失败");
		}
		//return null;
	}
	
	/**
	 * Nodes
	 * @param vo
	 * @return
	 * 
	 */
	@PutMapping
	public NodesVO modify(@RequestBody @Validated NodesVO vo) {
		return null;	
	}
	
	/**
	 * Nodes
	 * @param id
	 */
	@DeleteMapping("/{id:\\d+}")
	public void remove(@PathVariable Integer id) {
		
	}
	
	private BasePage<NodesVO> convert(BasePage<Nodes> basePage) {
		List<NodesVO> resultList = new ArrayList<NodesVO>();
					
		for (Nodes nodes : basePage.getRecords()) {
			resultList.add(NodesVO.newInstance(nodes));
		}
		BasePage<NodesVO> result = new BasePage<NodesVO>(basePage.getPage(),
				basePage.getPageSize(), basePage.getCurrent(), basePage.getTotal(), resultList);
		return result;
	} 
	
	
}
