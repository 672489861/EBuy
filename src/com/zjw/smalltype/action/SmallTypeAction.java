package com.zjw.smalltype.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
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
import com.zjw.domain.ProductSmallType;
import com.zjw.product.service.ProductService;
import com.zjw.smalltype.service.ProductSmallTypeService;

/***
 * 
 * @Description: 商品小类
 * 
 * @author zjw
 * 
 * @create time 2015-6-23 上午12:53:15
 */

@Controller
public class SmallTypeAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource(name = "smallTypeService")
	private ProductSmallTypeService smallTypeService;

	@Resource(name = "productService")
	private ProductService productService;

	private ProductSmallType s_smallType;
	private ProductSmallType smallType;
	private String page;
	private String bigTypeId;
	
	public String getBigTypeId() {
		return bigTypeId;
	}

	public void setBigTypeId(String bigTypeId) {
		this.bigTypeId = bigTypeId;
	}

	public ProductSmallType getS_smallType() {
		return s_smallType;
	}

	public void setS_smallType(ProductSmallType s_smallType) {
		this.s_smallType = s_smallType;
	}

	public ProductSmallType getSmallType() {
		return smallType;
	}

	public void setSmallType(ProductSmallType smallType) {
		this.smallType = smallType;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	/**
	 * 
	 * Description: 商品大类管理
	 * 
	 * @throws IOException
	 * 
	 * @create time 2015-6-22 下午10:35:18
	 * 
	 * 
	 */

	public void list() throws IOException {
		PageBean pageBean = new PageBean(Integer.parseInt(page),
				Constants.ADMINMANAGER_SIZE);
		List<ProductSmallType> users = smallTypeService
				.getSmallTypeListWithManager(s_smallType, pageBean);
		long total = smallTypeService.getSmallTypeListTotal(s_smallType);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("total", total);
		resultMap.put("rows", users);
		ResponseUtil.write(ServletActionContext.getResponse(),
				GsonUtil.toJsonWithExposrGson(resultMap));
	}

	/***
	 * 
	 * Description: 删除商品小类
	 * 
	 * @throws IOException
	 * 
	 * @create time 2015-6-22 下午11:32:29
	 * 
	 * 
	 */
	public void delete() throws IOException {
		JSONObject result = new JSONObject();
		if (productService.existProductWithSmallTypeId(smallType.getId())) {
			result.put("success", false);
			result.put("errorMsg", "商品小类下有商品,无法删除!");
		} else {
			smallTypeService.delete(smallType.getId());
			result.put("success", true);
		}
		ResponseUtil.write(ServletActionContext.getResponse(),
				result.toString());
	}

	/***
	 * 
	 * Description: 跳转商品小类新增和修改界面
	 * 
	 * @create time 2015-6-23 上午11:39:14
	 * 
	 * @return
	 * 
	 */
	public String toSmallTypeUpdate() {
		if (smallType.getId() != 0) {
			smallType = smallTypeService.getSmallTypeById(smallType.getId());
		}
		return "toSmallTypeUpdate_success";
	}

	/***
	 * 
	 * Description: 添加或修改商品小类
	 * @throws IOException 
	 * 
	 * @create time 2015-6-23 下午01:53:50
	 * 
	 * 
	 */
	public void saveSmallType() throws IOException {
		smallTypeService.saveSmallType(smallType);
		JSONObject result = new JSONObject();
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(),
				result.toString());
	}
	
	/***
	 * 
	 * Description: 加载商品小类下拉
	 * 
	 * @throws IOException
	 * 
	 * @create time 2015-6-23 下午01:49:58
	 * 
	 * 
	 */
	public void comboList() throws IOException {
		List<ProductSmallType> smallTypes = smallTypeService.getSmallTypeComboList(bigTypeId);
		ProductSmallType productSmallType = new ProductSmallType();
		productSmallType.setId(0);
		productSmallType.setName("请选择");
		smallTypes.add(0, productSmallType);
		ResponseUtil.write(ServletActionContext.getResponse(),
				GsonUtil.toJsonWithExposrGson(smallTypes));
	}
	
}
