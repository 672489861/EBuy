package com.zjw.product.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.zjw.domain.Product;
import com.zjw.product.service.ProductService;
import com.zjw.common.dao.BaseDAO;
import com.zjw.common.util.PageBean;

/***
 * 
 * @Description: 商品业务层实现类
 * 
 * @author zjw
 * 
 * @create time 2015-6-13 上午10:39:42
 */

@Service("productService")
public class ProductServiceImpl implements ProductService {

	@Resource(name = "baseDAO")
	private BaseDAO<Product> baseDAO;

	@Override
	@Transactional(readOnly = true)
	public List<Product> findProducts(Product s_product, PageBean pageBean) {
		List<Object> params = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer("from Product ");
		if (s_product != null) {
			// 商品大类查询
			if (s_product.getBigType() != null) {
				sql.append("and bigTypeId = ?");
				params.add(s_product.getBigType().getId());
			}
			if (s_product.getSmallType() != null) {
				sql.append("and smallTypeId = ?");
				params.add(s_product.getSmallType().getId());
			}
			// 商品名称查询
			if (StringUtils.hasText(s_product.getName())) {
				sql.append("and name like ?");
				params.add('%' + s_product.getName() + "%");
			}
			// 特价商品
			if (s_product.getSpecialPrice() == 1) {
				sql.append("and specialPrice = 1 order by specialTime desc");
			}
			// 热卖商品
			if (s_product.getHot() == 1) {
				sql.append("and hot = 1 order by hotTime desc");
			}
		}
		if (pageBean != null) {
			return baseDAO.find(sql.toString().replaceFirst("and", "where"),
					params, pageBean);
		}
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public long getProductCount(Product s_product) {
		List<Object> params = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer("select count(1) from Product ");
		if (s_product != null) {
			// 商品大类查询
			if (s_product.getBigType() != null) {
				sql.append("and bigTypeId = ?");
				params.add(s_product.getBigType().getId());
			}
			if (s_product.getSmallType() != null) {
				sql.append("and smallTypeId = ?");
				params.add(s_product.getSmallType().getId());
			}
			// 商品名称查询
			if (StringUtils.hasText(s_product.getName())) {
				sql.append("and name like ?");
				params.add('%' + s_product.getName() + "%");
			}
		}
		return baseDAO.count(sql.toString().replaceFirst("and", "where"),
				params);
	}

	@Override
	@Transactional(readOnly = true)
	public Product getProductById(int productId) {
		return baseDAO.get(Product.class, productId);
	}

}
