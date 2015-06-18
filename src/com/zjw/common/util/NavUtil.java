package com.zjw.common.util;

/***
 * 
 * @Description: 当前位置(导航)工具类
 * 
 * @author zjw
 * 
 * @create time 2015-6-15 下午11:27:04
 */
public class NavUtil {

	/***
	 * 
	 * Description: 生成当前位置代码
	 * 
	 * @create time 2015-6-15 下午11:27:50
	 * 
	 * @param subName
	 *            当前页的名称
	 * @return
	 * 
	 */
	public static String getNavCode(String subName) {
		StringBuffer navCode = new StringBuffer();
		navCode.append("当前位置: ");
		navCode.append("<a href='index.jsp'>首页</a>&nbsp;");
		navCode.append("&gt;");
		navCode.append(subName);
		return navCode.toString();
	}

}
