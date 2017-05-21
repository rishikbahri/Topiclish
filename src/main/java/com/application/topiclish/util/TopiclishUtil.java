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
	public void validateInput(String topicName, String topicDesc) throws TopiclishCustomException{
		int supportedNameLen = props.getSupportedTopicNameLength();
		int supportedDescLen = props.getSupportedTopicDescLength();
		if(topicName==null){
			log.error("Topic Name is null");
			throw new TopiclishCustomException(TopiclishCustomException.missingData);
		}else if(topicName.length()>supportedNameLen){
			log.error("Topic Name length is more than supported size supported"
					+ " size=["+supportedNameLen+"] actual=["+topicName.length()+"]");
			throw new TopiclishCustomException(TopiclishCustomException.missingData);
		}
		if(topicDesc==null){
			log.error("Topic Desc is null");
			throw new TopiclishCustomException(TopiclishCustomException.missingData);
		}else if(topicDesc.length()>supportedDescLen){
			log.error("Topic Desc length is more than supported size supported"
					+ " size=["+supportedDescLen+"] actual=["+topicName.length()+"]");
			throw new TopiclishCustomException(TopiclishCustomException.missingData);
		}
	}
}
