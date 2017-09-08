package com.lix.service;

import com.lix.pojo.Blogger;

public interface BloggerService {
    
    public Blogger queryBloggerByUserName(String userName);

    public Blogger queryBlogger();

    public Boolean updateBlogger(Blogger blogger);
    
}
