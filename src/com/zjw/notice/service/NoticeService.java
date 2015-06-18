package com.zjw.notice.service;

import java.util.List;

import com.zjw.domain.Notice;
import com.zjw.common.util.PageBean;

/***
 * 
 * @Description: 最新公告业务层接口
 * 
 * @author zjw
 * 
 * @create time 2015-6-13 上午09:32:04
 */

public interface NoticeService {

	/***
	 * 
	 * Description: 加载最新公告
	 * 
	 * @create time 上午09:33:46
	 * 
	 * @param s_notice
	 * @param pageBean
	 * @return
	 * 
	 */
	public List<Notice> findNoticeList(Notice s_notice, PageBean pageBean);

	/***
	 * 
	 * Description: 根据id加载详细公告信息
	 * 
	 * @create time 2015-6-16 下午02:19:08
	 * 
	 * @param noticeId
	 * @return
	 * 
	 */
	public Notice getNoticeById(int noticeId);

}
