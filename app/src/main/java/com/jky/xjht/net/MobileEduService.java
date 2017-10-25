package com.jky.xjht.net;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import android.content.Context;

public class MobileEduService {
	private static MobileEduService instence;
	private VolleyRequest request;

	private MobileEduService(Context context) {
		request = VolleyRequest.getInstance(context);
	}

	public static MobileEduService getInstance(Context context) {
		if (instence == null) {
			instence = new MobileEduService(context);
		}
		return instence;
	}

	public interface RequestCallBack {
		public void onSuccess(String tag, String result);

		public void onFailed(String errorInfo);
	}

	public void userLogin(String userName, String password, String tag,
			RequestListener listener) {
		String md5Pwd = "";
		try {
			md5Pwd = getMD5(password);
		} catch (NoSuchAlgorithmException e) {
			// Log.e(TAG, "getMD5 string failed !", e);
		}
		Map<String, String> params = new HashMap<String, String>();
		params.put("LoginType", "2");
		params.put("LoginName", userName);
		params.put("Pwd", md5Pwd);
		params.put("Vcode", "");
		request.volleyPost(ApiConstant.USER_LOGIN, params, listener, tag);
	}

	// 工程标注点
	public void getProjectPointList(String tag, RequestListener listener) {
		Map<String, String> params = new HashMap<String, String>();
		request.volleyPost(ApiConstant.GET_PROJECT_POINT_LIST, params,
				listener, tag);
	}

	/**
	 * 工程列表
	 * UserId 登录用户Id
	 * RoleId 登录用户角色Id= LoginType
	 * PrjKeyword 工程名称关键字
	 * AreaId 所在区县Id
	 * PrjTypeId 工程类型Id
	 * CurrentPage 当前请求的页
	 * PageSize 每页行数
	 * */ 
	public void getProjectList(String userId,String RoleId,String PrjKeyword,String AreaId,String PrjTypeId, int page, int pageSize,
			String tag, RequestListener listener) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("UserId", userId);
		params.put("RoleId", RoleId);
		params.put("PrjKeyword", PrjKeyword);
		params.put("AreaId", AreaId);
		params.put("PrjTypeId", PrjTypeId);
		params.put("CurrentPage", page + "");
		params.put("PageSize", pageSize + "");
		request.volleyPost(ApiConstant.GET_PROJECT_LIST, params, listener, tag);
	}

	// 工程详情
	public void getProjectDetail(String id, String tag, RequestListener listener) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("Id", id);
		request.volleyPost(ApiConstant.GET_PROJECT_DETAIL, params, listener,
				tag);
	}

	public static String getMD5(String val) throws NoSuchAlgorithmException {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		byte[] strTemp = val.getBytes();
		MessageDigest mdTemp = MessageDigest.getInstance("MD5");
		mdTemp.update(strTemp);
		byte[] md = mdTemp.digest();
		int j = md.length;
		char str[] = new char[j * 2];
		int k = 0;
		for (int i = 0; i < j; i++) {
			byte b = md[i];
			str[k++] = hexDigits[b >> 4 & 0xf];
			str[k++] = hexDigits[b & 0xf];
		}
		return new String(str);
	}
}
