package com.zjw.order.service.impl;

import java.util.ArrayList;
import java.util.List;

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

}
