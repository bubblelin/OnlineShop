package com.yanlin.shop.category.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yanlin.shop.category.dao.CategoryDao;
import com.yanlin.shop.category.vo.Category;

/**
 * 一级分类业务层的对象
 * @author bubblelin
 *
 */
@Transactional
public class CategoryService {
	//注入categoryDao
	private CategoryDao categoryDao;
	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}
	/**
	 * 业务层，一级分类查询所有数据的方法
	 * @return
	 */
	public List<Category> findAll() {
		
		return categoryDao.findAll();
	}
	/**
	 * 业务层，后台添加一级分类的方法
	 * @param category
	 */
	public void save(Category category) {
		categoryDao.save(category);
	}
	/**
	 * 业务层，根据cid查询一级分类
	 * @param cid
	 * @return
	 */
	public Category findByCid(Integer cid) {
		return categoryDao.findByCid(cid);
	}
	/**
	 * 删除一级分类的操作
	 * @param category
	 */
	public void delete(Category category) {
		categoryDao.delete(category);
	}
	/**
	 * 业务层，后台修改一级分类的方法
	 * @param _category
	 */
	public void update(Category category) {
		categoryDao.update(category);
	}
	
	
}
