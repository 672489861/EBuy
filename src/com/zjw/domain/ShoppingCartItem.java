package com.zjw.domain;

/***
 * 
 * @Description: ���ﳵ��Ʒ��Ŀ
 * 
 * @author zjw
 * 
 * @create time 2015-6-17 ����01:46:22
 */
public class ShoppingCartItem {

	private int count; // ��Ʒ����
	private int id;
	private Product product; // ��Ʒ

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
