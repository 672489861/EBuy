package com.zjw.news.service;

import java.util.List;

import com.zjw.domain.News;
import com.zjw.common.util.PageBean;

/***
 * 
 * @Description: ����ҵ���ӿ�
 * 
 * @author zjw
 * 
 * @create time 2015-6-13 ����09:32:04
 */

public interface NewsService {

	/***
	 * 
	 * Description: ������������
	 * 
	 * @create time ����09:33:46
	 * 
	 * @param s_news
	 * @param pageBean
	 * @return
	 * 
	 */
	public List<News> findNewsList(News s_news, PageBean pageBean);

	/***
	 * 
	 * Description: ����id����������ϸ��Ϣ
	 * 
	 * @create time 2015-6-16 ����02:31:54
	 * 
	 * @param newsId
	 * @return
	 * 
	 */
	public News getNewsById(int newsId);
}
