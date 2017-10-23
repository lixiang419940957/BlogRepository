package com.lix.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/")
@Controller
public class IndexController {

	@RequestMapping("/index")
	public String index(ModelMap map, @RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "typeId", required = false) String typeId,
			@RequestParam(value = "releaseDateStr", required = false) String releaseDateStr, ModelMap modelMap,
			HttpServletRequest request) {
		return null;
	}

}
