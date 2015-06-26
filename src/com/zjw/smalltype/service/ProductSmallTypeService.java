package com.zjw.smalltype.service;

import java.util.List;

import com.zjw.common.util.PageBean;
import com.zjw.domain.ProductSmallType;

/***
 * 
 * @Description: ��ƷС��ҵ���ӿ�
 * 
 * @author zjw
 * 
 * @create time 2015-6-22 ����11:35:58
 */
public interface ProductSmallTypeService {

	/***
	 * 
	 * Description: �ж���Ʒ�������Ƿ���С��
	 * 
	 * @create time 2015-6-22 ����11:40:57
	 * 
	 * @param id
	 * @return
	 * 
	 */
	public boolean existSmallTypeWithBigTypeId(int id);

	/***
	 * 
	 * Description: ��ƷС���̨����
	 * 
	 * @create time 2015-6-23 ����11:25:05
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
	 * Description: ��ƷС���¼��
	 * 
	 * @create time 2015-6-23 ����11:25:09
	 * 
	 * @param s_smallType
	 * @return
	 * 
	 */
	public long getSmallTypeListTotal(ProductSmallType s_smallType);

	/***
	 * 
	 * Description: ɾ����ƷС��
	 * 
	 * @create time 2015-6-23 ����11:35:54
	 * 
	 * @param id
	 * 
	 */
	public void delete(int id);

	/***
	 * 
	 * Description: ���ݱ�ż�����ƷС��
	 * 
	 * @create time 2015-6-23 ����01:46:56
	 * 
	 * @param id
	 * @return
	 * 
	 */
	public ProductSmallType getSmallTypeById(int id);

	/***
	 * 
	 * Description: ������ƷС��
	 * 
	 * @create time 2015-6-23 ����02:21:10
	 * 
	 * @param smallType
	 * 
	 */
	public void saveSmallType(ProductSmallType smallType);
	
	/***
	 * 
	 * Description: ������ƷС��
	 *         
	 * @create time 2015-6-24 ����10:47:20
	 *
	 * @param bigTypeId
	 * @return       
	 *
	 */
	public List<ProductSmallType> getSmallTypeComboList(String bigTypeId);

}
