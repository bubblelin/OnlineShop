package com.yanlin.shop.cart.action;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.yanlin.shop.cart.vo.Cart;
import com.yanlin.shop.cart.vo.CartItem;
import com.yanlin.shop.product.service.ProductService;
import com.yanlin.shop.product.vo.Product;

/**
 * 购物车的Action
 * @author bubblelin
 *
 */
public class CartAction extends ActionSupport{
	//接受用户传入的pid,count
	private Integer pid;
	private int count;
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public void setCount(int count) {
		this.count = count;
	}
	//注入productService
	private ProductService productService;
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	/**
	 * 将购物项添加到购物车的执行方法
	 * @return
	 */
	public String addCart(){
		//封装一个购物项对象CartItem的对象
		CartItem cartItem = new CartItem();
		//1.设置数量
		cartItem.setCount(count);
		//2.根据pid查询商品，并设置商品
		Product product = productService.findByPid(pid);
		cartItem.setProduct(product);
		//将购物项添加到购物车
			//购物车设置在session中
		Cart cart = getCart();
		cart.cartAdd(cartItem);
		return "addCart";
	}
	
	/**
	 * 清空购物车
	 * @return
	 */
	public String clearCart(){
		Cart cart = getCart();
		cart.cartClear();
		return "clearCart";
	}
	
	/**
	 * 购物项移出购物车
	 * @return
	 */
	public String removeCart(){
		Cart cart = getCart();
		cart.cartRemove(pid);
		
		return "removeCart";
	}
	/**
	 * 跳转到我的购物车
	 * @return
	 */
	public String myCart(){
		
		return "myCart";
	}
	
	//从session中获取购物车
	private Cart getCart() {
		Cart cart = (Cart)ServletActionContext.getRequest().getSession().getAttribute("cart");
		if(cart == null){
			cart = new Cart();
			ServletActionContext.getRequest().getSession().setAttribute("cart", cart);
		}
		return cart;
	}
}
