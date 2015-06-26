package com.zjw.common.util;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/***
 * 
 * @Description: gson������
 * 
 * @author zjw
 * 
 * @create time 2015-6-20 ����08:05:13
 */
public class GsonUtil {

	// ���������͵�ת������ ������Ĭ�ϵ��ֶ���ת������ ���ŵĴ�ӡ��ʽ
	private static Gson normalGson = new GsonBuilder().setPrettyPrinting()
			.setDateFormat("yyyy-MM-dd")
			.setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create();
	// ����Annotation��ע��ת��json
	private static Gson exposeGson = new GsonBuilder()
			.excludeFieldsWithoutExposeAnnotation().setPrettyPrinting()
			.setDateFormat("yyyy-MM-dd")
			.setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create();

	/***
	 * 
	 * Description: ͨ��gson������ת����json�ַ���
	 * 
	 * @create time 2015-6-20 ����08:17:44
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
	 * Description: ͨ��gson������ת����json�ַ���
	 * 
	 * @create time 2015-6-20 ����08:17:44
	 * 
	 * @param obj
	 * @return
	 * 
	 */
	public static String toJsonWithExposrGson(Object obj) {
		return exposeGson.toJson(obj);
	}
	
}
