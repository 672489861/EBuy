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
 * @Description: 初始化Action 加载商品相关信息
 * 
 * @author zjw
 * 
 * @create time 2015-6-12 下午06:52:33
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
		// =============商品大类小类信息,加入到 application 缓存中==============
		ProductBigTypeService productBigTypeService = (ProductBigTypeService) applicationContext
				.getBean("productBigTypeService");
		List<ProductBigType> productBigTypes = productBigTypeService
				.findAllBigTypeList();
		servletContext.setAttribute("bigTypeList", productBigTypes);
		// =============Tag 标签加入到 application 缓存中==============
		TagService tagService = (TagService) applicationContext
				.getBean("tagService");
		List<Tag> tags = tagService.findTagList();
		servletContext.setAttribute("tagList", tags);
		// =============最新公告加入到 application 缓存中=============
		NoticeService noticeService = (NoticeService) applicationContext
				.getBean("noticeService");
		List<Notice> notices = noticeService.findNoticeList(null, new PageBean(
				1, 7));
		servletContext.setAttribute("noticeList", notices);
		// =============最新新闻加入到 application 缓存中=============
		NewsService newsService = (NewsService) applicationContext
				.getBean("newsService");
		List<News> news = newsService.findNewsList(null, new PageBean(1, 7));
		servletContext.setAttribute("newsList", news);
		// =============今日特价加入到 application 缓存中=============
		Product product = new Product();
		product.setSpecialPrice(1);
		ProductService productService = (ProductService) applicationContext
				.getBean("productService");
		List<Product> specialProducts = productService.findProducts(product,
				new PageBean(1, 8));
		servletContext.setAttribute("specialPriceProductList", specialProducts);
		// =============热卖推荐加入到 application 缓存中=============
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
