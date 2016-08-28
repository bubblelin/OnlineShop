package com.yanlin.shop.adminuser.action;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.yanlin.shop.adminuser.service.AdminUserService;
import com.yanlin.shop.adminuser.vo.AdminUser;

/**
 * 后台用户的Action
 * @author bubblelin
 *
 */
public class AdminUserAction extends ActionSupport implements ModelDriven<AdminUser>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//获取模型驱动
	private AdminUser adminUser = new AdminUser();
	public AdminUser getModel() {
		return adminUser;
	}
	//获取service
	private AdminUserService adminUserService;
	public void setAdminUserService(AdminUserService adminUserService) {
		this.adminUserService = adminUserService;
	}
	
	/**
	 * 用户登陆的方法
	 */
	public String login(){
		AdminUser existAdminUser = adminUserService.login(adminUser);
		if(existAdminUser == null){
			//登陆失败
			this.addActionError("用户名或密码不对，请重试！");
			return "loginFail";
		}
		//登陆成功,把用户信息存放在session中
		ServletActionContext.getRequest().getSession().setAttribute("existAdminUser", existAdminUser);
		return "loginSuccess";
	}
}
