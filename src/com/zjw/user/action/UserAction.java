package com.zjw.user.action;

import java.io.IOException;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.zjw.common.action.BaseAction;
import com.zjw.common.util.NavUtil;
import com.zjw.common.util.ResponseUtil;
import com.zjw.domain.User;
import com.zjw.user.service.UserService;

/***
 * 
 * @Description: 用户Action
 * 
 * @author zjw
 * 
 * @create time 2015-6-16 下午04:10:21
 */

@Controller
public class UserAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource(name = "userService")
	private UserService userService;

	private String userName; // 用户名 ajax验证
	private User user; // 封装要添加的用户信息
	private String error; // 错误信息
	private String imageCode; // 验证码
	private String time; // 生成不同的验证码
	private String navCode; // 导航
	private String mainPage; // 主页面

	public String getNavCode() {
		return navCode;
	}

	public void setNavCode(String navCode) {
		this.navCode = navCode;
	}

	public String getMainPage() {
		return mainPage;
	}

	public void setMainPage(String mainPage) {
		this.mainPage = mainPage;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getImageCode() {
		return imageCode;
	}

	public void setImageCode(String imageCode) {
		this.imageCode = imageCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	/***
	 * 
	 * Description: 注册用户
	 * 
	 * @create time 2015-6-16 下午04:19:32
	 * 
	 * @return
	 * 
	 */
	public String register() {
		userService.saveUser(user);
		return "register_success";
	}

	/***
	 * 
	 * Description: ajax验证用户名是否存在
	 * 
	 * @create time 2015-6-16 下午04:24:36
	 * 
	 * @return
	 * @throws IOException
	 * 
	 */

	public void existUserWithUserName() throws IOException {
		boolean exist = userService.existUserWithUserName(userName);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("exist", exist);
		ResponseUtil.write(ServletActionContext.getResponse(),
				jsonObject.toString());
	}

	/***
	 * 
	 * Description: 跳转登录界面
	 * 
	 * @create time 2015-6-16 下午10:09:26
	 * 
	 * 
	 */
	public String toLogin() {
		return "toLogin_success";
	}

	/***
	 * 
	 * Description: 创建图像验证码
	 * 
	 * @create time 2015-6-16 下午10:38:11
	 * 
	 * @return
	 * 
	 */
	public String createCheckCode() {
		return "toImage_success";
	}

	/***
	 * 
	 * Description: 用户登录
	 * 
	 * @create time 2015-6-16 下午10:45:38
	 * 
	 * @return
	 * 
	 */

	public String login() {
		User currentUser = userService.getUserWithLogin(user);
		// 判断用户名密码是否正确
		if (currentUser == null) {
			error = "用户名或密码错误！";
			// 后台管理员登录判断
			if (user.getStatus() == 2) {
				return "adminLogin_Error";
			} else {
				return ERROR;
			}
		}
		// 判断验证码是否正确
		String checkCode = (String) session.get("checkCode");
		if (!checkCode.equals(imageCode)) {
			error = "验证码错误";
			if (user.getStatus() == 2) {
				return "adminLogin_Error";
			} else {
				return ERROR;
			}
		}
		session.put("currentUser", currentUser);
		// 管理员登录直接跳转到后台,普通用户跳转到首页
		return currentUser.getStatus() == 2 ? "adminLogin_success"
				: "login_success";
	}

	/***
	 * 
	 * Description: 注销操作
	 * 
	 * @create time 2015-6-16 下午11:40:23
	 * 
	 * @return
	 * 
	 */

	public String loginOut() {
		session.clear();
		return "login_success";
	}

	/***
	 * 
	 * Description: 用户个人中心
	 * 
	 * @create time 2015-6-17 下午10:59:18
	 * 
	 * @return
	 * 
	 */
	public String userCenter() {
		navCode = NavUtil.getNavCode("个人中心");
		mainPage = "/WEB-INF/views/userCenter/userInfo.jsp";
		return "userCenter";
	}

	/***
	 * 
	 * Description: 跳转修改界面
	 * 
	 * @create time 2015-6-17 下午11:23:30
	 * 
	 * @return
	 * 
	 */
	public String preSave() {
		navCode = NavUtil.getNavCode("个人中心");
		mainPage = "/WEB-INF/views/userCenter/userSave.jsp";
		return "userCenter";
	}

	/***
	 * 
	 * Description: 更新用户
	 * 
	 * @create time 2015-6-17 下午11:29:27
	 * 
	 * @return
	 * 
	 */

	public String save() {
		userService.saveUser(user);
		session.put("currentUser", user); // 更新Session
		navCode = NavUtil.getNavCode("个人中心");
		mainPage = "/WEB-INF/views/userCenter/userInfo.jsp";
		return "userCenter";
	}

}
