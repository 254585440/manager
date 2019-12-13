package com.haiyu.manager.dao;

import com.haiyu.manager.dto.PageSearchDTO;
import com.haiyu.manager.pojo.PageConfigure;

import java.util.List;

public interface PageConfigureMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PageConfigure record);

    int insertSelective(PageConfigure record);

    PageConfigure selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PageConfigure record);

    int updateByPrimaryKey(PageConfigure record);

    List<PageConfigure> getPageConfigureList(PageSearchDTO pageSearchDTO);
}