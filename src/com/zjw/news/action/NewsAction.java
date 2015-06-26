package com.zjw.news.action;

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
import com.zjw.domain.News;
import com.zjw.news.service.NewsService;

/***
 * 
 * @Description: ����Action
 * 
 * @author zjw
 * 
 * @create time 2015-6-16 ����02:49:59
 */

@Controller
public class NewsAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String mainPage; // ��ҳ
	private String navCode; // ��ҳ����Ĵ���
	private News news; // ��װ��ϸ��Ϣ ���ظ�ҳ��
	private String newsId; // �������
	private News s_new; // ���Ź����ѯ��װ
	private News newsManage; // ���Ź��������޸�ɾ����װ
	private String page;

	public News getS_new() {
		return s_new;
	}

	public void setS_new(News s_new) {
		this.s_new = s_new;
	}

	public News getNewsManage() {
		return newsManage;
	}

	public void setNewsManage(News newsManage) {
		this.newsManage = newsManage;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	@Resource(name = "newsService")
	private NewsService newsService;

	public String getMainPage() {
		return mainPage;
	}

	public String getNavCode() {
		return navCode;
	}

	public News getNews() {
		return news;
	}

	public String getNewsId() {
		return newsId;
	}

	public void setMainPage(String mainPage) {
		this.mainPage = mainPage;
	}

	public void setNavCode(String navCode) {
		this.navCode = navCode;
	}

	public void setNews(News news) {
		this.news = news;
	}

	public void setNewsId(String newsId) {
		this.newsId = newsId;
	}

	/***
	 * 
	 * Description: ����������ϸ��Ϣ
	 * 
	 * @create time 2015-6-16 ����02:41:48
	 * 
	 * @return
	 * 
	 */
	public String showNews() {
		news = newsService.getNewsById(Integer.parseInt(newsId));
		mainPage = "/WEB-INF/views/news/newsDetails.jsp";
		navCode = NavUtil.getNavCode("������Ϣ");
		return SUCCESS;
	}

	/***
	 * 
	 * Description: ��ת���Ź������
	 * 
	 * @create time 2015-6-24 ����10:10:18
	 * 
	 * @return
	 * 
	 */
	public String toNewsManager() {
		return "toNewsManagerSuccess";
	}

	/***
	 * 
	 * Description: ���Ź����������ѯ
	 * 
	 * @throws IOException
	 * 
	 * @create time 2015-6-24 ����10:23:21
	 * 
	 * 
	 */
	public void list() throws IOException {
		PageBean pageBean = new PageBean(Integer.parseInt(page),
				Constants.ADMINMANAGER_SIZE);
		Map<String, Object> resultMap = newsService.getNewsManageList(s_new,
				pageBean);
		ResponseUtil.write(ServletActionContext.getResponse(),
				GsonUtil.toJsonWithExposrGson(resultMap));
	}

	/***
	 * 
	 * Description: ɾ������
	 * 
	 * @create time 2015-6-24 ����11:35:53
	 * 
	 * @throws IOException
	 * 
	 */
	public void delete() throws IOException {
		newsService.delete(newsManage);
		JSONObject result = new JSONObject();
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(),
				result.toString());
	}

	/***
	 * 
	 * Description: ��ת���������޸Ľ���
	 * 
	 * @create time 2015-6-24 ����11:44:51
	 * 
	 * @return
	 * 
	 */
	public String toNewsUpdate() {
		if (newsManage.getId() != 0) {
			newsManage = newsService.getNewsById(newsManage.getId());
		}
		return "toNewsUpdate_success";
	}

	/***
	 * 
	 * Description: �޸Ļ���������
	 * 
	 * @create time 2015-6-25 ����12:43:24
	 * 
	 * @return
	 * 
	 */
	public void saveNews() throws Exception {
		newsManage.setCreateTime(DateTimeUtil.strToDate(
				DateTimeUtil.getNowDate("yyyy-MM-dd HH:mm:ss"),
				"yyyy-MM-dd HH:mm:ss"));
		newsService.saveNews(newsManage);
		JSONObject result = new JSONObject();
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(),
				result.toString());
	}
}
