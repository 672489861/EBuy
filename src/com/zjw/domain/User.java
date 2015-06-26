package com.zjw.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.google.gson.annotations.Expose;

/***
 * 
 * @Description: 用户实体类
 * 
 * @author zjw
 * 
 * @create time 2015-6-12 下午01:39:16
 */

@Entity
@Table(name = "t_user")
public class User {
	
	@Expose
	private String address; // 收货地址
	@Expose
	private Date birthday; // 出生日期
	@Expose
	private String dentityCode; // 身份证
	@Expose
	private String email; // 邮箱
	@Expose
	private Integer id;
	@Expose
	private String mobile; // 手机
	private List<Order> orderList = new ArrayList<Order>();
	@Expose
	private String password; // 密码
	@Expose
	private String sex; // 性别
	@Expose
	private int status = 1; // 普通用户1 管理员2
	@Expose
	private String trueName; // 真实姓名
	@Expose
	private String userName; // 用户名

	@Column(length = 200)
	public String getAddress() {
		return address;
	}

	@Column
	public Date getBirthday() {
		return birthday;
	}

	@Column(length = 20)
	public String getDentityCode() {
		return dentityCode;
	}

	@Column(length = 20)
	public String getEmail() {
		return email;
	}

	@Id
	@GeneratedValue(generator = "_native")
	@GenericGenerator(strategy = "native", name = "_native")
	public Integer getId() {
		return id;
	}

	@Column(length = 20)
	public String getMobile() {
		return mobile;
	}

	@OneToMany(targetEntity = Order.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "userId",updatable=false)
	public List<Order> getOrderList() {
		return orderList;
	}

	@Column(length = 20)
	public String getPassword() {
		return password;
	}

	@Column(length = 5)
	public String getSex() {
		return sex;
	}

	@Column(nullable = false)
	public int getStatus() {
		return status;
	}

	@Column(length = 20)
	public String getTrueName() {
		return trueName;
	}

	@Column(length = 20)
	public String getUserName() {
		return userName;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public void setDentityCode(String dentityCode) {
		this.dentityCode = dentityCode;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
