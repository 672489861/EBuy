package com.zjw.comment.service;

import java.util.List;

import com.zjw.common.util.PageBean;
import com.zjw.domain.Comment;

/***
 * 
 * @Description: ����ҵ���ӿ�
 * 
 * @author zjw
 * 
 * @create time 2015-6-17 ����12:01:30
 */
public interface CommentService {

	/***
	 * 
	 * Description: ���������������Լ���
	 * 
	 * @create time 2015-6-17 ����12:03:46
	 * 
	 * @param comment
	 * @param pageBean
	 * @return
	 * 
	 */
	public List<Comment> findCommentList(Comment comment, PageBean pageBean);

	/***
	 * 
	 * Description: ��ȡ���Լ�¼����
	 * 
	 * @create time 2015-6-17 ����12:04:05
	 * 
	 * @param comment
	 * @return
	 * 
	 */

	public Long getCommentCount(Comment comment);
	
	/***
	 * 
	 * Description: �ύ����
	 *         
	 * @create time 2015-6-17 ����01:02:44
	 *
	 * @param comment       
	 *
	 */
	
	public void saveComment(Comment comment);
}
