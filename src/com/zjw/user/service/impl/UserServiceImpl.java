package com.zjw.user.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.zjw.common.dao.BaseDAO;
import com.zjw.common.util.PageBean;
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
		if (user.getStatus() == 2) {
			hql += " and status = 2";
		}
		return (User) baseDAO.get(hql,
				new Object[] { user.getUserName(), user.getPassword() });
	}

	@Override
	@Transactional(readOnly = true)
	public List<User> getUserList(User user, PageBean pageBean) {
		List<Object> params = new ArrayList<Object>();
		StringBuffer hql = new StringBuffer("from User ");
		if (user != null) {
			// 根据用户名查询
			if (StringUtils.hasText(user.getUserName())) {
				hql.append("and userName like ? ");
				params.add("%" + user.getUserName() + "%");
			}
		}
		return baseDAO.find(hql.toString().replaceFirst("and", "where"),
				params, pageBean);
	}

	@Override
	@Transactional(readOnly = true)
	public long getUserListTotal(User user) {
		List<Object> params = new ArrayList<Object>();
		StringBuffer hql = new StringBuffer("select count(1) from User ");
		if (user != null) {
			// 根据用户名查询
			if (StringUtils.hasText(user.getUserName())) {
				hql.append("and userName like ? ");
				params.add("%" + user.getUserName() + "%");
			}
		}
		return baseDAO.count(hql.toString().replaceFirst("and", "where"),
				params);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delete(User user) {
		user = baseDAO.get(User.class, user.getId());
		baseDAO.delete(user);
	}

	@Override
	@Transactional(readOnly = true)
	public User getUserById(int id) {
		return baseDAO.get(User.class, id);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveUserWithAdmin(User user) {
		baseDAO.merge(user);
	}

}
