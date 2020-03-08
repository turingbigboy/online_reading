package com.yyjj.reading.service.bo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

public interface IBaseBO<T> {

	void accpet(QueryWrapper<T> queryWrapper);

}
