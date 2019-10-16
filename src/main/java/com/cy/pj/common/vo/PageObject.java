package com.cy.pj.common.vo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
@Data
public class PageObject<T> implements Serializable{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1684876792546164773L;
	/**当前页的页码值*/
		private Integer pageCurrent=1;
	    /**页面大小*/
	    private Integer pageSize=3;
	    /**总行数(通过查询获得)*/
	    private Integer rowCount=0;
	    /**总页数(通过计算获得)*/
	    private Integer pageCount=0;
	    /**当前页记录*/
	    private List<T> records;

	    public PageObject(Integer pageCurrent, Integer pageSize, Integer rowCount, List<T> records) {
			super();
			this.pageCurrent = pageCurrent;
			this.pageSize = pageSize;
			this.rowCount = rowCount;
			this.records = records;
//			this.pageCount=rowCount/pageSize;
//			if(rowCount%pageSize!=0) {
//				pageCount++;
//			}
			this.pageCount=(rowCount-1)/pageSize+1;
		}   
}
