package com.jky.xjht.activity;

/**
 * <p>基类Activity</p>
 *@author zhangliwei
 */

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jky.xjht.R;
import com.jky.xjht.utils.DensityUtils;

public class BaseActivity extends Activity implements OnClickListener {


	protected ImageView mIvBack; // 返回
	protected TextView mTitleCenterTv; // 标题
	protected View mTitleBarView;
	protected Context context;
	public ImageView mRightBtn;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setTranslucentStatus();
		context = this;
	}

	/**
	 * 设置状态栏背景状态
	 */
	public void setTranslucentStatus() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			// 透明状态栏
			getWindow().addFlags(
					WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

			// 透明导航栏
			/*
			 * getWindow().addFlags(
			 * WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
			 */
		} else {
			getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
					WindowManager.LayoutParams.FLAG_FULLSCREEN); // 全屏
		}
	}
	private void setupView() {
		mIvBack = (ImageView) findViewById(R.id.iv_common_back);
		mTitleCenterTv = (TextView) findViewById(R.id.tv_common_title_center);
		mRightBtn = (ImageView) findViewById(R.id.right_btn);
	}
	
	protected void onResume() {
		super.onResume();
	}
	
	protected void onPause() {
		super.onPause();
	}
	/**
	 * 隐藏标题栏
	 */
	public void hideHeadBar() {
		if (mTitleBarView != null)
			mTitleBarView.setVisibility(View.GONE);
	}

	@Override
	public void setContentView(int layoutResID) {
		super.setContentView(R.layout.common_base);
		LinearLayout rootView = (LinearLayout) findViewById(R.id.linearlayout_base);
		getLayoutInflater().inflate(layoutResID, rootView);
		mTitleBarView = rootView.findViewById(R.id.layout_base_widget_title_bar);
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT, DensityUtils.dip2px(this, 47));
			mTitleBarView.setLayoutParams(params);
		}
		setupView();
		addListener();
	}

	private void addListener() {
		mIvBack.setOnClickListener(this);
		mTitleCenterTv.setOnClickListener(this);
		mRightBtn.setOnClickListener(this);
	}

	@Override
	public void setTitle(CharSequence title) {
		super.setTitle(title);
		mTitleCenterTv.setText(title);
	}


	@Override
	public void onClick(View v) {
		if (R.id.iv_common_back == v.getId()) {
			finish();
		}else if (R.id.tv_common_title_center == v.getId()) {
			finish();
		}
	}
}
