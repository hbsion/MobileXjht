package com.jky.xjht.net;

import android.util.Log;

import com.jky.xjht.volley.Response.ErrorListener;
import com.jky.xjht.volley.Response.Listener;
import com.jky.xjht.volley.VolleyError;


/**
 * 
 *@ClassName RequestListener.java
 *@Todo TODO 请求监听
 *@Date 2016-1-11 上午9:39:54
 *@author xujianye
 */
public abstract class RequestListener {

	private Listener<String> successListener; //请求成功监听
	private ErrorListener errorListener; //请求失败监听

	public RequestListener() {
		init();
	}

	private void init() {
		successListener = new Listener<String>() {

			@Override
			public void onResponse(String arg0, String tag) {
				onSuccess(arg0, tag);
				Log.i("https: ", "-----flag is: " +  tag  + "   result is: " + arg0);

			}
		};

		errorListener = new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError arg0) {
				onFailed(arg0);
				
			}
		};
	}
	
	public Listener<String> getListener(){
		return successListener;
	}
	
	public ErrorListener getErrorListener(){
		return errorListener;
	}

	public abstract void onSuccess(String result, String tag);

	public abstract void onFailed(VolleyError error);
}
