package com.application.topiclish.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.topiclish.dao.TopiclishDao;
import com.application.topiclish.dto.Topic;
import com.application.topiclish.exception.TopiclishCustomException;
import com.application.topiclish.util.TopiclishProperties;
import com.application.topiclish.util.TopiclishUtil;

@Service
public class TopiclishService {

	@Autowired
	private TopiclishDao dataStructure;
	
	@Autowired
	private TopiclishUtil topiclishUtil;
	
	@Autowired
	private TopiclishProperties props;
	
	private Logger log = LoggerFactory.getLogger(TopiclishService.class);
	
	public void createTopic(String topicDesc) throws TopiclishCustomException{
		topiclishUtil.validateInput(topicDesc);
		Topic topic = new Topic(topicDesc);
		dataStructure.createTopic(topic);
	}
	public List<Topic> getTopTopics(){
		return dataStructure.getTopNTopics(props.getTopTopicsToRetirieve());
	}
	public void upvoteTopic(String topicId) throws TopiclishCustomException{
		dataStructure.upvoteTopic(topicId);
	}
	public void downvoteTopic(String topicId) throws TopiclishCustomException{
		dataStructure.downvoteTopic(topicId);
	}
}