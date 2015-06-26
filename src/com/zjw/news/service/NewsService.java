package com.zjw.news.service;

import java.util.List;
import java.util.Map;

import com.zjw.domain.News;
import com.zjw.common.util.PageBean;

/***
 * 
 * @Description: 新闻业务层接口
 * 
 * @author zjw
 * 
 * @create time 2015-6-13 上午09:32:04
 */

public interface NewsService {

	/***
	 * 
	 * Description: 加载最新新闻
	 * 
	 * @create time 上午09:33:46
	 * 
	 * @param s_news
	 * @param pageBean
	 * @return
	 * 
	 */
	public List<News> findNewsList(News s_news, PageBean pageBean);

	/***
	 * 
	 * Description: 根据id加载新闻详细信息
	 * 
	 * @create time 2015-6-16 下午02:31:54
	 * 
	 * @param newsId
	 * @return
	 * 
	 */
	public News getNewsById(int newsId);

	/***
	 * 
	 * Description: 新闻管理后台查询
	 * 
	 * @create time 2015-6-24 下午10:34:05
	 * 
	 * @param s_new
	 * @param pageBean
	 * @return
	 * 
	 */
	public Map<String, Object> getNewsManageList(News s_new, PageBean pageBean);

	/***
	 * 
	 * Description: 删除新闻
	 * 
	 * @create time 2015-6-24 下午11:40:33
	 * 
	 * @param newsManage
	 * 
	 */
	public void delete(News newsManage);

	/***
	 * 
	 * Description: 新增或修改新闻
	 * 
	 * @create time 2015-6-25 上午12:46:00
	 * 
	 * @param newsManage
	 * 
	 */
	public void saveNews(News newsManage);
}
