package com.yanlin.shop.category.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.yanlin.shop.category.vo.Category;

/**
 * 一级分类的持久层的对象
 * @author bubblelin
 *
 */
public class CategoryDao extends HibernateDaoSupport{

	/**
	 * 持久层，一级分类查询所有数据的方法
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Category> findAll() {
		String hql = "from Category";
		List<Category> cList = this.getHibernateTemplate().find(hql);
		return cList;
	}
	/**
	 * 持久层，后台添加一级分类
	 * @param category
	 */
	public void save(Category category) {
		this.getHibernateTemplate().save(category);
	}
	
	/**
	 * 持久层，根据cid查询一级分类
	 * @param cid
	 * @return
	 */
	public Category findByCid(Integer cid) {
		return this.getHibernateTemplate().get(Category.class, cid);
	}
	/**
	 * 持久层，删除一级分类的操作
	 * @param category
	 */
	public void delete(Category category) {
		this.getHibernateTemplate().delete(category);
	}
	/**
	 * 持久层，修改一级分类的方法
	 * @param _category
	 */
	public void update(Category category) {
		this.getHibernateTemplate().update(category);
	}

}
