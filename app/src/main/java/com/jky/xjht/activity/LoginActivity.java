package com.jky.xjht.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.hikvision.sdk.VMSNetSDK;
import com.hikvision.sdk.consts.HttpConstants;
import com.hikvision.sdk.net.bean.LoginData;
import com.hikvision.sdk.net.business.OnVMSNetSDKBusiness;
import com.hikvision.sdk.utils.SDKUtil;
import com.jky.xjht.R;
import com.jky.xjht.app.XjhtApplication;
import com.jky.xjht.bean.TempDatas;
import com.jky.xjht.bean.User;
import com.jky.xjht.net.JsonParse;
import com.jky.xjht.net.MobileEduService;
import com.jky.xjht.net.RequestCallBackModel;
import com.jky.xjht.net.RequestListener;
import com.jky.xjht.utils.Constants;
import com.jky.xjht.utils.Preferences;
import com.jky.xjht.utils.SingleToast;
import com.jky.xjht.utils.StringUtils;
import com.jky.xjht.utils.UIUtils;
import com.jky.xjht.volley.VolleyError;

import java.lang.ref.WeakReference;

/**
 * <p>登录Activity</p>
 * @author zhangliwei
 * @version V1.0.0
 */
public class LoginActivity extends Activity implements View.OnClickListener {
	/**
	 * 登录成功
	 */
	public static final int LOGIN_SUCCESS = 1;
	/**
	 * 登录失败
	 */
	public static final int LOGIN_FAILED = 2;
	/**
	 * 退出成功
	 */
	public static final int LOGOUT_SUCCESS = 3;
	/**
	 * 退出失败
	 */
	public static final int LOGOUT_FAILED = 4;
	/***
	 * 用户名输入控件
	 */
	private EditText mUserEdit;
	/***
	 * 密码输入控件
	 */
	private EditText mPsdEdit;
	// /***
	// * SharedPreferences
	// */
	// private SharedPreferences sharedPreferences;
	/**
	 * 发送消息的对象
	 */
	private Handler mHandler = null;

	/***
	 * UI处理Handler
	 */
	private static class ViewHandler extends Handler {
		private final WeakReference<LoginActivity> mActivity;

		ViewHandler(LoginActivity activity) {
			mActivity = new WeakReference<>(activity);
		}

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case LOGIN_SUCCESS:
				// 登录成功
				UIUtils.cancelProgressDialog();
				break;
			case LOGIN_FAILED:
				// 登录失败
				UIUtils.cancelProgressDialog();
				UIUtils.showToast(mActivity.get(), R.string.login_failed);
				break;
			case LOGOUT_SUCCESS:
				// 退出成功
				UIUtils.cancelProgressDialog();
				mActivity.get().finish();
				break;
			case LOGOUT_FAILED:
				// 退出失败
				UIUtils.cancelProgressDialog();
				UIUtils.showToast(mActivity.get(), R.string.logout_failed);
				break;
			default:
				break;
			}
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		mHandler = new ViewHandler(this);
		initView();
	}

	/***
	 * 初始化视图
	 */
	private void initView() {
		mUserEdit = (EditText) findViewById(R.id.main_user_name);
		mPsdEdit = (EditText) findViewById(R.id.main_psd);
		findViewById(R.id.main_login_btn).setOnClickListener(this);
		findViewById(R.id.main_logout_btn).setOnClickListener(this);
		// 持久化输入数据，避免多次输入
		String name = Preferences.getInstance(LoginActivity.this).getString(Constants.KEY_USER_NAME);
		mUserEdit.setText(name);
		mUserEdit.setSelection(name.length());
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.main_login_btn:
			String userName = mUserEdit.getText().toString().trim();
			String password = mPsdEdit.getText().toString().trim();
			if(TextUtils.isEmpty(userName)){
				SingleToast.show(LoginActivity.this, "用户名不能为空");
				return;
			}
			if(TextUtils.isEmpty(password)){
				SingleToast.show(LoginActivity.this, "密码不能为空");
				return;
			}
			MobileEduService.getInstance(this).userLogin(userName,password, "getData",listener);
			break;
		case R.id.main_logout_btn:
			logoutOpt();
			break;
			
		default:
			break;
		}
	}
	private RequestListener listener = new RequestCallBackModel() {

		@Override
		public void onSuccess(String result, String tag) {
			super.onSuccess(result, tag);
			
				if ("getData".equals(tag)) {
					if (code == 0) {
						User user =	JsonParse.toObject(data,User.class);
						Preferences.getInstance(LoginActivity.this).putString(
								Constants.KEY_USER_NAME,
								user.getUserName());
						Preferences.getInstance(LoginActivity.this).putString(
								Constants.KEY_USER_ROLE,
								user.getRoleID());
						Preferences.getInstance(LoginActivity.this).putString(
								Constants.KEY_USER_ID,
								user.getUserID());
						Preferences.getInstance(LoginActivity.this).putString(
								Constants.KEY_AREA_ID,
								user.getAreaID());
						
						loginOpt();
					} else {
						SingleToast.show(LoginActivity.this, msg);
					}
				} 
		}
		@Override
		public void onFailed(VolleyError error) {
			super.onFailed(error);
		}
	};
	
	/***
	 * 登录方法
	 */
	private void loginOpt() {
		UIUtils.showLoadingProgressDialog(LoginActivity.this,
				R.string.loading_process_tip, false);
		userLogin("0",Constants.SDK_USER_NAME, Constants.SDK_USER_PASSWORD);
	}
	
	public  void userLogin(final String flag,String userName, String passWord) {
        //https 证书验证
//      openHttps();
      // 登录请求
		String macAddress = XjhtApplication.getIns().getMacAddress();
		String loginAddress = HttpConstants.HTTPS + Constants.NET_URL;
		// 检查登录参数合法性
		if (StringUtils.isStrEmpty(Constants.NET_URL)
				|| StringUtils.isStrEmpty(userName)
				|| StringUtils.isStrEmpty(passWord)
				|| StringUtils.isStrEmpty(macAddress)) {
			UIUtils.showToast(LoginActivity.this, R.string.empty_tip);
		}
		VMSNetSDK.getInstance().Login(loginAddress, userName, passWord,
				macAddress, new OnVMSNetSDKBusiness() {
					@Override
					public void onFailure() {
						mHandler.sendEmptyMessage(LOGIN_FAILED);
					}

					@Override
					public void onSuccess(Object obj) {
						if (obj instanceof LoginData) {
							 mHandler.sendEmptyMessage(LOGIN_SUCCESS);
							// 存储登录数据
							TempDatas.getIns().setLoginData((LoginData) obj);
							TempDatas.getIns().setLoginAddr(Constants.NET_URL);
							
							
							String appVersion = ((LoginData) obj).getVersion();
							SDKUtil.analystVersionInfo(appVersion);
							// 解析版本号
							if(flag.equals("0")){
								// 跳转资源列表页面
								Intent intent = new Intent(LoginActivity.this, MainActivity.class);
								intent.putExtra(Constants.IntentKey.GET_ROOT_NODE, true);
								startActivity(intent);
							}
						}
					}
				});
	}
	
	
	/***
	 * 退出登录
	 */
	private void logoutOpt() {
		finish();

		// LoginData loginData = TempDatas.getIns().getLoginData();
		// String url = TempDatas.getIns().getLoginAddr();
		// if (loginData == null || TextUtils.isEmpty(url)) {
		// UIUtils.showToast(this, R.string.have_not_login);
		// return;
		// }
		// UIUtils.showLoadingProgressDialog(LoginActivity.this,
		// R.string.loading_process_tip, false);
		// VMSNetSDK.getInstance().Logout(new OnVMSNetSDKBusiness() {
		// @Override
		// public void onFailure() {
		// mHandler.sendEmptyMessage(LOGOUT_FAILED);
		// }
		//
		// @Override
		// public void onSuccess(Object obj) {
		// //清除登录数据
		// TempDatas.getIns().setLoginData(null);
		// TempDatas.getIns().setLoginAddr(null);
		// mHandler.sendEmptyMessage(LOGOUT_SUCCESS);
		// }
		// });
	}

	// /**
	// * 开启https网络交换模式，加载证书
	// */
	// public void openHttps() {
	// // The certificate's password
	// String STORE_PASS = "ivms8700";
	// // The certificate's alias.
	// String STORE_ALIAS = "ivms8700";
	// try {
	// KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
	// InputStream is = getResources().openRawResource(R.raw.ivms8700);
	// keyStore.load(is, STORE_PASS.toCharArray());
	// SSLSocketFactory sf = new SecureSocketFactory(keyStore, STORE_ALIAS);
	// sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
	// AsyncHttpExecute.getIns().setSSLSocketFactory(sf);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
}
