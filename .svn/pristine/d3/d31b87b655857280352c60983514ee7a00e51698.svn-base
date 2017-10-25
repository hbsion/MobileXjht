package com.jky.xjht.utils;

public class DownLoadUtil {
	
	private static int MB_SIZE = 1024 * 1024;
	private static int KB_SIZE = 1024;
	
	public static String getFileSize(long size) {
		if (size <= 0) {
			return "0,M";
		}
		if (size >= MB_SIZE) {
			return ((((double) (size * 100 / MB_SIZE)) / 100) + ",M");
		} else if (size >= KB_SIZE) {
			return (((double) (size * 100 / KB_SIZE)) / 100 + ",K");
		} else {
			return size + ",B";
		}
	}
	
}
