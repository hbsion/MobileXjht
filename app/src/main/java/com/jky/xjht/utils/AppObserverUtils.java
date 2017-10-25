package com.jky.xjht.utils;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 
 *  实现整个APP观察者模式
 * @author luo
 *
 */
public final class AppObserverUtils {



	public static final int LOGIN_LOG_OUT = 10001;//退出登陆


	// 实现整个app观察者模式
	private static List<HashMap<Integer, ObserverListener>> observerListeners = new ArrayList<HashMap<Integer, ObserverListener>>();

	// 实现整个app观察者模式,同一个action可以注册多个监听
	/**
	 * 注册监听，不需要的时候要取消监听，可在ondestory()中取消
	 * 
	 * @param action
	 * @param listener
	 */
	public static void registerObserver(int action, ObserverListener listener) {
		if (listener != null) {
			HashMap<Integer, ObserverListener> hm = new HashMap<Integer, ObserverListener>();
			hm.put(action, listener);
			observerListeners.add(hm);
		}
	}

	public static void unRegisterObserver(int action) {
		for (int i = 0; i < observerListeners.size(); i++) {
			if (observerListeners.get(i).containsKey(action)) {
				observerListeners.remove(i);
			}
		}
		// if (observerListeners.containsKey(action)) {
		// observerListeners.remove(action);
		// }
	}

	/**
	 * 通知已经注册此action的监听去执行 ,action 必传，其他可传(null)
	 * 
	 * @param action
	 *            需要传递的action要与注册的一样，
	 * @param bundle
	 *            封装对象，
	 * @param object
	 *            也可以传递自己封装的对象，
	 */
	public static void notifyDataChange(int action, Bundle bundle, Object object) {
		for (int i = 0; i < observerListeners.size(); i++) {
			if (observerListeners.get(i).containsKey(action)
					&& observerListeners.get(i).get(action) != null) {
				observerListeners.get(i).get(action)
						.notifyChange(bundle, object);
			}
		}
	}
}
