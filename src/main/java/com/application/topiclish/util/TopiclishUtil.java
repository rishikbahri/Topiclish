package com.application.topiclish.util;

import org.springframework.stereotype.Component;

import com.application.topiclish.dto.BaseJsonResponse;
import com.application.topiclish.dto.Status;

@Component
public class TopiclishUtil {
	public BaseJsonResponse constructSucessfulResponse(Object obj){
		BaseJsonResponse resp = new BaseJsonResponse();
		Status status = new Status();
		status.setCode(Status.Constants.successCode);
		status.setMeaning(Status.Constants.successStatus);
		resp.setStatus(status);
		if(obj!=null){
			resp.setData(obj);
		}
		return resp;
	}
	public BaseJsonResponse constructErrorResponse(String errorMsg){
		BaseJsonResponse resp = new BaseJsonResponse();
		Status status = new Status();
		status.setCode(Status.Constants.failureCode);
		status.setMeaning(Status.Constants.failureStatus);
		status.setErrorMessage(errorMsg);
		resp.setStatus(status);
		return resp;
	}
}
