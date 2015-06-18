package com.zjw.bigtype.service;

import java.util.List;

import com.zjw.domain.ProductBigType;

/***
 * 
 * @Description: 商品大类业务层接口
 * 
 * @author zjw
 * 
 * @create time 2015-6-12 下午06:55:45
 */

public interface ProductBigTypeService {

	/***
	 * 
	 * Description: 加载所有商品大类信息
	 * 
	 * @create time 下午06:56:30
	 * 
	 * @return
	 * 
	 */

	public List<ProductBigType> findAllBigTypeList();

}
