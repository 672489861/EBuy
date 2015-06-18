package com.zjw.product.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

import com.zjw.common.action.BaseAction;
import com.zjw.common.constant.Constants;
import com.zjw.domain.Product;
import com.zjw.product.service.ProductService;
import com.zjw.common.util.NavUtil;
import com.zjw.common.util.PageBean;
import com.zjw.common.util.PageUtil;

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

	@Resource(name = "productService")
	private ProductService productService;

	private List<Product> productList;
	private Product s_product; // ��װ��ѯ����
	private String page; // ��ǰҳ
	private long total; // �ܼ�¼��
	private String pageCode; // ��ҳ����
	private String mainPage; // ��ҳ��
	private String navCode; // ��ǰλ�ô���
	private int productId; // ��ʾ��ϸ��Ϣ����Ʒid
	private Product product; // ������Ʒ����ϸ��Ϣ

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	public Product getS_product() {
		return s_product;
	}

	public void setS_product(Product s_product) {
		this.s_product = s_product;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public String getPageCode() {
		return pageCode;
	}

	public void setPageCode(String pageCode) {
		this.pageCode = pageCode;
	}

	public String getMainPage() {
		return mainPage;
	}

	public void setMainPage(String mainPage) {
		this.mainPage = mainPage;
	}

	public String getNavCode() {
		return navCode;
	}

	public void setNavCode(String navCode) {
		this.navCode = navCode;
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

}
