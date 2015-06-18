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

import org.hibernate.annotations.GenericGenerator;

/***
 * 
 * @Description: 商品实体类
 * 
 * @author zjw
 * 
 * @create time 2015-6-12 下午02:31:33
 */

@Entity
@Table(name = "t_product")
public class Product {

	private String description; // 商品描述
	private int hot; // 是否热卖 1是热卖
	private Date hotTime; // 热卖时间
	private Integer id;
	private String name; // 商品名称
	private List<OrderProduct> orderProductList = new ArrayList<OrderProduct>();
	private float price; // 商品价格
	private ProductBigType bigType; // 商品大类
	private ProductSmallType smallType; // 商品小类
	private String proPic; // 商品图片
	private int specialPrice; // 是否特价 1是特价
	private Date specialTime; // 特价时间
	private int stock; // 商品库存

	@Column(length = 1500)
	public String getDescription() {
		return description;
	}

	@Column
	public int getHot() {
		return hot;
	}

	@Column
	public Date getHotTime() {
		return hotTime;
	}

	@Id
	@GeneratedValue(generator = "_native")
	@GenericGenerator(strategy = "native", name = "_native")
	public Integer getId() {
		return id;
	}

	@Column(length = 50)
	public String getName() {
		return name;
	}

	@OneToMany
	@JoinColumn(name = "productId")
	public List<OrderProduct> getOrderProductList() {
		return orderProductList;
	}

	@Column
	public float getPrice() {
		return price;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bigTypeId")
	public ProductBigType getBigType() {
		return bigType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "smallTypeId")
	public ProductSmallType getSmallType() {
		return smallType;
	}

	@Column
	public String getProPic() {
		return proPic;
	}

	@Column
	public int getSpecialPrice() {
		return specialPrice;
	}

	@Column
	public Date getSpecialTime() {
		return specialTime;
	}

	@Column
	public int getStock() {
		return stock;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setHot(int hot) {
		this.hot = hot;
	}

	public void setHotTime(Date hotTime) {
		this.hotTime = hotTime;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setOrderProductList(List<OrderProduct> orderProductList) {
		this.orderProductList = orderProductList;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public void setBigType(ProductBigType bigType) {
		this.bigType = bigType;
	}

	public void setSmallType(ProductSmallType smallType) {
		this.smallType = smallType;
	}

	public void setProPic(String proPic) {
		this.proPic = proPic;
	}

	public void setSpecialPrice(int specialPrice) {
		this.specialPrice = specialPrice;
	}

	public void setSpecialTime(Date specialTime) {
		this.specialTime = specialTime;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

}
