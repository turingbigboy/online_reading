package com.yyjj.reading.api.controller;

import com.yyjj.reading.api.vo.TypeVO;
import com.yyjj.reading.db.model.Type;
import com.yyjj.reading.domain.service.BasePage;
import com.yyjj.reading.service.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


/**
 * Type
 * @author yml
 *
 */
@RestController
@RequestMapping("/Type")
public class TypeController {
		
	@Autowired
	TypeService typeService;
	
	/**
	 * ${controllerName}
	 * @param vo
	 * @return
	 */
	@GetMapping
	public BasePage<TypeVO> listBasePage(TypeVO vo){
		return null;
	}
	
	/**
	 *${controllerName}
	 * @param id Typeid
	 * @return
	 */
	@GetMapping("/{id:\\d+}")
	public TypeVO Detail(@PathVariable Integer id) {
		
		return null;
	}
	
	
	/**
	 * Type
	 * @param vo
	 * @return
	 * 
	 */
	@PostMapping
	public TypeVO add(@RequestBody @Validated TypeVO vo) {
		return null;	
	}
	
	/**
	 * Type
	 * @param vo
	 * @return
	 * 
	 */
	@PutMapping
	public TypeVO modify(@RequestBody @Validated TypeVO vo) {
		return null;	
	}
	
	/**
	 * Type
	 * @param id
	 */
	@DeleteMapping("/{id:\\d+}")
	public void remove(@PathVariable Integer id) {
		
	}
	
	private BasePage<TypeVO> convert(BasePage<Type> basePage) {
		List<TypeVO> resultList = new ArrayList<TypeVO>();
					
		for (Type type : basePage.getRecords()) {
			resultList.add(TypeVO.newInstance(type));
		}
		BasePage<TypeVO> result = new BasePage<TypeVO>(basePage.getPage(),
				basePage.getPageSize(), basePage.getCurrent(), basePage.getTotal(), resultList);
		return result;
	} 
	
	
}
