package com.lix.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tk.mybatis.mapper.entity.Example;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lix.dao.LinkDao;
import com.lix.pojo.Link;
import com.lix.service.LinkService;
import com.lix.utils.EasyUIResult;

@Service
public class LinkServiceImpl extends BaseService<Link> implements LinkService {

    @Autowired
    private LinkDao linkDao;

    public EasyUIResult queryLinkList(Integer page, Integer rows) {
        // 设置分页参数
        PageHelper.startPage(page, rows);

        Example example = new Example(Link.class);
        // 按照创建时间排序
        example.setOrderByClause("orderNo ASC");
        List<Link> list = this.linkDao.selectByExample(example);

        PageInfo<Link> pageInfo = new PageInfo<Link>(list);

        return new EasyUIResult(pageInfo.getTotal(), pageInfo.getList());
    }

    public Boolean saveLink(Link link) {
        Integer count = super.save(link);
        return count == 1;
    }

    public Boolean updateLink(Link link) {
        Integer count = super.updateSelective(link);
        return count == 1;
    }

    public Boolean deleteLink(String[] ids) {
        List<Object> list = new ArrayList<Object>();
        for (int i = 0; i < ids.length; i++) {
            list.add(ids[i]);
        }
        Integer count = super.deleteByIds(Link.class, "id", list);
        return count == ids.length;
    }

	@Override
	public List<Link> queryCountList() {
		Example example = new Example(Link.class);
		example.setOrderByClause("orderNo ASC");
		List<Link> linkCountList = this.linkDao.selectByExample(example);
		return linkCountList;
	}

}
