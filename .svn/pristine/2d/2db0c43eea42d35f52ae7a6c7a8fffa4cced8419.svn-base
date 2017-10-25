

package com.jky.xjht.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.view.WindowManager;

/**
 * PhoneUtil类用于获取手机信息，如型号、版本、Mac地址、屏幕尺寸；
 * 
 * @author 
 * @version 0.1
 */
public class PhoneUtil {
	
	public static String macAddress;

	/**
	 * 获取手机的基本信息如型号、API 级别；
	 * 
	 * @return 手机信息
	 */
	@SuppressWarnings("deprecation")
	public static String getBasicInfo() {
		return Build.MODEL + "," + Build.VERSION.SDK + ","
				+ Build.VERSION.RELEASE;
	}

	/**
	 * 获取应用版本号；
	 * 
	 * @param context
	 * @return 应用版本号，如1
	 */
	public static int getVerCode(Context context) {
		int verCode = -1;
		if (context != null) {
			try {
				PackageInfo info = context.getPackageManager().getPackageInfo(
						context.getPackageName(), 0);
				verCode = info.versionCode;
			} catch (NameNotFoundException e) {
				e.printStackTrace();
			}
		}
		return verCode;
	}

	/**
	 * 获取版本名称；
	 * 
	 * @param context
	 * @return 应用版本名称，如1.0.0
	 */
	public static String getVerName(Context context) {
		String verName = "1.0.0";
		if (context != null) {
			try {
				PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
				verName = info.versionName;
			} catch (NameNotFoundException e) {
				e.printStackTrace();
				return verName;
			}
		}
		return verName;

	}

	/**
	 * 获取MAC地址，注意：手机重启，mac地址为null；
	 * 
	 * @param context
	 * @return mac地址；
	 */
	public static String getMac(Context context) {
		if (context != null) {
			WifiManager wifi = (WifiManager) context
					.getSystemService(Context.WIFI_SERVICE);
			WifiInfo info = wifi.getConnectionInfo();
			return info.getMacAddress();
		} else {
			return "";
		}
	}

	/**
	 * 获取手机序列号；
	 * 
	 * @param context
	 */
	public static String getDeviceID(Context context) {
		TelephonyManager tm = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		return tm.getDeviceId();
	}

	/**
	 * 获取手机号码；
	 * 
	 * @param context
	 */
	public static String getPhoneNumber(Context context) {
		String number = "";
		TelephonyManager tm = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		if (tm != null) {
			number = tm.getLine1Number();
		}
		return number;
	}

	/**
	 * 获取屏幕宽度；
	 */
	public static int getScreenWidth(Context context) {
		return context.getApplicationContext().getResources()
				.getDisplayMetrics().widthPixels;
	}
	/**
	 * 判断网络连接状态
	 * 
	 * @param context
	 * @return true 网络正处于连接状态，false 网络状态不可用
	 */
	public static boolean checkNetInfo(Context context) {

		ConnectivityManager manager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		if (manager == null) {
			return false;
		}

		NetworkInfo networkinfo = manager.getActiveNetworkInfo();
		if (networkinfo == null || !networkinfo.isAvailable()) {
			return false;
		}

		return true;
	}
	/**
	 * 获取屏幕高度；
	 */
	public static int getScreenHeight(Context context) {
		return context.getApplicationContext().getResources()
				.getDisplayMetrics().heightPixels;
	}

	/**
	 * 验证手机号码是否正确;
	 * 
	 * @param number
	 *            手机号码
	 * @return true为合法，否则非法；
	 */
	public static boolean checkPhoneNumber(String number) {
		if (number.length() == 11
				&& (number.startsWith("13") || number.startsWith("14")
						|| number.startsWith("15") || number.startsWith("16") || number
							.startsWith("18"))) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 获取AndroidID；
	 * 
	 * @param context
	 */
	public static String getAndroidID(Context context) {
		return Settings.System.getString(context.getContentResolver(),
				Settings.Secure.ANDROID_ID);
	}

	/**
	 * 获取手机品牌和型号；
	 * 
	 * @param context
	 */
	public static String getBrandModel(Context context) {
		String brand = Build.BRAND + Build.MODEL;
		return brand;
	}

	/**
	 * 获取手机系统版本；
	 * 
	 * @param context
	 */
	public static String getPhoneVersion(Context context) {
		String version = Build.VERSION.RELEASE;
		return version;
	}

	/**
	 * 获取屏幕分辨率；
	 */
	public static String getResolution(Context context) {
		return getScreenWidth(context) + "*" + getScreenHeight(context);
	}

	/**
	 * 检测网络类型；平板电脑可能没有电话service; 联通3GNETWORK_TYPE_UMTS，移动3GNETWORK_TYPE_HSDPA，
	 * 电信3GNETWORK_TYPE_EVDO_0NETWORK_TYPE_EVDO_A
	 * 
	 * @param context
	 * @return boolean
	 */
	public static String getNetWork(Context context) {

		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (cm != null) {
			NetworkInfo info = cm.getActiveNetworkInfo();
			if (info != null && info.getType() == 1) {
				return "WIFI";
			}
		}

		TelephonyManager tm = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		// 获得手机SIMType
		if (tm != null) {
			int type = tm.getNetworkType();
			if (type == TelephonyManager.NETWORK_TYPE_UMTS
					|| type == TelephonyManager.NETWORK_TYPE_HSDPA
					|| type == TelephonyManager.NETWORK_TYPE_EVDO_0
					|| type == TelephonyManager.NETWORK_TYPE_EVDO_A) {

				return "3G";
			} else if (type == TelephonyManager.NETWORK_TYPE_GPRS
					|| type == TelephonyManager.NETWORK_TYPE_EDGE
					|| type == TelephonyManager.NETWORK_TYPE_CDMA) {
				return "2G";
			}
		}

		return "未知";
	}

	/**
	 * 获取operator：运营商名称；
	 * 
	 * @param context
	 * @return 运营商名称
	 */
	public static String getOperator(Context context) {
		TelephonyManager telManager = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);

		String operator = telManager.getSimOperator();

		if (operator != null) {

			if (operator.equals("46000") || operator.equals("46002")
					|| operator.equals("46007")) {

				// 中国移动
				return "中国移动";

			} else if (operator.equals("46001")) {

				// 中国联通
				return "中国联通";
			} else if (operator.equals("46003")) {

				// 中国电信
				return "中国电信";
			}
		}
		return "未知";

	}

	/**
	 * 获取AppKey；
	 * 
	 * @param context
	 * @return AppKey
	 */
	public static String getAppKey(Context context) {
		try {
			ApplicationInfo ai = context.getPackageManager()
					.getApplicationInfo(context.getPackageName(),
							PackageManager.GET_META_DATA);
			String appKey = ai.metaData.getString("APPKEY");
			return appKey;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取channelID；
	 * 
	 * @param context
	 * @return channelID
	 */
	public static String getChannelID(Context context) {
		try {
			ApplicationInfo ai = context.getPackageManager()
					.getApplicationInfo(context.getPackageName(),
							PackageManager.GET_META_DATA);
			String appKey = ai.metaData.getString("CHANNELID");
			return appKey;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取UMENG_APPKEY；
	 * 
	 * @param context
	 * @return UMENG_APPKEY
	 */
	public static String getUmeng(Context context) {
		try {
			ApplicationInfo ai = context.getPackageManager()
					.getApplicationInfo(context.getPackageName(),
							PackageManager.GET_META_DATA);
			String appKey = ai.metaData.getString("UMENG_APPKEY");
			return appKey;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
	 */
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	/**
	 * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	/**
	 * 当前Android系统版本是否在（ Donut） Android 1.6或以上
	 * 
	 * @return
	 */
	public static boolean hasDonut() {
		return Build.VERSION.SDK_INT >= Build.VERSION_CODES.DONUT;
	}

	/**
	 * 当前Android系统版本是否在（ Eclair） Android 2.0或 以上
	 * 
	 * @return
	 */
	public static boolean hasEclair() {
		return Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR;
	}

	/**
	 * 当前Android系统版本是否在（ Froyo） Android 2.2或 Android 2.2以上
	 * 
	 * @return
	 */
	public static boolean hasFroyo() {
		return Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO;
	}

	/**
	 * 当前Android系统版本是否在（ Gingerbread） Android 2.3x或 Android 2.3x 以上
	 * 
	 * @return
	 */
	public static boolean hasGingerbread() {
		return Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD;
	}

	/**
	 * 当前Android系统版本是否在（ Honeycomb） Android3.1或 Android3.1以上
	 * 
	 * @return
	 */
	public static boolean hasHoneycomb() {
		return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB;
	}

	/**
	 * 当前Android系统版本是否在（ HoneycombMR1） Android3.1.1或 Android3.1.1以上
	 * 
	 * @return
	 */
	public static boolean hasHoneycombMR1() {
		return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1;
	}

	/**
	 * 当前Android系统版本是否在（ IceCreamSandwich） Android4.0或 Android4.0以上
	 * 
	 * @return
	 */
	public static boolean hasIcecreamsandwich() {
		return Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH;
	}

	@SuppressLint("DefaultLocale")
	public static boolean isDroidXDroid2() {
		return (Build.MODEL.trim().equalsIgnoreCase("DROIDX")
				|| Build.MODEL.trim().equalsIgnoreCase("DROID2")
				|| Build.FINGERPRINT.toLowerCase().contains("shadow") || Build.FINGERPRINT
				.toLowerCase().contains("droid2"));
	}

	/**
	 * Get the memory class of this device (approx. per-app memory limit)
	 * 
	 * @param context
	 * @return
	 */
	public static int getMemoryClass(Context context) {
		return ((ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE)).getMemoryClass();
	}

	/**
	 * 获取应用名称
	 * 
	 * @param context
	 * @return
	 */
	public static String getApplicationName(Context context) {
		PackageManager packageManager = null;
		ApplicationInfo applicationInfo = null;
		try {
			packageManager = context.getPackageManager();
			applicationInfo = packageManager.getApplicationInfo(
					context.getPackageName(), 0);
		} catch (NameNotFoundException e) {
			applicationInfo = null;
		}
		String applicationName = (String) packageManager
				.getApplicationLabel(applicationInfo);
		return applicationName;
		
	}
	
	/**
	 * 获取应用是否有录音权限
	 * @param context
	 * @return
	 */
	public static boolean recoderPermissionGranted(Context context){
		PackageManager pm = context.getPackageManager();  
        boolean permission = (PackageManager.PERMISSION_GRANTED ==   
                pm.checkPermission("android.permission.RECORD_AUDIO", context.getPackageName()));    
		return permission;
	}
	
	/**
	 * 是否被禁用录音
	 * @param context
	 * @return
	 */
	public static boolean recoderPermissionDenied(Context context){
		PackageManager pm = context.getPackageManager();  
        boolean permission = (PackageManager.PERMISSION_DENIED ==   
                pm.checkPermission("android.permission.RECORD_AUDIO", context.getPackageName()));    
		return permission;
	}
	
	/**
	 * 设置添加屏幕的背景透明度
	 * 
	 * @param bgAlpha 0~1  不透明~完全透明
	 */
	public static void setBackgroundAlpha(Context mContext, float bgAlpha) {
		WindowManager.LayoutParams lp = ((Activity) mContext).getWindow().getAttributes();
		lp.alpha = bgAlpha; // 0.0-1.0
		((Activity) mContext).getWindow().setAttributes(lp);
	}
}
