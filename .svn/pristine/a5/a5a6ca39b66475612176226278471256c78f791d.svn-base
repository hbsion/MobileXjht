package com.jky.xjht.net;

import org.json.JSONException;
import org.json.JSONObject;

import com.jky.xjht.volley.VolleyError;



import android.util.Log;

/**
 * 
 *@ClassName RequestCallBackModel.java
 *@Todo TODO
 *@Date 2016-1-27 下午5:04:54
 *@author xujianye
 */
public abstract class RequestCallBackModel extends RequestListener {

	public int code = -1;
	public String msg;
	public String data;
	
	@Override
	public void onSuccess(String result, String tag) {
		code = -1;
//		if (ZlysApplication.mVerEnum == VerEnum.MobileZLYS){
//			try {
//				JSONObject jsonObject = new JSONObject(result);
//				if (!jsonObject.isNull("code")) {
//					code = jsonObject.getInt("code");
//				} 
//				if(!jsonObject.isNull("msg")){
//					msg = jsonObject.getString("msg");
//				}
//				if(!jsonObject.isNull("data")){
//					data = jsonObject.getString("data");
//				}
//			} catch (JSONException e) {
//				e.printStackTrace();
//			}
//		}else{
			try {
				JSONObject jsonObject = new JSONObject(JSONTokener(result));
//				JSONObject jsonObject = new JSONObject(result);
				if (!jsonObject.isNull("Code")) {
					code = jsonObject.getInt("Code");
				} 
				if(!jsonObject.isNull("Msg")){
					msg = jsonObject.getString("Msg");
				}
				if(!jsonObject.isNull("Data")){
					data = jsonObject.getString("Data");
				}
				
				if (!jsonObject.isNull("errorCode")) {
					code = jsonObject.getInt("errorCode");
				}
				
			} catch (JSONException e) {
				e.printStackTrace();
			}
//		}
		
	}
	 public String JSONTokener(String in) {
	        // consume an optional byte order mark (BOM) if it exists
	         if (in != null && in.startsWith("\ufeff")) {
	            in = in.substring(1);
	       }
	      return in;
	 }
	@Override
	public void onFailed(VolleyError error) {
		Log.e("错误信息", error.toString());
	}

}
