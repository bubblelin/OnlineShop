package com.yanlin.shop.categorysecond.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yanlin.shop.categorysecond.dao.CategorySecondDao;
import com.yanlin.shop.categorysecond.vo.CategorySecond;
import com.yanlin.shop.util.PageBean;

/**
 * 持久层二级分类的代码
 * @author bubblelin
 *
 */
@Transactional
public class CategorySecondService {
	//注入dao
	private CategorySecondDao categorySecondDao;
	public void setCategorySecondDao(CategorySecondDao categorySecondDao) {
		this.categorySecondDao = categorySecondDao;
	}
	
	/**
	 * 业务层，查询二级分类的方法
	 * @param currentPage
	 * @return
	 */
	public PageBean<CategorySecond> findByPage(Integer currentPage) {
		PageBean<CategorySecond> pageBean = new PageBean<CategorySecond>();
		//1.设置当前页面
		pageBean.setCurrentPage(currentPage);
		//2.设置每页显示记录数
		Integer pageCount = 10;
		pageBean.setPageCount(pageCount);
		//3.设置总记录数
		Integer totalCount = 0;
		totalCount = categorySecondDao.findCount();
		pageBean.setTotalCount(totalCount);
		//4.设置总页数
		Integer totalPage = 0;
		totalPage = totalCount % pageCount == 0 ? totalCount / pageCount : totalCount / pageCount + 1;
		pageBean.setTotalPage(totalPage);
		//5.设置每页的显示集
		Integer currentCount = (currentPage - 1) * pageCount;
		List<CategorySecond> listData = categorySecondDao.findByPage(currentCount ,pageCount);
		pageBean.setListData(listData );
		return pageBean;
	}
	/**
	 * 业务层，保存后台用户提交二级分类
	 * @param categorySecond
	 */
	public void save(CategorySecond categorySecond) {
		categorySecondDao.save(categorySecond);
	}
	/**
	 * 业务层，根据后台用户提交的csid查询出二级分类
	 * @param csid
	 * @return
	 */
	public CategorySecond findByCsid(Integer csid) {
		return categorySecondDao.findByCsid(csid);
	}
	/**
	 * 业务层，删除后台用户请求的二级分类
	 * @param categorySecond
	 */
	public void delete(CategorySecond categorySecond) {
		categorySecondDao.delete(categorySecond);
	}
	/**
	 * 业务层，修改用户提交的二级分类
	 * @param categorySecond
	 */
	public void update(CategorySecond categorySecond) {
		categorySecondDao.update(categorySecond);
	}
	
	/**
	 * 业务层，后台查询所有的二级分类
	 * @return
	 */
	public List<CategorySecond> findAll() {
		return categorySecondDao.findAll();
	}


}
