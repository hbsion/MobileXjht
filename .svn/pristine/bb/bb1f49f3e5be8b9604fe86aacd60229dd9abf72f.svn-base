package com.jky.xjht.utils;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimeUtil {

	/**
	 * 获取现在时间
	 * 
	 * @return 返回时间类型 yyyy-MM-dd HH:mm:ss
	 */
	public static Date getNowDate() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		ParsePosition pos = new ParsePosition(8);
		Date currentTime_2 = formatter.parse(dateString, pos);
		return currentTime_2;
	}

	/**
	 * 获取现在时间
	 * 
	 * @return返回短时间格式 yyyy-MM-dd
	 */
	public static Date getNowDateShort() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(currentTime);
		ParsePosition pos = new ParsePosition(8);
		Date currentTime_2 = formatter.parse(dateString, pos);
		return currentTime_2;
	}

	/**
	 * 获取现在时间
	 * 
	 * @return返回字符串格式 yyyy-MM-dd HH:mm:ss
	 */
	public static String getStringDate() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	 * 获取当前时间
	 * 
	 * @return 返回格式 yyyy-MM-dd
	 */
	public static String getStringDateShort() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String date = formatter.format(currentTime);
		return date;
	}

	/**
	 * 将日期格式转为毫秒数
	 * 
	 * @param in
	 *            格式为 2014-09-30
	 * @return 返回格式为 1345185923140
	 */
	public static long dateToLong(String in) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date = format.parse(in);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			return cal.getTimeInMillis();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return 0;
	}

	/**
	 * 将日期格式转为毫秒数
	 * 
	 * @param in
	 *            格式为 2014-09-30 09:50
	 * @return 返回格式为 1345185923140
	 */
	public static long dateToLong1(String in) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			Date date = format.parse(in);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			return cal.getTimeInMillis();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return 0;
	}

	/**
	 * 将日期格式转为毫秒数
	 * 
	 * @param in
	 *            格式为 2014-09-30 09:50:05
	 * @return 返回格式为 1345185923140
	 */
	public static long dateToLong3(String in) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date date = format.parse(in);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			return cal.getTimeInMillis();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return 0;
	}

	/**
	 * 将日期格式转为毫秒数
	 * 
	 * @param in
	 *            格式为 2014年09月30日 09:50
	 * @return 返回格式为 1345185923140
	 */
	public static long dateToLong2(String in) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
		try {
			Date date = format.parse(in);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			return cal.getTimeInMillis();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return 0;
	}

	/**
	 * 将毫米数转为日期
	 * 
	 * @param millis
	 *            格式为1345185923140L
	 * @return 返回格式为 年-月-日 时：分：秒
	 */
	public static String longToDate(long millis) {
		Date date = new Date(millis);
		Calendar gc = Calendar.getInstance();
		gc.setTime(date);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sb = format.format(gc.getTime());
		System.out.println(sb);
		return sb;
	}

	/**
	 * 将long数转为日期
	 * 
	 * @param millis
	 *            格式为1345185923140
	 * @return 返回格式为 年-月-日 时：分
	 */
	public static String longToDate1(long millis) {
		Date date = new Date(millis);
		Calendar gc = Calendar.getInstance();
		gc.setTime(date);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String sb = format.format(gc.getTime());
		System.out.println(sb);
		return sb;
	}

	/**
	 * 将毫秒数转为日期
	 * 
	 * @param millis
	 *            格式为1345185923140L
	 * @return 返回格式为 年-月-日
	 */
	public static String longToDate2(long millis) {
		Date date = new Date(millis);
		Calendar gc = Calendar.getInstance();
		gc.setTime(date);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String sb = format.format(gc.getTime());
		System.out.println(sb);
		return sb;
	}

	/**
	 * 将毫秒数转为日期
	 * 
	 * @param millis
	 *            格式为1345185923140L
	 * @return 返回格式为 2014年09月07日 10:30
	 */
	public static String longToDate3(long millis) {
		Date date = new Date(millis);
		Calendar gc = Calendar.getInstance();
		gc.setTime(date);
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
		String sb = format.format(gc.getTime());
		System.out.println(sb);
		return sb;
	}

	/**
	 * 将毫秒数转为日期 返回一个数组
	 * 
	 * @param millis
	 *            格式为1345185923140L
	 * @return 返回格式为 2014年09月07日 10:30
	 */
	public static String[] longToDate6(long millis) {
		String[] times = new String[2];
		Date date = new Date(millis);
		Calendar gc = Calendar.getInstance();
		gc.setTime(date);
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
		String sb = format.format(gc.getTime());
		times[0] = sb;
		times[1] = millis + "";
		System.out.println(sb);
		return times;
	}

	/**
	 * 将毫秒数转为日期
	 * 
	 * @param millis
	 *            格式为1345185923140L
	 * @return 返回格式为 2015
	 */
	public static String longToDate4(long millis) {
		Date date = new Date(millis);
		Calendar gc = Calendar.getInstance();
		gc.setTime(date);
		SimpleDateFormat format = new SimpleDateFormat("yyyy");
		String sb = format.format(gc.getTime());
		// System.out.println(sb);
		return sb;
	}

	/**
	 * 将毫秒数转为日期
	 * 
	 * @param millis
	 *            格式为1345185923140L
	 * @return 返回格式为 2014.09.07
	 */
	public static String longToDate5(long millis) {
		Date date = new Date(millis);
		Calendar gc = Calendar.getInstance();
		gc.setTime(date);
		SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
		String sb = format.format(gc.getTime());
		// System.out.println(sb);
		return sb;
	}

	/**
	 * 将毫秒数转为时间
	 * 
	 * @param millis
	 *            格式为1345185923140L
	 * @return 返回格式为 09:07
	 */
	public static String longToDate7(long millis) {
		Date date = new Date(millis);
		Calendar gc = Calendar.getInstance();
		gc.setTime(date);
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
		String sb = format.format(gc.getTime());
		// System.out.println(sb);
		return sb;
	}

	/**
	 * 将毫秒数转为时间
	 * 
	 * @param millis
	 *            格式为1345185923140L
	 * @return 返回格式为 xxxx年xx月xx日
	 */
	public static String longToDate8(long millis) {
		Date date = new Date(millis);
		Calendar gc = Calendar.getInstance();
		gc.setTime(date);
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
		String sb = format.format(gc.getTime());
		// System.out.println(sb);
		return sb;
	}

	/**
	 * 获得当天0点时间
	 * 
	 * @return
	 */
	public static long getTimesMorning() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTimeInMillis();
	}

	/**
	 * 获得当天24点时间
	 * 
	 * @return
	 */
	public static long getTimesNight() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 24);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTimeInMillis();
	}

	/**
	 * 获得指定时间的0点时间
	 * 
	 * @param millis
	 * @return
	 */
	public static long getTimesMorning(long millis) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(millis);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTimeInMillis();
	}

	/**
	 * 获得指定时间的24点时间
	 * 
	 * @param millis
	 * @return
	 */
	public static long getTimesNight(long millis) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(millis);
		cal.set(Calendar.HOUR_OF_DAY, 24);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTimeInMillis();
	}

	public static boolean isMobileNO(String mobiles) {
		Pattern p = Pattern
				.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");

		Matcher m = p.matcher(mobiles);

		System.out.println(m.matches() + "---");

		return m.matches();
	}

	public static String getChatTime(long time) {
		return getMinTime(time);
	}

	public static String getMinTime(long time) {
		SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd HH:mm");
		return format.format(new Date(time));
	}

	/**
	 * 根据时间格式获得时间
	 * 
	 * @param time
	 * @param dateFormat
	 * @return
	 */
	public static String getDate(int time, DateFormatEnum dateFormat) {
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.set(1970, 0, 1, 0, 0, 0);
		cal.add(Calendar.SECOND, time);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				dateFormat.toString());
		return simpleDateFormat.format(date);
	}

	/**
	 * 获取2个日期的时间差
	 * 
	 * @param date1
	 *            格式 xxxx-xx-xx xx:xx:xx
	 * @param date2
	 *            格式 xxxx-xx-xx xx:xx:xx
	 * @return
	 */

	public static String getDifTime(String desc,String date1, String date2) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String difTime = "";
		String str = desc;
//		"离直播开播还有";
		try {
			Date d1 = df.parse(date1);
			Date d2 = df.parse(date2);
			long time1 = d1.getTime();
			long time2 = d2.getTime();
			// long diff = d2.getTime() - d1.getTime();// 这样得到的差值是微秒级别
			long diff;
			if (time1 < time2) {
				diff = time2 - time1;
			} else {
				diff = time1 - time2;
			}
			long days = diff / (1000 * 60 * 60 * 24);
			long hours = (diff - days * (1000 * 60 * 60 * 24))
					/ (1000 * 60 * 60);
			long minutes = (diff - days * (1000 * 60 * 60 * 24) - hours
					* (1000 * 60 * 60))
					/ (1000 * 60);

			if (days == 0) {
				if (hours == 0) {
					difTime = (minutes > 10 ? minutes : "0" + minutes) + "分";
					difTime = str + difTime;
				} else {
					if (minutes == 0) {
						difTime = hours + "小时";
					} else {
						difTime = hours + "小时"
								+ (minutes > 10 ? minutes : "0" + minutes)
								+ "分";
					}
					difTime = str + difTime;
				}
			} else {
				if (days < 0) {
					difTime = "直播结束";
				} else {
					if (hours == 0) {
						if (minutes == 0) {
							difTime = days + "天";
						} else {
							difTime = days + "天"
									+ (minutes > 10 ? minutes : "0" + minutes)
									+ "分";
						}
					} else {
						if (minutes == 0) {
							difTime = days + "天" + hours + "小时";
						} else {
							difTime = days + "天" + hours + "小时"
									+ (minutes > 10 ? minutes : "0" + minutes)
									+ "分";
						}
					}
					difTime = str + difTime;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return difTime;
	}
}
