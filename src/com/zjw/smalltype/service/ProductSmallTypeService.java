package com.zjw.smalltype.service;

import java.util.List;

import com.zjw.common.util.PageBean;
import com.zjw.domain.ProductSmallType;

/***
 * 
 * @Description: 商品小类业务层接口
 * 
 * @author zjw
 * 
 * @create time 2015-6-22 下午11:35:58
 */
public interface ProductSmallTypeService {

	/***
	 * 
	 * Description: 判断商品大类下是否有小类
	 * 
	 * @create time 2015-6-22 下午11:40:57
	 * 
	 * @param id
	 * @return
	 * 
	 */
	public boolean existSmallTypeWithBigTypeId(int id);

	/***
	 * 
	 * Description: 商品小类后台管理
	 * 
	 * @create time 2015-6-23 上午11:25:05
	 * 
	 * @param s_smallType
	 * @param pageBean
	 * @return
	 * 
	 */
	public List<ProductSmallType> getSmallTypeListWithManager(
			ProductSmallType s_smallType, PageBean pageBean);

	/***
	 * 
	 * Description: 商品小类记录数
	 * 
	 * @create time 2015-6-23 上午11:25:09
	 * 
	 * @param s_smallType
	 * @return
	 * 
	 */
	public long getSmallTypeListTotal(ProductSmallType s_smallType);

	/***
	 * 
	 * Description: 删除商品小类
	 * 
	 * @create time 2015-6-23 上午11:35:54
	 * 
	 * @param id
	 * 
	 */
	public void delete(int id);

	/***
	 * 
	 * Description: 根据编号加载商品小类
	 * 
	 * @create time 2015-6-23 下午01:46:56
	 * 
	 * @param id
	 * @return
	 * 
	 */
	public ProductSmallType getSmallTypeById(int id);

	/***
	 * 
	 * Description: 保存商品小类
	 * 
	 * @create time 2015-6-23 下午02:21:10
	 * 
	 * @param smallType
	 * 
	 */
	public void saveSmallType(ProductSmallType smallType);
	
	/***
	 * 
	 * Description: 加载商品小类
	 *         
	 * @create time 2015-6-24 上午10:47:20
	 *
	 * @param bigTypeId
	 * @return       
	 *
	 */
	public List<ProductSmallType> getSmallTypeComboList(String bigTypeId);

}
