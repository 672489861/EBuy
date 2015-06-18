/**
 *  print out a bunch of interesting things
 *	
 *	System.out.println("获取间隔的天数:" + DateTimeUtil.getTwoDay("2009-01-01", "2009-01-05"));
 *	System.out.println("获取今天是本周的第几天: " + DateTimeUtil.getDayOfWeek());
 *	System.out.println("获取当前年: " + DateTimeUtil.getYear());
 *	System.out.println("获取当前月: " + DateTimeUtil.getMonth());
 *	System.out.println("获取当前小时: " + DateTimeUtil.getHourOfDay());
 *	System.out.println("获取当前分钟: " + DateTimeUtil.getMinite());
 *	System.out.println("getMinute(Long time): "
 *				+ DateTimeUtil
 *						.getMinite(DateTimeUtil.strToDate(
 *								"2014-11-12 21:17:17", "yyyy-MM-dd hh:mm:ss")
 *								.getTime()));
 *	System.out.println("获取当前秒数:  " + DateTimeUtil.getSecond());
 *	System.out.println("获取当前日期: " + DateTimeUtil.getNowDate());
 *	System.out.println("获取本周一的日期: " + DateTimeUtil.getMondayOFWeek());
 *	System.out.println("获取本周日的日期: " + DateTimeUtil.getCurrentWeekday());
 *	System.out.println("获取本月第一天日期:" + DateTimeUtil.getFirstDayOfMonth());
 *	System.out.println("获取本月最后一天日期:" + DateTimeUtil.getDefaultDay());
 *	System.out.println("获取上月第一天日期:" + DateTimeUtil.getPreviousMonthFirst());
 *	System.out.println("获取上月最后一天的日期:" + DateTimeUtil.getPreviousMonthEnd());
 *	System.out.println("获取下月第一天日期:" + DateTimeUtil.getNextMonthFirst());
 *	System.out.println("获取下月最后一天日期:" + DateTimeUtil.getNextMonthEnd());
 *	System.out.println("获取本年的第一天日期:" + DateTimeUtil.getCurrentYearFirst());
 *	System.out.println("获取本年最后一天日期:" + DateTimeUtil.getCurrentYearEnd());
 *	System.out.println("获取去年的第一天日期:" + DateTimeUtil.getPreviousYearFirst());
 *	System.out.println("获取去年的最后一天日期:" + DateTimeUtil.getPreviousYearEnd());
 *	System.out.println("获取明年第一天日期:" + DateTimeUtil.getNextYearFirst());
 *	System.out.println("获取明年最后一天日期:" + DateTimeUtil.getNextYearEnd());
 *  
 **/
package com.zjw.common.util;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.zjw.common.exception.MyException;


/***
 * 
 * 日期时间格式化工具类
 * 
 * @author zjw
 * 
 */

public class DateTimeUtil {

	private static Calendar calendar = Calendar.getInstance();
	private static SimpleDateFormat dateFormat = new SimpleDateFormat();

	/***
	 * 获取当前年份
	 * 
	 * @return
	 */

	public static int getYear() {
		return calendar.get(Calendar.YEAR);
	}

	/***
	 * 
	 * 获取当前月份
	 * 
	 * @return
	 */

	public static int getMonth() {
		return calendar.get(Calendar.MONTH) + 1;
	}

	/**
	 * 返回今天是这个月的第几天
	 * 
	 * @return
	 */

	public static int getDayOfMonth() {
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	/***
	 * 
	 * 返回今天是这周的第几天
	 * 
	 * @return
	 */

	public static int getDayOfWeek() {
		return calendar.get(Calendar.DAY_OF_WEEK) - 1;
	}

	/***
	 * 获取当前小时
	 * 
	 * @param timeMillis
	 *            根据数字型时间返回当前小时
	 * 
	 * @return
	 */

	public static int getHourOfDay(long timeMillis) {
		calendar.setTime(new Date(timeMillis));
		return calendar.get(Calendar.HOUR_OF_DAY);
	}

	/***
	 * 获取当前小时
	 * 
	 * @return
	 */

	public static int getHourOfDay() {
		calendar.setTime(new Date());
		return calendar.get(Calendar.HOUR_OF_DAY);
	}

	/***
	 * 获取当前分钟
	 * 
	 * @param timeMillis
	 *            根据数字型时间返回当前分钟
	 * 
	 * @return
	 */

	public static int getMinite(long timeMillis) {
		calendar.setTime(new Date(timeMillis));
		return calendar.get(Calendar.MINUTE);
	}

	/***
	 * 获取当前分钟
	 * 
	 * @return
	 */

	public static int getMinite() {
		calendar.setTime(new Date());
		return calendar.get(Calendar.MINUTE);
	}

	/***
	 * 获取当前秒
	 * 
	 * @param timeMillis
	 *            根据数字型时间返回当前秒
	 * 
	 * @return
	 */

	public static int getSecond(long timeMillis) {
		calendar.setTime(new Date(timeMillis));
		return calendar.get(Calendar.SECOND);
	}

	/***
	 * 获取当前秒
	 * 
	 * @return
	 */

	public static int getSecond() {
		calendar.setTime(new Date());
		return calendar.get(Calendar.SECOND);
	}

	/***
	 * 以yyyy-MM-dd的形式返回当前时间
	 * 
	 * @return
	 */

	public static String getNowDate() {
		dateFormat.applyPattern("yyyy-MM-dd");
		return dateFormat.format(new Date());
	}

	/***
	 * 
	 * 按照指定的格式返回当前时间
	 * 
	 * @param pattern
	 *            格式化字符串
	 * @return
	 * @throws MyException
	 */

	public static String getNowDate(String pattern) throws MyException {
		try {
			dateFormat.applyPattern(pattern);
		} catch (Exception e) {
			throw new MyException("请输入正确的表达式!");
		}
		return dateFormat.format(new Date());
	}

	/***
	 * 
	 * 按照指定格式,格式化日期
	 * 
	 * @param date
	 *            日期
	 * @param pattern
	 *            格式化字符串
	 * @return
	 */

	public static String dateToStr(Date date, String pattern) {
		dateFormat.applyPattern(pattern);
		return dateFormat.format(date);
	}

	/***
	 * 
	 * 将给定的日期型数据以yyyy-MM-dd的格式转换成字符串
	 * 
	 * @param date
	 *            日期
	 * 
	 * @return
	 */
	public static String dateToStr(Date date) {
		dateFormat.applyPattern("yyyy-MM-dd");
		return dateFormat.format(date);
	}

	/**
	 * 将短时间格式字符串转换为时间 yyyy-MM-dd
	 * 
	 * @param strDate
	 *            字符串形式的日期
	 * 
	 * @return
	 */
	public static Date strToDate(String strDate) {
		dateFormat.applyPattern("yyyy-MM-dd");
		ParsePosition pos = new ParsePosition(0);
		Date date = dateFormat.parse(strDate, pos);
		return date;
	}

	/**
	 * 按照指定的格式,将日期型字符串格式化成Date型
	 * 
	 * @param strDate
	 *            字符串形式的日期
	 * @param pattern
	 *            格式化字符串
	 * 
	 * @return
	 */
	public static Date strToDate(String strDate, String pattern) {
		dateFormat.applyPattern(pattern);
		ParsePosition pos = new ParsePosition(0);
		Date date = dateFormat.parse(strDate, pos);
		return date;
	}

	/**
	 * 判断当前年是否润年
	 * 
	 * @param date
	 *            年份
	 * 
	 * @return
	 */
	public static boolean isLeapYear(String date) {

		/**
		 * 详细设计： 1.被400整除是闰年，否则： 2.不能被4整除则不是闰年 3.能被4整除同时不能被100整除则是闰年
		 * 3.能被4整除同时能被100整除则不是闰年
		 */
		Date d = strToDate(date);
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(d);
		int year = gc.get(Calendar.YEAR);
		if ((year % 400) == 0) {
			return true;
		} else if ((year % 4) == 0) {
			if ((year % 100) == 0) {
				return false;
			} else {
				return true;
			}
		} else {
			return false;
		}
	}

	/**
	 * 判断指定年是否润年
	 * 
	 * @param date
	 *            年份
	 * @return
	 */
	public static boolean isLeapYear(int year) {
		if ((year % 400) == 0) {
			return true;
		} else if ((year % 4) == 0) {
			if ((year % 100) == 0) {
				return false;
			} else {
				return true;
			}
		} else {
			return false;
		}
	}

	/***
	 * 
	 * 根据给定的时间,返回对应的日期范围(单位:分钟 一般用户定时任务使用)
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @param hour
	 * @param minute
	 * @param interval
	 *            时间间隔(分钟)
	 * @return list.get(0)开始范围 1结束范围
	 */

	public static List<String> getBeginEndTime(int year, int month, int day,
			int hour, int minute, int interval) {
		int endMinute = minute + interval;
		int endhour = hour;
		int endday = day;
		int endmonth = month;
		int endyear = year;

		if (minute > 60) {
			hour++;
			minute -= 60;
		}
		endMinute = minute + interval;
		if (endMinute > 60) {
			endhour++;
			endMinute -= 60;
		}

		if (endhour == 24) {
			endhour = 0;
			endday++;
			if (endmonth == 4 || endmonth == 6 || endmonth == 9
					|| endmonth == 11) { // 30天
				if (endday > 30) {
					endmonth++;
					endday = 1;
				}
			} else if (endmonth == 2) {
				if (isLeapYear(endyear)) {
					if (endday > 29) {
						endmonth++;
						endday = 1;
					}
				} else {
					if (endday > 28) {
						endmonth++;
						endday = 1;
					}
				}
			} else { // 31天
				if (endday > 31) {
					endmonth++;
					endday = 1;
				}
			}
		}
		if (endmonth > 12) {
			endyear++;
			endmonth = 1;
		}
		String beginTime = dateToStr(
				strToDate(year + "-" + month + "-" + day + " " + hour + ":"
						+ minute + ":00", "yyyy-MM-dd HH:mm:ss"),
				"yyyy-MM-dd HH:mm:ss");
		String endTime = dateToStr(
				strToDate(endyear + "-" + endmonth + "-" + endday + " "
						+ endhour + ":" + endMinute + ":00",
						"yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss");
		List<String> times = new ArrayList<String>();
		times.add(beginTime);
		times.add(endTime);
		return times;
	}

	/***
	 * 返回当前月份天数
	 * 
	 * @return
	 */

	public static int getEndDateOfMonth() {
		return getEndDay(getMonth(), getYear());
	}

	/***
	 * 返回给定时间月份的天数
	 * 
	 * @param date
	 *            yyyy-MM-dd形式的日期
	 * 
	 * @return
	 */

	public static int getEndDateOfMonth(Date date) {
		String str = dateToStr(date);
		return getEndDay(Integer.parseInt(str.substring(0, 4)),
				Integer.parseInt(str.substring(5, 7)));
	}

	/***
	 * 返回给定时间月份的天数
	 * 
	 * @param date
	 *            日期形式的字符串
	 * 
	 * @return
	 */

	public static int getEndDateOfMonth(String date) {
		return getEndDateOfMonth(strToDate(date));
	}

	/***
	 * 返回指定月有几天
	 * 
	 * @param mon
	 *            月份
	 * @param year
	 *            年份
	 * @return
	 */

	private static int getEndDay(int mon, int year) {
		if ((mon == 1) || (mon == 3) || (mon == 5) || (mon == 7) || (mon == 8)
				|| (mon == 10) || (mon == 12))
			return 31;
		else if ((mon == 4) || (mon == 6) || (mon == 9) || (mon == 11)) {
			return 30;
		} else if (isLeapYear(year))
			return 29;
		else {
			return 28;
		}
	}

	/***
	 * 
	 * 返回当前系统时间 （yyyy-MM-dd HH:mm:ss）
	 * 
	 * @return
	 */

	public static String getCurrentTimeAsString() {
		return getTimeAsString(System.currentTimeMillis());
	}

	/***
	 * 
	 * 按照指定的格式 格式化长整型的日期
	 * 
	 * @param timeMillis
	 *            时间
	 * @param pattern
	 *            格式化字符串
	 * @return
	 */

	public static String getTimeAsString(long timeMillis, String pattern) {
		dateFormat.applyPattern(pattern);
		return dateFormat.format(new Date(timeMillis));
	}

	/***
	 * 
	 * 根据数字时间返回当前时间 （yyyy-MM-dd HH:mm:ss）
	 * 
	 * @param timeMillis
	 *            长整型的日期
	 * @return
	 */

	public static String getTimeAsString(long timeMillis) {
		return getTimeAsString(timeMillis, "yyyy-MM-dd HH:mm:ss");
	}

	/***
	 * 
	 * 获取两个日期之间间隔天数
	 * 
	 * @param dateBegin
	 *            yyyy-MM-dd的日期开始日期
	 * 
	 * @param dateEnd
	 *            结束日期
	 * 
	 * @return
	 */

	public static String getTwoDay(String dateBegin, String dateEnd) {
		long day = 0L;
		Date date1 = strToDate(dateBegin);
		Date date2 = strToDate(dateEnd);
		day = (date2.getTime() - date1.getTime()) / 86400000L;
		return String.valueOf(day);
	}

	/***
	 * 
	 * 获取本周的第一天日期
	 * 
	 * @return
	 * 
	 */

	public static String getMondayOFWeek() {
		GregorianCalendar currentDate = new GregorianCalendar();
		int mondayPlus = getMondayPlus();
		currentDate.add(Calendar.DATE, mondayPlus);
		Date monday = currentDate.getTime();
		return dateToStr(monday);
	}

	/***
	 * 计算距离本周第一天的天数
	 * 
	 * @return
	 */

	private static int getMondayPlus() {
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (dayOfWeek == 0) {
			return -6;
		}
		return 1 - dayOfWeek;
	}

	/***
	 * 
	 * 获取本周周日的日期
	 * 
	 * @return
	 * 
	 */

	public static String getCurrentWeekday() {
		GregorianCalendar currentDate = new GregorianCalendar();
		int mondayPlus = getCurrentWeekdayPlus();
		currentDate.add(Calendar.DATE, mondayPlus);
		Date date = currentDate.getTime();
		return dateToStr(date);
	}

	/***
	 * 计算距离本周最后一天的天数
	 * 
	 * @return
	 * 
	 */

	private static int getCurrentWeekdayPlus() {
		GregorianCalendar currentDate = new GregorianCalendar();
		int dayOfWeek = currentDate.get(Calendar.DAY_OF_WEEK) - 1;
		if (dayOfWeek != 0) {
			return 7 - dayOfWeek;
		}
		return 0;
	}

	/***
	 * 
	 * 返回当月第一天的日期
	 * 
	 * @return
	 * 
	 */

	public static String getFirstDayOfMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DATE, 1);
		return dateToStr(calendar.getTime());
	}

	/***
	 * 
	 * 获取这个月最后一天的日期
	 * 
	 * @return
	 * 
	 */

	public static String getDefaultDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DATE, 1);
		calendar.add(Calendar.MONTH, 1);
		calendar.add(Calendar.DATE, -1);
		return dateToStr(calendar.getTime());
	}

	/***
	 * 获取上个月第一天的日期
	 * 
	 * @return
	 * 
	 */

	public static String getPreviousMonthFirst() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DATE, 1);
		calendar.add(Calendar.MONTH, -1);
		return dateToStr(calendar.getTime());
	}

	/***
	 * 获取上个月最后一天的日期
	 * 
	 * @return
	 * 
	 */

	public static String getPreviousMonthEnd() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DATE, 1);
		calendar.add(Calendar.DATE, -1);
		return dateToStr(calendar.getTime());
	}

	/***
	 * 
	 * 获取下个月第一天的日期
	 * 
	 * @return
	 * 
	 */

	public static String getNextMonthFirst() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DATE, 1);
		calendar.add(Calendar.MONTH, 1);
		return dateToStr(calendar.getTime());
	}

	/***
	 * 
	 * 获取下个月最后一天的日期
	 * 
	 * @return
	 * 
	 */

	public static String getNextMonthEnd() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DATE, 1);
		calendar.add(Calendar.MONTH, 2);
		calendar.add(Calendar.DATE, -1);
		return dateToStr(calendar.getTime());
	}

	/***
	 * 
	 * 获取今年第一天的日期
	 * 
	 * @return
	 * 
	 */

	public static String getCurrentYearFirst() {
		int yearPlus = getYearPlus();
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, yearPlus);
		return dateToStr(calendar.getTime());
	}

	/***
	 * 
	 * 相关年日期计算通用方法
	 * 
	 * @return
	 * 
	 */

	private static int getYearPlus() {
		Calendar cd = Calendar.getInstance();
		int yearOfNumber = cd.get(Calendar.DAY_OF_YEAR);
		cd.set(Calendar.DAY_OF_YEAR, 1);
		cd.roll(Calendar.DAY_OF_YEAR, -1);
		if (yearOfNumber == 1) {
			return 0;
		}
		return 1 - yearOfNumber;
	}

	/***
	 * 
	 * 获取今年最后一天的日期
	 * 
	 * @return
	 * 
	 */

	public static String getCurrentYearEnd() {
		String years = dateToStr(new Date(), "yyyy");
		return years + "-12-31";
	}

	/***
	 * 
	 * @return
	 * 
	 */

	public static String getPreviousYearFirst() {
		String years = dateToStr(new Date(), "yyyy");
		int years_value = Integer.parseInt(years);
		years_value--;
		return years_value + "-1-1";
	}

	/***
	 * 
	 * 获取去年最后一天的日期
	 * 
	 * @return
	 * 
	 */

	public static String getPreviousYearEnd() {
		String years = dateToStr(new Date(), "yyyy");
		int years_value = Integer.parseInt(years);
		years_value--;
		return years_value + "-12-31";
	}

	/***
	 * 
	 * 获取明年第一天的日期
	 * 
	 * @return
	 * 
	 */

	public static String getNextYearFirst() {
		String years = dateToStr(new Date(), "yyyy");
		int years_value = Integer.parseInt(years);
		years_value++;
		return years_value + "-1-1";
	}

	/***
	 * 
	 * 获取明年最后一天的日期
	 * 
	 * @return
	 * 
	 */

	public static String getNextYearEnd() {
		String years = dateToStr(new Date(), "yyyy");
		int years_value = Integer.parseInt(years);
		years_value++;
		return years_value + "-12-31";
	}

}