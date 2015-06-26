package com.zjw.domain;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.google.gson.annotations.Expose;

/***
 * 
 * @Description: 新闻实体类
 * 
 * @author zjw
 * 
 * @create time 2015-6-12 下午01:40:16
 */

@Entity
@Table(name = "t_news")
public class News {

	@Expose
	private String content; // 新闻内容
	@Expose
	private Date createTime; // 新闻创建时间
	@Expose
	private Integer id;
	@Expose
	private String title; // 新闻标题

	@Lob
	@Column(columnDefinition = "text")
	@Basic(fetch = FetchType.LAZY)
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
	public Integer getId() {
		return id;
	}

	@Column(length = 50)
	public String getTitle() {
		return title;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
