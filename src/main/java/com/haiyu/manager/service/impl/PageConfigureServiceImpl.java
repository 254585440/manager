package com.haiyu.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.haiyu.manager.dao.PageConfigureMapper;
import com.haiyu.manager.dto.AdminUserDTO;
import com.haiyu.manager.dto.PageSearchDTO;
import com.haiyu.manager.dto.UserSearchDTO;
import com.haiyu.manager.pojo.PageConfigure;
import com.haiyu.manager.response.PageDataResult;
import com.haiyu.manager.service.PageConfigureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PageConfigureServiceImpl implements PageConfigureService {

    @Autowired
    private PageConfigureMapper pageConfigureMapper;

    @Override
    public PageDataResult getPageConfigureList(PageSearchDTO pageSearchDTO, Integer pageNum, Integer pageSize) {
        PageDataResult pageDataResult = new PageDataResult();
        List<PageConfigure> pageConfigures = pageConfigureMapper.getPageConfigureList(pageSearchDTO);

        PageHelper.startPage(pageNum, pageSize);

        if(pageConfigures.size() != 0){
            PageInfo<PageConfigure> pageInfo = new PageInfo<>(pageConfigures);
            pageDataResult.setList(pageConfigures);
            pageDataResult.setTotals((int) pageInfo.getTotal());
        }

        return pageDataResult;
    }
}
