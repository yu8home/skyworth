package com.neusoft.base.comm;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import com.neusoft.biz.console.sys.dict.model.DataDict;

/**
 * 数据字典
 *
 * @author：yu8home
 * @date：2009年12月08日 上午10:30:20
 */
public class Dict {
    /**
     * 是、否
     */
    public static final int YES_OR_NOT = 9000;
    /**
     * 系统来源、接口来源
     */
    public static final int SYS_SRC = 9001;
    /**
     * 授权类型
     */
    public static final int ROLE_TYPE = 9002;
    /**
     * 编码规则：日期类型
     */
    public static final int CODING_DATETYPE = 9003;
    /**
     * 编码规则：值重置类型
     */
    public static final int CODING_RESETVALUETYPE = 9004;
    /**
     * 权限管理-权限类型
     */
    public static final int AUTH_TYPE = 9050;
    /**
     * 行政区划代码-级别
     */
    public static final int AREA_LEVLES = 9100;

    /**
     * 状态
     */
    public static final int CPT_STATUS = 1000;
    /**
     * 不良阶段
     */
    public static final int BAD_PHASE = 1001;
    /**
     * RMA故障现象类型
     */
    public static final int RMA_FAILURETYPE = 1102;
    /**
     * 非RMA故障现象类型
     */
    public static final int NONRMA_FAILURETYPE = 1103;
    /**
     * 部件名称
     */
    public static final int PART_NAME = 1104;
    /**
     * 非屏体-初审意见类型
     */
    public static final int NON_PANEL_INITIAL_TYPE = 1105;
    /**
     * 屏体-初审意见类型
     */
    public static final int PANEL_INITIAL_TYPE = 1106;
    /**
     * 临时措施类型
     */
    public static final int TEMPORARY_MEASURETYPE = 1107;
    /**
     * 备损出货方式
     */
    public static final int DAMAGELOSS_SHIPPING = 1108;
    /**
     * 非屏体-确认解决方案
     */
    public static final int NON_PANEL_CONFIRMATION_SOLUTION = 1109;
    /**
     * 客诉来源
     */
    public static final int CUST_CPT_SRC = 1110;
    /**
     * 供应商工单状态
     */
    public static final int VENDOR_FORM_STATUS = 1113;
    /**
     * 工单优先级
     */
    public static final int PRIORITY = 1120;

    // //////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 存储数据字典对象
     */
    private static LinkedHashMap<Integer, List<DataDict>> m_linkedHashMap = null;// 所有的dictCodeMap
    private static LinkedHashMap<Integer, List<DataDict>> p_linkedHashMap = null;// 父节点的parentIdMap

    /**
     * 加载数据字典到“缓存”
     */
    public static void loadDict(List<DataDict> dictList) {
        m_linkedHashMap = new LinkedHashMap<Integer, List<DataDict>>();
        p_linkedHashMap = new LinkedHashMap<Integer, List<DataDict>>();

        List<DataDict> sub_list;
        List<DataDict> p_list;
        if (CollectionUtils.isNotEmpty(dictList)) {
            for (DataDict dt : dictList) {
                sub_list = (List<DataDict>) m_linkedHashMap.get(dt.getDictCode());
                if (null == sub_list) {
                    sub_list = new ArrayList<DataDict>();
                }
                sub_list.add(dt);
                m_linkedHashMap.put(dt.getDictCode(), sub_list);

                if (dt.getPid() != null) {
                    p_list = (List<DataDict>) p_linkedHashMap.get(dt.getPid());
                    if (null == p_list) {
                        p_list = new ArrayList<DataDict>();
                    }
                    p_list.add(dt);
                    p_linkedHashMap.put(dt.getPid(), p_list);
                }
            }
        }
    }

    /**
     * 根据 dictCode 获取字典类型的 list
     */
    public static List<DataDict> getSubDict(int dictCode) {
        return m_linkedHashMap.get(dictCode);
    }

    /**
     * 根据 parentId 获取字典类型的 list（字典表级联使用）
     */
    public static List<DataDict> getDictByParentId(int parentId) {
        return p_linkedHashMap.get(parentId);
    }

    /**
     * 根据“字典类型、字典值”获取字典名称
     */
    public static String getDictName(int dictCode, Integer value) {
        if (value == null) {
            return null;
        } else {
            return getDictName(dictCode, value.toString(), "");
        }
    }

    /**
     * 根据“字典类型、字典值”获取字典名称
     */
    public static String getDictName(int dictCode, String value) {
        return getDictName(dictCode, value, "");
    }

    /**
     * 根据“字典类型、字典值”获取字典名称，没有时返回默认值
     */
    public static String getDictName(int dictCode, String value, String defStr) {
        if (value != null) {
            List<DataDict> sub_list = m_linkedHashMap.get(dictCode);
            if (CollectionUtils.isNotEmpty(sub_list)) {
                for (DataDict dt : sub_list) {
                    if (dt.getValue().equals(value)) {
                        return dt.getText();
                    }
                }
            }
        }
        return defStr;
    }

    /**
     * 根据“字典类型、字典名称”获取字典value，没有时返回""
     */
    public static String getDictValue(int dictCode, String name) {
        List<DataDict> sub_list = m_linkedHashMap.get(dictCode);
        if (CollectionUtils.isNotEmpty(sub_list)) {
            for (DataDict dt : sub_list) {
                if (dt.getText().equals(name)) {
                    return dt.getValue();
                }
            }
        }
        return "";
    }

}