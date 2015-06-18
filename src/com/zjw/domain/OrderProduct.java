package com.zjw.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/***
 * 
 * @Description: 订单和商品的中间表
 * 
 * @author Administrator
 * 
 * @create time 2015-6-12 下午03:13:46
 */
@Entity
@Table(name = "t_order_product")
public class OrderProduct {

	private Integer id;
	private int num; // 商品数量
	private Order order; // 订单
	private Product product; // 商品

	@Id
	@GeneratedValue(generator = "_native")
	@GenericGenerator(strategy = "native", name = "_native")
	public Integer getId() {
		return id;
	}

	@Column(nullable = false)
	public int getNum() {
		return num;
	}

	@ManyToOne
	@JoinColumn(name = "orderId")
	public Order getOrder() {
		return order;
	}

	@ManyToOne
	@JoinColumn(name = "productId")
	public Product getProduct() {
		return product;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
