package com.application.topiclish.test.testscripts;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.anarsoft.vmlens.concurrent.junit.ConcurrentTestRunner;
import com.application.topiclish.dao.TopiclishDao;
import com.application.topiclish.dto.Topic;
import com.application.topiclish.exception.TopiclishCustomException;

// Runs test case in 4 parallel threads
@RunWith(ConcurrentTestRunner.class)
public class TopiclishDaoMultiThreadTest {
	
	private TopiclishDao topiclishDao;

	@Before
	public void prepareData(){
		topiclishDao = new TopiclishDao();
		List<Topic> topicList = createTopicList(100);
		for(int i=0;i<topicList.size();i++){
			topiclishDao.createTopic(topicList.get(i));
		}		
	}
	
	@Test
    public void testVoting() throws TopiclishCustomException {
		topiclishDao.upvoteTopic("50");
		topiclishDao.upvoteTopic("40");
		topiclishDao.upvoteTopic("40");
		topiclishDao.upvoteTopic("50");
		topiclishDao.upvoteTopic("50");
		topiclishDao.downvoteTopic("50");
		topiclishDao.downvoteTopic("50");
    }
	@After
	public void assertCount(){
		List<Topic> topicList = createTopicList(100);
		
		Topic topic = topicList.get(50);
        topic.setVoteCount(topic.getVoteCount()+4);
        topicList.remove(50);
        topicList.add(topic);
        
        Topic topic1 = topicList.get(40);
        topic1.setVoteCount(topic1.getVoteCount()+8);
        topicList.remove(40);
        topicList.add(topic1);
        
        
        Collections.reverse(topicList);
        List<Topic> expectedTopicList = topicList.subList(0, 20);
        List<Topic> actualTopicList = topiclishDao.getTopNTopics(20);
        
		assertThat(actualTopicList,is(expectedTopicList));
	}
	
	private List<Topic> createTopicList(int count){
		List<Topic> topicList = new ArrayList<Topic>();
		for(int i=0;i<count;i++){
			topicList.add(new Topic(String.valueOf(i),"This is Topic "+i));
		}
		return topicList;
	}
}
