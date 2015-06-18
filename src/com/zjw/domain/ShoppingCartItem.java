package com.zjw.domain;

/***
 * 
 * @Description: 购物车商品条目
 * 
 * @author zjw
 * 
 * @create time 2015-6-17 下午01:46:22
 */
public class ShoppingCartItem {

	private int count; // 商品数量
	private int id;
	private Product product; // 商品

	public int getCount() {
		return count;
	}

	public int getId() {
		return id;
	}

	public Product getProduct() {
		return product;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
