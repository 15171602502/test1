package com.ysk.kxt.sourceUit;

import java.util.List;

import com.github.pagehelper.PageInfo;

/**
 * 分页工具类
 * 
 * @author admin
 *
 * @param <T>
 */
public class PageBean<T> {

	// 当前页
	private Integer currentPage = 1;
	// 每页显示的总条数
	private Integer pageSize = 10;
	// 总条数
	private Integer totalNum;
	// 是否有下一页
	private Integer isMore;
	// 总页数
	private Integer totalPage;
	// 开始索引
	private Integer startIndex;

	public PageBean() {
		super();
	}

	public PageBean(Integer currentPage, Integer pageSize, List<T> list) {
		super();
		this.currentPage = currentPage;
		this.pageSize = pageSize;

		PageInfo<T> pageInfo = new PageInfo<T>(list);
		this.totalNum = (int) pageInfo.getTotal();

		this.totalPage = (this.totalNum + this.pageSize - 1) / this.pageSize;
		this.startIndex = (this.currentPage - 1) * this.pageSize;
		this.isMore = this.currentPage >= this.totalPage ? 0 : 1;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

	public Integer getIsMore() {
		return isMore;
	}

	public void setIsMore(Integer isMore) {
		this.isMore = isMore;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public Integer getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}

}
