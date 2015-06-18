package com.zjw.common.dao;

import java.io.Serializable;
import java.util.List;

import com.zjw.common.util.PageBean;
import com.zjw.domain.Order;

/***
 * 
 * @Description: ���ݿ���ز������ͽӿ�
 * 
 * @author zjw
 * 
 * @create time 2015-6-12 ����11:11:52
 */
public interface BaseDAO<T> {

	/***
	 * 
	 * Description: ����hql����ѯ����
	 * 
	 * @create time ����07:02:31
	 * 
	 * @param hql
	 * @return
	 * 
	 */

	public List<T> find(String hql);
	
	/***
	 * 
	 * Description: ����hql���Ͳ�����ѯ���󼯺�
	 * 
	 * @create time ����07:02:31
	 * 
	 * @param hql
	 * @param params
	 * @return
	 * 
	 */
	
	public List<T> find(String hql, Object[] params);
		
	/***
	 * 
	 * Description: ����hql���Ͳ�����ѯ���󲢷�ҳ
	 * 
	 * @create time ����10:09:48
	 * 
	 * @param hql
	 * @param params
	 *            ����
	 * @return
	 * 
	 */
	
	public List<Order> find(String hql, List<Object> params);
	
	/***
	 * 
	 * Description: ����hql���Ͳ�����ѯ���󲢷�ҳ
	 * 
	 * @create time ����10:09:48
	 * 
	 * @param hql
	 * @param params
	 *            ����
	 * @param pageBean
	 *            ��ҳ
	 * @return
	 * 
	 */

	public List<T> find(String hql, List<Object> params, PageBean pageBean);
	
	/***
	 * 
	 * Description: ����hql�Ͳ�����ȡ��¼��
	 * 
	 * @create time 2015-6-15 ����08:00:01
	 * 
	 * @param hql
	 * @param params
	 * @return
	 * 
	 */

	public long count(String hql, List<Object> params);
	
	/***
	 * 
	 * Description: ����hql�Ͳ�����ȡ��¼��
	 *         
	 * @create time 2015-6-16 ����04:03:47
	 *
	 * @param hql
	 * @param param
	 * @return       
	 *
	 */
	public Long count(String hql, Object[] param);
	
	/***
	 * 
	 * Description: ���ݱ�ż�����ϸ��Ϣ
	 * 
	 * @create time 2015-6-16 ����11:23:37
	 * 
	 * @param c
	 * @param id
	 * @return
	 * 
	 */
	public T get(Class<T> c, Serializable id);
	
	/***
	 * 
	 * Description: ����hql�Ͷ�Ӧ�Ĳ������ض���
	 *         
	 * @create time 2015-6-16 ����11:09:53
	 *
	 * @param hql
	 * @param params
	 * @return       
	 *
	 */
	
	public T get(String hql, Object[] params);
	
	/***
	 * 
	 * Description: ���ָ������
	 *         
	 * @create time 2015-6-16 ����03:36:39
	 *
	 * @param o       
	 *
	 */
	public void merge(T o);
	
	/***
	 * 
	 * Description: ���ָ������
	 *         
	 * @create time 2015-6-17 ����09:28:17
	 *
	 * @param o       
	 *
	 */
	public Serializable save(T o);
	
	/***
	 * 
	 * Description: 
	 *         
	 * @create time 2015-6-18 ����01:17:21
	 *
	 * @param hql
	 * @param params       
	 *
	 */
	public void executeHql(String hql, List<Object> params);

}
