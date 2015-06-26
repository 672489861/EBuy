package com.zjw.sys.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zjw.common.dao.BaseDAO;
import com.zjw.domain.User;
import com.zjw.sys.service.SysService;

/***
 * 
 * @Description: 系统管理业务层实现类
 * 
 * @author zjw
 * 
 * @create time 2015-6-24 下午04:20:28
 */

@Service("sysService")
public class SysServiceImpl implements SysService {

	@Resource(name = "baseDAO")
	private BaseDAO<User> baseDAO;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void modifyPassword(User user) {
		baseDAO.executeSQL("update t_user set password = " + user.getPassword()
				+ " where id = " + user.getId());
	}

}
