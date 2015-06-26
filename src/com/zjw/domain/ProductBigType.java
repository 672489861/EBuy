package com.zjw.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.google.gson.annotations.Expose;

/***
 * 
 * @Description: 商品大类实体
 * 
 * @author zjw
 * 
 * @create time 2015-6-12 下午02:55:15
 */

@Entity
@Table(name = "t_bigtype")
public class ProductBigType {

	@Expose
	private Integer id;
	@Expose
	private String name; // 大类名称
	private List<Product> products = new ArrayList<Product>();
	@Expose
	private String remark; // 备注
	private List<ProductSmallType> smallTypes = new ArrayList<ProductSmallType>();

	@Id
	@GeneratedValue(generator = "_native")
	@GenericGenerator(name = "_native", strategy = "native")
	public Integer getId() {
		return id;
	}

	@Column(length = 100)
	public String getName() {
		return name;
	}

	@OneToMany(mappedBy = "bigType")
	public List<Product> getProducts() {
		return products;
	}

	@Column(length = 255)
	public String getRemark() {
		return remark;
	}

	@OneToMany(mappedBy = "bigType", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	public List<ProductSmallType> getSmallTypes() {
		return smallTypes;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setSmallTypes(List<ProductSmallType> smallTypes) {
		this.smallTypes = smallTypes;
	}

}
