package com.yyjj.reading.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


/**
 * Nodes
 * @author yml
 *
 */
@RestController
@RequestMapping("/Nodes")
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
	 * Nodes
	 * @param vo
	 * @return
	 * 
	 */
	@PostMapping
	public NodesVO add(@RequestBody @Validated NodesVO vo) {
		return null;	
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
