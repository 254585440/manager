package com.haiyu.manager.controller.configure;


import com.alibaba.fastjson.JSONObject;
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
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

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
    public ModelAndView pageConfigure(Integer id) {
        log.info("进入页面配置");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/configure/pageConfigure");
        PageConfigure pageConfigure = null;
        if(id != null){
            pageConfigure = pageConfigureMapper.selectByPrimaryKey(id);
        }
        modelAndView.addObject("pageConfigure",pageConfigure);
        return modelAndView;
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
     * 添加或修改配置
     * @param pageConfigure
     * @return
     */
    @RequestMapping("/addOrUpdateConfigure")
    @ResponseBody
    public Map<String,String> addOrUpdateConfigure(PageConfigure pageConfigure){
        int i = pageConfigureService.addOrUpdateConfigure(pageConfigure);
        Map<String,String> map = new HashMap<>();
        map.put("code",String.valueOf(i));
        map.put("msg","添加或修改成功");
        return map;
    }

    /**
     * 删除页面配置
     * @param id
     * @return
     */
    @RequestMapping("/delConfigure")
    @ResponseBody
    public Map<String,String> delConfigure(Integer id){
        int i = pageConfigureMapper.deleteByPrimaryKey(id);
        Map<String,String> map = new HashMap<>();
        map.put("code",String.valueOf(i));
        map.put("msg","删除成功");
        return map;
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
