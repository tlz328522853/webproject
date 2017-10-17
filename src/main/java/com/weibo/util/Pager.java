package com.weibo.util;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class Pager<T> implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String ASC = "asc";
    public static final String DESC = "desc";

    //-- 分页参数 --//
    protected int pageNo = 1;
    protected int pageSize = 20;
    protected String orderBy;
    protected String order;
    protected boolean autoCount = true;
    protected Map<String, Object> params = new HashMap<String, Object>();

    //-- 返回结果 --//
    protected List<T> result = new ArrayList<T>();
    protected long totalCount = -1;

    //-- 构造函数 --//
    public Pager() {
    }

    public Pager(int pageSize) {
        this.pageSize = pageSize;
    }

    //-- 分页参数访问函数 --//

    /**
     * 获得当前页的页号,序号从1开始,默认为1.
     */
    public int getPageNo() {
        return pageNo;
    }

    /**
     * 设置当前页的页号,序号从1开始,低于1时自动调整为1.
     */
    public void setPageNo(final int pageNo) {
        this.pageNo = pageNo;

        if (pageNo < 1) {
            this.pageNo = 1;
        }
    }

    /**
     * 返回Page对象自身的setPageNo函数,可用于连续设置。
     */
    public Pager<T> pageNo(final int thePageNo) {
        setPageNo(thePageNo);
        return this;
    }

    /**
     * 获得每页的记录数量, 默认为-1.
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * 设置每页的记录数量.
     */
    public void setPageSize(final int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 返回Page对象自身的setPageSize函数,可用于连续设置。
     */
    public Pager<T> pageSize(final int thePageSize) {
        setPageSize(thePageSize);
        return this;
    }

    /**
     * 根据pageNo和pageSize计算当前页第一条记录在总结果集中的位置,序号从1开始.
     */
    public int getFirst() {
        return ((pageNo - 1) * pageSize) + 1 - 1;
    }

    /**
     * 获得排序字段,无默认值. 多个排序字段时用','分隔.
     */
    public String getOrderBy() {
        return orderBy;
    }

    /**
     * 设置排序字段,多个排序字段时用','分隔.
     */
    public void setOrderBy(final String orderBy) {
        this.orderBy = orderBy;
    }

    /**
     * 返回Page对象自身的setOrderBy函数,可用于连续设置。
     */
    public Pager<T> orderBy(final String theOrderBy) {
        setOrderBy(theOrderBy);
        return this;
    }

    /**
     * 获得排序方向, 无默认值.
     */
    public String getOrder() {
        return order;
    }

    /**
     * 设置排序方式向.
     *
     * @param order 可选值为desc或asc,多个排序字段时用','分隔.
     */
    public void setOrder(final String order) {
        String lowcaseOrder = order.toLowerCase();

        //检查order字符串的合法值
        String[] orders = lowcaseOrder.split(",");
        for (String orderStr : orders) {
            if (!DESC.equals(orderStr) && !ASC.equals(orderStr)) {
                throw new IllegalArgumentException("排序方向" + orderStr + "不是合法值");
            }
        }

        this.order = lowcaseOrder;
    }

    /**
     * 返回Page对象自身的setOrder函数,可用于连续设置。
     */
    public Pager<T> order(final String theOrder) {
        setOrder(theOrder);
        return this;
    }

    /**
     * 是否已设置排序字段,无默认值.
     */
    public boolean isOrderBySetted() {
        return (StringUtils.isEmpty(orderBy) && StringUtils.isEmpty(order));
    }
    
    /**
     * 组装排序在sql中的表达式
     * @return
     */
    public String getOrderSql(){
    	
    	if(!isOrderBySetted()){
    		StringBuilder stringBuilder = new StringBuilder();
    		String[] orders = this.order.split(",");
    		String[] orderBys = this.orderBy.split(",");
    		int i = 0;
    		for(; i < orderBys.length - 1; i ++){
    			stringBuilder.append(orderBys[i]);
    			stringBuilder.append(" ");
    			stringBuilder.append(i < orders.length ? orders[i] : ""); //如果排序个数少于排序字段个数，默认用升序
    			stringBuilder.append(",");
    		}
    		stringBuilder.append(orderBys[i]);
			stringBuilder.append(" ");
			stringBuilder.append(i < orders.length ? orders[i] : "");
			return stringBuilder.toString();
    	}
    	return null;
    }

    /**
     * 获得查询对象时是否先自动执行count查询获取总记录数, 默认为false.
     */
    public boolean isAutoCount() {
        return autoCount;
    }

    /**
     * 设置查询对象时是否自动先执行count查询获取总记录数.
     */
    public void setAutoCount(final boolean autoCount) {
        this.autoCount = autoCount;
    }

    /**
     * 返回Page对象自身的setAutoCount函数,可用于连续设置。
     */
    public Pager<T> autoCount(final boolean theAutoCount) {
        setAutoCount(theAutoCount);
        return this;
    }

    //-- 访问查询结果函数 --//

    /**
     * 获得页内的记录列表.
     */
    public List<T> getResult() {
        return result;
    }

    /**
     * 设置页内的记录列表.
     */
    public void setResult(final List<T> result) {
        this.result = result;
    }

    /**
     * 获得总记录数, 默认值为-1.
     */
    public long getTotalCount() {
        return totalCount;
    }

    /**
     * 设置总记录数.
     */
    public void setTotalCount(final long totalCount) {
        this.totalCount = totalCount;
    }

    /**
     * 根据pageSize与totalCount计算总页数, 默认值为-1.
     */
    public long getTotalPages() {
        if (totalCount < 0) {
            return -1;
        }

        long count = totalCount / pageSize;
        if (totalCount % pageSize > 0) {
            count++;
        }
        return count;
    }

    /**
     * 是否还有下一页.
     */
    public boolean isHasNext() {
        return (pageNo + 1 <= getTotalPages());
    }

    /**
     * 取得下页的页号, 序号从1开始.
     * 当前页为尾页时仍返回尾页序号.
     */
    public int getNextPage() {
        if (isHasNext()) {
            return pageNo + 1;
        } else {
            return pageNo;
        }
    }

    /**
     * 是否还有上一页.
     */
    public boolean isHasPre() {
        return (pageNo - 1 >= 1);
    }

    /**
     * 取得上页的页号, 序号从1开始.
     * 当前页为首页时返回首页序号.
     */
    public int getPrePage() {
        if (isHasPre()) {
            return pageNo - 1;
        } else {
            return pageNo;
        }
    }

    /**
     * get the neighbor pos NO of current pos
     */
    public List<Integer> getNeighborPagePos() {
        int totalPage = (int) getTotalPages();
        List<Integer> posList = new ArrayList<Integer>();
        int start = totalPage > 6 && pageNo > 2 ? pageNo - 2 : 1;
        int end = start + 5 > totalPage ? totalPage : start + 5;
        start = (end == start + 5) ? start : (end > 5) ? end - 5 : 1;
        for (int i = start; i < end + 1; i++) {
            posList.add(i);
        }
        return posList;
    }

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	@Override
	public String toString() {
		return "Pager [pageNo=" + pageNo + ", pageSize=" + pageSize + ", orderBy=" + orderBy + ", order=" + order
				+ ", autoCount=" + autoCount + ", params=" + params + ", result=" + result + ", totalCount="
				+ totalCount + "]";
	}

	/*@Override
	public String toString() {
		return "Pager [pageNo=" + pageNo + ", pageSize=" + pageSize
				+ ", orderBy=" + orderBy + ", order=" + order + ", autoCount="
				+ autoCount + ", result=" + result + ", totalCount="
				+ totalCount + "]";
	}*/
	
	

}

