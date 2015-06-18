package com.zjw.tag.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zjw.common.dao.BaseDAO;
import com.zjw.domain.Tag;
import com.zjw.tag.service.TagService;

/***
 * 
 * @Description: 标签业务层实现类
 * 
 * @author zjw
 * 
 * @create time 2015-6-13 上午09:42:31
 */

@Service("tagService")
public class TagServiceImpl implements TagService {

	@Resource(name = "baseDAO")
	private BaseDAO<Tag> baseDAO;

	@Override
	@Transactional(readOnly = true)
	public List<Tag> findTagList() {
		return baseDAO.find("from Tag");
	}

}
