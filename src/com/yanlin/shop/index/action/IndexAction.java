package com.yanlin.shop.index.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.yanlin.shop.category.service.CategoryService;
import com.yanlin.shop.category.vo.Category;
import com.yanlin.shop.product.service.ProductService;
import com.yanlin.shop.product.vo.Product;

/**
 * 首页访问的Action
 * @author yl.anin@qq.com
 *
 */
public class IndexAction extends ActionSupport {
	//注入Service
	private CategoryService categoryService;
	private ProductService productService;
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}


	/**
	 * 执行访问首页的方法
	 */
	public String execute(){
		//查询所有一级分类的所有数据
		List<Category> cList =  categoryService.findAll();
		ActionContext.getContext().getSession().put("cList", cList);
		//查询商品的热门商品
		List<Product> hProductList = productService.findHot();
		ActionContext.getContext().getValueStack().set("hProductList", hProductList);
		//查询商品的最新商品
		List<Product> nProductList = productService.findNew();
		ActionContext.getContext().getValueStack().set("nProductList", nProductList);
		
		return "index";
	}
}
