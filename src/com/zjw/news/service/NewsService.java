package com.zjw.news.service;

import java.util.List;
import java.util.Map;

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

	/***
	 * 
	 * Description: ���Ź����̨��ѯ
	 * 
	 * @create time 2015-6-24 ����10:34:05
	 * 
	 * @param s_new
	 * @param pageBean
	 * @return
	 * 
	 */
	public Map<String, Object> getNewsManageList(News s_new, PageBean pageBean);

	/***
	 * 
	 * Description: ɾ������
	 * 
	 * @create time 2015-6-24 ����11:40:33
	 * 
	 * @param newsManage
	 * 
	 */
	public void delete(News newsManage);

	/***
	 * 
	 * Description: �������޸�����
	 * 
	 * @create time 2015-6-25 ����12:46:00
	 * 
	 * @param newsManage
	 * 
	 */
	public void saveNews(News newsManage);
}
