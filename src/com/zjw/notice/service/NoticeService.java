package com.zjw.notice.service;

import java.util.List;

import com.zjw.domain.Notice;
import com.zjw.common.util.PageBean;

/***
 * 
 * @Description: ���¹���ҵ���ӿ�
 * 
 * @author zjw
 * 
 * @create time 2015-6-13 ����09:32:04
 */

public interface NoticeService {

	/***
	 * 
	 * Description: �������¹���
	 * 
	 * @create time ����09:33:46
	 * 
	 * @param s_notice
	 * @param pageBean
	 * @return
	 * 
	 */
	public List<Notice> findNoticeList(Notice s_notice, PageBean pageBean);

	/***
	 * 
	 * Description: ����id������ϸ������Ϣ
	 * 
	 * @create time 2015-6-16 ����02:19:08
	 * 
	 * @param noticeId
	 * @return
	 * 
	 */
	public Notice getNoticeById(int noticeId);

}
