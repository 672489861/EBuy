package com.zjw.bigtype.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zjw.bigtype.service.ProductBigTypeService;
import com.zjw.common.dao.BaseDAO;
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

}
