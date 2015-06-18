package com.zjw.news.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.zjw.domain.News;
import com.zjw.news.service.NewsService;
import com.zjw.common.dao.BaseDAO;
import com.zjw.common.util.PageBean;

/***
 * 
 * @Description: 新闻业务层实现类
 * 
 * @author zjw
 * 
 * @create time 2015-6-13 上午09:54:17
 */

@Service("newsService")
public class NewsServiceImpl implements NewsService {

	@Resource(name = "baseDAO")
	private BaseDAO<News> baseDAO;

	@Override
	@Transactional(readOnly = true)
	public List<News> findNewsList(News s_news, PageBean pageBean) {
		List<Object> params = new ArrayList<Object>(); // 封装参数
		StringBuffer sql = new StringBuffer("from News");
		// 后台标签管理中的查询
		if (s_news != null) {
			if (StringUtils.hasText(s_news.getTitle())) {
				sql.append(" and title like ?");
				params.add("%" + s_news.getTitle() + "%");
			}
		}
		sql.append(" order by createTime desc");
		if (pageBean != null) {
			return baseDAO.find(sql.toString().replaceFirst("and", "where"),
					params, pageBean);
		}
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public News getNewsById(int newsId) {
		return baseDAO.get(News.class, newsId);
	}

}
