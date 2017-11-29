package com.lix.controller.admin;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.lix.pojo.Blogger;
import com.lix.service.BloggerService;
import com.lix.utils.CryptographyUtil;

@RequestMapping("/admin/blogger")
@Controller
public class BloggerAdminCotroller {

    @Autowired
    private BloggerService bloggerService;

    // 允许上传的格式
    private static final String[] IMAGE_TYPE = new String[] { ".bmp", ".jpg", ".jpeg", ".gif", ".png" };

    /**
     * 查询博主信息
     * 
     * @return
     */
    @RequestMapping(value = "/find", method = RequestMethod.GET)
    @ResponseBody
    public Blogger queryBlogger() {

        return this.bloggerService.queryBlogger();
    }

    /**
     * 保存博主信息
     * 
     * @param imageFile
     * @param blogger
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> saveBlogger(@RequestParam("imageFile") MultipartFile imageFile,
            Blogger blogger, HttpServletRequest request) throws Exception {
        Boolean isLegal = false;
        Map<String, Object> result = new HashMap<String, Object>();
        for (String type : IMAGE_TYPE) {
            if (StringUtils.endsWithIgnoreCase(imageFile.getOriginalFilename(), type)) {
                isLegal = true;
            }
        }

        if (!isLegal) {
            result.put("isLegal", true);
            return result;
        }

        // 获取文件新路径
        String filePath = this.getFilePath(imageFile.getOriginalFilename(), request, blogger);
        File newFile = new File(filePath);
        // 写文件到磁盘
        imageFile.transferTo(newFile);
        BufferedImage image = ImageIO.read(newFile);
        if (image == null) {
            // 不合法，将磁盘上的文件删除
            newFile.delete();
            result.put("isLegal", true);
            return result;
        }

        Boolean flag = this.bloggerService.updateBlogger(blogger);

        if (flag) {
            result.put("success", true);
        } else {
            result.put("success", false);
        }

        return result;
    }

    /**
     * 修改博主密码
     * 
     * @param newPassword
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/modifyPassword", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> modifyPassword(String newPassword, String id, HttpServletResponse response)
            throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();
        Blogger blogger = new Blogger();
        blogger.setId(Integer.parseInt(id));
        blogger.setPassword(CryptographyUtil.md5(newPassword, "Myblog"));
        Boolean flag = bloggerService.updateBlogger(blogger);
        if (flag) {
            result.put("success", true);
        } else {
            result.put("success", false);
        }

        return result;
    }

    /**
     * 注销
     * 
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/logout")
    public String logout() throws Exception {
        SecurityUtils.getSubject().logout();
        return "redirect:/login.jsp";
    }

    /**
     * 文件新路径
     * 
     * @param sourceFileName
     * @param request
     * @return
     */
    private String getFilePath(String sourceFileName, HttpServletRequest request, Blogger blogger) {
        String baseFolder = request.getServletContext().getRealPath("/") + "static" + File.separator
                + "userImages";
        Date nowDate = new Date();
        // yyyy/MM/dd
        String fileFolder = baseFolder + File.separator + new DateTime(nowDate).toString("yyyy")
                + File.separator + new DateTime(nowDate).toString("MM") + File.separator
                + new DateTime(nowDate).toString("dd");
        File file = new File(fileFolder);
        if (!file.isDirectory()) {
            // 如果目录不存在，则创建目录
            file.mkdirs();
        }
        // 生成新的文件名
        String fileName = new DateTime(nowDate).toString("yyyyMMddhhmmssSSSS")
                + RandomUtils.nextInt(100, 9999) + "." + StringUtils.substringAfterLast(sourceFileName, ".");
        blogger.setImageName(fileName);
        return fileFolder + File.separator + fileName;
    }
}
