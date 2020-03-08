package com.yyjj.reading.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


/**
 * User
 * @author yml
 *
 */
@RestController
@RequestMapping("/User")
public class UserController {
		
	@Autowired
	UserService userService;
	
	/**
	 * ${controllerName}
	 * @param vo
	 * @return
	 */
	@GetMapping
	public BasePage<UserVO> listBasePage(UserVO vo){
		return null;
	}
	
	/**
	 *${controllerName}
	 * @param id Userid
	 * @return
	 */
	@GetMapping("/{id:\\d+}")
	public UserVO Detail(@PathVariable Integer id) {
		
		return null;
	}
	
	
	/**
	 * User
	 * @param vo
	 * @return
	 * 
	 */
	@PostMapping
	public UserVO add(@RequestBody @Validated UserVO vo) {
		return null;	
	}
	
	/**
	 * User
	 * @param vo
	 * @return
	 * 
	 */
	@PutMapping
	public UserVO modify(@RequestBody @Validated UserVO vo) {
		return null;	
	}
	
	/**
	 * User
	 * @param id
	 */
	@DeleteMapping("/{id:\\d+}")
	public void remove(@PathVariable Integer id) {
		
	}
	
	private BasePage<UserVO> convert(BasePage<User> basePage) {
		List<UserVO> resultList = new ArrayList<UserVO>();
					
		for (User user : basePage.getRecords()) {
			resultList.add(UserVO.newInstance(user));
		}
		BasePage<UserVO> result = new BasePage<UserVO>(basePage.getPage(),
				basePage.getPageSize(), basePage.getCurrent(), basePage.getTotal(), resultList);
		return result;
	} 
	
	
}
