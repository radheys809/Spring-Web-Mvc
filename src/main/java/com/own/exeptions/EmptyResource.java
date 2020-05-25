package com.own.exeptions;

public final class EmptyResource extends RuntimeException {

	private final String message;
	private final Integer code;
	public EmptyResource(String message,Integer code) {
		super(message);
		this.message=message;
		this.code=code;
	}

	public String getMessage() {
		return message;
	}

	public Integer getCode() {
		return code;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -512715186440325580L;

}
