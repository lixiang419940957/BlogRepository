package com.lix.service;

import java.util.List;

import com.lix.pojo.Link;
import com.lix.utils.EasyUIResult;

public interface LinkService {
    
    public EasyUIResult queryLinkList(Integer page, Integer rows);

    public Boolean saveLink(Link link);

    public Boolean updateLink(Link link);
    
    public Boolean deleteLink(String[] ids);
 
    public List<Link> queryCountList();
    
}
