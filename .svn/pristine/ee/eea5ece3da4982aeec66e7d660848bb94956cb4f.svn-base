package com.jky.xjht.dialog;

import com.jky.xjht.R;

import android.app.AlertDialog;
import android.content.Context;
import android.text.util.Linkify;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.TextView;



/**
 * 简单型 只有标题、内容、取消、确定的提示框
 *@ClassName SimpleDialog.java
 *@Todo TODO
 *@Date 2017-4-21 下午1:43:01
 *@author WTT
 */
public class SimpleDialog implements OnClickListener {
	private AlertDialog mDialog;
	private Context mContext;
	public TextView mTitleTv , mContentTv;
	private Button mCancleBtn , mSureBtn;
	private String mTextTitle,mTextContent, mTextCancle, mTextSure;
	private boolean mCanceledOnTouchOutside = true;

	/**
	 * @param context 上下文
	 * @param textTitle 标题
	 * @param textContent 内容
	 * @param textCancle 取消
	 * @param textSure 确定
	 * @param canceledOnTouchOutside 能否点击外部消失弹框
	 */
	public SimpleDialog(Context context , String textTitle,String textContent, String textCancle, String textSure , boolean canceledOnTouchOutside) {
		this.mContext = context;
		this.mTextTitle = textTitle;
		this.mTextContent = textContent;
		this.mTextCancle = textCancle;
		this.mTextSure = textSure;
		this.mCanceledOnTouchOutside = canceledOnTouchOutside;
		createDialog();
	}

	private void createDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
		View view = LayoutInflater.from(mContext).inflate(R.layout.layout_simple_dialog, null);
		mTitleTv = (TextView) view.findViewById(R.id.dialog_tv_title);
		mContentTv = (TextView) view.findViewById(R.id.dialog_tv_content);
		mCancleBtn = (Button) view.findViewById(R.id.dialog_btn_cancle);
		mSureBtn = (Button) view.findViewById(R.id.dialog_btn_sure);
		mContentTv.setAutoLinkMask(Linkify.ALL);
		mCancleBtn.setOnClickListener(this);
		mSureBtn.setOnClickListener(this);
		builder.setView(view);
		mDialog = builder.create();
		mDialog.setCanceledOnTouchOutside(mCanceledOnTouchOutside);
		showMySimpleDialog();
	}
	
	public void showMySimpleDialog(){
		if(!mDialog.isShowing()){
			mTitleTv.setText(mTextTitle);
			mContentTv.setText(mTextContent);
			mCancleBtn.setText(mTextCancle);
			mSureBtn.setText(mTextSure);
			mDialog.show();
			LayoutParams layoutParams = mDialog.getWindow().getAttributes();
			DisplayMetrics dm = new DisplayMetrics();   
			dm = mContext.getResources().getDisplayMetrics(); 
			int screenWidth = dm.widthPixels;  
			layoutParams.width = (int) (screenWidth * 0.8);
			layoutParams.height = LayoutParams.WRAP_CONTENT;
			mDialog.getWindow().setAttributes(layoutParams);
		}
	}
	
	public void disMissMySimpleDialog(){
		if(mDialog !=null && mDialog.isShowing()){
			mDialog.dismiss();
		}
	}
	
	public void setCancleBtnVisibility(boolean visibile){
		if (visibile) {
			mCancleBtn.setVisibility(View.VISIBLE);
		}else {
			mCancleBtn.setVisibility(View.GONE);
		}
	}
	
	public void setSureBtnVisibility(boolean visibile){
		if (visibile) {
			mSureBtn.setVisibility(View.VISIBLE);
		}else {
			mSureBtn.setVisibility(View.GONE);
		}
	}
	
	//确定取消监听
	private OnMySimpleDialogListener mSimpleDialogistener;
	public void SetOnMySimpleDialogListener(OnMySimpleDialogListener listener){
		this.mSimpleDialogistener = listener;
	}
	
	public interface OnMySimpleDialogListener{
		public void onMyCancle();
		public void onMySure();
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.dialog_btn_cancle) {
			disMissMySimpleDialog();
			if(mSimpleDialogistener != null ){
				mSimpleDialogistener.onMyCancle();
			}
		}else if (v.getId() == R.id.dialog_btn_sure) {
			disMissMySimpleDialog();
			if(mSimpleDialogistener != null ){
				mSimpleDialogistener.onMySure();
			}
		}
	}
}
