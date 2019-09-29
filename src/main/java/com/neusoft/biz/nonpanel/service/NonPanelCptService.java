package com.neusoft.biz.nonpanel.service;

import org.apache.poi.ss.usermodel.Workbook;

import com.neusoft.base.service.BaseService;
import com.neusoft.biz.nonpanel.model.NonPanelCpt;

public interface NonPanelCptService extends BaseService<NonPanelCpt> {

    /**
     * excel导入
     *
     * @author：yu8home
     * @date：2018年7月8日 下午3:39:20
     */
    String excelImport(Workbook wb);
}