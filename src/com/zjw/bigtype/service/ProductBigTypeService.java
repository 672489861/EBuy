package com.zjw.bigtype.service;

import java.util.List;

import com.zjw.domain.ProductBigType;

/***
 * 
 * @Description: ��Ʒ����ҵ���ӿ�
 * 
 * @author zjw
 * 
 * @create time 2015-6-12 ����06:55:45
 */

public interface ProductBigTypeService {

	/***
	 * 
	 * Description: ����������Ʒ������Ϣ
	 * 
	 * @create time ����06:56:30
	 * 
	 * @return
	 * 
	 */

	public List<ProductBigType> findAllBigTypeList();

}
