package com.jky.xjht.activity;


import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.jky.xjht.R;
import com.jky.xjht.utils.MyWebViewClient;


/**
 * <p>webview Activity</p>
 *@author zhangliwei
 */
public class WebActivity extends BaseActivity {
	
	private WebView wv_content;
	private String url;
	private int mTag;
	ProgressDialog progressDialog; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_web);
		context = this;
		init();
	}
	
	private void init(){
		url = getIntent().getStringExtra("url");
		mTag = getIntent().getIntExtra("tag", 0);
		findView();
		setListener();
	}
	
	@SuppressLint({ "NewApi", "SetJavaScriptEnabled" })
	private void findView(){
		wv_content = (WebView) findViewById(R.id.wv_content);
		if (mTag == 4){
			setTitle("服务协议");
		} else {
			setTitle("推荐");
		}
		WebSettings settings = wv_content.getSettings();
		//设置WebView属性，能够执行Javascript脚本    
		settings.setJavaScriptEnabled(true);    
        //设置可以访问文件  
		settings.setAllowFileAccess(true);  
		settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
		settings.setSupportZoom(true);
		settings.setBuiltInZoomControls(true);
		settings.setDisplayZoomControls(false);
		wv_content.loadUrl(url);
		wv_content.setWebViewClient(new MyWebViewClient(WebActivity.this, wv_content, progressDialog));
	}
	
	private void setListener(){
		findViewById(R.id.iv_common_back).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (wv_content.canGoBack()) {    
		        	wv_content.goBack(); //goBack()表示返回WebView的上一页面    
		        	return;
		        }    
		        finish();
			}
		});
	}
	 @Override   
    public boolean onKeyDown(int keyCode, KeyEvent event) {    
        if ((keyCode == KeyEvent.KEYCODE_BACK) && wv_content.canGoBack()) {    
        	wv_content.goBack(); //goBack()表示返回WebView的上一页面    
            return true;    
        }    
        finish();
        return false;    
    } 
}
