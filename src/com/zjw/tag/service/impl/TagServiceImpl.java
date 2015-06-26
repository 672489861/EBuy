package com.zjw.tag.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.zjw.common.dao.BaseDAO;
import com.zjw.common.util.PageBean;
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

	@Override
	@Transactional(readOnly = true)
	public Map<String, Object> getTagListWithManage(Tag s_tag, PageBean pageBean) {
		StringBuffer hql = new StringBuffer("from Tag ");
		StringBuffer countHql = new StringBuffer("select count(1) from Tag ");
		List<Object> params = new ArrayList<Object>();
		if (s_tag != null) {
			if (StringUtils.hasText(s_tag.getName())) {
				hql.append(" and name like ?");
				countHql.append(" and name like ?");
				params.add("%" + s_tag.getName() + "%");
			}
		}
		List<Tag> tags = baseDAO.find(
				hql.toString().replaceFirst("and", "where"), params, pageBean);
		long total = baseDAO.count(
				countHql.toString().replaceFirst("and", "where"), params);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("total", total);
		resultMap.put("rows", tags);
		return resultMap;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delete(Tag tag) {
		tag = baseDAO.get(Tag.class, tag.getId());
		baseDAO.delete(tag);
	}

	@Override
	@Transactional(readOnly = true)
	public Tag getTagById(int id) {
		return baseDAO.get(Tag.class, id);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveTag(Tag tag) {
		baseDAO.merge(tag);
	}
}
