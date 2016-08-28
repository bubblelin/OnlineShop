package com.yanlin.shop.interceptor;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.yanlin.shop.adminuser.vo.AdminUser;

/**
 * 后台权限检验的拦截器
 * @author bubblelin
 *
 */
public class PrivilegeInterceptor extends MethodFilterInterceptor{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
		//判断session中是否有保存的用户信息
		AdminUser existAdminUser = (AdminUser) ServletActionContext.getRequest().getSession().getAttribute("existAdminUser");
		if(existAdminUser == null){
			ActionSupport actionSupport = (ActionSupport) actionInvocation.getAction();
			actionSupport.addActionError("您没有权限访问，请先去登陆");
			return "loginFail";
		}else{
			return actionInvocation.invoke();
		}
	}

}
