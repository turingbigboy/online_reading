package com.yyjj.reading.domain.service;

import java.util.LinkedList;
import java.util.Objects;

/**
 * 分页参数
 */
public class BasePageVO {

    /**
     * 当前页码
     */
    private Long page;

    /**
     * 每页行数
     */
    private Long pageSize;
    /**
     * 复合排序
     */
    private LinkedList<OrderItems> orders;//排序

    /**
     * 正序OR倒序
     */
    private Boolean asc ;
    public BasePageVO() { }
    
    public BasePageVO(Long page, Long pageSize) {
        super();
        this.page = page;
        this.pageSize = pageSize;
    }

	public Long getPage() {
        return Objects.isNull(page)? 1: page;
    }

    public void setPage(Long page) {
        this.page = page;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }

	public Boolean getAsc() {
		return asc;
	}

	public void setAsc(Boolean asc) {
		this.asc = asc;
	}

	public LinkedList<OrderItems> getOrders() {
		return orders;
	}

	public void setOrders(LinkedList<OrderItems> orders) {
		this.orders = orders;
	}

	@Override
	public String toString() {
		return "BasePageVO [page=" + page + ", pageSize=" + pageSize + ", orders=" + orders + ", asc=" + asc + "]";
	}

	
   
    
}
