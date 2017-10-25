package com.jky.xjht.net;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jky.xjht.bean.ProjectDetailInfo;
import com.jky.xjht.bean.ProjectInfo;
import com.jky.xjht.bean.ProjectPointInfo;

public class JsonParse {
	public static <T> T toObject(String json, Class<T> claxx) {
		Gson gson = new Gson();
		return gson.fromJson(json, claxx);
	}
	
	public static List<ProjectPointInfo> toList(String json) {
		Gson gson = new Gson();
		Type type = new TypeToken<List<ProjectPointInfo>>() {}.getType();
		return gson.fromJson(json, type);
	}
	public static List<ProjectDetailInfo> toProjectDetailList(String json) {
		Gson gson = new Gson();
		Type type = new TypeToken<List<ProjectDetailInfo>>() {}.getType();
		return gson.fromJson(json, type);
	}
	public static String toJson(Object object){
		Gson gson = new Gson(); 
		return 	gson.toJson(object);
	}
	
}
