package com.zjw.common.util;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/***
 * 
 * @Description: gson工具类
 * 
 * @author zjw
 * 
 * @create time 2015-6-20 下午08:05:13
 */
public class GsonUtil {

	// 设置日期型的转换方案 并设置默认的字段名转换规则 优雅的打印格式
	private static Gson normalGson = new GsonBuilder().setPrettyPrinting()
			.setDateFormat("yyyy-MM-dd")
			.setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create();
	// 根据Annotation标注来转换json
	private static Gson exposeGson = new GsonBuilder()
			.excludeFieldsWithoutExposeAnnotation().setPrettyPrinting()
			.setDateFormat("yyyy-MM-dd")
			.setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create();

	/***
	 * 
	 * Description: 通过gson将对象转换成json字符串
	 * 
	 * @create time 2015-6-20 下午08:17:44
	 * 
	 * @param obj
	 * @return
	 * 
	 */
	public static String toJsonWithNormalGson(Object obj) {
		return normalGson.toJson(obj);
	}
	
	/***
	 * 
	 * Description: 通过gson将对象转换成json字符串
	 * 
	 * @create time 2015-6-20 下午08:17:44
	 * 
	 * @param obj
	 * @return
	 * 
	 */
	public static String toJsonWithExposrGson(Object obj) {
		return exposeGson.toJson(obj);
	}
	
}
