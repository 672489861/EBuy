package com.zjw.user.service;

import java.util.List;

import com.zjw.common.util.PageBean;
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

	/***
	 * 
	 * Description: �û������������ѯ
	 * 
	 * @create time 2015-6-20 ����08:23:42
	 * 
	 * @param user
	 * @param pageBean
	 * @return
	 * 
	 */
	public List<User> getUserList(User user, PageBean pageBean);

	/***
	 * 
	 * Description: �û������������¼����ѯ
	 * 
	 * @create time 2015-6-20 ����08:24:01
	 * 
	 * @param user
	 * @return
	 * 
	 */
	public long getUserListTotal(User user);

	/***
	 * 
	 * Description: ����id��ѯ�û�
	 * 
	 * @create time 2015-6-22 ����01:49:28
	 * 
	 * @param id
	 * @return
	 * 
	 */
	public User getUserById(int id);

	/***
	 * 
	 * Description: ɾ���û�
	 * 
	 * @create time 2015-6-20 ����11:24:15
	 * 
	 * @param user
	 * 
	 */
	public void delete(User user);

	/***
	 * 
	 * Description: ��̨��ӻ�����û�
	 * 
	 * @create time 2015-6-22 ����02:23:16
	 * 
	 * @param user
	 * 
	 */
	public void saveUserWithAdmin(User user);

}
