package com.zjw.tag.service;

import java.util.List;

import com.zjw.domain.Tag;

/***
 * 
 * @Description: ��ǩҵ���ӿ�
 *
 * @author zjw
 * 
 *      @create time  2015-6-13 ����09:27:27
 */

public interface TagService {
	
	/***
	 * 
	 * Description: �������б�ǩ
	 *         
	 * @create time ����09:32:52
	 *
	 * @return       
	 *
	 */
	public List<Tag> findTagList();

}
