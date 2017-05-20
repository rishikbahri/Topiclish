package com.application.topiclish.dto;

import java.io.Serializable;

public class Status implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String code;
	private String meaning;
	private String errorMessage;
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return the meaning
	 */
	public String getMeaning() {
		return meaning;
	}
	/**
	 * @param meaning the meaning to set
	 */
	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public class Constants{
		public static final String successStatus = "Success";
		public static final String failureStatus = "Fail";
		public static final String successCode = "00";
		public static final String failureCode = "01";
	}
	
}
