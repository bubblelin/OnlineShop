package com.yanlin.shop.categorysecond.adminaction;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.yanlin.shop.category.service.CategoryService;
import com.yanlin.shop.category.vo.Category;
import com.yanlin.shop.categorysecond.service.CategorySecondService;
import com.yanlin.shop.categorysecond.vo.CategorySecond;
import com.yanlin.shop.util.PageBean;

/**
 * 二级分类的Action类
 * @author bubblelin
 *
 */
public class AdminCategorySecondAction extends ActionSupport implements ModelDriven<CategorySecond>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//模型驱动所使用的对象
	private CategorySecond categorySecond = new CategorySecond();
	public CategorySecond getModel() {
		return categorySecond;
	}
	//注入二级分类的service
	private CategorySecondService categorySecondService;
	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}
	//接受currentPage
	private Integer currentPage;
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	//注入一级分类的service
	private CategoryService categoryService;
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	/**
	 * 查询二级分类的方法
	 */
	public String findAll(){
		 PageBean<CategorySecond> pageBean = categorySecondService.findByPage(currentPage);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}
	/**
	 * 跳转到二级分类的添加页面
	 */
	public String addPage(){
		//查询出所有的一级分类，存放在值栈总
		List<Category> cList = categoryService.findAll();
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "addPageSuccess";
	}
	/**
	 * 保存二级分类数据的执行方法
	 */
	public String save(){
		categorySecondService.save(categorySecond);
		return "saveSuccess";
	}
	
	/**
	 * 删除二级分类的执行方法
	 */
	public String delete(){
		//根据csid查询得到后台用户要删除的二级分类
		categorySecond = categorySecondService.findByCsid(categorySecond.getCsid());
		
		categorySecondService.delete(categorySecond);
		return "deleteSuccess";
	}
	/**
	 * 编辑二级分类的执行方法
	 */
	public String edit(){
		//根据csid获取用户需要修改的二级分类，存放在模型驱动，即在值栈
		categorySecond = categorySecondService.findByCsid(categorySecond.getCsid());
		//查询出所有关联的一级分类,存放在值栈
		List<Category> cList = categoryService.findAll();
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "editSuccess";
	}
	/**
	 * 修改二级分类的执行方法
	 */
	public String update(){
		categorySecondService.update(categorySecond);
		return "updateSuccess";
	}
}
