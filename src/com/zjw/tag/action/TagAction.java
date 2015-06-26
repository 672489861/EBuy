package com.zjw.tag.action;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.zjw.common.action.BaseAction;
import com.zjw.common.constant.Constants;
import com.zjw.common.util.GsonUtil;
import com.zjw.common.util.PageBean;
import com.zjw.common.util.ResponseUtil;
import com.zjw.domain.Tag;
import com.zjw.tag.service.TagService;

/***
 * 
 * @Description: 标签管理Action
 * 
 * @author zjw
 * 
 * @create time 2015-6-24 下午04:35:57
 */

@Controller
public class TagAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource(name = "tagService")
	private TagService tagService;

	private String page;
	private Tag tag;
	private Tag s_tag;

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	public Tag getS_tag() {
		return s_tag;
	}

	public void setS_tag(Tag s_tag) {
		this.s_tag = s_tag;
	}

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	/***
	 * 
	 * Description: 标签管理主界面查询
	 * 
	 * @throws IOException
	 * 
	 * @create time 2015-6-24 下午05:04:25
	 * 
	 * 
	 */
	public void list() throws IOException {
		PageBean pageBean = new PageBean(Integer.parseInt(page),
				Constants.ADMINMANAGER_SIZE);
		Map<String, Object> resultMap = tagService.getTagListWithManage(s_tag,
				pageBean);
		ResponseUtil.write(ServletActionContext.getResponse(),
				GsonUtil.toJsonWithExposrGson(resultMap));
	}

	/***
	 * 
	 * Description: 删除标签
	 * 
	 * @throws IOException
	 * 
	 * @create time 2015-6-24 下午05:34:01
	 * 
	 * 
	 */
	public void delete() throws IOException {
		tagService.delete(tag);
		JSONObject result = new JSONObject();
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(),
				result.toString());
	}

	/***
	 * 
	 * Description: 跳转
	 * 
	 * @create time 2015-6-24 下午07:28:50
	 * 
	 * @return
	 * 
	 */
	public String toTagUpdate() {
		if (tag.getId() != 0) {
			tag = tagService.getTagById(tag.getId());
		}
		return "toTagUpdate_success";
	}

	/***
	 * 
	 * Description: 添加标签
	 * 
	 * @throws IOException
	 * 
	 * @create time 2015-6-24 下午07:57:23
	 * 
	 * 
	 */
	public void saveTag() throws IOException {
		tagService.saveTag(tag);
		JSONObject result = new JSONObject();
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(),
				result.toString());
	}
}
