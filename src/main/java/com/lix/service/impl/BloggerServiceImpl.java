package com.lix.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lix.dao.BloggerDao;
import com.lix.pojo.Blogger;
import com.lix.service.BloggerService;

@Service
public class BloggerServiceImpl extends BaseService<Blogger> implements BloggerService {

    @Autowired
    private BloggerDao bloggerDao;

    public Blogger queryBloggerByUserName(String userName) {
        Blogger record = new Blogger();
        record.setUserName(userName);
        Blogger blogger = super.queryOne(record);
        return blogger;
    }

    public Blogger queryBlogger() {
        List<Blogger> blogger = super.queryAll();
        return blogger.get(0);
    }

    public Boolean updateBlogger(Blogger blogger) {
        Integer count = super.updateSelective(blogger);
        return count == 1;
    }

}
