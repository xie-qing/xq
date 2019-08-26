package comm;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

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
