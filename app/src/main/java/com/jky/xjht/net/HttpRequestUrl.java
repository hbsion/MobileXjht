package com.jky.xjht.net;

public class HttpRequestUrl {
	
	public static final String  URL = "http://xmxt.jiankeyan.com:11082";
	
	/**
	 *关于我们 服务协议url  对应项目的界面  在=号后面拼对应的参数
	 *物资验收wzys 图纸管理tzgl 监理旁站 jlpz 云施工分户验收fhys  见证取样jzqy 云施工现场试验xcsy 云施工施工记录sgjl
	 *云施工质量验收zlys 云施工安全检查aqjc 项目协同xmxt 云施工质量检查 zljc 安全验收 aqys
	 * */
	public static final String  SERVICEURL = URL + "/appview/fwxyshow.aspx?partype=";
	//分享URL
	public static final String  APP_SHARE_URL_ZLYS = URL + "/appview/share.html";
	//分享的项目图片链接地址
	public static final String APP_ICON_URL_ZLYS = URL+"/Images/logo/zlys.png";
}
