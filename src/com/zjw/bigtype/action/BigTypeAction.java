package com.zjw.bigtype.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.zjw.bigtype.service.ProductBigTypeService;
import com.zjw.common.action.BaseAction;
import com.zjw.common.constant.Constants;
import com.zjw.common.util.GsonUtil;
import com.zjw.common.util.PageBean;
import com.zjw.common.util.ResponseUtil;
import com.zjw.domain.ProductBigType;
import com.zjw.smalltype.service.ProductSmallTypeService;

/***
 * 
 * @Description: ��Ʒ����Action
 * 
 * @author zjw
 * 
 * @create time 2015-6-22 ����07:39:27
 */
@Controller
public class BigTypeAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource(name = "productBigTypeService")
	private ProductBigTypeService bigTypeService;

	@Resource(name = "smallTypeService")
	private ProductSmallTypeService smallTypeService;

	private String page;
	private ProductBigType s_bigType; // ��װ��ѯ
	private ProductBigType bigType; // ��װ����

	public ProductBigType getBigType() {
		return bigType;
	}

	public void setBigType(ProductBigType bigType) {
		this.bigType = bigType;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public ProductBigType getS_bigType() {
		return s_bigType;
	}

	public void setS_bigType(ProductBigType s_bigType) {
		this.s_bigType = s_bigType;
	}

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	/**
	 * 
	 * Description: ��Ʒ�������
	 * 
	 * @throws IOException
	 * 
	 * @create time 2015-6-22 ����10:35:18
	 * 
	 * 
	 */

	public void list() throws IOException {
		PageBean pageBean = new PageBean(Integer.parseInt(page),
				Constants.ADMINMANAGER_SIZE);
		List<ProductBigType> users = bigTypeService.getBigTypeListWithManager(
				s_bigType, pageBean);
		long total = bigTypeService.getBigTypeListTotal(s_bigType);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("total", total);
		resultMap.put("rows", users);
		ResponseUtil.write(ServletActionContext.getResponse(),
				GsonUtil.toJsonWithExposrGson(resultMap));
	}

	/***
	 * 
	 * Description: ɾ����Ʒ����
	 * 
	 * @throws IOException
	 * 
	 * @create time 2015-6-22 ����11:32:29
	 * 
	 * 
	 */
	public void delete() throws IOException {
		JSONObject result = new JSONObject();
		if (smallTypeService.existSmallTypeWithBigTypeId(bigType.getId())) {
			result.put("success", false);
			result.put("errorMsg", "��Ʒ���������ƷС��");
		} else {
			bigTypeService.delete(bigType.getId());
			result.put("success", true);
		}
		ResponseUtil.write(ServletActionContext.getResponse(),
				result.toString());
	}

	/***
	 * 
	 * Description: ��ת��Ʒ������½���
	 * 
	 * @create time 2015-6-22 ����11:56:21
	 * 
	 * @return
	 * 
	 */

	public String toBigTypeUpdate() {
		if (bigType.getId() != 0) {
			bigType = bigTypeService.getBigTypeById(bigType.getId());
		}
		return "toBigTypeUpdate_success";
	}

	/***
	 * 
	 * Description: ������Ʒ����
	 * 
	 * @throws IOException
	 * 
	 * @create time 2015-6-23 ����12:27:42
	 * 
	 * 
	 */
	public void saveBigType() throws IOException {
		bigTypeService.saveBigType(bigType);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(),
				jsonObject.toString());
	}

	/***
	 * 
	 * Description: ������Ʒ��������
	 * 
	 * @throws IOException
	 * 
	 * @create time 2015-6-23 ����01:49:58
	 * 
	 * 
	 */
	public void comboList() throws IOException {
		List<ProductBigType> bigTypes = bigTypeService.getBigTypeList();
		ProductBigType productBigType = new ProductBigType();
		productBigType.setId(0);
		productBigType.setName("��ѡ��");
		bigTypes.add(0, productBigType);
		ResponseUtil.write(ServletActionContext.getResponse(),
				GsonUtil.toJsonWithExposrGson(bigTypes));
	}

}
