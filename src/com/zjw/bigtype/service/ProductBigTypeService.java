package com.zjw.bigtype.service;

import java.util.List;

import com.zjw.common.util.PageBean;
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

	/***
	 * 
	 * Description: 商品大类管理查询
	 * 
	 * @create time 2015-6-22 下午10:35:46
	 * 
	 * @param s_bigType
	 * @param pageBean
	 * @return
	 * 
	 */
	public List<ProductBigType> getBigTypeListWithManager(
			ProductBigType s_bigType, PageBean pageBean);

	/***
	 * 
	 * Description: 商品大类记录数
	 * 
	 * @create time 2015-6-22 下午10:35:50
	 * 
	 * @param s_bigType
	 * @return
	 * 
	 */
	public long getBigTypeListTotal(ProductBigType s_bigType);

	/***
	 * 
	 * Description: 删除商品大类
	 * 
	 * @create time 2015-6-22 下午11:41:22
	 * 
	 * @param id
	 * 
	 */
	public void delete(int id);

	/***
	 * 
	 * Description: 根据编号查找商品大类
	 * 
	 * @create time 2015-6-23 上午12:17:25
	 * 
	 * @param id
	 * @return
	 * 
	 */
	public ProductBigType getBigTypeById(int id);

	/***
	 * 
	 * Description: 保存商品大类
	 * 
	 * @create time 2015-6-23 上午12:31:10
	 * 
	 * @param bigType
	 * 
	 */

	public void saveBigType(ProductBigType bigType);

	/***
	 * 
	 * Description: 加载商品大类列表框
	 * 
	 * @create time 2015-6-23 下午01:51:14
	 * 
	 * @return
	 * 
	 */
	public List<ProductBigType> getBigTypeList();

}
