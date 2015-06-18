package com.zjw.shopping.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.zjw.common.action.BaseAction;
import com.zjw.common.util.NavUtil;
import com.zjw.common.util.ResponseUtil;
import com.zjw.domain.Product;
import com.zjw.domain.ShoppingCart;
import com.zjw.domain.ShoppingCartItem;
import com.zjw.domain.User;
import com.zjw.product.service.ProductService;

/***
 * 
 * @Description: ����Action
 * 
 * @author zjw
 * 
 * @create time 2015-6-17 ����01:51:05
 */
@Controller
public class ShoppingAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource(name = "productService")
	private ProductService productService;

	private String productId; // ��Ʒ���
	private String mainPage; // ��ʾ��ҳ��
	private String navCode; // ����
	private String count; // ��Ʒ����

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getProductId() {
		return productId;
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

	public void setProductId(String productId) {
		this.productId = productId;
	}

	/**
	 * 
	 * Description: ����Ʒ��ӵ����ﳵ
	 * 
	 * @throws IOException
	 * 
	 * @create time 2015-6-17 ����02:14:51
	 * 
	 * 
	 */
	public void addShoppingCartItem() throws IOException {
		Product product = productService.getProductById(Integer
				.parseInt(productId));
		// ��ȡsession�еĹ��ﳵ
		ShoppingCart shoppingCart = (ShoppingCart) session.get("shoppingCart");
		if (shoppingCart == null) { // ��һ�μ��빺�ﳵ
			shoppingCart = new ShoppingCart();
			User currentUser = (User) session.get("currentUser");
			shoppingCart.setUserId(currentUser.getId());
		}
		// �жϵ�ǰ��ӵ���Ʒ�ڹ��ﳵ���Ƿ���� ���ھͽ�����+1
		List<ShoppingCartItem> shoppingCartItems = shoppingCart
				.getShoppingCartItems();
		boolean flag = true;
		for (ShoppingCartItem cartItem : shoppingCartItems) {
			if (cartItem.getProduct().getId() == Integer.parseInt(productId)) {
				cartItem.setCount(cartItem.getCount() + 1);
				flag = false;
				break;
			}
		}
		// �����ھͼ��빺�ﳵ
		if (flag) {
			ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
			shoppingCartItem.setCount(1);
			shoppingCartItem.setProduct(product);
			shoppingCartItems.add(shoppingCartItem);
		}
		session.put("shoppingCart", shoppingCart);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(),
				jsonObject.toString());
	}

	/***
	 * 
	 * Description: ������ﳵ
	 * 
	 * @create time 2015-6-17 ����02:44:05
	 * 
	 * @return
	 */

	public String list() {
		mainPage = "/WEB-INF/views/shopping/shopping.jsp";
		navCode = NavUtil.getNavCode("���ﳵ");
		return SUCCESS;
	}

	/**
	 * 
	 * Description: ֱ�ӹ�����Ʒ��ֱ���������ﳵ
	 * 
	 * @throws IOException
	 * 
	 * @create time 2015-6-17 ����02:14:51
	 * 
	 * 
	 */
	public String buy() throws IOException {
		Product product = productService.getProductById(Integer
				.parseInt(productId));
		// ��ȡsession�еĹ��ﳵ
		ShoppingCart shoppingCart = (ShoppingCart) session.get("shoppingCart");
		if (shoppingCart == null) { // ��һ�μ��빺�ﳵ
			shoppingCart = new ShoppingCart();
			User currentUser = (User) session.get("currentUser");
			shoppingCart.setUserId(currentUser.getId());
		}
		// �жϵ�ǰ��ӵ���Ʒ�ڹ��ﳵ���Ƿ���� ���ھͽ�����+1
		List<ShoppingCartItem> shoppingCartItems = shoppingCart
				.getShoppingCartItems();
		boolean flag = true;
		for (ShoppingCartItem cartItem : shoppingCartItems) {
			if (cartItem.getProduct().getId() == Integer.parseInt(productId)) {
				cartItem.setCount(cartItem.getCount() + 1);
				flag = false;
				break;
			}
		}
		// �����ھͼ��빺�ﳵ
		if (flag) {
			ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
			shoppingCartItem.setCount(1);
			shoppingCartItem.setProduct(product);
			shoppingCartItems.add(shoppingCartItem);
		}
		session.put("shoppingCart", shoppingCart);
		mainPage = "/WEB-INF/views/shopping/shopping.jsp";
		navCode = NavUtil.getNavCode("���ﳵ");
		return SUCCESS;
	}

	/***
	 * 
	 * Description: ˢ��sesion�й��ﳵ����Ʒ����
	 * 
	 * @throws IOException
	 * 
	 * @create time 2015-6-17 ����06:04:03
	 * 
	 * 
	 */

	public void updateShoppingCartItem() throws IOException {
		// ��ȡsession�еĹ��ﳵ
		ShoppingCart shoppingCart = (ShoppingCart) session.get("shoppingCart");
		List<ShoppingCartItem> shoppingCartItems = shoppingCart
				.getShoppingCartItems();
		// ���¶�Ӧ���ﳵ��Ŀ����
		for (ShoppingCartItem cartItem : shoppingCartItems) {
			if (cartItem.getProduct().getId() == Integer.parseInt(productId)) {
				cartItem.setCount(Integer.parseInt(count));
				break;
			}
		}
		// ����session
		session.put("shoppingCart", shoppingCart);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(),
				jsonObject.toString());
	}

	/***
	 * 
	 * Description: ɾ����Ʒ��Ŀ
	 * 
	 * @throws IOException
	 * 
	 * @create time 2015-6-17 ����06:55:49
	 * 
	 * 
	 */

	public void removeShoppingCartItem() throws IOException {
		// ��ȡsession�еĹ��ﳵ
		ShoppingCart shoppingCart = (ShoppingCart) session.get("shoppingCart");
		List<ShoppingCartItem> shoppingCartItems = shoppingCart
				.getShoppingCartItems();
		// ɾ����Ӧ��Ŀ
		for (ShoppingCartItem shoppingCartItem : shoppingCartItems) {
			if (shoppingCartItem.getProduct().getId() == Integer
					.parseInt(productId)) {
				shoppingCartItems.remove(shoppingCartItem);
				break;
			}
		}
		// ����session
		session.put("shoppingCart", shoppingCart);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(),
				jsonObject.toString());
	}

}
