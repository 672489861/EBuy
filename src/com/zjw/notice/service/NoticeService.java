package com.zjw.notice.service;

import java.util.List;
import java.util.Map;

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

	/***
	 * 
	 * Description: ��������ѯ
	 * 
	 * @create time 2015-6-25 ����10:22:30
	 * 
	 * @param s_notice
	 * @param pageBean
	 * @return
	 * 
	 */
	public Map<String, Object> getNoticeManageList(Notice s_notice,
			PageBean pageBean);

	/***
	 * 
	 * Description: ɾ������
	 * 
	 * @create time 2015-6-25 ����10:26:23
	 * 
	 * @param noticeManage
	 * 
	 */
	public void delete(Notice noticeManage);

	/***
	 * 
	 * Description: �������޸Ĺ���
	 * 
	 * @create time 2015-6-25 ����10:26:53
	 * 
	 * @param noticeManage
	 * 
	 */
	public void saveNotice(Notice noticeManage);

}
