package com.yanlin.shop.categorysecond.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import com.yanlin.shop.categorysecond.vo.CategorySecond;
import com.yanlin.shop.util.PageHibernateCallback;

/**
 * 业务层二級分類的service
 * @author bubblelin
 *
 */
public class CategorySecondDao extends HibernateDaoSupport{

	/**
	 * 持久层查询二级分类的记录数
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Integer findCount() {
		String hql = "select count(*) from CategorySecond";
		List<Long> list = this.getHibernateTemplate().find(hql);
		return list != null && list.size() > 0 ? list.get(0).intValue() : null;
	}

	/**
	 * 持久层，查询当前页的记录集
	 * @param currentCount
	 * @param pageCount
	 * @return
	 */
	public List<CategorySecond> findByPage(Integer currentCount,
			Integer pageCount) {
		String hql = "from CategorySecond order by csid desc";
		List<CategorySecond> listData = this.getHibernateTemplate().execute(new PageHibernateCallback<CategorySecond>(hql, null, currentCount, pageCount));
		return listData;
	}
	/**
	 * 持久层，保存后台用户提交的二级分类
	 * @param categorySecond
	 */
	public void save(CategorySecond categorySecond) {
		this.getHibernateTemplate().save(categorySecond);
	}
	/**
	 * 根据后台用户提交的csid查询二级分类,get(class,id)
	 * @param csid
	 * @return
	 */
	public CategorySecond findByCsid(Integer csid) {
		return this.getHibernateTemplate().get(CategorySecond.class, csid);
	}
	/**
	 * 删除后台用户请求删除的二级分类
	 * @param categorySecond
	 */
	public void delete(CategorySecond categorySecond) {
		this.getHibernateTemplate().delete(categorySecond);
	}
	/**
	 * 持久层，修改用户提交的二级分类
	 * @param categorySecond
	 */
	public void update(CategorySecond categorySecond) {
		this.getHibernateTemplate().update(categorySecond);
	}
	
	/**
	 * 持久层，后台查询所有的二级分类
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CategorySecond> findAll() {
		String hql = "from CategorySecond";
		List<CategorySecond> list = this.getHibernateTemplate().find(hql);
		return list;
	}
	
	
}
