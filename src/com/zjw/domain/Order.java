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
 * @Description: ����ʵ����
 * 
 * @author zjw
 * 
 * @create time 2015-6-12 ����03:07:04
 */

@Entity
@Table(name = "t_order")
public class Order {

	private float cost; // �������
	private Date createTime; // ����ʱ��
	private Integer id;
	private String orderNo; // ������
	private List<OrderProduct> orderProductList = new ArrayList<OrderProduct>();
	private int status; // ״̬   1 ����� 2 ���ͨ�� 3�����ѷ��� 4���ջ� (������� ��
	private User user; // ������Ӧʵ��

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
	@Cascade(value = { org.hibernate.annotations.CascadeType.SAVE_UPDATE }) // ��������
	@JoinColumn(name = "orderId")
	public List<OrderProduct> getOrderProductList() {
		return orderProductList;
	}

	@Column(nullable = false)
	public int getStatus() {
		return status;
	}

	// ���һ��ϵ �����ز���
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
