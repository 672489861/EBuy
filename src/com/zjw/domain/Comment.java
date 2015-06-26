package com.zjw.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.google.gson.annotations.Expose;

/***
 * 
 * @Description: ����ʵ����
 * 
 * @author zjw
 * 
 * @create time 2015-6-12 ����02:27:42
 */

@Entity
@Table(name = "t_comment")
public class Comment {

	@Expose
	private String content; // ��������
	@Expose
	private Date createTime; // ����ʱ��
	@Expose
	private int id;
	@Expose
	private String nickName; // ������
	@Expose
	private String replyContent; // �ظ�����
	@Expose
	private Date replyTime; // �ظ�ʱ��

	@Column(length = 1000)
	public String getContent() {
		return content;
	}

	@Column
	public Date getCreateTime() {
		return createTime;
	}

	@Id
	@GeneratedValue(generator = "_native")
	@GenericGenerator(strategy = "native", name = "_native")
	public int getId() {
		return id;
	}

	@Column(length = 30)
	public String getNickName() {
		return nickName;
	}

	@Column(length = 1000)
	public String getReplyContent() {
		return replyContent;
	}

	@Column
	public Date getReplyTime() {
		return replyTime;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public void setReplyTime(Date replyTime) {
		this.replyTime = replyTime;
	}

}
