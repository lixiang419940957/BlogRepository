package com.lix.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.lix.pojo.Blog;
import com.lix.pojo.BlogType;
import com.lix.pojo.Blogger;
import com.lix.pojo.Link;
import com.lix.service.BlogService;
import com.lix.service.BlogTypeService;
import com.lix.service.BloggerService;
import com.lix.service.LinkService;

@RequestMapping("/admin/system")
@Controller
public class SystemAdminController {

	@Autowired
	private BloggerService bloggerService;

	@Autowired
	private BlogTypeService blogTypeService;

	@Autowired
	private BlogService blogService;

	@Autowired
	private LinkService linkService;

	@RequestMapping(value = "/refreshSystem", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> refreshSystem(HttpServletRequest request) {

		ServletContext application = RequestContextUtils.getWebApplicationContext(request).getServletContext();

		Map<String, Object> result = new HashMap<String, Object>();
		try {
			Blogger blogger = bloggerService.queryBlogger(); // 查询博主信息
			blogger.setPassword(null);
			application.setAttribute("blogger", blogger);

			List<BlogType> blogTypeCountList = blogTypeService.queryCountList(); // 查询博客类别以及博客的数量
			application.setAttribute("blogTypeCountList", blogTypeCountList);

			List<Blog> blogCountList = blogService.queryCountList(); // 根据日期分组查询博客
			application.setAttribute("blogCountList", blogCountList);

			List<Link> linkList = linkService.queryCountList();// 获取所有友情链接
			application.setAttribute("linkList", linkList);
		} catch (Exception e) {
			result.put("success", false);
			e.printStackTrace();
			return result;
		}
		result.put("success", true);
		return result;
	}

}
