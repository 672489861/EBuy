package com.zjw.news.action;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.zjw.common.action.BaseAction;
import com.zjw.common.util.NavUtil;
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
}
