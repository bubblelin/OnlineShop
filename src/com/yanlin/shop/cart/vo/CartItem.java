package com.yanlin.shop.cart.vo;

import java.io.Serializable;

import com.yanlin.shop.product.vo.Product;

/**
 * 购物车项目的实体类
 * @author bubblelin
 *
 */
public class CartItem implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Product product;	//商品对象
	private int count;			//商品数量
	private double subtotal;	//该商品项目小计
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	//自动计算价格小计
	public double getSubtotal() {
		return count * product.getShop_price();
	}
	/*public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}*/
}
