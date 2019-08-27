package com.xq.comm.enumcode;

public enum CodeEnum {

	SUCCESS("200", "操作成功！"), ERROR("-1", "操作失败！");

	String code;
	String message;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	CodeEnum() {
	}

	CodeEnum(String code, String message) {
		this.code = code;
		this.message = message;
	}

	CodeEnum(String code) {
		this.code = code;
	}

	public static CodeEnum getMessageByCode(String code) {
		CodeEnum codeEnum = null;

		for (int i = 0; i < CodeEnum.values().length; i++) {
			codeEnum = CodeEnum.values()[i];
			if (code.equals(codeEnum.getCode())) {
				break;
			}
		}
		return codeEnum;
	}

}
