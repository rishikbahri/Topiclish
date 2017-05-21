package com.application.topiclish.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TopiclishProperties {
	
	@Value("${supportedTopicNameLength}")
	private int supportedTopicNameLength;
	
	@Value("${supportedTopicDescLength}")
	private int supportedTopicDescLength;
	
	@Value("${topTopicsToRetirieve}")
	private int topTopicsToRetirieve;

	public int getSupportedTopicNameLength() {
		return supportedTopicNameLength;
	}

	public int getSupportedTopicDescLength() {
		return supportedTopicDescLength;
	}

	public int getTopTopicsToRetirieve() {
		return topTopicsToRetirieve;
	}
}
