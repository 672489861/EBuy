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
	private String page; // ��ҳ
	private Notice s_notice; // ��װ��ѯ
	private Notice noticeManage; // ��װ�޸�����ɾ������

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

	/***
	 * 
	 * Description: ��ת����������
	 * 
	 * @create time 2015-6-25 ����09:55:13
	 * 
	 * @return
	 * 
	 */
	public String toNoticeManager() {
		return "toNoticeManager_success";
	}

	/***
	 * 
	 * Description: ��������ѯ
	 * 
	 * @throws IOException
	 * 
	 * @create time 2015-6-25 ����10:16:09
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
	 * Description: ɾ������
	 * 
	 * @create time 2015-6-25 ����10:24:31
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
	 * Description: ��ת�������������޸Ľ���
	 * 
	 * @create time 2015-6-25 ����10:24:43
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
	 * Description: �޸Ļ���������
	 * 
	 * @create time 2015-6-25 ����10:25:26
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
