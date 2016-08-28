package com.yanlin.shop.order.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yanlin.shop.order.dao.OrderDao;
import com.yanlin.shop.order.vo.Order;
import com.yanlin.shop.order.vo.OrderItem;
import com.yanlin.shop.util.PageBean;

/**
 * 订单的业务层代码
 * @author bubblelin
 *
 */
@Transactional
public class OrderService {

	//注入OrderDao
	private OrderDao orderDao;
	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}
	/**
	 * 业务层，订单的保存方法
	 */
	public void save(Order order) {
		orderDao.save(order);
		
	}
	/**
	 * 业务层，我的订单的分页查询
	 * @param uid
	 * @param currentPage
	 * @return
	 */
	public PageBean<Order> findByUidPage(Integer uid, Integer currentPage) {
		PageBean<Order> pageBean = new PageBean<Order>();
		//1.设置当前页，用户输入
		pageBean.setCurrentPage(currentPage);
		//2.设置每页显示记录数，
		Integer pageCount = 5;
		pageBean.setPageCount(pageCount);
		//3.设置总记录数，通过查询得到
		Integer totalCount = 0;
		totalCount = orderDao.findByCountUid(uid);
		pageBean.setTotalCount(totalCount);
		//4.设置总页数，通过总记录数和每页显示记录数得到
		Integer totalPage = 0;
		totalPage = totalCount % pageCount == 0 ? totalCount / pageCount : totalCount / pageCount + 1;
		pageBean.setTotalPage(totalPage);
		//5.每页显示的数据集
		Integer currentCount = (currentPage - 1) * pageCount;
		List<Order> listData = orderDao.findByUidPage(uid,currentCount,pageCount);
		pageBean.setListData(listData);
		return pageBean;
	}
	/**
	 * 业务层，根据oid查询订单
	 * @param oid
	 * @return
	 */
	public Order findByOid(Integer oid) {
		return orderDao.findByOid(oid);
	}
	
	/**
	 * 业务层，修改要付款的订单
	 * @param currOrder
	 */
	public void update(Order currOrder) {
		orderDao.update(currOrder);
	}
	/**
	 * 业务层，获取分页的所有订单的方法
	 * @param currentPage
	 * @return
	 */
	public PageBean<Order> findByPage(Integer currentPage) {
		PageBean<Order> pageBean = new PageBean<Order>();
		//1.设置当前页
		pageBean.setCurrentPage(currentPage);
		//2.设置每页记录数
		Integer pageCount = 5;
		pageBean.setPageCount(pageCount);
		//3.设置总记录数
		Integer totalCount = 0;
		totalCount = orderDao.findCount();
		pageBean.setTotalCount(totalCount);
		//4.设置总页数
		Integer totalPage = 0;
		totalPage = totalCount % pageCount == 0 ? totalCount / pageCount : totalCount / pageCount + 1;
		pageBean.setTotalPage(totalPage);
		//5.设置每页显示的记录集合
		Integer currentCount = 0;
		currentCount = (currentPage - 1) * pageCount;
		List<Order> listData = orderDao.findByPage(currentCount,pageCount);
		pageBean.setListData(listData);
		
		return pageBean;
	}
	/**
	 * 业务层，根据oid获取orderItem
	 * @param oid
	 * @return
	 */
	public List<OrderItem> findOrderItem(Integer oid) {

		return orderDao.findOrderItem(oid);
	}
}
