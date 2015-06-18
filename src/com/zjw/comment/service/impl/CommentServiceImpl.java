package com.zjw.comment.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zjw.comment.service.CommentService;
import com.zjw.common.dao.BaseDAO;
import com.zjw.common.util.PageBean;
import com.zjw.domain.Comment;

/***
 * 
 * @Description: 留言业务层实现类
 * 
 * @author zjw
 * 
 * @create time 2015-6-17 上午12:02:08
 */

@Service("commentService")
public class CommentServiceImpl implements CommentService {

	@Resource(name = "baseDAO")
	private BaseDAO<Comment> baseDAO;

	@Override
	@Transactional(readOnly = true)
	public List<Comment> findCommentList(Comment comment, PageBean pageBean) {
		List<Object> params = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer("from Comment ");

		sql.append(" order by createTime desc ");
		if (pageBean != null) {
			return baseDAO.find(sql.toString(), params, pageBean);
		}
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public Long getCommentCount(Comment comment) {
		List<Object> params = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer("select count(1) from Comment ");
		return baseDAO.count(sql.toString(), params);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveComment(Comment comment) {
		baseDAO.merge(comment);
	}

}
