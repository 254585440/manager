package com.haiyu.manager.controller.configure;


import com.haiyu.manager.dao.PageConfigureMapper;
import com.haiyu.manager.dto.PageSearchDTO;
import com.haiyu.manager.pojo.PageConfigure;
import com.haiyu.manager.response.PageDataResult;
import com.haiyu.manager.service.PageConfigureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("configure")
@Slf4j
public class ConfigureController {

    @Autowired
    private PageConfigureMapper pageConfigureMapper;

    @Autowired
    private PageConfigureService pageConfigureService;

    /**
     * 跳转首页配置
     * @return
     */
    @RequestMapping("/pageConfigure")
    public String pageConfigure(Integer id) {
        log.info("进入页面配置");
        if(id != null){
            PageConfigure pageConfigure = pageConfigureMapper.selectByPrimaryKey(id);
        }
        return "/configure/pageConfigure";
    }

    /**
     * 跳转页面配置列表
     * @return
     */
    @RequestMapping("/pageConfigureList")
    public String pageConfigureList() {
        return "/configure/pageConfigureList";
    }

    /**
     *
     * 功能描述: 分页查询用户列表
     *
     * @param:
     * @return:
     * @auther: zel
     * @date: 2019/12/13 11:10
     */
    @RequestMapping(value = "/getPageConfigureList", method = RequestMethod.POST)
    @ResponseBody
    public PageDataResult getPageConfigureList(@RequestParam("pageNum") Integer pageNum,
                                               @RequestParam("pageSize") Integer pageSize,/*@Valid PageRequest page,*/ PageSearchDTO pageSearchDTO) {
        /*logger.info("分页查询用户列表！搜索条件：userSearch：" + userSearch + ",pageNum:" + page.getPageNum()
                + ",每页记录数量pageSize:" + page.getPageSize());*/
        PageDataResult pageDataResult = new PageDataResult();
        try {
            if(null == pageNum) {
                pageNum = 1;
            }
            if(null == pageSize) {
                pageSize = 10;
            }
            // 获取用户列表
            pageDataResult = pageConfigureService.getPageConfigureList(pageSearchDTO, pageNum ,pageSize);
            log.info("页面配置列表查询=pdr:" + pageDataResult);

        } catch (Exception e) {
            e.printStackTrace();
            log.error("页面配置列表查询异常！", e);
        }
        return pageDataResult;
    }
}
