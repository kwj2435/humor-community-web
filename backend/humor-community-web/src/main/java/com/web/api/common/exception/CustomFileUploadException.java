package com.web.api.common.exception;

@SuppressWarnings("serial")
public class CustomFileUploadException extends RuntimeException{
	public CustomFileUploadException(String message) {
		super(message);
	}
	
	public CustomFileUploadException(String message,Throwable cause) {
		super(message, cause);
	}
}
