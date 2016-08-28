package com.yanlin.shop.adminuser.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.yanlin.shop.adminuser.vo.AdminUser;

/**
 * 后台用户的持久层代码
 * @author bubblelin
 *
 */
public class AdminUserDao extends HibernateDaoSupport{

	@SuppressWarnings("unchecked")
	public AdminUser login(AdminUser adminUser) {
		String hql = "from AdminUser where username=? and password=?";
		List<AdminUser> adminUserList = this.getHibernateTemplate().find(hql, adminUser.getUsername(),adminUser.getPassword());
		return adminUserList != null && adminUserList.size() > 0 ? adminUserList.get(0) : null;
	}

}
