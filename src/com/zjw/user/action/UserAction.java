package com.zjw.user.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.zjw.common.action.BaseAction;
import com.zjw.common.constant.Constants;
import com.zjw.common.util.GsonUtil;
import com.zjw.common.util.NavUtil;
import com.zjw.common.util.PageBean;
import com.zjw.common.util.ResponseUtil;
import com.zjw.domain.User;
import com.zjw.user.service.UserService;

/***
 * 
 * @Description: �û�Action
 * 
 * @author zjw
 * 
 * @create time 2015-6-16 ����04:10:21
 */

@Controller
public class UserAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource(name = "userService")
	private UserService userService;

	private String userName; // �û��� ajax��֤
	private User user; // ��װ�û���Ϣ
	private String error; // ������Ϣ
	private String imageCode; // ��֤��
	private String time; // ���ɲ�ͬ����֤��
	private String navCode; // ����
	private String mainPage; // ��ҳ��
	private String page; // �û������ҳ����
	private User s_user; // ��̨�û������ѯ��װ

	public User getS_user() {
		return s_user;
	}

	public void setS_user(User s_user) {
		this.s_user = s_user;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

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
	 * Description: ע���û�
	 * 
	 * @create time 2015-6-16 ����04:19:32
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
	 * Description: ajax��֤�û����Ƿ����
	 * 
	 * @create time 2015-6-16 ����04:24:36
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
	 * Description: ��ת��¼����
	 * 
	 * @create time 2015-6-16 ����10:09:26
	 * 
	 * 
	 */
	public String toLogin() {
		return "toLogin_success";
	}

	/***
	 * 
	 * Description: ����ͼ����֤��
	 * 
	 * @create time 2015-6-16 ����10:38:11
	 * 
	 * @return
	 * 
	 */
	public String createCheckCode() {
		return "toImage_success";
	}

	/***
	 * 
	 * Description: �û���¼
	 * 
	 * @create time 2015-6-16 ����10:45:38
	 * 
	 * @return
	 * 
	 */

	public String login() {
		User currentUser = userService.getUserWithLogin(user);
		// �ж��û��������Ƿ���ȷ
		if (currentUser == null) {
			error = "�û������������";
			// ��̨����Ա��¼�ж�
			if (user.getStatus() == 2) {
				return "adminLogin_Error";
			} else {
				return ERROR;
			}
		}
		// �ж���֤���Ƿ���ȷ
		String checkCode = (String) session.get("checkCode");
		if (!checkCode.equals(imageCode)) {
			error = "��֤�����";
			if (user.getStatus() == 2) {
				return "adminLogin_Error";
			} else {
				return ERROR;
			}
		}
		session.put("currentUser", currentUser);
		// ����Ա��¼ֱ����ת����̨,��ͨ�û���ת����ҳ
		return currentUser.getStatus() == 2 ? "adminLogin_success"
				: "login_success";
	}

	/***
	 * 
	 * Description: ע������
	 * 
	 * @create time 2015-6-16 ����11:40:23
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
	 * Description: ע������
	 * 
	 * @create time 2015-6-16 ����11:40:23
	 * 
	 * @return
	 * 
	 */

	public String adminLoginOut() {
		session.clear();
		return "adminLogin_out";
	}
	
	/***
	 * 
	 * Description: �û���������
	 * 
	 * @create time 2015-6-17 ����10:59:18
	 * 
	 * @return
	 * 
	 */
	public String userCenter() {
		navCode = NavUtil.getNavCode("��������");
		mainPage = "/WEB-INF/views/userCenter/userInfo.jsp";
		return "userCenter";
	}

	/***
	 * 
	 * Description: ��ת�޸Ľ���
	 * 
	 * @create time 2015-6-17 ����11:23:30
	 * 
	 * @return
	 * 
	 */
	public String preSave() {
		navCode = NavUtil.getNavCode("��������");
		mainPage = "/WEB-INF/views/userCenter/userSave.jsp";
		return "userCenter";
	}

	/***
	 * 
	 * Description: �������ĸ����û�
	 * 
	 * @create time 2015-6-17 ����11:29:27
	 * 
	 * @return
	 * 
	 */

	public String save() {
		userService.saveUser(user);
		session.put("currentUser", user); // ����Session
		navCode = NavUtil.getNavCode("��������");
		mainPage = "/WEB-INF/views/userCenter/userInfo.jsp";
		return "userCenter";
	}

	/***
	 * 
	 * Description: ��ת����Ա��¼����
	 * 
	 * @create time 2015-6-18 ����02:52:29
	 * 
	 * @return
	 * 
	 */
	public String toAdminLogin() {
		return "toAdminLogin_success";
	}

	/***
	 * 
	 * Description: ��ת�û��������
	 * 
	 * @create time 2015-6-20 ����02:41:18
	 * 
	 * @return
	 * 
	 */

	public String toUserManager() {
		return "toUserManager_success";
	}

	/***
	 * 
	 * Description: �û������������ѯ
	 * 
	 * @throws IOException
	 * 
	 * @create time 2015-6-20 ����07:56:10
	 * 
	 * 
	 */

	public void list() throws IOException {
		PageBean pageBean = new PageBean(Integer.parseInt(page),
				Constants.ADMINMANAGER_SIZE);
		List<User> users = userService.getUserList(s_user, pageBean);
		long total = userService.getUserListTotal(s_user);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("total", total);
		resultMap.put("rows", users);
		ResponseUtil.write(ServletActionContext.getResponse(),
				GsonUtil.toJsonWithExposrGson(resultMap));
	}

	/***
	 * 
	 * Description: ɾ���û�
	 * 
	 * @throws IOException
	 * 
	 * @create time 2015-6-20 ����11:19:30
	 * 
	 * 
	 */
	public void delete() throws IOException {
		userService.delete(user);
		JSONObject object = new JSONObject();
		object.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(),
				object.toString());
	}

	/***
	 * 
	 * Description: ��ת�û��޸�ɾ������
	 * 
	 * @create time 2015-6-20 ����11:48:13
	 * 
	 * @return
	 * 
	 */
	public String toUserUpdate() {
		if (user.getId() != 0) {
			user = userService.getUserById(user.getId());
		}
		return "toUserUpdate_success";
	}

	/***
	 * 
	 * Description: ��̨��ӻ�����û�
	 * 
	 * @throws IOException
	 * 
	 * @create time 2015-6-22 ����02:22:27
	 * 
	 * 
	 */
	public void saveUser() throws IOException {
		userService.saveUserWithAdmin(user);
		JSONObject object = new JSONObject();
		object.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(),
				object.toString());
	}

}
