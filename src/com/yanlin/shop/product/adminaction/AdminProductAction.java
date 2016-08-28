package com.yanlin.shop.product.adminaction;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.yanlin.shop.categorysecond.service.CategorySecondService;
import com.yanlin.shop.categorysecond.vo.CategorySecond;
import com.yanlin.shop.product.service.ProductService;
import com.yanlin.shop.product.vo.Product;
import com.yanlin.shop.util.PageBean;

/**
 * 后台商品管理的action
 * 
 * @author bubblelin
 * 
 */
public class AdminProductAction extends ActionSupport implements
		ModelDriven<Product> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 模型驱动使用的对象
	private Product product = new Product();
	public Product getModel() {
		// TODO Auto-generated method stub
		return product;
	}

	// 注入productService
	private ProductService productService;
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	/**
	 * 注入CategorySecondService
	 * 
	 */
	private CategorySecondService categorySecondService;
	public void setCategorySecondService(
			CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}

	// 获取currentPage
	private Integer currentPage;
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	/**
	 * 文件上传需要的参数 
	 * upload 上传的文件，与name一致 
	 * uploadFileName 接受文件上传的文件名字
	 * uploadContextType 接受文件上传的文件的MIME的类型
	 */
	private File upload;
	private String uploadFileName;
	private String uploadContextType;
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public void setUploadContextType(String uploadContextType) {
		this.uploadContextType = uploadContextType;
	}

	/**
	 * 后台查询所有的商品的执行方法
	 */
	public String findAll() {
		PageBean<Product> pageBean = productService.findAll(currentPage);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}

	/**
	 * 后台跳转到添加商品的页面
	 */
	public String addPage() {
		// 查询获取所有的二级分类
		List<CategorySecond> csList = categorySecondService.findAll();
		ActionContext.getContext().getValueStack().set("csList", csList);
		return "addPage";
	}

	/**
	 * 后台添加商品的执行方法
	 * @return
	 * @throws IOException
	 */
	public String save() throws IOException {
		// 需要设置的参数
		product.setPdate(new Date());
		if (upload != null) {
			// 1.获取文件上传的磁盘绝对路径
			String realPath = ServletActionContext.getServletContext()
					.getRealPath("/products");
			// 2.创建一个磁盘文件
			File destFile = new File(realPath + "//" + uploadFileName);
			// 3.文件上传操作设置
			FileUtils.copyFile(upload, destFile);
			product.setImage("products/" + uploadFileName);
		}
		// 调用service保存
		productService.save(product);
		return "saveSuccess";
	}
	
	/**
	 * 后台删除商品的执行方法
	 */
	public String delete(){
		product = productService.findByPid(product.getPid());
		//删除图片
		String path = product.getImage();
		if(path != null){
			String realPath = ServletActionContext.getServletContext().getRealPath("/"+path);
			File file = new File(realPath);
			file.delete();
		}
		//删除商品
		productService.delete(product);
		return "deleteSuccess";
	}
	
	/**
	 * 后台跳转到编辑页面的方法
	 */
	public String edit(){
		//根据用户请求的pid获取商品
		product = productService.findByPid(product.getPid());
		//获取所有二级分类集合
		List<CategorySecond> csList = categorySecondService.findAll();
		ActionContext.getContext().getValueStack().set("csList", csList);
		return "editPage";
	}
	
	/**
	 * 后台修改商品的自信方法
	 * @throws IOException 
	 */
	public String update() throws IOException{
		product.setPdate(new Date());
		// 旧的图片删除，上传新的图片
		if(upload != null){
			String path = product.getImage();
			File file = new File(ServletActionContext.getServletContext().getRealPath("/"+path));
			file.delete();
			
			String realPath = ServletActionContext.getServletContext().getRealPath("/products");
			File destFile = new File(realPath + "//" + uploadFileName);
			FileUtils.copyFile(upload, destFile);
			product.setImage("products/"+uploadFileName);
		}
		productService.update(product);
		
		return "updateSuccess";
	}
}
