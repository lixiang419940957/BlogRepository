package com.lix.service.impl;

import org.springframework.stereotype.Service;

import com.lix.pojo.Blogger;
import com.lix.service.BloggerService;

@Service
public class BloggerServiceImpl extends BaseService<Blogger> implements BloggerService{

    public Blogger queryBloggerByUserName(String userName) {
        Blogger record = new Blogger();
        record.setUserName(userName);
        Blogger blogger = super.queryOne(record);
        return blogger;
    }

}
