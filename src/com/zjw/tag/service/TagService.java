package com.zjw.tag.service;

import java.util.List;

import com.zjw.domain.Tag;

/***
 * 
 * @Description: 标签业务层接口
 *
 * @author zjw
 * 
 *      @create time  2015-6-13 上午09:27:27
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

}
