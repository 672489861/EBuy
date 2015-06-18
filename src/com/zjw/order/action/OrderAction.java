package com.zjw.order.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.zjw.common.action.BaseAction;
import com.zjw.common.exception.MyException;
import com.zjw.common.util.DateTimeUtil;
import com.zjw.common.util.NavUtil;
import com.zjw.common.util.ResponseUtil;
import com.zjw.domain.Order;
import com.zjw.domain.OrderProduct;
import com.zjw.domain.ShoppingCart;
import com.zjw.domain.ShoppingCartItem;
import com.zjw.domain.User;
import com.zjw.order.service.OrderService;

/***
 * 
 * @Description: ����Action
 * 
 * @author zjw
 * 
 * @create time 2015-6-17 ����08:56:03
 */

@Controller
public class OrderAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource(name = "orderService")
	private OrderService orderService;

	private String mainPage; // ��ҳ��
	private String navCode; // ��ǰλ�ô���
	private Order s_order; // ��װ��ѯ����
	private List<Order> orders;
	private int status; // ����״̬
	private String orderNo; // �޸ĵĶ�����

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Order getS_order() {
		return s_order;
	}

	public void setS_order(Order s_order) {
		this.s_order = s_order;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public String getMainPage() {
		return mainPage;
	}

	public void setMainPage(String mainPage) {
		this.mainPage = mainPage;
	}

	public String getNavCode() {
		return navCode;
	}

	public void setNavCode(String navCode) {
		this.navCode = navCode;
	}

	/***
	 * 
	 * Description: ��Ӷ���
	 * 
	 * @create time 2015-6-17 ����08:58:01
	 * 
	 * @return
	 * @throws MyException
	 * 
	 */
	public String save() throws MyException {
		// ��Session�л�ȡ���ﳵ
		ShoppingCart shoppingCart = (ShoppingCart) session.get("shoppingCart");
		User user = (User) session.get("currentUser");
		// ��Ӷ���
		Order order = new Order();
		order.setUser(user);
		order.setCreateTime(new Date());
		order.setStatus(1);
		order.setOrderNo(DateTimeUtil.getNowDate("yyyymmddhhmmss"));
		// �������ﳵ �����ܽ��
		float totalPrice = 0;
		List<OrderProduct> orderProducts = new ArrayList<OrderProduct>();
		for (ShoppingCartItem shoppingCartItem : shoppingCart
				.getShoppingCartItems()) {
			OrderProduct orderProduct = new OrderProduct();
			orderProduct.setNum(shoppingCartItem.getCount());
			orderProduct.setProduct(shoppingCartItem.getProduct());
			orderProduct.setOrder(order);
			orderProducts.add(orderProduct);
			totalPrice += shoppingCartItem.getCount()
					* shoppingCartItem.getProduct().getPrice(); // �����ܽ��
		}
		order.setOrderProductList(orderProducts); // ��������
		order.setCost(totalPrice);
		orderService.saveOrder(order);
		// ��չ��ﳵ
		session.remove("shoppingCart");
		navCode = NavUtil.getNavCode("����");
		mainPage = "/WEB-INF/views/shopping/shopping-result.jsp";
		return SUCCESS;
	}

	/***
	 * 
	 * Description: �û���������
	 * 
	 * @create time 2015-6-17 ����11:54:43
	 * 
	 * @return
	 * 
	 */
	public String findOrder() {
		User currentUser = (User) session.get("currentUser");
		if (s_order == null) {
			s_order = new Order();
			s_order.setUser(currentUser);
		}
		orders = orderService.findOrder(s_order, null);
		navCode = NavUtil.getNavCode("���˶�������");
		mainPage = "/WEB-INF/views/userCenter/orderList.jsp";
		return "orderList";
	}

	/***
	 * 
	 * Description: ȷ���ջ�����
	 * 
	 * @create time 2015-6-18 ����01:20:56
	 * 
	 * @throws IOException
	 * 
	 */
	public void confirmReceive() throws IOException {
		orderService.updateOrderStatus(status, orderNo);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(),
				jsonObject.toString());
	}
}
