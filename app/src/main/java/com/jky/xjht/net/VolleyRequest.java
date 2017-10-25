package com.jky.xjht.net;


import android.content.Context;
import android.util.Log;

import com.jky.xjht.volley.AuthFailureError;
import com.jky.xjht.volley.Request.Method;
import com.jky.xjht.volley.RequestQueue;
import com.jky.xjht.volley.toolbox.StringRequest;
import com.jky.xjht.volley.toolbox.Volley;

import java.util.Map;

/**
 * 
 * @ClassName VolleyRequest.java
 * @Todo TODO Volley请求
 * @Date 2016-1-11 上午9:40:53
 * @author xujianye
 */
public class VolleyRequest {

	private VolleyRequest(Context context) {
		mQueue = Volley.newRequestQueue(context);
	}

	private RequestQueue mQueue;

	public static VolleyRequest getInstance(Context context) {
		if (instance == null) {
			instance = new VolleyRequest(context);
		}
		return instance;
	}

	private static VolleyRequest instance;

	public void volleyPost(String url, final Map<String, String> params,
			RequestListener listener, String tag) {
		Log.i("https: ", "-----url is: " +  url);
		StringBuilder sb = new StringBuilder();
		for(String key : params.keySet()){
			sb.append(key + " = " + params.get(key)).append("---");
		}
		Log.i("https:", "params: " + sb.toString());
		StringRequest sr = new StringRequest(Method.POST, url,
				listener.getListener(), listener.getErrorListener()) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {				
				return params;
			}
		};
		sr.setTag(tag);
		sr.addMarker(tag);
		mQueue.add(sr);
	}
	
	public RequestQueue getHttpRequestQueue(){
		return mQueue;
	}
}
