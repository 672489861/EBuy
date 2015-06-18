package com.zjw.common.util;

/***
 * 
 * @Description: 分页组件
 *
 * @author zjw
 * 
 *      @create time  2015-6-12 下午02:20:13
 */
public class PageBean {

	private int page; // 第几页
	private int pageSize; // 每页记录数

	public PageBean(int page, int pageSize) {
		this.page = page;
		this.pageSize = pageSize;
	}

	public int getStart() {
		return (page - 1) * pageSize;
	}

	public int getPageSize() {
		return pageSize;
	}

}
