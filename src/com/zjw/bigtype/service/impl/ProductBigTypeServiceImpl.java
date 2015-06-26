package com.zjw.bigtype.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.zjw.bigtype.service.ProductBigTypeService;
import com.zjw.common.dao.BaseDAO;
import com.zjw.common.util.PageBean;
import com.zjw.domain.ProductBigType;

/***
 * 
 * @Description: 商品大类业务层实现类
 * 
 * @author zjw
 * 
 * @create time 2015-6-12 下午06:57:15
 */

@Service("productBigTypeService")
public class ProductBigTypeServiceImpl implements ProductBigTypeService {

	@Resource(name = "baseDAO")
	private BaseDAO<ProductBigType> baseDAO;

	@Override
	@Transactional(readOnly = true)
	public List<ProductBigType> findAllBigTypeList() {
		return baseDAO.find("from ProductBigType ");
	}

	@Override
	@Transactional(readOnly = true)
	public List<ProductBigType> getBigTypeListWithManager(
			ProductBigType s_bigType, PageBean pageBean) {
		StringBuffer hql = new StringBuffer("from ProductBigType ");
		List<Object> params = new ArrayList<Object>();
		if (s_bigType != null) {
			if (StringUtils.hasText(s_bigType.getName())) {
				hql.append(" and name like ?");
				params.add("%" + s_bigType.getName() + "%");
			}
		}

		return baseDAO.find(hql.toString().replaceFirst("and", "where"),
				params, pageBean);
	}

	@Override
	@Transactional(readOnly = true)
	public long getBigTypeListTotal(ProductBigType s_bigType) {
		StringBuffer hql = new StringBuffer(
				"select count(1) from ProductBigType ");
		List<Object> params = new ArrayList<Object>();
		if (s_bigType != null) {
			if (StringUtils.hasText(s_bigType.getName())) {
				hql.append(" and name like ?");
				params.add("%" + s_bigType.getName() + "%");
			}
		}
		return baseDAO.count(hql.toString().replaceFirst("and", "where"),
				params);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delete(int id) {
		ProductBigType bigType = baseDAO.get(ProductBigType.class, id);
		baseDAO.delete(bigType);
	}

	@Override
	@Transactional(readOnly = true)
	public ProductBigType getBigTypeById(int id) {
		return baseDAO.get(ProductBigType.class, id);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveBigType(ProductBigType bigType) {
		baseDAO.merge(bigType);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ProductBigType> getBigTypeList() {
		return baseDAO.find("from ProductBigType", new Object[] {});
	}

}
