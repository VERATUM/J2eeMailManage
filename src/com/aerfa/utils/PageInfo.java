package com.aerfa.utils;

import java.util.List;
/**
 * Title:
 * 
 * Description:
 * 
 * Copyright: Copyright (c) 2014年9月1日
 * 
 * Company: midaipay
 * 
 * @author wumin
 * 
 * @version 1.0
 */
public class PageInfo<T> {
	// 每页显示记录数
	private int length;
	// 查询结果总记录数
	private int count;
	// 当前页码
	private int current;
	// 总共页数
	private int total;
	/** 分页数据 */
	private List<T> results;
   //每页显示条数
	public PageInfo() {
		this.length=5;
	}
	public PageInfo(int length) {
		this.length=length;
	}

	
	public List<T> getResults() {
		return results;
	}

	public void setResults(List<T> results) {
		this.results = results;
	}
	

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
		
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
		this.total = (int) Math.floor((this.count * 1.0d) / this.length);
		if (this.count % this.length != 0) {
			this.total++;
		}
	}

	public int getCurrent() {
		return current;
	}

	public void setCurrent(int current) {
		this.current = current;
	}

	public int getTotal() {
		return (int)Math.ceil(this.count*1.0/this.length);
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
}
