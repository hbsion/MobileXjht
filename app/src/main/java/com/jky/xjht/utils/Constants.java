package com.jky.xjht.utils;

/**
 * <p>静态常量</p>
 * @author zhangliwei
 * @version V1.0.0
 */
public final class Constants {

    public static String NET_URL = "139.129.163.131";
    public static String SDK_USER_NAME = "admin";
    public static String SDK_USER_PASSWORD = "P@ssw0rd";

    public static String KEY_USER_NAME = "key_user_name";
    public static String KEY_USER_ROLE = "key_user_role";
    public static String KEY_AREA_ID = "key_area_id";
    public static String KEY_USER_ID = "key_user_id";
    
	public static final String WX_APP_ID_ZLYS = "wx6c00ce19d3a072e4"; // 微信开放平台
	public static final String QQ_APP_ID_ZLYS = "1105618258"; // qq开放平台
	public static final String APP_NAME_ZLYS = "质量验收";
	

	
	public static final String SHARE_URL = "http://a.app.qq.com/o/simple.jsp?pkgname=";
    /**
     * Intent相关常量
     */
    public interface IntentKey {
        /**
         * 获取根节点数据
         */
        String GET_ROOT_NODE = "getRootNode";
        /**
         * 获取子节点列表
         */
        String GET_SUB_NODE = "getChildNode";
        /**
         * 父节点类型
         */
        String PARENT_NODE_TYPE = "parentNodeType";
        /**
         * 父节点ID
         */
        String PARENT_ID = "parentId";
        /**
         * 监控点资源
         */
        String CAMERA = "Camera";
    }
}