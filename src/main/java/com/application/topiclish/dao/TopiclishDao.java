package com.application.topiclish.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.application.topiclish.dto.Topic;

// Creating a singleton so that only one instance is there for this class
@Scope(value = "singleton")
@Component
public class TopiclishDao {
	// This is used to create vote buckets and places the corresponding topicIds within their vote bucket
	// TreeMap will auto sort based on the vote count whenever a new bucket is created
	private TreeMap<Integer,LinkedList<String>> topicVoteIndex;
	
	// This is used to store the Topic object with topicId as the unique key
	private HashMap<String,Topic> topicMap;
	
	public TopiclishDao(){
		topicVoteIndex = new TreeMap<Integer,LinkedList<String>>(Collections.reverseOrder());
		topicMap = new HashMap<String,Topic>();
	}
	
	public void createTopic(Topic topic){
		topicMap.put(topic.getTopicId(), topic);
		LinkedList<String> topicIdList;
		
		if(topicVoteIndex.get(0)==null){
			topicIdList = new LinkedList<String>();
			topicIdList.push(topic.getTopicId());
			topicVoteIndex.put(0, topicIdList);
		}else{
			topicIdList = topicVoteIndex.get(0);
			topicIdList.push(topic.getTopicId());
		}
	}
	
	public void upvoteTopic(String topicId){
		updateTopicVote(topicId,true);
	}
	
	public void downvoteTopic(String topicId){
		updateTopicVote(topicId,false);
	}
	/*
	   Synchronized method for performing voting operations so as to avoid concurrent 
	   operations by different threads
	*/
	private synchronized void updateTopicVote(String topicId,boolean isUpvote){
		// Get the topic from the topic map to update its vote count
		Topic currentTopic = topicMap.get(topicId);
		int currentVoteCount = currentTopic.getVoteCount();
		int newVoteCount;
		if(isUpvote){
			newVoteCount = currentVoteCount+1;
		}else{
			newVoteCount = currentVoteCount-1;
		}
		currentTopic.setVoteCount(newVoteCount);
		
		// Re-adjust position of topic in vote bucket to corresponding to its new vote count
		// Removing the topic form the existing vote bucket
		LinkedList<String> topicList = topicVoteIndex.get(currentVoteCount);
		int topicIndex = topicList.indexOf(topicId);
		topicList.remove(topicIndex);
		if(topicList.isEmpty()){
			topicVoteIndex.remove(currentVoteCount);
		}
		
		// Adding topic to the new vote bucket
		if(topicVoteIndex.containsKey(newVoteCount)){
			topicVoteIndex.get(newVoteCount).push(topicId);
		}else{
			LinkedList<String> newTopicList = new LinkedList<String>();
			newTopicList.push(topicId);
			topicVoteIndex.put(newVoteCount, newTopicList);
		}
	}
	
	public List<Topic> getTopNTopics(int topNTopics){
		List<Topic> topicList = new ArrayList<Topic>();
		// Looping through the vote bucket in order of descending vote count
		Object[] keySet = topicVoteIndex.keySet().toArray();
		b1: for(int i=0;i<keySet.length;i++){
			LinkedList<String> topicLinkedList = topicVoteIndex.get(keySet[i]);
		    for(int j=0;j<topicLinkedList.size();j++){
		    	// Getting matching topics from topic map
		    	topicList.add(topicMap.get(topicLinkedList.get(j)));
		    	if(topicList.size()==topNTopics){
		    		// Breaking outer loop if the required number of top topics has been retrieved
		    		break b1;
		    	}
		    }
		}
		return topicList;
	}
}
