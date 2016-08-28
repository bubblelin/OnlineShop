package com.yanlin.shop.product.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.yanlin.shop.product.vo.Product;
import com.yanlin.shop.util.PageHibernateCallback;

/**
 * 商品的持久层的代码
 * @author bubblelin
 *
 */
public class ProductDao extends HibernateDaoSupport{

	/**
	 * 持久层，查询product的热门分类商品,带有分页，降序
	 */
	@SuppressWarnings("unchecked")
	public List<Product> findHot() {
		//使用离线条件查询分页
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		criteria.add(Restrictions.eq("is_hot", 1));
		//倒序排序输出查询的数据
		criteria.addOrder(Order.desc("pdate"));
		List<Product> hProductList = this.getHibernateTemplate().findByCriteria(criteria, 0, 10);
		return hProductList;
	}

	/**
	 * 持久层，查询product的最新商品，降序
	 */
	@SuppressWarnings("unchecked")
	public List<Product> findNew() {
		//使用离线条件查询
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		//倒序排序输出查询
		criteria.addOrder(Order.desc("pdate"));
		List<Product> nProductList  = this.getHibernateTemplate().findByCriteria(criteria, 0, 10);
		return nProductList;
	}
	/**
	 * 持久層，根據id查詢商品
	 */
	public Product findByPid(Integer pid) {
		return this.getHibernateTemplate().get(Product.class, pid);
	}
	/**
	 * 根据一级分类cid查询商品的总记录数totalCount
	 * @param cid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Integer findCountCid(Integer cid) {
		String hql = "select count(*) from Product p where p.categorySecond.category.cid=?";
		List<Long> list = this.getHibernateTemplate().find(hql, cid);
		return list != null && list.size() > 0 ? list.get(0).intValue() : null;
	}
	/**
	 * 根据一级分类的cid和用户传入大的currentPage和设定的pageCount查询当前页的记录集listData
	 * @param cid
	 * @param currentCount
	 * @return
	 */
	public List<Product> findByCidPage(Integer cid, Integer currentCount,Integer pageCount) {
		//select p.* from category c,categorysecond cs,product p where p.csid=cs.csid and cs.cid=c.cid and c.cid=1;
		//select p from Category c,CategorySecond cs,Product p where p.categorySecond.csid = cs.csid and cs.category.cid = c.cid and c.cid = ?
		String hql = "select p from Product p join p.categorySecond cs join cs.category c where c.cid=?";
		//分页查询：简单的使用离线条件，这里用另一种写法
		List<Product> listData = this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, new Object[]{cid}, currentCount, pageCount));
		return listData != null && listData.size() > 0 ? listData : null;
	}
	/**
	 * 根据二级分类的csid查询商品的总记录数
	 * @param csid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Integer findCountCsid(Integer csid) {
		String hql = "select count(*) from Product p where p.categorySecond.csid=?";
		List<Long> list = this.getHibernateTemplate().find(hql, csid);
		return list != null && list.size() > 0 ? list.get(0).intValue() : null;
	}
	/**
	 * 根据二级分类的csid和用户传入的currentCount和设定的pageCount查询当前页的记录集listData
	 * @param csid
	 * @param currentPage
	 * @param pageCount
	 * @return
	 */
	public List<Product> findByCsidPage(Integer csid, Integer currentCount,
			Integer pageCount) {
		String hql = "select p from Product p join p.categorySecond cs where cs.csid=?";
		List<Product> listData = this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, new Object[]{csid}, currentCount, pageCount));
		return listData;
	}
	/**
	 * 持久层，后台查询商品总记录数的方法
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Integer findCount() {
		String hql = "select count(*) from Product";
		List<Long> list = this.getHibernateTemplate().find(hql);
		return list != null && list.size() > 0 ? list.get(0).intValue() : null;
	}

	/**
	 * 持久层，后台分页查询商品当前页的商品集
	 * @param currentCount
	 * @param pageCount
	 * @return
	 */
	public List<Product> findByPage(Integer currentCount, Integer pageCount) {
		String hql = "from Product order by pdate desc";
		List<Product> listData = this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, null, currentCount, pageCount));
		return listData;
	}

	/**
	 * 持久层，商品的添加操作的方法
	 * @param product
	 */
	public void save(Product product) {
		
		this.getHibernateTemplate().save(product);
	}

	/**
	 * 持久层，商品删除的操作
	 * @param product
	 */
	public void delete(Product product) {
		this.getHibernateTemplate().delete(product);
	}

	/**
	 * 持久层，商品修改的方法
	 * @param product
	 */
	public void update(Product product) {
		this.getHibernateTemplate().update(product);
	}


}
