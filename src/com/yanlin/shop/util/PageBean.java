package com.yanlin.shop.util;

import java.util.List;

/**
 * 分頁的封裝
 * @author bubblelin
 * @param <T>
 *
 */
public class PageBean<T> {

	private Integer currentPage; //當前頁
	private Integer totalCount;	//縂記錄數
	private Integer totalPage;	//縂的頁數
	private Integer pageCount;	//每頁顯示的記錄數
	private List<T> listData;	//每頁顯示的數據集
	
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public Integer getPageCount() {
		return pageCount;
	}
	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}
	public List<T> getListData() {
		return listData;
	}
	public void setListData(List<T> listData) {
		this.listData = listData;
	}
	
}
