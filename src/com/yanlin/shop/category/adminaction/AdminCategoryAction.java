package com.yanlin.shop.category.adminaction;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.yanlin.shop.category.service.CategoryService;
import com.yanlin.shop.category.vo.Category;

/**
 * 后台一级分类管理的Action
 * @author bubblelin
 *
 */
public class AdminCategoryAction extends ActionSupport implements ModelDriven<Category>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//模型驱动使用的对象
	private Category category = new Category();
	public Category getModel() {
		return category;
	}
	
	// 注入一级分类的Service
	private CategoryService categoryService;
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	/**
	 * 后台查询所有一级分类的执行方法
	 */
	public String findAll(){
		//查询所有一级分类
		List<Category> cList = categoryService.findAll();
		//将集合的数据显示到home.jsp页面，存放在值栈中
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "findAll";
	}
	
	/**
	 * 后台添加一级分类的执行方法
	 */
	public String save(){
		categoryService.save(category);
		return "saveSuccess";
	}
	
	/**
	 * 后台删除一级分类的执行方法
	 */
	public String delete(){
		//接受cid，可以使用模型驱动，删除一级分类同时需要删除二级分类。必须根据cid查询再删
		category = categoryService.findByCid(category.getCid());
		categoryService.delete(category);
		System.out.println("============");
		return "deleteSuccesss";
	}
	
	/**
	 * 查询并跳转到编辑一级分类页面的执行方法
	 */
	public String edit(){
		//使用模型驱动，根据cid查询得到一级分类
		category = categoryService.findByCid(category.getCid());
		return "editSuccess";
	}
	/**
	 * 编辑一级分类的方法
	 */
	public String update(){
		categoryService.update(category);
		return "updateSuccess";
	}
}
