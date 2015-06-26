package com.zjw.product.action;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;

import com.zjw.common.action.BaseAction;
import com.zjw.common.constant.Constants;
import com.zjw.domain.Product;
import com.zjw.product.service.ProductService;
import com.zjw.common.util.GsonUtil;
import com.zjw.common.util.NavUtil;
import com.zjw.common.util.PageBean;
import com.zjw.common.util.PageUtil;
import com.zjw.common.util.ResponseUtil;

/***
 * 
 * @Description: 商品Action
 * 
 * @author zjw
 * 
 * @create time 2015-6-15 下午08:03:01
 */

@Controller
public class ProductAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String mainPage; // 主页面

	private String navCode; // 当前位置代码
	private String page; // 当前页
	private String pageCode; // 分页代码
	private Product product; // 返回商品的详细信息
	private int productId; // 显示详细信息的商品id
	private List<Product> productList;
	private Product productManager; // 后台封装新增 删除 修改 的信息
	@Resource(name = "productService")
	private ProductService productService;

	private File proPic;

	private String proPicFileName;

	private Product s_product; // 封装查询条件

	private Product s_productManager; // 封装查询的信息

	private long total; // 总记录数

	/***
	 * 
	 * Description: 删除商品
	 * 
	 * @throws IOException
	 * 
	 * @create time 2015-6-24 上午09:34:36
	 * 
	 * 
	 */
	public void delete() throws IOException {
		productService.delete(productManager.getId());
		JSONObject result = new JSONObject();
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
	}

	// 默认方法为execute
	@Override
	public String execute() throws Exception {
		if (!StringUtils.hasText(page)) {
			page = "1";
		}
		PageBean bean = new PageBean(Integer.parseInt(page),
				Constants.Product_BIGTYPE_SIZE);
		productList = productService.findProducts(s_product, bean);
		total = productService.getProductCount(s_product);
		StringBuffer params = new StringBuffer();
		if (s_product != null) {
			if (s_product.getBigType() != null) {
				params.append("s_product.bigType.id=").append(
						s_product.getBigType().getId());
			}
			if (s_product.getSmallType() != null) {
				params.append("s_product.smallType.id=").append(
						s_product.getSmallType().getId());
			}
			if (StringUtils.hasText(s_product.getName())) {
				params.append("s_product.name=").append(s_product.getName());
			}
		}
		pageCode = PageUtil.getPagination(request.getContextPath()
				+ "/product.action", Integer.parseInt(String.valueOf(total)),
				Integer.parseInt(page), Constants.Product_BIGTYPE_SIZE,
				params.toString());
		navCode = NavUtil.getNavCode("商品列表");
		mainPage = "/WEB-INF/views/product/productList.jsp";
		return SUCCESS;
	}

	public String getMainPage() {
		return mainPage;
	}

	public String getNavCode() {
		return navCode;
	}

	public String getPage() {
		return page;
	}

	public String getPageCode() {
		return pageCode;
	}

	public Product getProduct() {
		return product;
	}

	public int getProductId() {
		return productId;
	}

	public List<Product> getProductList() {
		return productList;
	}

	public Product getProductManager() {
		return productManager;
	}

	public File getProPic() {
		return proPic;
	}

	public String getProPicFileName() {
		return proPicFileName;
	}

	public Product getS_product() {
		return s_product;
	}

	public Product getS_productManager() {
		return s_productManager;
	}

	public long getTotal() {
		return total;
	}

	/***
	 * 
	 * Description: 商品管理主界面查询
	 * 
	 * @throws IOException
	 * 
	 * @create time 2015-6-23 下午10:05:26
	 * 
	 * 
	 */
	public void list() throws IOException {
		PageBean pageBean = new PageBean(Integer.parseInt(page),
				Constants.ADMINMANAGER_SIZE);
		List<Product> products = productService.findProducts(s_productManager,
				pageBean);
		long total = productService.getProductCount(s_productManager);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("total", total);
		resultMap.put("rows", products);
		ResponseUtil.write(ServletActionContext.getResponse(),
				GsonUtil.toJsonWithExposrGson(resultMap));
	}

	/***
	 * 
	 * Description: 保存最近浏览
	 * 
	 * @create time 2015-6-16 下午01:54:14
	 * 
	 * @param product
	 * 
	 */
	@SuppressWarnings("unchecked")
	private void saveCurrentBrowse(Product product) {
		List<Product> products = (List<Product>) session.get("currentBrowse");
		if (products == null) { // 第一次点击商品
			products = new ArrayList<Product>();
		}
		boolean flag = true;
		// 判断浏览的商品是否已存在最近浏览中
		for (Product p : products) {
			if (p.getId() == product.getId()) {
				flag = false;
				break;
			}
		}
		if (flag) { // 不存在 添加商品至集合第一位置
			products.add(0, product);
		}
		if (products.size() == 5) { // 只保存4个商品
			products.remove(4);
		}
		session.put("currentBrowse", products);
	}

	public void setMainPage(String mainPage) {
		this.mainPage = mainPage;
	}

	public void setNavCode(String navCode) {
		this.navCode = navCode;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public void setPageCode(String pageCode) {
		this.pageCode = pageCode;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	public void setProductManager(Product productManager) {
		this.productManager = productManager;
	}

	public void setProPic(File proPic) {
		this.proPic = proPic;
	}

	public void setProPicFileName(String proPicFileName) {
		this.proPicFileName = proPicFileName;
	}

	public void setS_product(Product s_product) {
		this.s_product = s_product;
	}

	public void setS_productManager(Product s_productManager) {
		this.s_productManager = s_productManager;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	/***
	 * 
	 * Description: 显示商品详细信息
	 * 
	 * @create time 2015-6-16 上午11:34:54
	 * 
	 * @return
	 * 
	 */

	public String showProduct() {
		product = productService.getProductById(productId);
		saveCurrentBrowse(product); // 保存最近浏览
		navCode = NavUtil.getNavCode("商品详情");
		mainPage = "/WEB-INF/views/product/productDetails.jsp";
		return SUCCESS;
	}

	/***
	 * 
	 * Description: 跳转商品管理界面
	 * 
	 * @create time 2015-6-23 下午04:27:50
	 * 
	 * @return
	 * 
	 */
	public String toManager() {
		return "toManager_success";
	}

	/***
	 * 
	 * Description: 跳转商品新增和修改界面
	 * 
	 * @create time 2015-6-24 上午09:38:53
	 * 
	 * 
	 */
	public String toProductUpdate() {
		if (productManager.getId() != 0) {
			productManager = productService.getProductById(productManager
					.getId());
		}
		return "toProductUpdate_success";
	}

	/***
	 * 
	 * Description: 添加或修改商品
	 * 
	 * @throws IOException
	 * 
	 * @create time 2015-6-24 上午11:20:19
	 * 
	 * 
	 */
	public void saveProduct() throws IOException {
		if (proPic != null) {
			// 文件名
			String imageName = String.valueOf(System.currentTimeMillis());
			// 保存路径
			String realPath = ServletActionContext.getServletContext()
					.getRealPath("/images/product");
			String imageFile = imageName + "." + proPicFileName.split("\\.")[1];
			File saveFile = new File(realPath, imageFile);
			FileCopyUtils.copy(proPic, saveFile);
			productManager.setProPic("images/product/" + imageName);
		}
		productService.saveProduct(productManager);
		JSONObject result = new JSONObject();
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(),
				result.toString());
	}

	/***
	 * 
	 * Description: 设置商品为热卖商品
	 * 
	 * @throws IOException
	 * 
	 * @create time 2015-6-24 下午02:00:47
	 * 
	 * 
	 */
	public void setHot() throws IOException {
		productManager.setHot(1);
		productManager.setHotTime(new Date());
		productService.updateProduct(productManager);
		JSONObject result = new JSONObject();
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
	}

	/***
	 * 
	 * Description: 设置商品为特价
	 * 
	 * @throws IOException
	 * 
	 * @create time 2015-6-24 下午02:00:47
	 * 
	 * 
	 */
	public void setSpecial() throws IOException {
		productManager.setSpecialPrice(1);
		productManager.setSpecialTime(new Date());
		productService.updateProduct(productManager);
		JSONObject result = new JSONObject();
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
	}
}
