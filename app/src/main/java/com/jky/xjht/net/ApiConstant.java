package com.jky.xjht.net;

public class ApiConstant {
	
//    private static final String RELEASE_HOST = "http://192.168.2.7:10086/";
    private static final String RELEASE_HOST = "http://xmxt.jiankeyan.com:8089/";
    
    private static String HOST = RELEASE_HOST;
    //登录
    public static final String USER_LOGIN = HOST + "api/Common/LoginIn?";
    //获取工程标注点信息
    public static final String GET_PROJECT_POINT_LIST = HOST + "api/Common/TGetAllProjectPoint";
    //工程列表
    public static final String GET_PROJECT_LIST = HOST + "api/Common/TGetProjectList?";
    //工程详情
    public static final String GET_PROJECT_DETAIL = HOST + "api/Common/TGetProjectInfo?";
    
    public static final String UPDATE_APP_URL = "http://xmxt.jiankeyan.com:11083/api/SYS/Update";
    
}
