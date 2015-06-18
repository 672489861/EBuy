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
 * @Description: 留言Action
 * 
 * @author zjw
 * 
 * @create time 2015-6-17 上午12:14:09
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
	private String page; // 当前页
	private String pageCode; // 分页代码
	private long total; // 记录总数
	private Comment s_comment; // 封装查询条件
	private Comment comment; // 留言实体
	
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
	 * 用户留言主界面加载数据
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
	 * Description: 提交留言
	 *         
	 * @create time 2015-6-17 下午12:59:43
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
