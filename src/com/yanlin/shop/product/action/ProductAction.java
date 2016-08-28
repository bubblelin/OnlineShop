package com.yanlin.shop.product.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.yanlin.shop.category.service.CategoryService;
import com.yanlin.shop.product.service.ProductService;
import com.yanlin.shop.product.vo.Product;
import com.yanlin.shop.util.PageBean;

/**
 * 商品的action类
 * @author bubblelin
 *
 */
public class ProductAction extends ActionSupport implements ModelDriven<Product>{
	//注入service對象
	private ProductService productService;
	private CategoryService categoryService;
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	//接收用戶輸入的數據
	private Product product = new Product();
	private Integer cid;
	private Integer currentPage;
	private Integer csid;
	public Integer getCsid() {
		return csid;
	}
	public void setCsid(Integer csid) {
		this.csid = csid;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Product getModel() {
		return product;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public Integer getCid() {
		return cid;
	}
	
	/**
	 * 根據用戶傳入的pid查詢是否存在對應的數據
	 */
	public String findByPid(){
		//調用service的根據id查詢數據的方法,Product显示的对像，就是模型驱动显示的对象
		product = productService.findByPid(product.getPid());
		return "findByPid";
	}
	/**
	 * 根據用戶傳入的分類cid和currentPage查詢商品
	 */
	public String findByCid(){
		//List<Category> cList = categoryService.findAll();
		PageBean<Product> pageBean = productService.findByCidPage(cid,currentPage);
		//将pageBean存放在值栈中
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCid";
	}
	/**
	 * 通过用户输入的二级目录的csid查询商品
	 * @return
	 */
	public String findByCsid(){
		PageBean<Product> pageBean = productService.findByCsidPage(csid,currentPage);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCsid";
	}
	
}
