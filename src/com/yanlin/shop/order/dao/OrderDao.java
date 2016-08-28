package com.yanlin.shop.order.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.yanlin.shop.order.vo.Order;
import com.yanlin.shop.order.vo.OrderItem;
import com.yanlin.shop.util.PageHibernateCallback;

/**
 * 订单的持久层代码
 * @author bubblelin
 *
 */
public class OrderDao extends HibernateDaoSupport{

	/**
	 * 持久层，订单的保存
	 */
	public void save(Order order) {
		this.getHibernateTemplate().save(order);
	}
	
	/**
	 * 根据用户的uid查询订单的总记录数
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Integer findByCountUid(Integer uid) {
		String hql = "select count(*) from Order o where o.user.uid=?";
		List<Long> list = this.getHibernateTemplate().find(hql,uid);
		return list != null && list.size() > 0 ?list.get(0).intValue() : null;
	}

	/**
	 * 根据用户的uid和currentCount以及pageCount查询当前页的记录集
	 * @param uid
	 * @param currentCount
	 * @param pageCount
	 * @return
	 */
	public List<Order> findByUidPage(Integer uid, Integer currentCount,
			Integer pageCount) {
		String hql = "from Order o where o.user.uid=? order by ordertime desc";
		List<Order> listData = this.getHibernateTemplate().execute(new PageHibernateCallback<Order>(hql, new Object[]{uid}, currentCount, pageCount));
		return listData != null && listData.size() > 0 ?listData : null;
	}
	/**
	 * 持久层根据oid查询获取订单
	 * @param oid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Order findByOid(Integer oid) {
		String hql = "from Order where oid=?";
		List<Order> orderList = this.getHibernateTemplate().find(hql, oid);
		return orderList != null && orderList.size() > 0 ? orderList.get(0) : null;
	}
	
	/**
	 * 持久层，修改要付款的订单
	 * @param currOrder
	 */
	public void update(Order currOrder) {
		this.getHibernateTemplate().update(currOrder);
	}
	/**
	 * 持久层查询订单总数的方法
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Integer findCount() {
		String hql = "select count(*) from Order";
		List<Long> list = this.getHibernateTemplate().find(hql);
		return list != null && list.size() > 0 ? list.get(0).intValue() : null;
	}

	/**
	 * 持久层，查村订单的每页显示记录集的方法
	 * @param currentCount
	 * @param pageCount
	 * @return
	 */
	public List<Order> findByPage(Integer currentCount, Integer pageCount) {
		String hql = "from Order order by ordertime desc";
		List<Order> listData = this.getHibernateTemplate().execute(new PageHibernateCallback<Order>(hql, null, currentCount, pageCount));
		return listData;
	}

	/**
	 * 持久层，根据oid查询得到订单的订单项
	 * @param oid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<OrderItem> findOrderItem(Integer oid) {
		String hql = "from OrderItem oi where oi.order.oid=?";
		List<OrderItem> list = this.getHibernateTemplate().find(hql, oid);
		return list;
	}

}
