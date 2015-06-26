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
 * @Description: 新闻Action
 * 
 * @author zjw
 * 
 * @create time 2015-6-16 下午02:49:59
 */

@Controller
public class NewsAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String mainPage; // 主页
	private String navCode; // 分页组件的代码
	private News news; // 封装详细信息 返回给页面
	private String newsId; // 请求参数
	private News s_new; // 新闻管理查询封装
	private News newsManage; // 新闻管理新增修改删除封装
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
	 * Description: 加载新闻详细信息
	 * 
	 * @create time 2015-6-16 下午02:41:48
	 * 
	 * @return
	 * 
	 */
	public String showNews() {
		news = newsService.getNewsById(Integer.parseInt(newsId));
		mainPage = "/WEB-INF/views/news/newsDetails.jsp";
		navCode = NavUtil.getNavCode("新闻信息");
		return SUCCESS;
	}

	/***
	 * 
	 * Description: 跳转新闻管理界面
	 * 
	 * @create time 2015-6-24 下午10:10:18
	 * 
	 * @return
	 * 
	 */
	public String toNewsManager() {
		return "toNewsManagerSuccess";
	}

	/***
	 * 
	 * Description: 新闻管理主界面查询
	 * 
	 * @throws IOException
	 * 
	 * @create time 2015-6-24 下午10:23:21
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
	 * Description: 删除新闻
	 * 
	 * @create time 2015-6-24 下午11:35:53
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
	 * Description: 跳转到新增和修改界面
	 * 
	 * @create time 2015-6-24 下午11:44:51
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
	 * Description: 修改或新增新闻
	 * 
	 * @create time 2015-6-25 上午12:43:24
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
