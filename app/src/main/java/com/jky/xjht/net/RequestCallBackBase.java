package com.jky.xjht.net;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jky.xjht.bean.GsonObjModel;
import com.jky.xjht.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.ParameterizedType;

/**
 * 
 *@ClassName RequestCallBackModel.java
 *@Todo TODO
 *@Date 2016-1-27 下午5:04:54
 *@author zjt
 *
 */
public abstract class RequestCallBackBase<T extends GsonObjModel<?>> extends RequestListener {
	/***
	 * json 返回成功
	 */
	public static final int HTTP_CODE_SUCCESS = 0;
	/***
	 * 解析json 失败
	 */
	public static final int HTTP_CODE_OTHERERROR = 0xffffffff;
	public static final int HTTP_CODE_FORMAT_ERR = HTTP_CODE_OTHERERROR - 1;
	
	public int code;
	public String msg;
	public T jsonResult;
	
	public String data;
	
	@Override
	public void onSuccess(String result, String tag) {
		Log.i("https: ", "-----flag is: " +  tag  + "   result is: " + result);
		Gson gson = new Gson();
		try {
			ParameterizedType parameterizedType = (ParameterizedType)this.getClass().getGenericSuperclass(); 
			
			jsonResult = gson.fromJson(result, 
					//new TypeToken<GsonObjModel<ResponseHome>>() {}.getType()
					//entityClass
					parameterizedType.getActualTypeArguments()[0]
					);
			code = jsonResult.Code;
			msg = jsonResult.Msg;
			
		} catch (Exception e) {
			GsonObjModel<String> response = null;
			try {
				response = gson.fromJson(result,
						new TypeToken<GsonObjModel<String>>() {}.getType());
				code = response.Code;
				msg = response.Msg;
				if(response.Data == null || 
						response.Data.length() == 0){
					if(code == 0){
						code = HTTP_CODE_FORMAT_ERR;
						msg = "无有效数据时返回的格式不正确！";
					}
				}				
			} catch (Exception e2) {
				//response = new GsonObjModel<String>();
//				code = HTTP_CODE_OTHERERROR;
//				msg = "数据转换错误：" + e2.getMessage();
				
				//{"Code":0,"Data":{"ID":"d14c96cf-272c-4489-9ff6-7e3eab595218","ProjectID":"db7cc250-fbcf-488f-8dc7-2370b30e42ae","RealName":"bjqsf1xmbc","UserType":"2","FunctionModule":"1,4,6,10"},"Msg":""}
				
				try {
					JSONObject jsonObj = new JSONObject(result);
					code = jsonObj.getInt("Code");
					msg = jsonObj.getString("Msg");
					data = jsonObj.getString("Data");
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					code = HTTP_CODE_OTHERERROR;
					msg = "数据转换错误：" + e2.getMessage();
				}
				
			}
		}
	}

	@Override
	public void onFailed(VolleyError error) {
		Log.e("错误信息", error.toString());
	}

}
