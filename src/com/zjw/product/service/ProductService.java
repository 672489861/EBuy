package com.zjw.product.service;

import java.util.List;

import com.zjw.domain.Product;
import com.zjw.common.util.PageBean;

/***
 * 
 * @Description: 商品业务层接口
 * 
 * @author zjw
 * 
 * @create time 2015-6-13 上午09:32:04
 */

public interface ProductService {

	/***
	 * 
	 * Description: 加载商品
	 * 
	 * @create time 上午09:33:46
	 * 
	 * @param s_product
	 * @param pageBean
	 * @return
	 * 
	 */
	public List<Product> findProducts(Product s_product, PageBean pageBean);

	/***
	 * 
	 * Description: 获取商品总数
	 * 
	 * @create time 下午07:56:02
	 * 
	 * @param s_product
	 * @return
	 * 
	 */

	public long getProductCount(Product s_product);

	/***
	 * 
	 * Description: 根据商品编号加载商品详细信息
	 * 
	 * @create time 2015-6-16 上午11:19:37
	 * 
	 * @param productId
	 * @return
	 * 
	 */
	public Product getProductById(int productId);

	/***
	 * 
	 * Description: 判断指定小类下是否有商品
	 * 
	 * @create time 2015-6-23 上午11:36:13
	 * 
	 * @param id
	 * @return
	 * 
	 */
	public boolean existProductWithSmallTypeId(int id);

	/***
	 * 
	 * Description: 删除商品
	 * 
	 * @create time 2015-6-24 上午09:37:42
	 * 
	 * @param id
	 * 
	 */
	public void delete(int id);

	/***
	 * 
	 * Description: 保存商品
	 * 
	 * @create time 2015-6-24 下午01:55:52
	 * 
	 * @param productManager
	 * 
	 */
	public void saveProduct(Product productManager);
	
	/***
	 * 
	 * Description: 更新商品
	 *         
	 * @create time 2015-6-24 下午02:05:10
	 *
	 * @param productManager       
	 *
	 */
	public void updateProduct(Product productManager);

}
