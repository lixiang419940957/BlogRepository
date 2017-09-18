package com.lix.controller.admin;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lix.pojo.Link;
import com.lix.service.LinkService;
import com.lix.utils.EasyUIResult;

@RequestMapping("/admin/link")
@Controller
public class LinkAdminController {

    @Autowired
    private LinkService LinkService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public EasyUIResult queryLinkList(@RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "rows", required = false) Integer rows) {

        return this.LinkService.queryLinkList(page, rows);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> saveLink(Link link) {

        // 保存或更新成功标识
        Boolean flag = null;
        if (link.getId() == null) {
            flag = LinkService.saveLink(link);
        } else {
            flag = LinkService.updateLink(link);
        }

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", flag);
        return result;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteLink(@RequestParam("ids") String ids) {
        String[] idsStr = ids.split(",");
        HashMap<String, Object> result = new HashMap<String, Object>();
        Boolean flag = this.LinkService.deleteLink(idsStr);
        result.put("success", flag);
        return result;
    }
}
