package com.yanlin.shop.order.action;

import java.io.IOException;
import java.util.Date;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.yanlin.shop.cart.vo.Cart;
import com.yanlin.shop.cart.vo.CartItem;
import com.yanlin.shop.order.service.OrderService;
import com.yanlin.shop.order.vo.Order;
import com.yanlin.shop.order.vo.OrderItem;
import com.yanlin.shop.user.vo.User;
import com.yanlin.shop.util.PageBean;
import com.yanlin.shop.util.PaymentUtil;

/**
 * 订单的Action类
 * @author bubblelin
 *
 */
public class OrderAction extends ActionSupport implements ModelDriven<Order>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//获取驱动模型
	private Order order = new Order();
	public Order getModel() {
		return order;
	}
	//注入OrderService
	private OrderService orderService;
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	//获取用户传入的请求数据
	private Integer currentPage;
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	//获取银行支付通道
	private String pd_FrpId;
	public void setPd_FrpId(String pd_FrpId) {
		this.pd_FrpId = pd_FrpId;
	}
	//获取付款成功后的参数：订单编号和付款金额
	private String r3_Amt;
	private String r6_Order;
	public void setR3_Amt(String r3_Amt) {
		this.r3_Amt = r3_Amt;
	}
	public void setR6_Order(String r6_Order) {
		this.r6_Order = r6_Order;
	}


	public String save(){
		//1.保存数据到数据库
		//订单数据：时间，状态补全
		order.setOrdertime(new Date());
		System.out.println("时间：");
		order.setState(1);
		//2，总计的数据是购物车中的信息,从session中获取购物车
		Cart cart = (Cart)ServletActionContext.getRequest().getSession().getAttribute("cart");
		if(cart == null){
			this.addActionError("亲，您尚未购物，请先购物！");
			return "msg";
		}
		order.setTotal(cart.getTotal());
		//3.设置订单中的订单项,从购物车的购物项中获取
		for(CartItem cartItem : cart.getCartItems()){
			OrderItem orderItem = new OrderItem();
			orderItem.setCount(cartItem.getCount());
			orderItem.setSubtotal(cartItem.getSubtotal());
			orderItem.setProduct(cartItem.getProduct());
			order.getOrderItemSet().add(orderItem);
		}
		//4.设置订单的用户
		User existUser = (User)ServletActionContext.getRequest().getSession().getAttribute("existUser");
		if(existUser == null){
			this.addActionMessage("亲，您尚未登陆，请先登陆！");
			return "login";
		}
		order.setUser(existUser);
		orderService.save(order);;
		//5.将订单显示到订单页面上,通过值栈的设置，order显示的对象就是模型驱动的使用对象
		//6.订单提交之后清空购物车
		cart.cartClear();
		return "saveSuccess";
	}
	

	/**
	 * 我的订单的查询
	 */
	public String findByUid(){
		//根据在session中的用户的id查询
		User existUser = (User)ServletActionContext.getRequest().getSession().getAttribute("existUser");
		PageBean<Order> pageBean = orderService.findByUidPage(existUser.getUid(),currentPage);
		System.out.println("我的订单分页："+pageBean);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		
		return "findByUid";
	}
	
	/**
	 * 根据用户请求的订单oid查询获取订单
	 */
	public String findByOid(){
		//order显示的对象就是模型驱动的使用对象
		order = orderService.findByOid(order.getOid());
		return "findByOid";
	}
	
	/**
	 * 订单的付款的方法
	 * @throws IOException 
	 */
	public String payOrder() throws IOException{
		//修改更新订单
		Order currOrder = orderService.findByOid(order.getOid());
		currOrder.setAddr(order.getAddr());
		currOrder.setPhone(order.getPhone());
		currOrder.setName(order.getName());
		orderService.update(currOrder);
		//为订单付款
		//付款需要的参数
		String p0_Cmd = "Buy"; // 业务类型:
		String p1_MerId = "10001126856";// 商户编号:
		String p2_Order = order.getOid().toString();// 订单编号:
		String p3_Amt = "0.01"; // 付款金额:
		String p4_Cur = "CNY"; // 交易币种:
		String p5_Pid = ""; // 商品名称:
		String p6_Pcat = ""; // 商品种类:
		String p7_Pdesc = ""; // 商品描述:
		String p8_Url = "http://localhost:8080/shop/order_callBack.action"; // 商户接收支付成功数据的地址:
		String p9_SAF = ""; // 送货地址:
		String pa_MP = ""; // 商户扩展信息:
		String pd_FrpId = this.pd_FrpId;// 支付通道编码:
		String pr_NeedResponse = "1"; // 应答机制:
		String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl"; // 秘钥
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt,
				p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP,
				pd_FrpId, pr_NeedResponse, keyValue); // hmac
		// 向易宝发送请求:
		StringBuffer sb = new StringBuffer("https://www.yeepay.com/app-merchant-proxy/node?");
		sb.append("p0_Cmd=").append(p0_Cmd).append("&");
		sb.append("p1_MerId=").append(p1_MerId).append("&");
		sb.append("p2_Order=").append(p2_Order).append("&");
		sb.append("p3_Amt=").append(p3_Amt).append("&");
		sb.append("p4_Cur=").append(p4_Cur).append("&");
		sb.append("p5_Pid=").append(p5_Pid).append("&");
		sb.append("p6_Pcat=").append(p6_Pcat).append("&");
		sb.append("p7_Pdesc=").append(p7_Pdesc).append("&");
		sb.append("p8_Url=").append(p8_Url).append("&");
		sb.append("p9_SAF=").append(p9_SAF).append("&");
		sb.append("pa_MP=").append(pa_MP).append("&");
		sb.append("pd_FrpId=").append(pd_FrpId).append("&");
		sb.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
		sb.append("hmac=").append(hmac);
		// 重定向:向易宝出发:
		ServletActionContext.getResponse().sendRedirect(sb.toString());
		
		return NONE;
	}
	
	/**
	 * 付款成功后跳转回来的路径
	 */
	public String callBack(){
		Order currOrder = orderService.findByOid(Integer.valueOf(r6_Order));
		//修改订单的状态为2，即已经付款
		currOrder.setState(2);
		orderService.update(currOrder);
		//http://localhost:8080/shop/order_callBack.action?r6_Order=9010&r3_Amt=0.01
		this.addActionMessage("支付成功！订单编号为："+r6_Order+"付款金额为："+r3_Amt);
		return "msg";
	}
	
	/**
	 * 确认收货，并修改状态的方法
	 */
	public String updateState(){
		Order currOrder = orderService.findByOid(order.getOid());	
		currOrder.setState(4);
		orderService.update(currOrder);
		return "updateState";
	}
}
