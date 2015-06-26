package com.zjw.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
 * @Description: 商品小类实体
 * 
 * @author zjw
 * 
 * @create time 2015-6-12 下午03:04:26
 */

@Entity
@Table(name = "t_smalltype")
public class ProductSmallType {

	@Expose
	private ProductBigType bigType;
	@Expose
	private Integer id;
	@Expose
	private String name; // 小类名称
	private List<Product> products = new ArrayList<Product>();
	@Expose
	private String remarks; // 备注

	@ManyToOne(cascade = { CascadeType.ALL})
	@JoinColumn(name = "bigTypeId")
	public ProductBigType getBigType() {
		return bigType;
	}

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

	@OneToMany(mappedBy = "smallType")
	public List<Product> getProducts() {
		return products;
	}

	@Column(length = 255)
	public String getRemarks() {
		return remarks;
	}

	public void setBigType(ProductBigType bigType) {
		this.bigType = bigType;
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

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
