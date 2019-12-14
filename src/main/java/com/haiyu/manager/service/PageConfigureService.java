package com.haiyu.manager.service;

import com.haiyu.manager.dto.PageSearchDTO;
import com.haiyu.manager.pojo.PageConfigure;
import com.haiyu.manager.response.PageDataResult;

public interface PageConfigureService {

    PageDataResult getPageConfigureList(PageSearchDTO pageSearchDTO, Integer pageNum, Integer pageSize);

    Integer addOrUpdateConfigure(PageConfigure pageConfigure);
}
