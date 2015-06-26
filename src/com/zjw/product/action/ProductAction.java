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
 * @Description: ��ƷAction
 * 
 * @author zjw
 * 
 * @create time 2015-6-15 ����08:03:01
 */

@Controller
public class ProductAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String mainPage; // ��ҳ��

	private String navCode; // ��ǰλ�ô���
	private String page; // ��ǰҳ
	private String pageCode; // ��ҳ����
	private Product product; // ������Ʒ����ϸ��Ϣ
	private int productId; // ��ʾ��ϸ��Ϣ����Ʒid
	private List<Product> productList;
	private Product productManager; // ��̨��װ���� ɾ�� �޸� ����Ϣ
	@Resource(name = "productService")
	private ProductService productService;

	private File proPic;

	private String proPicFileName;

	private Product s_product; // ��װ��ѯ����

	private Product s_productManager; // ��װ��ѯ����Ϣ

	private long total; // �ܼ�¼��

	/***
	 * 
	 * Description: ɾ����Ʒ
	 * 
	 * @throws IOException
	 * 
	 * @create time 2015-6-24 ����09:34:36
	 * 
	 * 
	 */
	public void delete() throws IOException {
		productService.delete(productManager.getId());
		JSONObject result = new JSONObject();
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
	}

	// Ĭ�Ϸ���Ϊexecute
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
		navCode = NavUtil.getNavCode("��Ʒ�б�");
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
	 * Description: ��Ʒ�����������ѯ
	 * 
	 * @throws IOException
	 * 
	 * @create time 2015-6-23 ����10:05:26
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
	 * Description: ����������
	 * 
	 * @create time 2015-6-16 ����01:54:14
	 * 
	 * @param product
	 * 
	 */
	@SuppressWarnings("unchecked")
	private void saveCurrentBrowse(Product product) {
		List<Product> products = (List<Product>) session.get("currentBrowse");
		if (products == null) { // ��һ�ε����Ʒ
			products = new ArrayList<Product>();
		}
		boolean flag = true;
		// �ж��������Ʒ�Ƿ��Ѵ�����������
		for (Product p : products) {
			if (p.getId() == product.getId()) {
				flag = false;
				break;
			}
		}
		if (flag) { // ������ �����Ʒ�����ϵ�һλ��
			products.add(0, product);
		}
		if (products.size() == 5) { // ֻ����4����Ʒ
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
	 * Description: ��ʾ��Ʒ��ϸ��Ϣ
	 * 
	 * @create time 2015-6-16 ����11:34:54
	 * 
	 * @return
	 * 
	 */

	public String showProduct() {
		product = productService.getProductById(productId);
		saveCurrentBrowse(product); // ����������
		navCode = NavUtil.getNavCode("��Ʒ����");
		mainPage = "/WEB-INF/views/product/productDetails.jsp";
		return SUCCESS;
	}

	/***
	 * 
	 * Description: ��ת��Ʒ�������
	 * 
	 * @create time 2015-6-23 ����04:27:50
	 * 
	 * @return
	 * 
	 */
	public String toManager() {
		return "toManager_success";
	}

	/***
	 * 
	 * Description: ��ת��Ʒ�������޸Ľ���
	 * 
	 * @create time 2015-6-24 ����09:38:53
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
	 * Description: ��ӻ��޸���Ʒ
	 * 
	 * @throws IOException
	 * 
	 * @create time 2015-6-24 ����11:20:19
	 * 
	 * 
	 */
	public void saveProduct() throws IOException {
		if (proPic != null) {
			// �ļ���
			String imageName = String.valueOf(System.currentTimeMillis());
			// ����·��
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
	 * Description: ������ƷΪ������Ʒ
	 * 
	 * @throws IOException
	 * 
	 * @create time 2015-6-24 ����02:00:47
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
	 * Description: ������ƷΪ�ؼ�
	 * 
	 * @throws IOException
	 * 
	 * @create time 2015-6-24 ����02:00:47
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
