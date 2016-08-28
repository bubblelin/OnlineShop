package com.yanlin.shop.category.vo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.yanlin.shop.categorysecond.vo.CategorySecond;

/**
 * 一级分类的实体类
 * @author bubblelin
 *
 */
public class Category implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer cid;
	private String cname;
	//二級分類的集合，存在一級分類中
	private Set<CategorySecond> categorySecondSet = new HashSet<CategorySecond>();
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public Set<CategorySecond> getCategorySecondSet() {
		return categorySecondSet;
	}
	public void setCategorySecondSet(Set<CategorySecond> categorySecondSet) {
		this.categorySecondSet = categorySecondSet;
	}
	
}
