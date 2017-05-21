package com.application.topiclish.exception;

public class TopiclishCustomException extends Exception{
	private static final long serialVersionUID = 1L;
	public static final String topicIDMissing = "Topic ID not present";
	public static final String dataLengthNotSupported = "Data Length not supported";
	public static final String missingData = "Data is missing";
	
	public TopiclishCustomException(Throwable cause) {
		super(cause);
	}

	public TopiclishCustomException(String errorMessage) {
		super(errorMessage);
	}
}
