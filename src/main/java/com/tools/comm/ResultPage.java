package com.tools.comm;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
/**
 * 
 * @author admin3
 *
 * @param <T>
 */
public class ResultPage<T> {
	
	/**
	 * 	获取分页条件
	 *  @return
	 */
	public IPage<T> getPage(PageHelp<T> pageHelp){
		Page<T> page = null;
		if(pageHelp != null) {
			page = new Page<T>(pageHelp.getCurrent(), pageHelp.getSize());
		}
		return page;
	}
	

	/**
	 * 	获取查询条件实体类
	 *  @return
	 */
	public Wrapper<T> getWrapper(PageHelp<T> page){
		Wrapper<T> wrapper = null;
		if(page != null && page.getData() != null) {
			wrapper = new QueryWrapper<T>(page.getData());
		}
		return wrapper;
	}

}
