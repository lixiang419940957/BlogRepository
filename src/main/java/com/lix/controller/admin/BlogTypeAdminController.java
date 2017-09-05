package com.lix.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lix.pojo.BlogType;
import com.lix.service.BlogService;
import com.lix.service.BlogTypeService;
import com.lix.utils.EasyUIResult;

@RequestMapping("/admin/blogType")
@Controller
public class BlogTypeAdminController {

    @Autowired
    private BlogTypeService blogTypeService;

    @Autowired
    private BlogService blogService;

    /**
     * 分页查询博客类别信息
     * 
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public EasyUIResult queryBlogTypeList(@RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "rows", required = false) Integer rows) {
        return this.blogTypeService.queryBlogTypeList(page, rows);
    }

    /**
     * 添加或修改博客类别信息
     * 
     * @param blogType
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> saveBlogType(BlogType blogType) {
        // 保存或更新成功标识
        Boolean flag = null;
        if (blogType.getId() == null) {
            flag = blogTypeService.saveBlogType(blogType);
        } else {
            flag = blogTypeService.updateBlogType(blogType);
        }

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", flag);
        return result;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteBlogType(@RequestParam("ids") String ids) {
        String[] idsStr = ids.split(",");
        HashMap<String, Object> result = new HashMap<String, Object>();
        for (int i = 0; i < idsStr.length; i++) {
            if (this.blogService.queryBlogCountByTypeId(Integer.parseInt(idsStr[i])) > 0) {
                result.put("exist", "博客类别下有博客,不能删除！");
            } else {
                this.blogTypeService.deleteBlogType(Integer.parseInt(idsStr[i]));
            }
        }
        result.put("success", true);
        return result;
    }
    
    @RequestMapping(value = "/comboboxlist", method = RequestMethod.GET)
    @ResponseBody
    public List<BlogType> queryBlogTypeComboBox(){
        return this.blogTypeService.queryBlogTypeComboBox();
    }
}
