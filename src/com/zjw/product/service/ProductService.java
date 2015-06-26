package com.zjw.product.service;

import java.util.List;

import com.zjw.domain.Product;
import com.zjw.common.util.PageBean;

/***
 * 
 * @Description: ��Ʒҵ���ӿ�
 * 
 * @author zjw
 * 
 * @create time 2015-6-13 ����09:32:04
 */

public interface ProductService {

	/***
	 * 
	 * Description: ������Ʒ
	 * 
	 * @create time ����09:33:46
	 * 
	 * @param s_product
	 * @param pageBean
	 * @return
	 * 
	 */
	public List<Product> findProducts(Product s_product, PageBean pageBean);

	/***
	 * 
	 * Description: ��ȡ��Ʒ����
	 * 
	 * @create time ����07:56:02
	 * 
	 * @param s_product
	 * @return
	 * 
	 */

	public long getProductCount(Product s_product);

	/***
	 * 
	 * Description: ������Ʒ��ż�����Ʒ��ϸ��Ϣ
	 * 
	 * @create time 2015-6-16 ����11:19:37
	 * 
	 * @param productId
	 * @return
	 * 
	 */
	public Product getProductById(int productId);

	/***
	 * 
	 * Description: �ж�ָ��С�����Ƿ�����Ʒ
	 * 
	 * @create time 2015-6-23 ����11:36:13
	 * 
	 * @param id
	 * @return
	 * 
	 */
	public boolean existProductWithSmallTypeId(int id);

	/***
	 * 
	 * Description: ɾ����Ʒ
	 * 
	 * @create time 2015-6-24 ����09:37:42
	 * 
	 * @param id
	 * 
	 */
	public void delete(int id);

	/***
	 * 
	 * Description: ������Ʒ
	 * 
	 * @create time 2015-6-24 ����01:55:52
	 * 
	 * @param productManager
	 * 
	 */
	public void saveProduct(Product productManager);
	
	/***
	 * 
	 * Description: ������Ʒ
	 *         
	 * @create time 2015-6-24 ����02:05:10
	 *
	 * @param productManager       
	 *
	 */
	public void updateProduct(Product productManager);

}
