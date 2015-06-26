package com.zjw.common.dao;

import java.io.Serializable;
import java.util.List;

import com.zjw.common.util.PageBean;
import com.zjw.domain.Order;

/***
 * 
 * @Description: 数据库相关操作泛型接口
 * 
 * @author zjw
 * 
 * @create time 2015-6-12 上午11:11:52
 */
public interface BaseDAO<T> {

	/***
	 * 
	 * Description: 根据hql语句查询对象
	 * 
	 * @create time 下午07:02:31
	 * 
	 * @param hql
	 * @return
	 * 
	 */

	public List<T> find(String hql);
	
	/***
	 * 
	 * Description: 根据hql语句和参数查询对象集合
	 * 
	 * @create time 下午07:02:31
	 * 
	 * @param hql
	 * @param params
	 * @return
	 * 
	 */
	
	public List<T> find(String hql, Object[] params);
		
	/***
	 * 
	 * Description: 根据hql语句和参数查询对象并分页
	 * 
	 * @create time 上午10:09:48
	 * 
	 * @param hql
	 * @param params
	 *            参数
	 * @return
	 * 
	 */
	
	public List<Order> find(String hql, List<Object> params);
	
	/***
	 * 
	 * Description: 根据hql语句和参数查询对象并分页
	 * 
	 * @create time 上午10:09:48
	 * 
	 * @param hql
	 * @param params
	 *            参数
	 * @param pageBean
	 *            分页
	 * @return
	 * 
	 */

	public List<T> find(String hql, List<Object> params, PageBean pageBean);
	
	/***
	 * 
	 * Description: 根据hql和参数获取记录数
	 * 
	 * @create time 2015-6-15 下午08:00:01
	 * 
	 * @param hql
	 * @param params
	 * @return
	 * 
	 */

	public long count(String hql, List<Object> params);
	
	/***
	 * 
	 * Description: 根据hql和参数获取记录数
	 *         
	 * @create time 2015-6-16 下午04:03:47
	 *
	 * @param hql
	 * @param param
	 * @return       
	 *
	 */
	public Long count(String hql, Object[] param);
	
	/***
	 * 
	 * Description: 根据编号加载详细信息
	 * 
	 * @create time 2015-6-16 上午11:23:37
	 * 
	 * @param c
	 * @param id
	 * @return
	 * 
	 */
	public T get(Class<T> c, Serializable id);
	
	/***
	 * 
	 * Description: 根据hql和对应的参数加载对象
	 *         
	 * @create time 2015-6-16 下午11:09:53
	 *
	 * @param hql
	 * @param params
	 * @return       
	 *
	 */
	
	public T get(String hql, Object[] params);
	
	/***
	 * 
	 * Description: 添加指定对象
	 *         
	 * @create time 2015-6-16 下午03:36:39
	 *
	 * @param o       
	 *
	 */
	public void merge(T o);
	
	/***
	 * 
	 * Description: 添加指定对象
	 *         
	 * @create time 2015-6-17 下午09:28:17
	 *
	 * @param o       
	 *
	 */
	public Serializable save(T o);
	
	/***
	 * 
	 * Description: 
	 *         
	 * @create time 2015-6-18 上午01:17:21
	 *
	 * @param hql
	 * @param params       
	 *
	 */
	public void executeHql(String hql, List<Object> params);

	/***
	 * 
	 * Description: 删除对象
	 *         
	 * @create time 2015-6-20 下午11:26:02
	 *
	 * @param o       
	 *
	 */
	public void delete(T o);
	
	/***
	 * 
	 * Description: 执行sql
	 *         
	 * @create time 2015-6-23 下午04:24:02
	 *
	 * @param sql       
	 *
	 */
	public void executeSQL(String sql);

}
