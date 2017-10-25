package com.jky.xjht.utils;

import java.io.InputStream;

import org.xmlpull.v1.XmlPullParser;

import com.jky.xjht.bean.UpdataInfo;

import android.util.Xml;

public class UpdataInfoParser {

	/*
	 * 用pull解析器解析服务器返回的xml文件 (xml封装了版本号)
	 */
	public static UpdataInfo getUpdataInfo(InputStream is) throws Exception{
		XmlPullParser  parser = Xml.newPullParser();  
		parser.setInput(is, "utf-8");//设置解析的数据源 
		int type = parser.getEventType();
		UpdataInfo info = new UpdataInfo();
		while(type != XmlPullParser.END_DOCUMENT ){
			switch (type) {
				case XmlPullParser.START_TAG:
					if("version".equals(parser.getName())){
						info.setVirsion(parser.nextText());	//获取版本号
					}else if ("url".equals(parser.getName())){
						info.setPath(parser.nextText());	//获取要升级的APK文件下载路径
					}else if ("description".equals(parser.getName())){
						info.setDescrip(parser.nextText());	//升级描述
					}else if("dburl".equals(parser.getName())){
						info.setDburl(parser.nextText()); //数据库下载路径
					}else if("size".equals(parser.getName())){
						info.setSize(Long.parseLong(parser.nextText()));
					}
				break;
			}
			type = parser.next();
		}
		return info;
	}
}
