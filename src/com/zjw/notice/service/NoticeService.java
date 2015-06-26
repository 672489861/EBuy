package com.zjw.notice.service;

import java.util.List;
import java.util.Map;

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

	/***
	 * 
	 * Description: 公告管理查询
	 * 
	 * @create time 2015-6-25 上午10:22:30
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
	 * Description: 删除公告
	 * 
	 * @create time 2015-6-25 上午10:26:23
	 * 
	 * @param noticeManage
	 * 
	 */
	public void delete(Notice noticeManage);

	/***
	 * 
	 * Description: 新增或修改公告
	 * 
	 * @create time 2015-6-25 上午10:26:53
	 * 
	 * @param noticeManage
	 * 
	 */
	public void saveNotice(Notice noticeManage);

}
