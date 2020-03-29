package com.yyjj.reading.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yyjj.reading.api.vo.ReadingRecordVO;
import com.yyjj.reading.db.model.ReadingRecord;
import com.yyjj.reading.domain.context.AjaxResult;
import com.yyjj.reading.domain.service.BasePage;
import com.yyjj.reading.service.service.ReadingRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


/**
 * 阅读记录
 * @author yml
 *
 */
@RestController
@RequestMapping("/ReadingRecord")
public class ReadingRecordController {
		
	@Autowired
	ReadingRecordService readingrecordService;
	
	/**
	 * 用户所有阅读记录
	 * @param vo
	 * @return
	 */
	@GetMapping("{userId:\\d+}")
	public BasePage<ReadingRecordVO> listBasePage(@PathVariable Integer userId, ReadingRecordVO vo){
		return 		readingrecordService.listPage(new QueryWrapper<ReadingRecord>().lambda().eq(ReadingRecord::getUserId,userId)).converterAll(this::convert);

	}

	/**
	 * 删除一条阅读记录
	 * @param id
	 */
	@DeleteMapping("/{id:\\d+}")
	public AjaxResult remove(@PathVariable Integer id) {

		Boolean result = readingrecordService.removeById(id);
		if(result){
			return AjaxResult.success("删除成功");
		}else {
			return AjaxResult.failed("删除失败");
		}
	}
	
	private BasePage<ReadingRecordVO> convert(BasePage<ReadingRecord> basePage) {
		List<ReadingRecordVO> resultList = new ArrayList<ReadingRecordVO>();
					
		for (ReadingRecord readingrecord : basePage.getRecords()) {
			ReadingRecordVO vo = ReadingRecordVO.newInstance(readingrecord);
			resultList.add(vo);
		}
		BasePage<ReadingRecordVO> result = new BasePage<ReadingRecordVO>(basePage.getPage(),
				basePage.getPageSize(), basePage.getCurrent(), basePage.getTotal(), resultList);
		return result;
	} 
	
	
}
