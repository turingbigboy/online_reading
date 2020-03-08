package com.yyjj.reading.api.controller;

import com.yyjj.reading.api.vo.ReadingRecordVO;
import com.yyjj.reading.db.model.ReadingRecord;
import com.yyjj.reading.domain.service.BasePage;
import com.yyjj.reading.service.service.ReadingRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


/**
 * ReadingRecord
 * @author yml
 *
 */
@RestController
@RequestMapping("/ReadingRecord")
public class ReadingRecordController {
		
	@Autowired
	ReadingRecordService readingrecordService;
	
	/**
	 * ${controllerName}
	 * @param vo
	 * @return
	 */
	@GetMapping
	public BasePage<ReadingRecordVO> listBasePage(ReadingRecordVO vo){
		return null;
	}
	
	/**
	 *${controllerName}
	 * @param id ReadingRecordid
	 * @return
	 */
	@GetMapping("/{id:\\d+}")
	public ReadingRecordVO Detail(@PathVariable Integer id) {
		
		return null;
	}
	
	
	/**
	 * ReadingRecord
	 * @param vo
	 * @return
	 * 
	 */
	@PostMapping
	public ReadingRecordVO add(@RequestBody @Validated ReadingRecordVO vo) {
		return null;	
	}
	
	/**
	 * ReadingRecord
	 * @param vo
	 * @return
	 * 
	 */
	@PutMapping
	public ReadingRecordVO modify(@RequestBody @Validated ReadingRecordVO vo) {
		return null;	
	}
	
	/**
	 * ReadingRecord
	 * @param id
	 */
	@DeleteMapping("/{id:\\d+}")
	public void remove(@PathVariable Integer id) {
		
	}
	
	private BasePage<ReadingRecordVO> convert(BasePage<ReadingRecord> basePage) {
		List<ReadingRecordVO> resultList = new ArrayList<ReadingRecordVO>();
					
		for (ReadingRecord readingrecord : basePage.getRecords()) {
			resultList.add(ReadingRecordVO.newInstance(readingrecord));
		}
		BasePage<ReadingRecordVO> result = new BasePage<ReadingRecordVO>(basePage.getPage(),
				basePage.getPageSize(), basePage.getCurrent(), basePage.getTotal(), resultList);
		return result;
	} 
	
	
}
