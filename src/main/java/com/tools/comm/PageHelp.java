package com.tools.comm;

import java.io.Serializable;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class PageHelp<T> implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    /**
     * 	每页显示数
     */
    private long size = 10;
    /**
     * 	当前页
     */
    private long current = 1;
    
    /**
     * 
     */
    private String[] ascs;
	
    /**
     *	查询条件实体列
     */
    private T data;
	
	
	

}
