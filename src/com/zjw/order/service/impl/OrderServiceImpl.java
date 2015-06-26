package com.zjw.order.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.zjw.common.dao.BaseDAO;
import com.zjw.common.util.PageBean;
import com.zjw.domain.Order;
import com.zjw.order.service.OrderService;

/***
 * 
 * @Description: 订单业务层实现类
 * 
 * @author zjw
 * 
 * @create time 2015-6-17 下午08:52:58
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {

	@Resource(name = "baseDAO")
	private BaseDAO<Order> baseDAO;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveOrder(Order order) {
		baseDAO.save(order);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Order> findOrder(Order s_order, PageBean pageBean) {
		List<Object> params = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer("from Order ");
		if (s_order != null) {
			// 当前用户的订单
			if (s_order.getUser() != null && s_order.getUser().getId() != 0) {
				sql.append(" and user.id = ?");
				params.add(s_order.getUser().getId());
			}
			// 订单号查询
			if (StringUtils.hasText(s_order.getOrderNo())) {
				sql.append(" and orderNo like ?");
				params.add("%" + s_order.getOrderNo() + "%");
			}
		}
		sql.append(" order by createTime desc");
		if (pageBean != null) {
			return baseDAO.find(sql.toString().replaceFirst("and", "where"),
					params, pageBean);
		} else {
			return baseDAO.find(sql.toString().replaceFirst("and", "where"),
					params);
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateOrderStatus(int status, String orderNo) {
		List<Object> params = new ArrayList<Object>();
		String hql = "update Order set status = ? where orderNo = ?";
		params.add(status);
		params.add(orderNo);
		baseDAO.executeHql(hql, params);
	}

	@Override
	@Transactional(readOnly = true)
	public Map<String, Object> list(Order s_order, PageBean pageBean) {
		StringBuffer hql = new StringBuffer("from Order");
		StringBuffer countHql = new StringBuffer("select count(1) from Order");
		List<Object> params = new ArrayList<Object>();
		if (s_order != null) {
			if (StringUtils.hasText(s_order.getOrderNo())) {
				hql.append(" and orderNo like ?");
				countHql.append(" and orderNo like ?");
				params.add("%" + s_order.getOrderNo() + "%");
			}
			if (s_order.getUser() != null) {
				hql.append(" and user.userName like ?");
				countHql.append(" and user.userName like ?");
				params.add("%" + s_order.getUser().getUserName() + "%");
			}
		}
		List<Order> orders = baseDAO.find(
				hql.toString().replaceFirst("and", "where"), params, pageBean);
		long total = baseDAO.count(
				countHql.toString().replaceFirst("and", "where"), params);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("total", total);
		resultMap.put("rows", orders);
		return resultMap;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Order getOrderById(int id) {
		return baseDAO.get(Order.class, id);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void changeStatus(Order orderManage) {
		Order order = baseDAO.get(Order.class, orderManage.getId());
		order.setStatus(orderManage.getStatus());
		baseDAO.merge(order);
	}

}
