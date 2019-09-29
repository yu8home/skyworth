package com.neusoft.base.comm;

/**
 * 全局-常量
 *
 * @author：yu8home
 * @date：2017年8月7日 下午2:41:31
 */
public class GlobalConst {
    public static final int NEGATIVE_INT = -1;
    public static final int ZERO = 0;
    public static final int ONE = 1;
    public static final int TWO = 2;
    public static final int THRER = 3;
    public static final int FOUR = 4;
    public static final int FIVE = 5;

    public static final String S_ZERO = "0";
    public static final String S_ONE = "1";
    public static final String S_TWO = "2";
    public static final String S_THRER = "3";
    public static final String S_FOUR = "4";
    public static final String S_FIVE = "5";

    public static final int YES = 1;
    public static final int NO = 0;
    public static final boolean TRUE = true;
    public static final boolean FALSE = false;

    public static final int SUCCESS = 1;
    public static final String SUCCESS_EN = "success";
    public static final String SUCCESS_CN = "操作成功！";
    public static final int FAIL = 0;
    public static final String FAIL_EN = "fail";
    public static final String FAIL_CN = "操作失败！";
    public static final String UNKNOWN_EXCEPTION = "未知异常，请联系管理员！";

    public static final Long SUPER_ADMIN_USERID = 1L;
    public static final String SUPER_ADMIN_USERCODE = "admin";
    public static final String SUPER_ADMIN_USERNAME = "超级管理员";

    /**
     * jquery.md5对123456处理过的密文
     */
    public static final String JQUERY_MD5_DEF = "e10adc3949ba59abbe56e057f20f883e";

    // 数据源
    public static final String DB_MASTER = "master";
    public static final String DB_SLAVE = "slave";

    // session
    /**
     * 用户-基本信息
     */
    public static final String SESSION_USER = "session_user";
    /**
     * 用户-角色
     */
    public static final String SESSION_USER_ROLE = "session_user_role";

    // 角色类型
    /**
     * 超级管理员
     */
    public static final String ROLETYPE_ADMIN = "A";
    /**
     * 管理员
     */
    public static final String ROLETYPE_MANAGER = "M";
    /**
     * 客户
     */
    public static final String ROLETYPE_CUSTOMER = "C";
    /**
     * 客服专员
     */
    public static final String ROLETYPE_CS = "CS";
    /**
     * 供应商
     */
    public static final String ROLETYPE_VENDOR = "S";

    // 编码规则
    /**
     * 管理员
     */
    public static final String CODING_MANAGER = "managerCode";
    /**
     * 客户
     */
    public static final String CODING_CUST = "custCode";
    /**
     * 客服专员
     */
    public static final String CODING_CUSTSERV = "custServCode";
    /**
     * 供应商
     */
    public static final String CODING_VENDOR = "vendorCode";
    /**
     * 客诉工单
     */
    public static final String CODING_CPT = "cptCode";

    /**
     * 系统部署时间：前端加载js预防缓存
     */
    public static String SYS_STARTUP_TIME;
    /**
     * 上传图片路径
     */
    public static String UPLOAD_IMG;
    /**
     * 附件路径
     */
    public static String UPLOAD_ATTACHMENT;

    // 工单状态
    /**
     * 新建
     */
    public static final int STATUS_NEW = 1;
    /**
     * 提交
     */
    public static final int STATUS_SUBMIT = 2;
    /**
     * 信息驳回
     */
    public static final int STATUS_INFO_REJECTED = 3;
    /**
     * 待客户确认初审意见
     */
    public static final int STATUS_INITIAL_CONFIRMING = 4;
    /**
     * 客户驳回初审意见
     */
    public static final int STATUS_INITIAL_REJECTED = 5;
    /**
     * 待客服提供解决方案
     */
    public static final int STATUS_SOLUTION_BEING_PROVIDED = 6;
    /**
     * 待客户确认解决方案
     */
    public static final int STATUS_SOLUTION_CONFIRMING = 7;
    /**
     * 客户驳回解决方案
     */
    public static final int STATUS_SOLUTION_REJECTED = 8;
    /**
     * 待闭环-1
     */
    public static final int STATUS_WILL_TO_BE_CLOSED = 9;
    /**
     * 待闭环-2
     */
    public static final int STATUS_TO_BE_CLOSED = 10;
    /**
     * 结束
     */
    public static final int STATUS_CLOSED = 11;

    /**
     * excel导入最大行数
     */
    public static final int EXCEL_IMPORT_MAX_ROW = 200;
    /**
     * 客诉单导入最大行数
     */
    public static final int CUSTCPT_IMPORT_MAX_ROW = 20;
    // 文件种类
    public static final String CONTENTYPE_XLS = GlobalConst.CONTENTYPE_XLSX;
    public static final String CONTENTYPE_XLSX = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
}