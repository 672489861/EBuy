package com.zjw.common.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

/***
 * 
 * @Description: JSON输出工具类
 * 
 * @author zjw
 * 
 * @create time 2015-6-16 下午04:25:28
 */

public class ResponseUtil {

	/***
	 * 
	 * Description: 向客户端输出JSON
	 * 
	 * @create time 2015-6-16 下午04:29:53
	 * 
	 * @param response
	 * @param o
	 * @throws IOException
	 * 
	 */
	public static void write(HttpServletResponse response, Object o)
			throws IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter writer = response.getWriter();
		writer.println(o.toString());
		writer.flush();
		writer.close();
	}

}
