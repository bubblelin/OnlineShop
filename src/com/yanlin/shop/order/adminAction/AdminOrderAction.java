package com.yanlin.shop.order.adminAction;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.yanlin.shop.order.service.OrderService;
import com.yanlin.shop.order.vo.Order;
import com.yanlin.shop.order.vo.OrderItem;
import com.yanlin.shop.util.PageBean;

/**
 * 后台订单管理的action类
 * @author bubblelin
 *
 */
public class AdminOrderAction extends ActionSupport implements ModelDriven<Order>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//模型驱动使用的对象
	private Order order = new Order();
	public Order getModel() {
		return order;
	}
	//注入OrderService
	private OrderService orderService;
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	/**
	 * 接受用户请求的查询所有订单的当前页
	 */
	private Integer currentPage;
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	/**
	 * 后台用户获取所有订单的方法
	 */
	public String findAll(){
		PageBean<Order> pageBean = orderService.findByPage(currentPage);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}
	
	/**
	 * 根据oid查询所有的订单项
	 */
	public String findOrderItem(){
		List<OrderItem> oiList = orderService.findOrderItem(order.getOid());
		ActionContext.getContext().getValueStack().set("oiList", oiList);
		return "findOrderItem";
	}
	/**
	 * 后台修改订单的状态
	 */
	public String updateState(){
		Order currOrder = orderService.findByOid(order.getOid());
		currOrder.setState(3);
		orderService.update(currOrder);
		return "updateState";
	}
}
