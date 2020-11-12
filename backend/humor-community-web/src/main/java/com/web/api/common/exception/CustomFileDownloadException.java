package com.web.api.common.exception;

@SuppressWarnings("serial")
public class CustomFileDownloadException extends RuntimeException{
	public CustomFileDownloadException(String message) {
		super(message);
	}
	
	public CustomFileDownloadException(String message,Throwable cause) {
		super(message, cause);
	}
}
