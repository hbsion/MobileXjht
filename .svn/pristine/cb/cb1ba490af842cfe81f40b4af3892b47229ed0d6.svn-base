package com.jky.xjht.utils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.StatFs;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jky.xjht.bean.GsonObjModel;
import com.jky.xjht.bean.UpdataInfo;
import com.jky.xjht.dialog.SimpleDialog;
import com.jky.xjht.dialog.SimpleDialog.OnMySimpleDialogListener;
import com.jky.xjht.net.RequestCallBackBase;
import com.jky.xjht.net.VolleyRequest;
import com.jky.xjht.volley.VolleyError;

public class CheckAppUpdate implements Runnable{
	private final int UPDATA_NONEED = 0;
	private final int UPDATA_CLIENT = 1;
	private final int GET_UNDATAINFO_ERROR = 2;
	private final int SDCARD_NOMOUNTED = 3;
	private final int DOWN_ERROR = 4;
	private final int DOWN_ERROR_SDCARD = 5;
	private final int NO_NETWORK = 6;
	  
	private UpdataInfo info;
	/**
	 * 可以是不包含host，也可以是包含host的
	 */
	private String mURL;	
	private Context mContext;
	private String mAppName;
	private boolean mShowWarn;
	private int mType ;
	
	
	/**
	 * 
	 * @param context
	 * @param url：可以是不包含host，也可以是包含host的
	 * @param appName：根具用戶app的名字获取更新信息
	 * @param showWarn：
	 */
	public CheckAppUpdate(Context context, String url, String appName, boolean showWarn,int mType ) {
		mAppName = appName;
		this.mContext = context;
		this.mShowWarn = showWarn;
		mURL = url;
		this.mType = mType;
	}	
	
	private  boolean NetWorkStatus() {
		boolean netSataus = false;
		ConnectivityManager cwjManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo infos = cwjManager.getActiveNetworkInfo();
		if (infos != null) {
			netSataus = infos.isAvailable();
		}
		return netSataus;
	}
	
	@SuppressWarnings("deprecation")
	private long sdcardSize(){
		File sdcardDir = Environment.getExternalStorageDirectory(); 
		StatFs sf = new StatFs(sdcardDir.getPath()); 
		long freeBlocks = sf.getAvailableBlocks();
		long blockSize = sf.getBlockSize();
		return freeBlocks * blockSize;
	}
	
	
	/*
	 * 获取当前程序的版本
	 */
	private String getVersionName(Context context) {
		PackageManager packageManager = context.getPackageManager();
		PackageInfo packInfo;
		try {
			packInfo = packageManager.getPackageInfo(context.getPackageName(),
					0);
		} catch (NameNotFoundException e) {
			Log.e("CheckAppUpdate", "获取App版本錯誤：" + e.toString());
			return "0";
		}
		return packInfo.versionName;
	}
	
	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case UPDATA_NONEED:
				if (mShowWarn) {
					Toast.makeText(mContext, "已是最新版本 v"+info.getVirsion(), Toast.LENGTH_SHORT).show();
				}
				break;
			case UPDATA_CLIENT:
				// 对话框通知用户升级程序
				showUpdataDialog();
				break;
			case GET_UNDATAINFO_ERROR:
				// 服务器超时
				if(mShowWarn) {
					Toast.makeText(mContext, "获取更新信息失败，请检查网络", Toast.LENGTH_SHORT).show();
				}
				break;
			case SDCARD_NOMOUNTED:
				Toast.makeText(mContext, "SD卡不可用", Toast.LENGTH_SHORT).show();
				break;
			case DOWN_ERROR:
				Toast.makeText(mContext, "下载新版本失败，请检查网络", Toast.LENGTH_SHORT).show();
				break;
			case DOWN_ERROR_SDCARD:
				Toast.makeText(mContext, "Sdcard内存不足", Toast.LENGTH_SHORT).show();
				break;
			case NO_NETWORK:
				Toast.makeText(mContext, "网络连接不可用！", Toast.LENGTH_SHORT).show();
				break;
			}
		}
	};
	
	@Override
	public void run() {
		if(NetWorkStatus()==false){
			Message msg = new Message();
			msg.what = NO_NETWORK;
			handler.sendMessage(msg);
			return;
		}
		try 
		{
//			RequestCallBackBase<GsonObjModel<UpdataInfoNew>> listener = new RequestCallBackBase<GsonObjModel<UpdataInfoNew>>() {
//				@Override
//				public void onSuccess(String result, String tag) {
//					super.onSuccess(result, tag);
//					if(code == 0){
//						if (jsonResult.Data.getVersion().compareTo(getVersionName(mContext)) <= 0) {
//							Message msg = Message.obtain();
//							msg.what = UPDATA_NONEED;
//							handler.sendMessage(msg);
//						} else {
//							Message msg = Message.obtain();
//							msg.what = UPDATA_CLIENT;
//							handler.sendMessage(msg);
//						}
//					}else{
//						Message msg = Message.obtain();
//						msg.what = GET_UNDATAINFO_ERROR;
//						handler.sendMessage(msg);
//					}
//				}
//				
			RequestCallBackBase<GsonObjModel<UpdataInfo>> listener = new RequestCallBackBase<GsonObjModel<UpdataInfo>>() {
				public void onSuccess(String result, String tag) {
					super.onSuccess(result, tag);
					if(code == 0){
						try {
							Gson gson=new Gson();
							info = jsonResult.Data;
							JSONObject dataJson = new JSONObject(result.toString());
							String data = dataJson.getString("Data");
							info=gson.fromJson(data, UpdataInfo.class);
							if (info.getVirsion().compareTo(getVersionName(mContext)) <= 0) {
//								AppObserverUtils.notifyDataChange(12, null, null);
								Message msg = Message.obtain();
								msg.what = UPDATA_NONEED;
								handler.sendMessage(msg);
							} else {
								Message msg = Message.obtain();
								msg.what = UPDATA_CLIENT;
								handler.sendMessage(msg);
							}
						} catch (Exception e) {
							e.printStackTrace();
							SingleToast.show(mContext, "当前已是最新版本！");
						}
//						info=gson.fromJson(jsonResult.Data, UpdataInfo.class);
//						if (jsonResult.Data.getVirsion().compareTo(getVersionName(mContext)) <= 0) {
//						if (info.getVirsion().compareTo(getVersionName(mContext)) <= 0) {
//							Message msg = Message.obtain();
//							msg.what = UPDATA_NONEED;
//							handler.sendMessage(msg);
//						} else {
//							Message msg = Message.obtain();
//							msg.what = UPDATA_CLIENT;
//							handler.sendMessage(msg);
//						}
					}else{
						Message msg = Message.obtain();
						msg.what = GET_UNDATAINFO_ERROR;
						handler.sendMessage(msg);
						
					}
				}
				
				public void onFailed(VolleyError error) {
					Message msg = Message.obtain();
					msg.what = GET_UNDATAINFO_ERROR;
					handler.sendMessage(msg);
				}
			};
			Map<String, String> map = new HashMap<>();
//			map.put("appName", mAppName);
			map.put("Type", mType+"");
			map.put("SSXT", mAppName);
//			map.put("Virsion", getVersionName(mContext));
//			VolleyRequest.getInstance(mContext).volleyPost(ServerConst.getNewFullURL(mURL),
//					map, listener , "checkAppVer");
			VolleyRequest.getInstance(mContext).volleyPost(mURL,
					map, listener , "checkAppVer");
//			String url;
//			url="http://192.168.2.85:8086/api/SYS/Update";
//			VolleyRequest.getInstance(mContext).volleyPost(url,
//					map, listener , "checkAppVer");
		}catch(Exception ex){
			Log.e("CheckAppUpdate", ex.toString());
		}
			/*
			URL url = new URL(mURL);
			HttpURLConnection conn = (HttpURLConnection) url
					.openConnection();
			conn.setConnectTimeout(5000);
			conn.connect();
			if(conn.getResponseCode() == 200) {
				InputStream is = conn.getInputStream();
				info = UpdataInfoParser.getUpdataInfo(is);
				is.close();
				if (info.getVersion().compareTo(getVersionName(mContext)) <= 0) {
					Message msg = new Message();
					msg.what = UPDATA_NONEED;
					handler.sendMessage(msg);
				} else {
					Message msg = new Message();
					msg.what = UPDATA_CLIENT;
					handler.sendMessage(msg);
				}
			} else {
				Message msg = new Message();
				msg.what = GET_UNDATAINFO_ERROR;
				handler.sendMessage(msg);
			}
			
		} catch (Exception e) {
//			System.out.println(e.getMessage());
			Message msg = new Message();
			msg.what = GET_UNDATAINFO_ERROR;
			handler.sendMessage(msg);
			e.printStackTrace();
		}
		*/
	}
	
	
	private SimpleDialog mTipDialog;
	/*
	 * 
	 * 弹出对话框通知用户更新程序
	 * 
	 */
	protected void showUpdataDialog() {
		mTipDialog = new SimpleDialog(mContext, "有新版本", info.getDescrip()+"\n"+"是否更新？", "取消", "确定" , false);
		mTipDialog.SetOnMySimpleDialogListener(new OnMySimpleDialogListener() {
			
			@Override
			public void onMySure() {
				// 当点确定按钮时从服务器上下载 新的apk 然后安装
				downLoadApk();
			}
			
			@Override
			public void onMyCancle() {
				// 当点取消按钮消失
			}
		});
	}

	/*
	 * 从服务器中下载APK
	 */
	protected void downLoadApk() {
		
		if (!Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			Message msg = new Message();
			msg.what = SDCARD_NOMOUNTED;
			handler.sendMessage(msg);
		} else {
			if(info.getSize() > sdcardSize()){
				Toast.makeText(mContext, "Sdcard容量不足", Toast.LENGTH_LONG).show();
			}else{
				final ProgressDialog pd;  // 进度条对话框
				pd = new ProgressDialog(mContext);
				pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
				pd.setMessage("正在下载更新");
				pd.setCancelable(false);
				pd.show();
				new Thread() {
					@Override
					public void run() {
						try {
							System.out.println("zlw=info.getPath()========"+info.getPath()+"===="+mContext.getPackageName());
							File file = DownLoadManager.getFileFromServer(info.getPath(), pd, mContext.getPackageName());
							System.out.println("zlw===file=========="+file);
							//调用更新数据库的操作
//							DownLoadManager.getDbFromServer(info.getDburl(), pd);
//							sleep(1000);
							pd.dismiss(); // 结束掉进度条对话框
							if (file !=null) {
								installApk(file);
							}else{
								Message msg = new Message();
								msg.what = DOWN_ERROR;
								handler.sendMessage(msg);
							}
		
						} catch (Exception e) {
							System.out.println(e.getMessage());
							Message msg = new Message();
							msg.what = DOWN_ERROR;
							handler.sendMessage(msg);
							e.printStackTrace();
							pd.dismiss();
						}
					}
				}.start();
			}
		}
	}

	// 安装apk
	protected void installApk(File file) {
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_VIEW);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setDataAndType(Uri.fromFile(file),
				"application/vnd.android.package-archive");
		mContext.startActivity(intent);
	}
}
	
