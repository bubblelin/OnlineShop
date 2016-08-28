package com.yanlin.shop.cart.vo;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 购物车的实体类
 * @author bubblelin
 *
 */
public class Cart implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//购物车内项目的集合：Map的key就是商品的pid,value就是购物项
	private Map<Integer, CartItem> map = new LinkedHashMap<Integer, CartItem>();
	private double total;
	
	public Collection<CartItem> getCartItems(){
		return map.values();
	}
	public double getTotal() {
		return total;
	}
	//购物车的功能
	//1.将购物项添加到购物车
	public void cartAdd(CartItem cartItem){
//		先判断购物车中是否存在该购物项
//			1.若存在，则该购物项数量增加
//			
//			2.若不存在，则将向map中添加购物项
//		
//		总计重计
		Integer pid = cartItem.getProduct().getPid();
		if(map.containsKey(pid)){
			CartItem _cartItem = map.get(pid);
			_cartItem.setCount(_cartItem.getCount() + cartItem.getCount());
		}else{
			map.put(pid, cartItem);
		}
		total += cartItem.getSubtotal();
	}
	//2.将购物项移出购物车
	public void cartRemove(Integer pid){
		//删除购物项
		CartItem cartItem = map.remove(pid);
		//总计重计
		total -= cartItem.getSubtotal();
	}
	//3.将购物车清空
	public void cartClear(){
		//清空购物项
		map.clear();
		//总计清零
		total = 0;
	}
}
