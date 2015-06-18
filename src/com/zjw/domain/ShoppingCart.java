package com.zjw.domain;

import java.util.ArrayList;
import java.util.List;

/***
 * 
 * @Description: ¹ºÎï³µ
 * 
 * @author zjw
 * 
 * @create time 2015-6-17 ÏÂÎç01:48:47
 */
public class ShoppingCart {

	private int userId;
	private List<ShoppingCartItem> shoppingCartItems = new ArrayList<ShoppingCartItem>();

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public List<ShoppingCartItem> getShoppingCartItems() {
		return shoppingCartItems;
	}

	public void setShoppingCartItems(List<ShoppingCartItem> shoppingCartItems) {
		this.shoppingCartItems = shoppingCartItems;
	}

}
