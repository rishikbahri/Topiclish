package com.application.topiclish.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.application.topiclish.dto.BaseJsonResponse;
import com.application.topiclish.dto.Status;
import com.application.topiclish.exception.TopiclishCustomException;

@Component
public class TopiclishUtil {
	
	@Autowired
	private TopiclishProperties props;
	
	private Logger log = LoggerFactory.getLogger(TopiclishUtil.class);
	
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
	public void validateInput(String topicDesc) throws TopiclishCustomException{
		int supportedDescLen = props.getSupportedTopicDescLength();
		if(topicDesc==null){
			log.error("Topic Desc is null");
			throw new TopiclishCustomException(TopiclishCustomException.missingData);
		}else if(topicDesc.length()>supportedDescLen){
			log.error("Topic Desc length is more than supported size supported"
					+ " size=["+supportedDescLen+"] actual=["+topicDesc.length()+"]");
			throw new TopiclishCustomException(TopiclishCustomException.missingData);
		}
	}
}
