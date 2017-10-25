package com.jky.xjht.utils;

import android.content.Context;

import com.hikvision.sdk.VMSNetSDK;
import com.hikvision.sdk.consts.HttpConstants;
import com.hikvision.sdk.net.bean.LoginData;
import com.hikvision.sdk.net.business.OnVMSNetSDKBusiness;
import com.hikvision.sdk.utils.SDKUtil;
import com.jky.xjht.R;
import com.jky.xjht.app.XjhtApplication;
import com.jky.xjht.bean.TempDatas;

public class LoginUtil {

	public static int mLoginTag=1;

	public static int userLogin(Context context, String userName, String passWord) {
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
			UIUtils.showToast(context, R.string.empty_tip);
			return -1;
		}
		VMSNetSDK.getInstance().Login(loginAddress, userName, passWord,
				macAddress, new OnVMSNetSDKBusiness() {
					@Override
					public void onFailure() {
 						mLoginTag = 0;
					}

					@Override
					public void onSuccess(Object obj) {
						if (obj instanceof LoginData) {
							mLoginTag = 1;
							// mHandler.sendEmptyMessage(LOGIN_SUCCESS);
							// 存储登录数据
							TempDatas.getIns().setLoginData((LoginData) obj);
							TempDatas.getIns().setLoginAddr(Constants.NET_URL);
							String appVersion = ((LoginData) obj).getVersion();
							SDKUtil.analystVersionInfo(appVersion);
						}
					}
				});
		return mLoginTag;
	}
}
