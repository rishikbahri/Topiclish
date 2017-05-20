package com.application.topiclish.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author jan.v.d.ronquillo
 * @param <T>
 * @param <T>
 *
 */
public class BaseJsonResponse implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Status status;
	private transient Object data;
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
