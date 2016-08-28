package com.yanlin.shop.user.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.yanlin.shop.user.vo.User;

/**
 * 用户模块持久层代码
 * 
 * @author yl.anin@qq.com
 * 
 */
@SuppressWarnings("all")
public class UserDao extends HibernateDaoSupport {

	/**
	 * 1.按名称查询是否有该用户
	 */
	public User findByUsername(String username) {
		
		String hql = "from User where username = ?";
		List<User> userList = this.getHibernateTemplate().find(hql, username);
		return userList != null && userList.size() > 0 ? userList.get(0) : null;
	}
	
	/**
	 * 2.注册用户的数据存入数据库的实现
	 */
	public void save(User user) {
		this.getHibernateTemplate().save(user);
	}
	
	/**
	 * 3.根据激活码查询是否存在该用户
	 */
	public User findByCode(String code) {
		String hql = "from User where code=?";
		List<User> userList = this.getHibernateTemplate().find(hql, code);
		return userList != null && userList.size() > 0 ? userList.get(0) : null;
	}
	/**
	 * 4.修改用户的状态
	 */
	public void update(User existUser) {
		this.getHibernateTemplate().update(existUser);
	}

	/**
	 * 5.根据用户名,密码,激活状态查询是否存在该用户,以实现用户的登录
	 */
	public User login(User user) {
		String hql = "from User where username=? and password=? and state=?";
		List<User> userList = this.getHibernateTemplate().find(hql, user.getUsername(),user.getPassword(),1);
		return userList != null && userList.size() > 0 ? userList.get(0) : null;
	}
}
