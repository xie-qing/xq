package com.xq.exception;


public class CustomizeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String code;
	
	String msg;
	
	Object[] params;
	
	public CustomizeException(){ }

    public CustomizeException(String code) {
        super(code);
    }

    public CustomizeException(String code, Object[] placeholders) {
        super(code) ;
        this.params = placeholders;
    }

    public CustomizeException(String code, Object[] params, String message) {
        super() ;
        this.msg = message;
    }

    public CustomizeException(String code, String message) {
        super(code) ;
        this.msg = message;
    }
	

}
