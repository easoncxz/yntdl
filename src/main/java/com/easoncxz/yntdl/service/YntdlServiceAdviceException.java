package com.easoncxz.yntdl.service;

public class YntdlServiceAdviceException extends RuntimeException {

	private static final long serialVersionUID = 1057178826300256815L;

	public YntdlServiceAdviceException(String message) {
		super(message);
	}

	public YntdlServiceAdviceException(String message, Throwable cause) {
		super(message, cause);
	}
}
