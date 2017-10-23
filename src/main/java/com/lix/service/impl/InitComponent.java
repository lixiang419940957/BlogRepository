package com.lix.service.impl;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.lix.pojo.Blog;
import com.lix.pojo.BlogType;
import com.lix.pojo.Blogger;
import com.lix.pojo.Link;
import com.lix.service.BlogService;
import com.lix.service.BlogTypeService;
import com.lix.service.BloggerService;
import com.lix.service.LinkService;

/**
 * 初始化:实现application缓存数据功能 在服务启动时，将数据库中的数据加载进内存 初始化组件 把博主信息 根据博客类别分类信息
 * 根据日期归档分类信息 存放到application中， 用以提高页面请求性能
 * 
 */
@Component
public class InitComponent implements ServletContextListener, ApplicationContextAware {

	private static ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		this.applicationContext = arg0;
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		ServletContext application = arg0.getServletContext();
		BloggerService bloggerService = (BloggerService) applicationContext.getBean("bloggerServiceImpl");
		// 查询博主信息
		Blogger blogger = bloggerService.queryBlogger();
		blogger.setPassword(null);
		application.setAttribute("blogger", blogger);
		// 查询博客类别以及博客的数量并按照博客类别排序码正序排序
		BlogTypeService blogTypeService = (BlogTypeService) applicationContext.getBean("blogTypeServiceImpl");
		List<BlogType> blogTypeCountList = blogTypeService.queryCountList();
		application.setAttribute("blogTypeCountList", blogTypeCountList);
		// 根据日期分组查询博客
		BlogService blogService = (BlogService) applicationContext.getBean("blogServiceImpl");
		List<Blog> blogCountList = blogService.queryCountList();
		application.setAttribute("blogCountList", blogCountList);
		// 查询全部友情链接的信息
		LinkService linkService = (LinkService) applicationContext.getBean("linkServiceImpl");
		List<Link> linkCountList = linkService.queryCountList();
		application.setAttribute("linkCountList", linkCountList);
	}

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {

	}

}
