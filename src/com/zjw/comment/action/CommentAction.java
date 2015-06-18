package com.zjw.comment.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

import com.zjw.comment.service.CommentService;
import com.zjw.common.action.BaseAction;
import com.zjw.common.constant.Constants;
import com.zjw.common.util.PageBean;
import com.zjw.common.util.PageUtil;
import com.zjw.domain.Comment;

/***
 * 
 * @Description: ����Action
 * 
 * @author zjw
 * 
 * @create time 2015-6-17 ����12:14:09
 */

@Controller
public class CommentAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Comment> commentList;

	@Resource(name = "commentService")
	private CommentService commentService;
	private String page; // ��ǰҳ
	private String pageCode; // ��ҳ����
	private long total; // ��¼����
	private Comment s_comment; // ��װ��ѯ����
	private Comment comment; // ����ʵ��
	
	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public Comment getS_comment() {
		return s_comment;
	}

	public void setS_comment(Comment s_comment) {
		this.s_comment = s_comment;
	}

	public List<Comment> getCommentList() {
		return commentList;
	}

	public String getPage() {
		return page;
	}

	public String getPageCode() {
		return pageCode;
	}

	public long getTotal() {
		return total;
	}

	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public void setPageCode(String pageCode) {
		this.pageCode = pageCode;
	}

	public void setTotal(long total) {
		this.total = total;
	}
	
	/***
	 * �û������������������
	 */
	@Override
	public String execute() throws Exception {
		if (!StringUtils.hasText(page)) {
			page = "1";
		}
		PageBean pageBean = new PageBean(Integer.parseInt(page),
				Constants.COMMENT_SIZE);
		commentList = commentService.findCommentList(s_comment, pageBean);
		total = commentService.getCommentCount(s_comment);
		pageCode = PageUtil.getPagination(request.getContextPath()
				+ "/comment.action", Integer.parseInt(String.valueOf(total)),
				Integer.parseInt(page), Constants.COMMENT_SIZE);
		return SUCCESS;
	}
	
	/***
	 * 
	 * Description: �ύ����
	 *         
	 * @create time 2015-6-17 ����12:59:43
	 *
	 * @return       
	 *
	 */
	public String save(){
		if(comment.getCreateTime()==null){
			comment.setCreateTime(new Date());
		}
		commentService.saveComment(comment);
		return "save_success";
	}
	
	
}