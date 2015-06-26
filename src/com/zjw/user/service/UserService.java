package com.zjw.user.service;

import java.util.List;

import com.zjw.common.util.PageBean;
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

	/***
	 * 
	 * Description: 用户管理主界面查询
	 * 
	 * @create time 2015-6-20 下午08:23:42
	 * 
	 * @param user
	 * @param pageBean
	 * @return
	 * 
	 */
	public List<User> getUserList(User user, PageBean pageBean);

	/***
	 * 
	 * Description: 用户管理主界面记录数查询
	 * 
	 * @create time 2015-6-20 下午08:24:01
	 * 
	 * @param user
	 * @return
	 * 
	 */
	public long getUserListTotal(User user);

	/***
	 * 
	 * Description: 根据id查询用户
	 * 
	 * @create time 2015-6-22 下午01:49:28
	 * 
	 * @param id
	 * @return
	 * 
	 */
	public User getUserById(int id);

	/***
	 * 
	 * Description: 删除用户
	 * 
	 * @create time 2015-6-20 下午11:24:15
	 * 
	 * @param user
	 * 
	 */
	public void delete(User user);

	/***
	 * 
	 * Description: 后台添加或更新用户
	 * 
	 * @create time 2015-6-22 下午02:23:16
	 * 
	 * @param user
	 * 
	 */
	public void saveUserWithAdmin(User user);

}
