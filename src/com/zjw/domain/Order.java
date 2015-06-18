package com.zjw.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

/***
 * 
 * @Description: 订单实体类
 * 
 * @author zjw
 * 
 * @create time 2015-6-12 下午03:07:04
 */

@Entity
@Table(name = "t_order")
public class Order {

	private float cost; // 订单金额
	private Date createTime; // 创建时间
	private Integer id;
	private String orderNo; // 订单号
	private List<OrderProduct> orderProductList = new ArrayList<OrderProduct>();
	private int status; // 状态   1 待审核 2 审核通过 3卖家已发货 4已收货 (交易完成 ）
	private User user; // 订单对应实体

	@Column(nullable = false)
	public float getCost() {
		return cost;
	}

	@Column
	public Date getCreateTime() {
		return createTime;
	}

	@Id
	@GeneratedValue(generator = "_native")
	@GenericGenerator(name = "_native", strategy = "native")
	public Integer getId() {
		return id;
	}

	@Column(length = 255)
	public String getOrderNo() {
		return orderNo;
	}

	@OneToMany(fetch = FetchType.EAGER)
	@Cascade(value = { org.hibernate.annotations.CascadeType.SAVE_UPDATE }) // 级联操作
	@JoinColumn(name = "orderId")
	public List<OrderProduct> getOrderProductList() {
		return orderProductList;
	}

	@Column(nullable = false)
	public int getStatus() {
		return status;
	}

	// 多对一关系 懒加载策略
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userId", updatable = false)
	public User getUser() {
		return user;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public void setOrderProductList(List<OrderProduct> orderProductList) {
		this.orderProductList = orderProductList;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
