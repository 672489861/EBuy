package com.zjw.user.service;

import com.zjw.domain.User;

/***
 * 
 * @Description: 用户业务层接口
 * 
 * @author zjw
 * 
 * @create time 2015-6-16 下午03:27:05
 */
public interface UserService {

	/***
	 * 
	 * Description: 添加用户
	 * 
	 * @create time 2015-6-16 下午03:33:35
	 * 
	 * @param user
	 * 
	 */
	public void saveUser(User user);

	/***
	 * 
	 * Description: 判断用户名是否存在
	 * 
	 * @create time 2015-6-16 下午03:33:47
	 * 
	 * @param userName
	 * @return
	 * 
	 */
	public boolean existUserWithUserName(String userName);
	
	/***
	 * 
	 * Description: 获取登录用户
	 *         
	 * @create time 2015-6-16 下午11:07:13
	 *
	 * @param user
	 * @return       
	 *
	 */
	public User getUserWithLogin(User user);

}
