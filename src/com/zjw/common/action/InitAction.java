package com.zjw.common.action;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.zjw.domain.News;
import com.zjw.domain.Notice;
import com.zjw.domain.Product;
import com.zjw.domain.ProductBigType;
import com.zjw.domain.Tag;
import com.zjw.news.service.NewsService;
import com.zjw.notice.service.NoticeService;
import com.zjw.product.service.ProductService;
import com.zjw.tag.service.TagService;
import com.zjw.bigtype.service.ProductBigTypeService;
import com.zjw.common.util.PageBean;

/***
 * 
 * @Description: ��ʼ��Action ������Ʒ�����Ϣ
 * 
 * @author zjw
 * 
 * @create time 2015-6-12 ����06:52:33
 */

@Component
public class InitAction implements ServletContextListener,
		ApplicationContextAware {

	private static ApplicationContext applicationContext;

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		ServletContext servletContext = servletContextEvent.getServletContext();
		// =============��Ʒ����С����Ϣ,���뵽 application ������==============
		ProductBigTypeService productBigTypeService = (ProductBigTypeService) applicationContext
				.getBean("productBigTypeService");
		List<ProductBigType> productBigTypes = productBigTypeService
				.findAllBigTypeList();
		servletContext.setAttribute("bigTypeList", productBigTypes);
		// =============Tag ��ǩ���뵽 application ������==============
		TagService tagService = (TagService) applicationContext
				.getBean("tagService");
		List<Tag> tags = tagService.findTagList();
		servletContext.setAttribute("tagList", tags);
		// =============���¹�����뵽 application ������=============
		NoticeService noticeService = (NoticeService) applicationContext
				.getBean("noticeService");
		List<Notice> notices = noticeService.findNoticeList(null, new PageBean(
				1, 7));
		servletContext.setAttribute("noticeList", notices);
		// =============�������ż��뵽 application ������=============
		NewsService newsService = (NewsService) applicationContext
				.getBean("newsService");
		List<News> news = newsService.findNewsList(null, new PageBean(1, 7));
		servletContext.setAttribute("newsList", news);
		// =============�����ؼۼ��뵽 application ������=============
		Product product = new Product();
		product.setSpecialPrice(1);
		ProductService productService = (ProductService) applicationContext
				.getBean("productService");
		List<Product> specialProducts = productService.findProducts(product,
				new PageBean(1, 8));
		servletContext.setAttribute("specialPriceProductList", specialProducts);
		// =============�����Ƽ����뵽 application ������=============
		product.setSpecialPrice(0);
		product.setHot(1);
		List<Product> hotProducts = productService.findProducts(product,
				new PageBean(1, 6));
		servletContext.setAttribute("hotProductList", hotProducts);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		InitAction.applicationContext = applicationContext;
	}

}
