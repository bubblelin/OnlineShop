package com.yanlin.shop.categorysecond.vo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.yanlin.shop.category.vo.Category;
import com.yanlin.shop.product.vo.Product;

/**
 * 二級分類的實體對象
 * @author bubblelin
 *
 */
public class CategorySecond implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer csid;
	private String csname;
	//一級分類的對象，存在二級分類中
	private Category category;
	//商品的对象，存在二级分类总中
	private Set<Product> productSet = new HashSet<Product>();
	public Integer getCsid() {
		return csid;
	}
	public void setCsid(Integer csid) {
		this.csid = csid;
	}
	public String getCsname() {
		return csname;
	}
	public void setCsname(String csname) {
		this.csname = csname;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Set<Product> getProductSet() {
		return productSet;
	}
	public void setProductSet(Set<Product> productSet) {
		this.productSet = productSet;
	}
	
}
