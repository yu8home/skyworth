package com.neusoft.biz.console.sys.logsystem.service.impl;

import org.springframework.stereotype.Service;

import com.neusoft.base.service.AbstractBaseService;
import com.neusoft.biz.console.sys.logsystem.dao.LogSystemMapper;
import com.neusoft.biz.console.sys.logsystem.model.LogSystem;
import com.neusoft.biz.console.sys.logsystem.service.LogSystemService;

@Service
public class LogSystemServiceImpl extends AbstractBaseService<LogSystemMapper, LogSystem> implements LogSystemService {
}