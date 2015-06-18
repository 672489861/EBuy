package com.zjw.common.util;

/***
 * 
 * @Description: 分页工具类
 * 
 * @author zjw
 * 
 * @create time 2015-6-15 下午08:38:08
 */
public class PageUtil {

	/***
	 * 
	 * Description: 生成分页代码
	 * 
	 * @create time 2015-6-15 下午08:39:24
	 * 
	 * @param targetUrl
	 *            当前基本URL
	 * @param totalNum
	 *            总记录数
	 * @param currentPage
	 *            当前页
	 * @param pageSize
	 *            每页显示的记录数
	 * @param params
	 *            其余的请求参数
	 * @return
	 * 
	 */
	public static String getPagination(String targetUrl, int totalNum,
			int currentPage, int pageSize, String params) {
		int totalPage = totalNum % pageSize == 0 ? totalNum / pageSize
				: totalNum / pageSize + 1;
		if (totalPage == 0) {
			return "未查询到数据";
		} else {
			// 首页 上一页 1 2 3 4 5 下一页 尾页
			StringBuffer pageCode = new StringBuffer();
			pageCode.append("<li><a href='").append(targetUrl)
					.append("?page=1&").append(params).append("'>首页</a></li>");
			if (currentPage > 1) {
				pageCode.append("<li><a href='").append(targetUrl)
						.append("?page=").append(currentPage - 1).append("&")
						.append(params).append("'>上一页</a></li>");
			} else {
				pageCode.append("<li>上一页</li>");
			}
			for (int i = currentPage - 2; i <= currentPage + 2; i++) {
				if (i < 1 || i > totalPage) {
					continue;
				}
				if (i == currentPage) {
					pageCode.append("<li>").append(i).append("</li>");
				} else {
					pageCode.append("<li><a href='").append(targetUrl)
							.append("?page=").append(i).append("&")
							.append(params).append("'>").append(i)
							.append("</a></li>");
				}
			}
			if (currentPage < totalPage) {
				pageCode.append("<li><a href='").append(targetUrl)
						.append("?page=").append(currentPage + 1).append("&")
						.append(params).append("'>下一页</a></li>");
			} else {
				pageCode.append("<li>下一页</li>");
			}
			pageCode.append("<li><a href='").append(targetUrl).append("?page=")
					.append(totalPage).append("&").append(params)
					.append("'>尾页</a></li>");
			return pageCode.toString();
		}
	}
	

	/***
	 * 
	 * Description: 生成分页代码
	 * 
	 * @create time 2015-6-15 下午08:39:24
	 * 
	 * @param targetUrl
	 *            当前基本URL
	 * @param totalNum
	 *            总记录数
	 * @param currentPage
	 *            当前页
	 * @param pageSize
	 *            每页显示的记录数
	 * @return
	 * 
	 */
	public static String getPagination(String targetUrl, int totalNum,
			int currentPage, int pageSize) {
		int totalPage = totalNum % pageSize == 0 ? totalNum / pageSize
				: totalNum / pageSize + 1;
		if (totalPage == 0) {
			return "未查询到数据";
		} else {
			// 首页 上一页 1 2 3 4 5 下一页 尾页
			StringBuffer pageCode = new StringBuffer();
			pageCode.append("<li><a href='").append(targetUrl)
					.append("?page=1").append("'>首页</a></li>");
			if (currentPage > 1) {
				pageCode.append("<li><a href='").append(targetUrl)
						.append("?page=").append(currentPage - 1).append("'>上一页</a></li>");
			} else {
				pageCode.append("<li>上一页</li>");
			}
			for (int i = currentPage - 2; i <= currentPage + 2; i++) {
				if (i < 1 || i > totalPage) {
					continue;
				}
				if (i == currentPage) {
					pageCode.append("<li>").append(i).append("</li>");
				} else {
					pageCode.append("<li><a href='").append(targetUrl)
							.append("?page=").append(i).append("'>").append(i)
							.append("</a></li>");
				}
			}
			if (currentPage < totalPage) {
				pageCode.append("<li><a href='").append(targetUrl)
						.append("?page=").append(currentPage + 1).append("'>下一页</a></li>");
			} else {
				pageCode.append("<li>下一页</li>");
			}
			pageCode.append("<li><a href='").append(targetUrl).append("?page=")
					.append(totalPage)
					.append("'>尾页</a></li>");
			return pageCode.toString();
		}
	}
}
