package com.zjw.bigtype.service;

import java.util.List;

import com.zjw.common.util.PageBean;
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

	/***
	 * 
	 * Description: ��Ʒ��������ѯ
	 * 
	 * @create time 2015-6-22 ����10:35:46
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
	 * Description: ��Ʒ�����¼��
	 * 
	 * @create time 2015-6-22 ����10:35:50
	 * 
	 * @param s_bigType
	 * @return
	 * 
	 */
	public long getBigTypeListTotal(ProductBigType s_bigType);

	/***
	 * 
	 * Description: ɾ����Ʒ����
	 * 
	 * @create time 2015-6-22 ����11:41:22
	 * 
	 * @param id
	 * 
	 */
	public void delete(int id);

	/***
	 * 
	 * Description: ���ݱ�Ų�����Ʒ����
	 * 
	 * @create time 2015-6-23 ����12:17:25
	 * 
	 * @param id
	 * @return
	 * 
	 */
	public ProductBigType getBigTypeById(int id);

	/***
	 * 
	 * Description: ������Ʒ����
	 * 
	 * @create time 2015-6-23 ����12:31:10
	 * 
	 * @param bigType
	 * 
	 */

	public void saveBigType(ProductBigType bigType);

	/***
	 * 
	 * Description: ������Ʒ�����б��
	 * 
	 * @create time 2015-6-23 ����01:51:14
	 * 
	 * @return
	 * 
	 */
	public List<ProductBigType> getBigTypeList();

}
