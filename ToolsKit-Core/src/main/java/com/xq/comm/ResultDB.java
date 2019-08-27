package com.xq.comm;

import com.xq.comm.enumcode.CodeEnum;
import lombok.Data;

/**
 * 统一响应类
 * 
 * @author xieqing
 * @date 2019-06
 */
@Data
public class ResultDB<T> {

	//	成功状态码
	private static final String SUCCESS_CODE = "200";
	//	失败状态码
	private static final String ERROR_CODE = "-1";

	private String code;

	private String message;

	private Object data;

	public ResultDB() {
	}

	public ResultDB(String code, String message, Object data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public ResultDB(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public ResultDB(String message, Object data) {
		this.data = data;
		this.message = message;
	}

	public ResultDB(Object data, String code) {
		this.code = code;
		this.data = data;
	}

	public ResultDB(Object data) {
		this.data = data;
	}

	public ResultDB(CodeEnum codeEnum) {
		this.data = null;
		this.code = codeEnum.getCode();
		this.message = codeEnum.getMessage();
	}

	public static ResultDB error() {
		return error("500", "未知异常，请联系管理员");
	}

	public static ResultDB error(String msg) {
		return error(ERROR_CODE, msg);
	}

	public static ResultDB error(String code, String msg) {
		return new ResultDB(code, msg, null);
	}

	public static ResultDB success(CodeEnum codeEnum) {
		return new ResultDB(codeEnum);
	}

	public static ResultDB success(String msg) {
		return new ResultDB(SUCCESS_CODE, msg);
	}

	public static ResultDB success(Object result) {
		return new ResultDB(SUCCESS_CODE, null, result);
	}

	public static ResultDB success(String msg, Object result) {
		return new ResultDB(SUCCESS_CODE, msg, result);
	}

	public static ResultDB failure(String code, String msg) {
		return new ResultDB(code, msg);
	}

	public ResultDB success(String code, String message, Object data) {
		return new ResultDB(code, message, data);
	}

}
