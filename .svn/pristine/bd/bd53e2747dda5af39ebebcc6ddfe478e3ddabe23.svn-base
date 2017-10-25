package com.jky.xjht.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


/**
 * <p>引导页Activity</p>
 *@author zhangliwei
 */
public class SplashActivity extends Activity {

	private static final int WHAT = 0x111;
	private static final long DELAT_TIME = 2000l; //延迟2s后跳转

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(com.jky.xjht.R.layout.activity_splash);
		mHandler.sendEmptyMessageDelayed(WHAT, DELAT_TIME);
	}

	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			toMain();
		}
	};
	
	protected void onDestroy() {
		super.onDestroy();
		mHandler.removeMessages(WHAT);
	}
	
	private void toMain(){
		mHandler.removeMessages(WHAT);
		Intent intent = new Intent(SplashActivity.this, MainActivity.class);
		startActivity(intent);
		finish();
	}
}
