package com.yanlin.shop.product.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yanlin.shop.product.dao.ProductDao;
import com.yanlin.shop.product.vo.Product;
import com.yanlin.shop.util.PageBean;

/**
 * 商品的业务层代码
 * 
 * @author bubblelin
 * 
 */
@Transactional
public class ProductService {
	// 注入Dao
	private ProductDao productDao;

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	/**
	 * 业务层，查询product的热门商品
	 */
	public List<Product> findHot() {
		return productDao.findHot();
	}

	/**
	 * 业务层，查询product的最新商品
	 * 
	 * @return
	 */
	public List<Product> findNew() {
		return productDao.findNew();
	}

	/**
	 * 業務層，根據pid查詢商品
	 * 
	 * @param pid
	 * @return
	 */
	public Product findByPid(Integer pid) {

		return productDao.findByPid(pid);
	}

	/**
	 * 根据用户传入的cid和currentPage查询商品
	 * 
	 * @param cid
	 * @param currentPage
	 * @return
	 */
	public PageBean<Product> findByCidPage(Integer cid, Integer currentPage) {
		PageBean<Product> pageBean = new PageBean<Product>();
		// 1.设置当前页，用户输入
		pageBean.setCurrentPage(currentPage);
		// 2.设置每页显示记录数，
		Integer pageCount = 8;
		pageBean.setPageCount(pageCount);
		// 3.设置总记录数，通过查询得到
		Integer totalCount = 0;
		totalCount = productDao.findCountCid(cid);
		pageBean.setTotalCount(totalCount);
		// 4.设置总页数，通过总记录数和每页显示记录数得到
		Integer totalPage = 0;
		totalPage = (totalCount % pageCount) == 0 ? totalCount / pageCount
				: totalCount / pageCount + 1;
		pageBean.setTotalPage(totalPage);
		// 5.每页显示的数据集
		Integer currentCount = (currentPage - 1) * pageCount;
		List<Product> listData = productDao.findByCidPage(cid, currentCount,
				pageCount);
		pageBean.setListData(listData);
		return pageBean;
	}

	/**
	 * g根据二级分类的csid查询商品
	 * 
	 * @param csid
	 * @param currentPage
	 * @return
	 */
	public PageBean<Product> findByCsidPage(Integer csid, Integer currentPage) {
		PageBean<Product> pageBean = new PageBean<Product>();
		// 1.设置当前页，用户输入
		pageBean.setCurrentPage(currentPage);
		// 2.设置每页显示记录数，
		Integer pageCount = 3;
		pageBean.setPageCount(pageCount);
		// 3.设置总记录数，通过查询得到
		Integer totalCount = 0;
		totalCount = productDao.findCountCsid(csid);
		pageBean.setTotalCount(totalCount);
		// 4.设置总页数，通过总记录数和每页显示记录数得到
		Integer totalPage = 0;
		totalPage = totalCount % pageCount == 0 ? totalCount / pageCount
				: totalCount / pageCount + 1;
		pageBean.setTotalPage(totalPage);
		// 5.每页显示的数据集
		Integer currentCount = (currentPage - 1) * pageCount;
		List<Product> listData = productDao.findByCsidPage(csid, currentCount,
				pageCount);
		pageBean.setListData(listData);
		return pageBean;
	}

	/**
	 * 业务层，后台分页查询所有商品的方法
	 * 
	 * @param currentPage
	 * @return
	 */
	public PageBean<Product> findAll(Integer currentPage) {
		PageBean<Product> pageBean = new PageBean<Product>();
		// 1.设置当前页
		pageBean.setCurrentPage(currentPage);
		// 2.设置每页的记录数
		Integer pageCount = 10;
		pageBean.setPageCount(pageCount);
		// 3.设置总记录数
		Integer totalCount = 0;
		totalCount = productDao.findCount();
		pageBean.setTotalCount(totalCount);
		// 4.设置总页数
		Integer totalPage = 0;
		totalPage = totalCount % pageCount == 0 ? totalCount / pageCount
				: totalCount / pageCount + 1;
		pageBean.setTotalPage(totalPage);
		// 5.设置每页显示集
		Integer currentCount = 0;
		currentCount = (currentPage - 1) * pageCount;
		List<Product> listData = productDao.findByPage(currentCount, pageCount);
		pageBean.setListData(listData);
		return pageBean;
	}

	/**
	 * 业务层，商品的添加操作方法
	 * 
	 * @param product
	 */
	public void save(Product product) {
		productDao.save(product);
	}

	public void delete(Product product) {
		productDao.delete(product);
	}

	/**
	 * 业务层，商品的修改方法
	 * @param product
	 */
	public void update(Product product) {
		productDao.update(product);
	}
}
