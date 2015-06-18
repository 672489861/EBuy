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
 * @Description: 购物Action
 * 
 * @author zjw
 * 
 * @create time 2015-6-17 下午01:51:05
 */
@Controller
public class ShoppingAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource(name = "productService")
	private ProductService productService;

	private String productId; // 商品编号
	private String mainPage; // 显示的页面
	private String navCode; // 导航
	private String count; // 商品数量

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
	 * Description: 将商品添加到购物车
	 * 
	 * @throws IOException
	 * 
	 * @create time 2015-6-17 下午02:14:51
	 * 
	 * 
	 */
	public void addShoppingCartItem() throws IOException {
		Product product = productService.getProductById(Integer
				.parseInt(productId));
		// 获取session中的购物车
		ShoppingCart shoppingCart = (ShoppingCart) session.get("shoppingCart");
		if (shoppingCart == null) { // 第一次加入购物车
			shoppingCart = new ShoppingCart();
			User currentUser = (User) session.get("currentUser");
			shoppingCart.setUserId(currentUser.getId());
		}
		// 判断当前添加的商品在购物车中是否存在 存在就将数量+1
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
		// 不存在就加入购物车
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
	 * Description: 浏览购物车
	 * 
	 * @create time 2015-6-17 下午02:44:05
	 * 
	 * @return
	 */

	public String list() {
		mainPage = "/WEB-INF/views/shopping/shopping.jsp";
		navCode = NavUtil.getNavCode("购物车");
		return SUCCESS;
	}

	/**
	 * 
	 * Description: 直接购买商品就直接跳到购物车
	 * 
	 * @throws IOException
	 * 
	 * @create time 2015-6-17 下午02:14:51
	 * 
	 * 
	 */
	public String buy() throws IOException {
		Product product = productService.getProductById(Integer
				.parseInt(productId));
		// 获取session中的购物车
		ShoppingCart shoppingCart = (ShoppingCart) session.get("shoppingCart");
		if (shoppingCart == null) { // 第一次加入购物车
			shoppingCart = new ShoppingCart();
			User currentUser = (User) session.get("currentUser");
			shoppingCart.setUserId(currentUser.getId());
		}
		// 判断当前添加的商品在购物车中是否存在 存在就将数量+1
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
		// 不存在就加入购物车
		if (flag) {
			ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
			shoppingCartItem.setCount(1);
			shoppingCartItem.setProduct(product);
			shoppingCartItems.add(shoppingCartItem);
		}
		session.put("shoppingCart", shoppingCart);
		mainPage = "/WEB-INF/views/shopping/shopping.jsp";
		navCode = NavUtil.getNavCode("购物车");
		return SUCCESS;
	}

	/***
	 * 
	 * Description: 刷新sesion中购物车的商品数量
	 * 
	 * @throws IOException
	 * 
	 * @create time 2015-6-17 下午06:04:03
	 * 
	 * 
	 */

	public void updateShoppingCartItem() throws IOException {
		// 获取session中的购物车
		ShoppingCart shoppingCart = (ShoppingCart) session.get("shoppingCart");
		List<ShoppingCartItem> shoppingCartItems = shoppingCart
				.getShoppingCartItems();
		// 更新对应购物车条目数量
		for (ShoppingCartItem cartItem : shoppingCartItems) {
			if (cartItem.getProduct().getId() == Integer.parseInt(productId)) {
				cartItem.setCount(Integer.parseInt(count));
				break;
			}
		}
		// 放入session
		session.put("shoppingCart", shoppingCart);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(),
				jsonObject.toString());
	}

	/***
	 * 
	 * Description: 删除商品条目
	 * 
	 * @throws IOException
	 * 
	 * @create time 2015-6-17 下午06:55:49
	 * 
	 * 
	 */

	public void removeShoppingCartItem() throws IOException {
		// 获取session中的购物车
		ShoppingCart shoppingCart = (ShoppingCart) session.get("shoppingCart");
		List<ShoppingCartItem> shoppingCartItems = shoppingCart
				.getShoppingCartItems();
		// 删除对应条目
		for (ShoppingCartItem shoppingCartItem : shoppingCartItems) {
			if (shoppingCartItem.getProduct().getId() == Integer
					.parseInt(productId)) {
				shoppingCartItems.remove(shoppingCartItem);
				break;
			}
		}
		// 放入session
		session.put("shoppingCart", shoppingCart);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(),
				jsonObject.toString());
	}

}
