package com.jky.xjht.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import com.hik.mcrsdk.MCRSDK;
import com.hik.mcrsdk.rtsp.RtspClient;
import com.hik.mcrsdk.talk.TalkClientSDK;
import com.hikvision.sdk.VMSNetSDK;

import java.util.LinkedList;
import java.util.List;
/**
 * <p>Application 类</p>
 * @author zhangliwei
 * @version V1.0.0
 */
public class XjhtApplication extends Application {
	
    private static XjhtApplication ins;
	private static XjhtApplication instance;
	private List<Activity> activityList = new LinkedList<Activity>();
	
    @Override
    public void onCreate() {
        super.onCreate();
        ins = this;
        MCRSDK.init();
        // 初始化RTSP
        RtspClient.initLib();
        MCRSDK.setPrint(1, null);
        // 初始化语音对讲
        TalkClientSDK.initLib();
        // SDK初始化
        VMSNetSDK.init(this);
    }
	public static XjhtApplication getInstance() {
		if(instance == null){
			
			instance = new XjhtApplication();
		}
		return instance;
	}
	
	// 添加Activity到容器中
		public void addActivity(Activity activity) {
			activityList.add(activity);
		}
	
    public static XjhtApplication getIns() {
        return ins;
    }

    /**
     * 获取登录设备mac地址
     * @return Mac地址
     */
    public String getMacAddress() {
        WifiManager wm = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        WifiInfo connectionInfo = wm.getConnectionInfo();
        String mac = connectionInfo.getMacAddress();
        return mac == null ? "" : mac;
    }
}
