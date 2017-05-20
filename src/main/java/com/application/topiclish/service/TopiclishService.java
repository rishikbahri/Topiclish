package com.application.topiclish.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.topiclish.dao.TopiclishDao;
import com.application.topiclish.dto.Topic;
import com.application.topiclish.exception.TopiclishCustomException;

@Service
public class TopiclishService {

	@Autowired
	private TopiclishDao dataStructure;
	
	private Logger log = LoggerFactory.getLogger(TopiclishService.class);
	
	// Number of topics to be displayed
	private final int topTopicsToRetirieve = 20;
	
	public void createTopic(String topicName, String topicDesc){
		Topic topic = new Topic(topicName,topicDesc);
		dataStructure.createTopic(topic);
	}
	public List<Topic> getTopTopics(){
		return dataStructure.getTopNTopics(topTopicsToRetirieve);
	}
	public void upvoteTopic(String topicId) throws TopiclishCustomException{
		dataStructure.upvoteTopic(topicId);
	}
	public void downvoteTopic(String topicId) throws TopiclishCustomException{
		dataStructure.downvoteTopic(topicId);
	}
}