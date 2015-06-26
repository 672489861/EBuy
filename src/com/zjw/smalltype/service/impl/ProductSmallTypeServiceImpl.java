package com.zjw.smalltype.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.zjw.common.dao.BaseDAO;
import com.zjw.common.util.PageBean;
import com.zjw.domain.ProductSmallType;
import com.zjw.smalltype.service.ProductSmallTypeService;

/***
 * 
 * @Description: 商品小类业务层实现类
 * 
 * @author zjw
 * 
 * @create time 2015-6-22 下午11:36:46
 */

@Service("smallTypeService")
public class ProductSmallTypeServiceImpl implements ProductSmallTypeService {

	@Resource(name = "baseDAO")
	private BaseDAO<ProductSmallType> baseDAO;

	@Override
	@Transactional(readOnly = true)
	public boolean existSmallTypeWithBigTypeId(int id) {
		String sql = "select count(1) from ProductSmallType where bigType.id = "
				+ id;
		return baseDAO.count(sql, new Object[] {}) > 0 ? true : false;
	}

	@Override
	@Transactional(readOnly = true)
	public List<ProductSmallType> getSmallTypeListWithManager(
			ProductSmallType s_smallType, PageBean pageBean) {
		StringBuffer hql = new StringBuffer("from ProductSmallType ");
		List<Object> params = new ArrayList<Object>();
		if (s_smallType != null) {
			if (StringUtils.hasText(s_smallType.getName())) {
				hql.append(" and name like ?");
				params.add("%" + s_smallType.getName() + "%");
			}
		}
		return baseDAO.find(hql.toString().replaceFirst("and", "where"),
				params, pageBean);
	}

	@Override
	@Transactional(readOnly = true)
	public long getSmallTypeListTotal(ProductSmallType s_smallType) {
		StringBuffer hql = new StringBuffer(
				"select count(1) from ProductSmallType ");
		List<Object> params = new ArrayList<Object>();
		if (s_smallType != null) {
			if (StringUtils.hasText(s_smallType.getName())) {
				hql.append(" and name like ?");
				params.add("%" + s_smallType.getName() + "%");
			}
		}
		return baseDAO.count(hql.toString().replaceFirst("and", "where"),
				params);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delete(int id) {
		baseDAO.executeSQL("delete from t_smalltype where id = " + id);
	}

	@Override
	@Transactional(readOnly = true)
	public ProductSmallType getSmallTypeById(int id) {
		return baseDAO.get(ProductSmallType.class, id);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveSmallType(ProductSmallType smallType) {
		baseDAO.merge(smallType);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ProductSmallType> getSmallTypeComboList(String bigTypeId) {
		return baseDAO.find("from ProductSmallType where bigType.id = "
				+ bigTypeId);
	}

}
