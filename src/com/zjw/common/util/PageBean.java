package com.zjw.common.util;

/***
 * 
 * @Description: ��ҳ���
 *
 * @author zjw
 * 
 *      @create time  2015-6-12 ����02:20:13
 */
public class PageBean {

	private int page; // �ڼ�ҳ
	private int pageSize; // ÿҳ��¼��

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
