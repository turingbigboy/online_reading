package com.yyjj.reading.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yyjj.reading.api.vo.TypeVO;
import com.yyjj.reading.db.model.BookType;
import com.yyjj.reading.db.model.Type;
import com.yyjj.reading.domain.context.AjaxResult;
import com.yyjj.reading.domain.service.BasePage;
import com.yyjj.reading.service.service.BookTypeService;
import com.yyjj.reading.service.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * 书籍分类接口
 * @author yml
 *
 */
@RestController
@RequestMapping("/type")
public class TypeController {
		
	@Autowired
	TypeService typeService;

	@Autowired
	BookTypeService bookTypeService;
	/**
	 * 获取所有的分类
	 * @param vo
	 * @return
	 */
	@GetMapping
	public AjaxResult<TypeVO> listBasePage(TypeVO vo){
		return AjaxResult.success("",typeService.listPage(new QueryWrapper<Type>(vo.convert())).converterAll(this::convert));
	}
	
	/**
	 *获取指定分类
	 * @param id Typeid
	 * @return
	 */
	@GetMapping("/{id:\\d+}")
	public AjaxResult<TypeVO> Detail(@PathVariable Integer id) {
		return AjaxResult.success("",TypeVO.newInstance(typeService.getById(id)));
	}
	
	
	/**
	 * 新增分类
	 * @param vo
	 * @return
	 * 
	 */
	@PostMapping
	public AjaxResult<TypeVO> add(@RequestBody @Validated TypeVO vo) {
		Type t = typeService.lambdaQuery().eq(Type::getTypeName,vo.getTypeName()).one();
		if(Objects.nonNull(t)){
			return AjaxResult.failed("该分类已存在");
		}
		Type type = vo.convert();
		Boolean result = typeService.save(type);
		if(result){
			return AjaxResult.success("添加成功",typeService.getById(type.getId()));
		}else {
			return AjaxResult.failed("添加失败");
		}
	}
	
	/**
	 * 修改分类
	 * @param vo
	 * @return
	 * 
	 */
	@PutMapping
	public AjaxResult<TypeVO> modify(@RequestBody @Validated TypeVO vo) {
		Type t = typeService.lambdaQuery().eq(Type::getTypeName,vo.getTypeName()).one();
		if(Objects.nonNull(t)){
			return AjaxResult.success("名称重复，无法修改");
		}
		Type type = vo.convert();
		Boolean result = typeService.updateById(vo.convert());
		if(result){
			return AjaxResult.success("修改成功",typeService.getById(type.getId()));
		}else {
			return AjaxResult.failed("修改失败");
		}
	}
	
	/**
	 * 删除分类
	 * @param id
	 */
	@DeleteMapping("/{id:\\d+}")
	public AjaxResult remove(@PathVariable Integer id) {
		bookTypeService.remove(new QueryWrapper<BookType>().lambda().eq(BookType::getTypeId,id));
		Boolean result = typeService.removeById(id);
		if(result){
			return AjaxResult.success("删除成功");
		}else {
			return AjaxResult.failed("删除失败");
		}
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
