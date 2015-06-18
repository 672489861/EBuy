package com.zjw.user.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zjw.common.dao.BaseDAO;
import com.zjw.domain.User;
import com.zjw.user.service.UserService;

/***
 * 
 * @Description: 用户业务层实现类
 * 
 * @author zjw
 * 
 * @create time 2015-6-16 下午03:27:41
 */
@Service("userService")
public class UserServiceImpl implements UserService {

	@Resource
	private BaseDAO<User> baseDAO;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveUser(User user) {
		baseDAO.merge(user);
	}

	@Override
	@Transactional(readOnly = true)
	public boolean existUserWithUserName(String userName) {
		String hql = "select count(1) from User where userName = ?";
		long count = baseDAO.count(hql, new Object[] { userName });
		return count > 0 ? true : false;
	}

	@Override
	@Transactional(readOnly = true)
	public User getUserWithLogin(User user) {
		String hql = "from User where userName = ? and password = ?";
		return (User) baseDAO.get(hql,
				new Object[] { user.getUserName(), user.getPassword() });
	}

}
