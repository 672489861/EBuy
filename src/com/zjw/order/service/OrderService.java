package com.zjw.order.service;

import java.util.List;

import com.zjw.common.util.PageBean;
import com.zjw.domain.Order;

/***
 * 
 * @Description: ����ҵ���ӿ�
 * 
 * @author zjw
 * 
 * @create time 2015-6-17 ����08:52:23
 */
public interface OrderService {
	
	/***
	 * 
	 * Description: ��Ӷ���
	 *         
	 * @create time 2015-6-17 ����08:54:22
	 *
	 * @param order       
	 *
	 */
	public void saveOrder(Order order);
	
	/***
	 * 
	 * Description: ���ݲ�����ѯ��������
	 *         
	 * @create time 2015-6-17 ����11:45:44
	 *
	 * @param s_order
	 * @param pageBean
	 * @return       
	 *
	 */
	public List<Order> findOrder(Order s_order,PageBean pageBean);
	
	/***
	 * 
	 * Description: ����ָ��������״̬
	 *         
	 * @create time 2015-6-18 ����01:15:21
	 *
	 * @param status
	 * @param orderNo       
	 *
	 */
	public void updateOrderStatus(int status,String orderNo);
	
}
