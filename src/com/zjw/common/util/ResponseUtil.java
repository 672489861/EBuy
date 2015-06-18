package com.zjw.common.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

/***
 * 
 * @Description: JSON���������
 * 
 * @author zjw
 * 
 * @create time 2015-6-16 ����04:25:28
 */

public class ResponseUtil {

	/***
	 * 
	 * Description: ��ͻ������JSON
	 * 
	 * @create time 2015-6-16 ����04:29:53
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
