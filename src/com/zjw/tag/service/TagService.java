package com.zjw.tag.service;

import java.util.List;
import java.util.Map;

import com.zjw.common.util.PageBean;
import com.zjw.domain.Tag;

/***
 * 
 * @Description: 标签业务层接口
 * 
 * @author zjw
 * 
 * @create time 2015-6-13 上午09:27:27
 */

public interface TagService {

	/***
	 * 
	 * Description: 加载所有标签
	 * 
	 * @create time 上午09:32:52
	 * 
	 * @return
	 * 
	 */
	public List<Tag> findTagList();

	/***
	 * 
	 * Description: 标签管理主界面查询
	 * 
	 * @create time 2015-6-24 下午05:04:49
	 * 
	 * @param s_tag
	 * @param pageBean
	 * @return
	 * 
	 */
	public Map<String, Object> getTagListWithManage(Tag s_tag, PageBean pageBean);

	/***
	 * 
	 * Description: 删除标签
	 * 
	 * @create time 2015-6-24 下午05:40:32
	 * 
	 * @param tag
	 * 
	 */
	public void delete(Tag tag);

	/***
	 * 
	 * Description: 根据编号加载标签
	 * 
	 * @create time 2015-6-24 下午07:39:48
	 * 
	 * @param id
	 * @return
	 * 
	 */
	public Tag getTagById(int id);

	/***
	 * 
	 * Description: 添加或修改标签
	 * 
	 * @create time 2015-6-24 下午07:58:26
	 * 
	 * @param tag
	 * 
	 */
	public void saveTag(Tag tag);

}
