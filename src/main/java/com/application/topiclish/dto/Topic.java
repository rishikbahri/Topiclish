package com.application.topiclish.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class Topic implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String topicId;
	private String topicName;
	private String topicDesc;
	private Date insertDatetime;
	private int voteCount;
	
	public Topic(String topicName, String topicDesc){
		this.topicName = topicName;
		this.topicDesc = topicDesc;
		this.topicId = UUID.randomUUID().toString();
		this.insertDatetime = new Date();
		this.voteCount = 0;
	}
	public Topic(String topicId, String topicName, String topicDesc){
		this.topicName = topicName;
		this.topicDesc = topicDesc;
		this.topicId = topicId;
		this.insertDatetime = new Date();
		this.voteCount = 0;
	}

	public String getTopicId() {
		return topicId;
	}
	public String getTopicName() {
		return topicName;
	}
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	public String getTopicDesc() {
		return topicDesc;
	}
	public void setTopicDesc(String topicDesc) {
		this.topicDesc = topicDesc;
	}
	public Date getInsertDatetime() {
		return insertDatetime;
	}
	public int getVoteCount() {
		return voteCount;
	}
	public void setVoteCount(int voteCount) {
		this.voteCount = voteCount;
	}
	@Override
	public String toString() {
		return "Topic [topicId=" + topicId + ", topicName=" + topicName + ", topicDesc=" + topicDesc
				+ ", insertDatetime=" + insertDatetime + ", voteCount=" + voteCount + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((topicId == null) ? 0 : topicId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Topic other = (Topic) obj;
		if (topicId == null) {
			if (other.topicId != null)
				return false;
		} else if (!topicId.equals(other.topicId))
			return false;
		return true;
	}
	
}