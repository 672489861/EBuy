package com.zjw.common.util;

/***
 * 
 * @Description: ��ҳ������
 * 
 * @author zjw
 * 
 * @create time 2015-6-15 ����08:38:08
 */
public class PageUtil {

	/***
	 * 
	 * Description: ���ɷ�ҳ����
	 * 
	 * @create time 2015-6-15 ����08:39:24
	 * 
	 * @param targetUrl
	 *            ��ǰ����URL
	 * @param totalNum
	 *            �ܼ�¼��
	 * @param currentPage
	 *            ��ǰҳ
	 * @param pageSize
	 *            ÿҳ��ʾ�ļ�¼��
	 * @param params
	 *            ������������
	 * @return
	 * 
	 */
	public static String getPagination(String targetUrl, int totalNum,
			int currentPage, int pageSize, String params) {
		int totalPage = totalNum % pageSize == 0 ? totalNum / pageSize
				: totalNum / pageSize + 1;
		if (totalPage == 0) {
			return "δ��ѯ������";
		} else {
			// ��ҳ ��һҳ 1 2 3 4 5 ��һҳ βҳ
			StringBuffer pageCode = new StringBuffer();
			pageCode.append("<li><a href='").append(targetUrl)
					.append("?page=1&").append(params).append("'>��ҳ</a></li>");
			if (currentPage > 1) {
				pageCode.append("<li><a href='").append(targetUrl)
						.append("?page=").append(currentPage - 1).append("&")
						.append(params).append("'>��һҳ</a></li>");
			} else {
				pageCode.append("<li>��һҳ</li>");
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
						.append(params).append("'>��һҳ</a></li>");
			} else {
				pageCode.append("<li>��һҳ</li>");
			}
			pageCode.append("<li><a href='").append(targetUrl).append("?page=")
					.append(totalPage).append("&").append(params)
					.append("'>βҳ</a></li>");
			return pageCode.toString();
		}
	}
	

	/***
	 * 
	 * Description: ���ɷ�ҳ����
	 * 
	 * @create time 2015-6-15 ����08:39:24
	 * 
	 * @param targetUrl
	 *            ��ǰ����URL
	 * @param totalNum
	 *            �ܼ�¼��
	 * @param currentPage
	 *            ��ǰҳ
	 * @param pageSize
	 *            ÿҳ��ʾ�ļ�¼��
	 * @return
	 * 
	 */
	public static String getPagination(String targetUrl, int totalNum,
			int currentPage, int pageSize) {
		int totalPage = totalNum % pageSize == 0 ? totalNum / pageSize
				: totalNum / pageSize + 1;
		if (totalPage == 0) {
			return "δ��ѯ������";
		} else {
			// ��ҳ ��һҳ 1 2 3 4 5 ��һҳ βҳ
			StringBuffer pageCode = new StringBuffer();
			pageCode.append("<li><a href='").append(targetUrl)
					.append("?page=1").append("'>��ҳ</a></li>");
			if (currentPage > 1) {
				pageCode.append("<li><a href='").append(targetUrl)
						.append("?page=").append(currentPage - 1).append("'>��һҳ</a></li>");
			} else {
				pageCode.append("<li>��һҳ</li>");
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
						.append("?page=").append(currentPage + 1).append("'>��һҳ</a></li>");
			} else {
				pageCode.append("<li>��һҳ</li>");
			}
			pageCode.append("<li><a href='").append(targetUrl).append("?page=")
					.append(totalPage)
					.append("'>βҳ</a></li>");
			return pageCode.toString();
		}
	}
}
