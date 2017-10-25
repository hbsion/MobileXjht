package com.jky.xjht.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MyWebViewClient extends WebViewClient {
	ProgressDialog progressDialog; 
	Context context;
	WebView webview;
	public MyWebViewClient(Context context,WebView webview,ProgressDialog progressDialog){
		this.context = context;
		this.webview = webview;
		this.progressDialog = progressDialog;
	}
	@Override
	public void onPageStarted(WebView view, String url, Bitmap favicon) {// 网页页面开始加载的时候


	if (progressDialog == null) {
	progressDialog = new ProgressDialog(context);
	progressDialog.setMessage("数据加载中，请稍后。。。");
	progressDialog.show();
	webview.setEnabled(false);// 当加载网页的时候将网页进行隐藏
	}
	super.onPageStarted(view, url, favicon);
	}

	@Override
	public void onPageFinished(WebView view, String url) {// 网页加载结束的时候
	if (progressDialog != null && progressDialog.isShowing()) {
	progressDialog.dismiss();
	progressDialog = null;
	webview.setEnabled(true);
	}
	}


	@Override
	public boolean shouldOverrideUrlLoading(WebView view, String url) { // 网页加载时的连接的网址
	view.loadUrl(url);
	return true;
	}
	}
