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

import com.google.gson.annotations.Expose;

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

	@Expose
	private ProductBigType bigType; // 商品大类
	private String description; // 商品描述
	@Expose
	private int hot; // 是否热卖 1是热卖
	private Date hotTime; // 热卖时间
	@Expose
	private Integer id;
	@Expose
	private String name; // 商品名称
	@Expose
	private int num;
	private List<OrderProduct> orderProductList = new ArrayList<OrderProduct>();
	@Expose
	private float price; // 商品价格
	@Expose
	private String proPic; // 商品图片
	@Expose
	private ProductSmallType smallType; // 商品小类
	@Expose
	private int specialPrice; // 是否特价 1是特价
	private Date specialTime; // 特价时间

	@Expose
	private int stock; // 商品库存

	@Expose
	private float subtotal;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "bigTypeId")
	public ProductBigType getBigType() {
		return bigType;
	}

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

	public int getNum() {
		return num;
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

	@Column
	public String getProPic() {
		return proPic;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "smallTypeId")
	public ProductSmallType getSmallType() {
		return smallType;
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

	public float getSubtotal() {
		return subtotal;
	}

	public void setBigType(ProductBigType bigType) {
		this.bigType = bigType;
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

	public void setNum(int num) {
		this.num = num;
	}

	public void setOrderProductList(List<OrderProduct> orderProductList) {
		this.orderProductList = orderProductList;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public void setProPic(String proPic) {
		this.proPic = proPic;
	}

	public void setSmallType(ProductSmallType smallType) {
		this.smallType = smallType;
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

	public void setSubtotal(float subtotal) {
		this.subtotal = subtotal;
	}

}
