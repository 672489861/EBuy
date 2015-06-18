package com.zjw.notice.action;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.zjw.common.action.BaseAction;
import com.zjw.common.util.NavUtil;
import com.zjw.domain.Notice;
import com.zjw.notice.service.NoticeService;

/***
 * 
 * @Description: ����Action
 * 
 * @author zjw
 * 
 * @create time 2015-6-16 ����02:36:11
 */

@Controller
public class NoticeAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource(name = "noticeService")
	private NoticeService noticeService;
	private Notice notice; // ��װ��ϸ��Ϣ ���ظ�ҳ��
	private String navCode; // ��ҳ����Ĵ���
	private String mainPage; // ��ҳ
	private String noticeId; // �������

	public Notice getNotice() {
		return notice;
	}

	public void setNotice(Notice notice) {
		this.notice = notice;
	}

	public String getNavCode() {
		return navCode;
	}

	public void setNavCode(String navCode) {
		this.navCode = navCode;
	}

	public String getMainPage() {
		return mainPage;
	}

	public void setMainPage(String mainPage) {
		this.mainPage = mainPage;
	}

	public String getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(String noticeId) {
		this.noticeId = noticeId;
	}

	/***
	 * 
	 * Description: ���ع�����ϸ��Ϣ
	 * 
	 * @create time 2015-6-16 ����02:41:48
	 * 
	 * @return
	 * 
	 */
	public String showNotice() {
		notice = noticeService.getNoticeById(Integer.parseInt(noticeId));
		mainPage = "/WEB-INF/views/notice/noticeDetails.jsp";
		navCode = NavUtil.getNavCode("������Ϣ");
		return SUCCESS;
	}

}
