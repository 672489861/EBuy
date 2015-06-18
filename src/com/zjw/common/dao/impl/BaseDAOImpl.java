package com.zjw.common.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.zjw.common.dao.BaseDAO;
import com.zjw.common.util.PageBean;
import com.zjw.domain.Order;

/***
 * 
 * @Description: 基础数据库操作实现类
 * 
 * @author zjw
 * 
 * @create time 2015-6-12 上午11:13:50
 */

@Repository("baseDAO")
@SuppressWarnings("unchecked")
public class BaseDAOImpl<T> implements BaseDAO<T> {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	@Override
	public long count(String hql, List<Object> params) {
		Query query = createQuery(hql, params);
		return (Long) query.uniqueResult();
	}

	@Override
	public Long count(String hql, Object[] param) {
		Query query = createQuery(hql, param);
		return (Long) query.uniqueResult();
	}

	@Override
	public List<T> find(String hql) {
		return this.getCurrentSession().createQuery(hql).list();
	}

	@Override
	public List<T> find(String hql, Object[] params) {
		Query query = createQuery(hql, params);
		return query.list();
	}

	@Override
	public List<Order> find(String hql, List<Object> params) {
		Query query = createQuery(hql, params);
		return query.list();
	}

	@Override
	public List<T> find(String hql, List<Object> params, PageBean pageBean) {
		Query query = createQuery(hql, params);
		return query.setFirstResult(pageBean.getStart())
				.setMaxResults(pageBean.getPageSize()).list();
	}

	@Override
	public T get(Class<T> c, Serializable id) {
		return (T) getCurrentSession().get(c, id);
	}

	@Override
	public T get(String hql, Object[] params) {
		List<T> list = find(hql, params);
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	@Override
	public void merge(T o) {
		this.getCurrentSession().merge(o);
	}

	@Override
	public Serializable save(T o) {
		return getCurrentSession().save(o);
	}

	@Override
	public void executeHql(String hql, List<Object> params) {
		Query query = createQuery(hql, params);
		query.executeUpdate();
	}

	/***
	 * 
	 * Description: 根据参数创建Query
	 * 
	 * @create time 上午10:13:34
	 * 
	 * @param hql
	 * @param params
	 * @return
	 * 
	 */
	private Query createQuery(String hql, List<Object> params) {
		Query query = this.getCurrentSession().createQuery(hql);
		if (params != null && params.size() > 0) {
			for (int i = 0; i < params.size(); i++) {
				query.setParameter(i, params.get(i));
			}
		}
		return query;
	}

	/***
	 * 
	 * Description: 根据参数创建Query
	 * 
	 * @create time 上午10:13:34
	 * 
	 * @param hql
	 * @param params
	 * @return
	 * 
	 */
	private Query createQuery(String hql, Object[] params) {
		Query query = this.getCurrentSession().createQuery(hql);
		if (params != null && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		return query;
	}

	/***
	 * 
	 * Description: 获取当前Session
	 * 
	 * @create time 上午10:13:31
	 * 
	 * @return
	 * 
	 */
	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

}
