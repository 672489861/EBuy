package com.zjw.tag.service;

import java.util.List;
import java.util.Map;

import com.zjw.common.util.PageBean;
import com.zjw.domain.Tag;

/***
 * 
 * @Description: ��ǩҵ���ӿ�
 * 
 * @author zjw
 * 
 * @create time 2015-6-13 ����09:27:27
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

	/***
	 * 
	 * Description: ��ǩ�����������ѯ
	 * 
	 * @create time 2015-6-24 ����05:04:49
	 * 
	 * @param s_tag
	 * @param pageBean
	 * @return
	 * 
	 */
	public Map<String, Object> getTagListWithManage(Tag s_tag, PageBean pageBean);

	/***
	 * 
	 * Description: ɾ����ǩ
	 * 
	 * @create time 2015-6-24 ����05:40:32
	 * 
	 * @param tag
	 * 
	 */
	public void delete(Tag tag);

	/***
	 * 
	 * Description: ���ݱ�ż��ر�ǩ
	 * 
	 * @create time 2015-6-24 ����07:39:48
	 * 
	 * @param id
	 * @return
	 * 
	 */
	public Tag getTagById(int id);

	/***
	 * 
	 * Description: ��ӻ��޸ı�ǩ
	 * 
	 * @create time 2015-6-24 ����07:58:26
	 * 
	 * @param tag
	 * 
	 */
	public void saveTag(Tag tag);

}
