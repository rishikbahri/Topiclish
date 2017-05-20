package com.application.topiclish.exception;

public class TopiclishCustomException extends Exception{
	private static final long serialVersionUID = 1L;
	public static final String topicIDMissing = "Topic ID not present";

	
	public TopiclishCustomException(Throwable cause) {
		super(cause);
	}

	public TopiclishCustomException(String errorMessage) {
		super(errorMessage);
	}
}
