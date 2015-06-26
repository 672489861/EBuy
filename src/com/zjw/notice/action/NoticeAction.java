package com.zjw.notice.action;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.zjw.common.action.BaseAction;
import com.zjw.common.constant.Constants;
import com.zjw.common.util.DateTimeUtil;
import com.zjw.common.util.GsonUtil;
import com.zjw.common.util.NavUtil;
import com.zjw.common.util.PageBean;
import com.zjw.common.util.ResponseUtil;
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
	private String page; // 分页
	private Notice s_notice; // 封装查询
	private Notice noticeManage; // 封装修改新增删除数据

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public Notice getS_notice() {
		return s_notice;
	}

	public void setS_notice(Notice s_notice) {
		this.s_notice = s_notice;
	}

	public Notice getNoticeManage() {
		return noticeManage;
	}

	public void setNoticeManage(Notice noticeManage) {
		this.noticeManage = noticeManage;
	}

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

	/***
	 * 
	 * Description: 跳转公告管理界面
	 * 
	 * @create time 2015-6-25 上午09:55:13
	 * 
	 * @return
	 * 
	 */
	public String toNoticeManager() {
		return "toNoticeManager_success";
	}

	/***
	 * 
	 * Description: 公告管理查询
	 * 
	 * @throws IOException
	 * 
	 * @create time 2015-6-25 上午10:16:09
	 * 
	 * 
	 */
	public void list() throws IOException {
		PageBean pageBean = new PageBean(Integer.parseInt(page),
				Constants.ADMINMANAGER_SIZE);
		Map<String, Object> resultMap = noticeService.getNoticeManageList(
				s_notice, pageBean);
		ResponseUtil.write(ServletActionContext.getResponse(),
				GsonUtil.toJsonWithExposrGson(resultMap));
	}

	/***
	 * 
	 * Description: 删除公告
	 * 
	 * @create time 2015-6-25 上午10:24:31
	 * 
	 * @throws IOException
	 * 
	 */
	public void delete() throws IOException {
		noticeService.delete(noticeManage);
		JSONObject result = new JSONObject();
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(),
				result.toString());
	}

	/**
	 * 
	 * Description: 跳转到公告新增和修改界面
	 * 
	 * @create time 2015-6-25 上午10:24:43
	 * 
	 * @return
	 * 
	 */
	public String toNoticeUpdate() {
		if (noticeManage.getId() != 0) {
			noticeManage = noticeService.getNoticeById(noticeManage.getId());
		}
		return "toNoticeUpdate_success";
	}

	/***
	 * 
	 * Description: 修改或新增新闻
	 * 
	 * @create time 2015-6-25 上午10:25:26
	 * 
	 * @throws Exception
	 * 
	 */
	public void saveNotice() throws Exception {
		noticeManage.setCreateTime(DateTimeUtil.strToDate(
				DateTimeUtil.getNowDate("yyyy-MM-dd HH:mm:ss"),
				"yyyy-MM-dd HH:mm:ss"));
		noticeService.saveNotice(noticeManage);
		JSONObject result = new JSONObject();
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(),
				result.toString());
	}

}
