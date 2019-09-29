package com.neusoft.base.spring;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.neusoft.base.comm.GlobalConst;
import com.neusoft.base.utils.CalendarUtils;
import com.neusoft.biz.console.sys.dict.service.DataDictService;
import com.neusoft.biz.console.sys.resource.service.ShiroFilterService;

import cn.hutool.core.date.DatePattern;
import lombok.extern.slf4j.Slf4j;

/**
 * 启动后加载类
 *
 * @author：yu8home
 * @date：2017年9月5日 下午4:05:54
 */
@Slf4j
@Component
public class StartupAfter implements ApplicationRunner {
    @Value("${upload-img}")
    private String upload_img;
    @Value("${upload_attachment}")
    private String upload_attachment;

    @Autowired
    private DataDictService dictService;
    @Autowired
    private ShiroFilterService shiroFilterService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        GlobalConst.UPLOAD_IMG = upload_img;
        GlobalConst.UPLOAD_ATTACHMENT = upload_attachment;
        GlobalConst.SYS_STARTUP_TIME = CalendarUtils.parseString(Calendar.getInstance(), DatePattern.PURE_DATETIME_PATTERN);

        log.debug("shiro权限-加载开始...");
        shiroFilterService.resetFilterChainDefinitionMap();
        log.debug("shiro权限-加载开始...");

        log.debug("字典表-加载开始...");
        dictService.loadDict();
        log.debug("字典表-加载结束！");
    }

}