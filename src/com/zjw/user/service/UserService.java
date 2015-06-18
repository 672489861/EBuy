package com.zjw.user.service;

import com.zjw.domain.User;

/***
 * 
 * @Description: �û�ҵ���ӿ�
 * 
 * @author zjw
 * 
 * @create time 2015-6-16 ����03:27:05
 */
public interface UserService {

	/***
	 * 
	 * Description: ����û�
	 * 
	 * @create time 2015-6-16 ����03:33:35
	 * 
	 * @param user
	 * 
	 */
	public void saveUser(User user);

	/***
	 * 
	 * Description: �ж��û����Ƿ����
	 * 
	 * @create time 2015-6-16 ����03:33:47
	 * 
	 * @param userName
	 * @return
	 * 
	 */
	public boolean existUserWithUserName(String userName);
	
	/***
	 * 
	 * Description: ��ȡ��¼�û�
	 *         
	 * @create time 2015-6-16 ����11:07:13
	 *
	 * @param user
	 * @return       
	 *
	 */
	public User getUserWithLogin(User user);

}
