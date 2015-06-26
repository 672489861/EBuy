package com.zjw.sys.action;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ApplicationAware;
import org.springframework.stereotype.Controller;

import com.zjw.bigtype.service.ProductBigTypeService;
import com.zjw.common.action.BaseAction;
import com.zjw.common.util.PageBean;
import com.zjw.common.util.ResponseUtil;
import com.zjw.domain.News;
import com.zjw.domain.Notice;
import com.zjw.domain.Product;
import com.zjw.domain.ProductBigType;
import com.zjw.domain.Tag;
import com.zjw.domain.User;
import com.zjw.news.service.NewsService;
import com.zjw.notice.service.NoticeService;
import com.zjw.product.service.ProductService;
import com.zjw.sys.service.SysService;
import com.zjw.tag.service.TagService;

/***
 * 
 * @Description: ϵͳ����Action
 * 
 * @author zjw
 * 
 * @create time 2015-6-24 ����03:59:18
 */
@Controller
public class SysAction extends BaseAction implements ApplicationAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Map<String, Object> application;

	private User user; // �޸�����

	@Resource(name = "productBigTypeService")
	private ProductBigTypeService productBigTypeService;

	@Resource(name = "tagService")
	private TagService tagService;

	@Resource(name = "noticeService")
	private NoticeService noticeService;

	@Resource(name = "newsService")
	private NewsService newsService;

	@Resource(name = "productService")
	private ProductService productService;

	@Resource(name = "sysService")
	private SysService sysService;

	@Override
	public void setApplication(Map<String, Object> application) {
		this.application = application;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void refreshSystem() throws IOException {
		// =============��Ʒ����С����Ϣ,���뵽 application ������==============
		List<ProductBigType> productBigTypes = productBigTypeService
				.findAllBigTypeList();
		application.put("bigTypeList", productBigTypes);
		// =============Tag ��ǩ���뵽 application ������==============
		List<Tag> tags = tagService.findTagList();
		application.put("tagList", tags);
		// =============���¹�����뵽 application ������=============
		List<Notice> notices = noticeService.findNoticeList(null, new PageBean(
				1, 7));
		application.put("noticeList", notices);
		// =============�������ż��뵽 application ������=============
		List<News> news = newsService.findNewsList(null, new PageBean(1, 7));
		application.put("newsList", news);
		// =============�����ؼۼ��뵽 application ������=============
		Product product = new Product();
		product.setSpecialPrice(1);
		List<Product> specialProducts = productService.findProducts(product,
				new PageBean(1, 8));
		application.put("specialPriceProductList", specialProducts);
		// =============�����Ƽ����뵽 application ������=============
		product.setSpecialPrice(0);
		product.setHot(1);
		List<Product> hotProducts = productService.findProducts(product,
				new PageBean(1, 6));
		application.put("hotProductList", hotProducts);
		JSONObject result = new JSONObject();
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(),
				result.toString());
	}

	/***
	 * 
	 * Description: �޸�����
	 * 
	 * @throws IOException
	 * 
	 * @create time 2015-6-24 ����04:19:01
	 * 
	 * 
	 */
	public void modifyPassword() throws IOException {
		sysService.modifyPassword(user);
		JSONObject result = new JSONObject();
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(),
				result.toString());
	}

}
