package com.zjw.common.util;

/***
 * 
 * @Description: ��ǰλ��(����)������
 * 
 * @author zjw
 * 
 * @create time 2015-6-15 ����11:27:04
 */
public class NavUtil {

	/***
	 * 
	 * Description: ���ɵ�ǰλ�ô���
	 * 
	 * @create time 2015-6-15 ����11:27:50
	 * 
	 * @param subName
	 *            ��ǰҳ������
	 * @return
	 * 
	 */
	public static String getNavCode(String subName) {
		StringBuffer navCode = new StringBuffer();
		navCode.append("��ǰλ��: ");
		navCode.append("<a href='index.jsp'>��ҳ</a>&nbsp;");
		navCode.append("&gt;");
		navCode.append(subName);
		return navCode.toString();
	}

}
