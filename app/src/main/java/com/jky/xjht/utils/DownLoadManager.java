package com.jky.xjht.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


import android.app.ProgressDialog;
import android.os.Environment;

public class DownLoadManager {

	/**
	 * 下载apk
	 * @param path
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public static File getFileFromServer(String path, ProgressDialog pd, String pkn) throws Exception{
		//如果相等的话表示当前的sdcard挂载在手机上并且是可用的
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
			URL url = new URL(path);
			HttpURLConnection conn =  (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5000);
			conn.connect();
			if(conn.getResponseCode() == 200) {
				pd.setMax(conn.getContentLength());
				InputStream is = conn.getInputStream();
				File m_file = new File(Environment.getExternalStorageDirectory(),".jky/updata/");
				if(!m_file.exists()){
					m_file.mkdirs();
				}
				
				File file = new File(Environment.getExternalStorageDirectory(), ".jky/updata/"+pkn+".apk");
				file.delete();
				FileOutputStream fos = new FileOutputStream(file);
				BufferedInputStream bis = new BufferedInputStream(is);
				byte[] buffer = new byte[2048];
				int len ;
				int total=0;
				while((len =bis.read(buffer))!=-1){
					fos.write(buffer, 0, len);
					total+= len;
					//获取当前下载量
					pd.setProgress(total);
				}
				fos.close();
				bis.close();
				is.close();
				return file;
			}
			
		}
		return null;
	}
}
