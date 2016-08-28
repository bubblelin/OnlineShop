package com.yanlin.shop.user.service;

import java.io.Serializable;

import org.springframework.transaction.annotation.Transactional;

import com.yanlin.shop.user.dao.UserDao;
import com.yanlin.shop.user.vo.User;
import com.yanlin.shop.util.MailUitls;
import com.yanlin.shop.util.UUIDUtils;
/**
 * 用户模块业务层代码
 * @author yl.anin@qq.com
 *
 */
@Transactional
public class UserService{
	// 注入UserDao
	private UserDao userDao;
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	/**
	 * 1.按用户名查询用户的方法
	 */
	public User findByUsername(String uerename) {
		return userDao.findByUsername(uerename);
	}
	
	/**
	 * 2.业务层完成用户的注册
	 */
	public void save(User user) {
		user.setState(0);//0表示激活状态
		String code = UUIDUtils.getUUID()+UUIDUtils.getUUID();
		user.setCode(code);
		userDao.save(user);
		//发送激活邮件
		MailUitls.sendMail(user.getEmail(), code);
	}
	/**
	 * 3.根据激活码查询用户的方法
	 */
	public User findByCode(String code) {
		return userDao.findByCode(code);
	}
	
	/**
	 * 4.修改用户的状态的方法
	 */
	public void update(User existUser) {
		userDao.update(existUser);
	}

	/**
	 * 5.用户登录的方法
	 */
	public User login(User user) {
		return userDao.login(user);
	}

}
