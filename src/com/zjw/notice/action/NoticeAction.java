package com.zjw.notice.action;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.zjw.common.action.BaseAction;
import com.zjw.common.util.NavUtil;
import com.zjw.domain.Notice;
import com.zjw.notice.service.NoticeService;

/***
 * 
 * @Description: 公告Action
 * 
 * @author zjw
 * 
 * @create time 2015-6-16 下午02:36:11
 */

@Controller
public class NoticeAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource(name = "noticeService")
	private NoticeService noticeService;
	private Notice notice; // 封装详细信息 返回给页面
	private String navCode; // 分页组件的代码
	private String mainPage; // 主页
	private String noticeId; // 请求参数

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
	 * Description: 加载公告详细信息
	 * 
	 * @create time 2015-6-16 下午02:41:48
	 * 
	 * @return
	 * 
	 */
	public String showNotice() {
		notice = noticeService.getNoticeById(Integer.parseInt(noticeId));
		mainPage = "/WEB-INF/views/notice/noticeDetails.jsp";
		navCode = NavUtil.getNavCode("公告信息");
		return SUCCESS;
	}

}
