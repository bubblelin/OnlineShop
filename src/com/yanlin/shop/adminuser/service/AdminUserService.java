package com.yanlin.shop.adminuser.service;

import org.springframework.transaction.annotation.Transactional;

import com.yanlin.shop.adminuser.dao.AdminUserDao;
import com.yanlin.shop.adminuser.vo.AdminUser;

/**
 * 后台用户的业务层代码
 * @author bubblelin
 *
 */
@Transactional
public class AdminUserService {

	//获取dao
	private AdminUserDao adminUserDao;
	public void setAdminUserDao(AdminUserDao adminUserDao) {
		this.adminUserDao = adminUserDao;
	}
	
	/**
	 * 业务层查询用户是否存在
	 * @param adminUser
	 * @return
	 */
	public AdminUser login(AdminUser adminUser) {
		return adminUserDao.login(adminUser);
	}
}
