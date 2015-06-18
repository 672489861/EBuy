package com.zjw.order.service;

import java.util.List;

import com.zjw.common.util.PageBean;
import com.zjw.domain.Order;

/***
 * 
 * @Description: 订单业务层接口
 * 
 * @author zjw
 * 
 * @create time 2015-6-17 下午08:52:23
 */
public interface OrderService {
	
	/***
	 * 
	 * Description: 添加订单
	 *         
	 * @create time 2015-6-17 下午08:54:22
	 *
	 * @param order       
	 *
	 */
	public void saveOrder(Order order);
	
	/***
	 * 
	 * Description: 根据参数查询订单集合
	 *         
	 * @create time 2015-6-17 下午11:45:44
	 *
	 * @param s_order
	 * @param pageBean
	 * @return       
	 *
	 */
	public List<Order> findOrder(Order s_order,PageBean pageBean);
	
	/***
	 * 
	 * Description: 更新指定订单的状态
	 *         
	 * @create time 2015-6-18 上午01:15:21
	 *
	 * @param status
	 * @param orderNo       
	 *
	 */
	public void updateOrderStatus(int status,String orderNo);
	
}
