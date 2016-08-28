package com.yanlin.shop.user.action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.yanlin.shop.user.service.UserService;
import com.yanlin.shop.user.vo.User;
/**
 * 用户模块Action的类
 * @author yl.anin@qq.com
 *
 */
public class UserAction extends ActionSupport implements ModelDriven<User>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String passeord;
	private String repassword;
	private String email;
	private String phone;
	
	public String getPassword() {
		return passeord;
	}
	public void setPassword(String passeord) {
		this.passeord = passeord;
	}
	public String getRepassword() {
		return repassword;
	}
	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	//===================================================
	//模型驱动使用的对象
	private User user = new User();
	public User getModel() {
		return user;
	}
	//注入UserService
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	//=====================================================
	//获取验证码
	private String checkcode;
	
	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}
	
	/**
	 * 1.跳转regist页面的执行方法
	 * @return
	 */
	public String registPage(){
		return "registPage";
	}
	
	/**
	 * 2.AJAX进行异步校验用户名的执行方法
	 * @return
	 * @throws IOException 
	 */
	public String findByName() throws IOException{
		//调用service进行查询
		User existUser = userService.findByUsername(user.getUsername());
		//获得response对象,输出页面
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		if(existUser != null){
			response.getWriter().println("<font color='red'>用户名已经存在</font>");
		}else{
			response.getWriter().println("<font color='green'>用户名可以用</font>");
		}
		
		return NONE;
	}
	
	/**
	 * 3.表单提交校验的执行方法
	 */
	public String regist(){
		//从session中获取验证码,判断验证码
		String checkcode1 = (String) ActionContext.getContext().getSession().get("checkcode");
		if(!this.checkcode.equalsIgnoreCase(checkcode1)){
			this.addActionError("验证码输入错误,请重试!");
			return "checkcodeFail";
		}
		userService.save(user);
		this.addActionMessage("注册成功!请去邮箱激活");
		return "msg";
	}
	
	/**
	 * 4.用户激活的执行方法
	 */
	public String active(){
		User existUser = userService.findByCode(user.getCode());
		if(existUser == null){
			this.addActionMessage("抱歉,激活失败!请输入正确的激活码!");
		}else{
			existUser.setState(1);
			existUser.setCode(null);
			userService.update(existUser);
			this.addActionMessage("恭喜!激活成功,请去登录!");
		}
		return "msg";
	}
	
	/**
	 * 5.跳转到登录页面的执行方法
	 */
	public String loginPage(){
		return "loginPage";
	}
	
	/**
	 * 6.用户登录的执行方法
	 */
	public String login(){
		User existUser = userService.login(user);
		if(existUser == null){
			this.addActionMessage("抱歉,登录失败,用户未激活!");
			return LOGIN;
		}else{
			//登录成功,并将用户信息存放在session中
			ActionContext.getContext().getSession().put("existUser", existUser);
			//ServletActionContext.getRequest().getSession().setAttribute("existUser", existUser);
			return "loginSuccess";
		}
	}
	
	/**
	 * 7.用户退出的执行方法
	 */
	public String quit(){
		ActionContext.getContext().getSession().remove("existUser");
		//ServletActionContext.getRequest().getSession().invalidate();
		return "quit";
	}
}
