package com.jky.xjht.dialog;

import com.jky.xjht.R;

import android.content.Context;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;


public class SelectMapDialog extends BaseDialog{

	private android.view.View.OnClickListener mOnClickListener;
	
	public SelectMapDialog(Context context,android.view.View.OnClickListener mOnClickListener) {
		super(context, R.layout.dialog_select_map);
		this.mOnClickListener = mOnClickListener;
		initView();
	}
	
	private void initView() {
		
		Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        window.setBackgroundDrawableResource(android.R.color.transparent);
        window.setWindowAnimations(R.style.modifyAvatarDialogWindowAnim);
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.gravity = Gravity.BOTTOM;
		findViewById(R.id.select_baidu_map).setOnClickListener(mOnClickListener);
		findViewById(R.id.select_gaode_map).setOnClickListener(mOnClickListener);
		findViewById(R.id.select_guge_map).setOnClickListener(mOnClickListener);
		findViewById(R.id.select_map_cancle).setOnClickListener(mOnClickListener);
		
	}
}
