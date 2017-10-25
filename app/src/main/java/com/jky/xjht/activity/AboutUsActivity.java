package com.jky.xjht.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jky.xjht.R;
import com.jky.xjht.net.HttpRequestUrl;
import com.jky.xjht.utils.PhoneUtil;
/**
 * <p>关于我们Activity</p>
 *@author zhangliwei
 */
public class AboutUsActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about_us);
		setTitle(getResources().getString(R.string.about_us));
		initView();
	}

	private void initView() {
		findViewById(R.id.sevice_ll).setOnClickListener(this);
		((TextView) findViewById(R.id.tv_version)).setText("当前版本 V" + PhoneUtil.getVerName(context));
	}
	
	@Override
	public void onClick(View v) {
		if (R.id.sevice_ll == v.getId()) {
			Intent intent = new Intent(AboutUsActivity.this,WebActivity.class);
			intent.putExtra("tag", 4);
			intent.putExtra("url",HttpRequestUrl.SERVICEURL);
			startActivity(intent);
		} else if (R.id.iv_common_back == v.getId()) {
			finish();
		}
	}
}