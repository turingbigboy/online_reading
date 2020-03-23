package com.yyjj.reading.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yyjj.reading.api.vo.UserVO;
import com.yyjj.reading.db.model.User;
import com.yyjj.reading.domain.context.AjaxResult;
import com.yyjj.reading.domain.service.BasePage;
import com.yyjj.reading.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * User
 * @author yml
 *
 */
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService userService;

	/**
	 * 分页查询
	 * @param vo
	 * @return
	 */
	@GetMapping
	public AjaxResult<BasePage<UserVO>> listBasePage(UserVO vo){
		return AjaxResult.success("",userService.listPage(new QueryWrapper<User>(vo.convert())).converterAll(this::convert));
	}

	/**
	 *用户详情
	 * @param id Userid
	 * @return
	 */
	@GetMapping("/{id:\\d+}")
	public AjaxResult<UserVO> Detail(@PathVariable Integer id) {
		return AjaxResult.success("",UserVO.newInstance(userService.getById(id)));
	}

	/**
	 * 判断当前用户是不是管理员
	 * @param request
	 * @return
	 */
	@GetMapping("check_identity")
	public AjaxResult<Boolean> ckeck(HttpServletRequest request) {
		Object attribute = request.getSession().getAttribute("user");
		if(Objects.isNull(attribute)) {
			return AjaxResult.failed("请登录");
		}else {
			User u = (User) attribute;
			if(u.getIdentity()==2) {
				return AjaxResult.success("", true);
			}else {
				return AjaxResult.success("", false);
			}
		}
	}
	/**
	 * 登录
	 * @param user
	 * @return
	 */
	@PostMapping("/login")
	public AjaxResult<UserVO> login(@RequestBody User user,HttpServletRequest request) {
		User u = userService.lambdaQuery().eq(User::getAccount, user.getAccount()).one();
		if(Objects.isNull(u)) {
			return AjaxResult.failed("登陆失败！无此账户");
		}
		userService.lambdaQuery().eq(User::getAccount, user.getAccount()).eq(User::getPassword, user.getPassword()).one();
		if(u.getPassword().equals(user.getPassword())) {
			request.getSession().setAttribute("user", UserVO.newInstance(u));
			return AjaxResult.success("登陆成功！欢迎您"+u.getName(), UserVO.newInstance(u));
		}else {
			return AjaxResult.failed("登陆失败！密码错误");
		}
	}

	/**
	 * 注册账户
	 * @return
	 */
	@PostMapping("/register")
	public AjaxResult<UserVO> register(@RequestBody User user) {
		User u = userService.lambdaQuery().eq(User::getAccount, user.getAccount()).one();
		if(Objects.isNull(u)) {
			userService.save(user);
		}else {
			return AjaxResult.failed("注册失败！账户名称重复");
		}
		return  AjaxResult.success("注册成功！请登录", UserVO.newInstance(user));
	}

	/**
	 * 註銷登錄
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@GetMapping("logout")
	public AjaxResult logout(HttpServletRequest request, HttpServletResponse respose) throws IOException {
		request.getSession().setAttribute("user", null);
		return AjaxResult.success("注销成功！");
	}

	/**
	 * 新增用户
	 * @param vo
	 * @return
	 *
	 */
	@PostMapping
	public AjaxResult<UserVO> add(@RequestBody @Validated UserVO vo) {
		boolean result = userService.save(vo.convert());
		if(result) {
			return AjaxResult.success("添加成功", UserVO.newInstance(userService.getById(vo.getId())));
		}else {
			return AjaxResult.failed("添加失败");
		}
	}

	/**
	 * 编辑用户
	 * @param vo
	 * @return
	 *
	 */
	@PutMapping
	public AjaxResult<UserVO> modify(@RequestBody @Validated UserVO vo) {
		boolean result = userService.updateById(vo.convert());
		if(result) {
			return AjaxResult.success("修改成功", UserVO.newInstance(userService.getById(vo.getId())));
		}else {
			return AjaxResult.failed("修改失败");
		}
	}

	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	@DeleteMapping("{id:\\d+}")
	public AjaxResult<UserVO> delete(@PathVariable Integer id) {
		boolean result = userService.removeById(id);
		if(result) {
			return AjaxResult.success("删除成功");
		}else {
			return AjaxResult.failed("删除失败");
		}
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
