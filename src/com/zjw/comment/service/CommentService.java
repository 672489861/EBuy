package com.zjw.comment.service;

import java.util.List;

import com.zjw.common.util.PageBean;
import com.zjw.domain.Comment;

/***
 * 
 * @Description: 留言业务层接口
 * 
 * @author zjw
 * 
 * @create time 2015-6-17 上午12:01:30
 */
public interface CommentService {

	/***
	 * 
	 * Description: 根据条件加载留言集合
	 * 
	 * @create time 2015-6-17 上午12:03:46
	 * 
	 * @param comment
	 * @param pageBean
	 * @return
	 * 
	 */
	public List<Comment> findCommentList(Comment comment, PageBean pageBean);

	/***
	 * 
	 * Description: 获取留言记录条数
	 * 
	 * @create time 2015-6-17 上午12:04:05
	 * 
	 * @param comment
	 * @return
	 * 
	 */

	public Long getCommentCount(Comment comment);
	
	/***
	 * 
	 * Description: 提交留言
	 *         
	 * @create time 2015-6-17 下午01:02:44
	 *
	 * @param comment       
	 *
	 */
	
	public void saveComment(Comment comment);
}
