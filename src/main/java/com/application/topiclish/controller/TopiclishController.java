package com.application.topiclish.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.application.topiclish.dto.BaseJsonResponse;
import com.application.topiclish.dto.Topic;
import com.application.topiclish.exception.TopiclishCustomException;
import com.application.topiclish.service.TopiclishService;
import com.application.topiclish.util.TopiclishProperties;
import com.application.topiclish.util.TopiclishUtil;

@Controller("/")
public class TopiclishController {
	
	@Autowired
	private TopiclishService topiclishService;
	
	@Autowired
	private TopiclishUtil topiclishUtil;
	
	@Autowired
	private TopiclishProperties props;
	
	private Logger log = LoggerFactory.getLogger(TopiclishController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response){
		ModelAndView view = new ModelAndView("index");
		view.addObject("supportedDescLen", props.getSupportedTopicDescLength());
		return view;
	}
	@RequestMapping(value = "/getTopTopics", method = RequestMethod.GET)
	public @ResponseBody BaseJsonResponse getTopTopics(HttpServletRequest request, HttpServletResponse response){
		List<Topic> topicList = topiclishService.getTopTopics();		
		return topiclishUtil.constructSucessfulResponse(topicList);
	}
	
	@RequestMapping(value = "/createTopic", method = RequestMethod.POST)
	public @ResponseBody BaseJsonResponse createTopic(HttpServletRequest request, HttpServletResponse response){
		String topicDesc = request.getParameter("topicDesc");
		log.debug("topicDesc=["+topicDesc+"]");
		BaseJsonResponse resp;
		
		try {
			topiclishService.createTopic(topicDesc);
			resp = topiclishUtil.constructSucessfulResponse(null);
		} catch (TopiclishCustomException e) {
			log.error(e.getMessage(),e);
			resp = topiclishUtil.constructErrorResponse(e.getMessage());
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		return resp;
	}
	@RequestMapping(value = "/upvoteTopic/{topicId}", method = RequestMethod.PUT)
	public @ResponseBody BaseJsonResponse upvoteTopic(@PathVariable String topicId,
			HttpServletRequest request, HttpServletResponse response){
		log.debug("topicId=["+topicId+"]");
		BaseJsonResponse resp;
		try {
			topiclishService.upvoteTopic(topicId);
			resp = topiclishUtil.constructSucessfulResponse(null);
		} catch (TopiclishCustomException e) {
			log.error(e.getMessage(),e);
			resp = topiclishUtil.constructErrorResponse(e.getMessage());
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		return resp;
	}
	@RequestMapping(value = "/downvoteTopic/{topicId}", method = RequestMethod.PUT)
	public @ResponseBody BaseJsonResponse downvoteTopic(@PathVariable String topicId,
			HttpServletRequest request, HttpServletResponse response){
		log.debug("topicId=["+topicId+"]");
		BaseJsonResponse resp;
		try {
			topiclishService.downvoteTopic(topicId);
			resp = topiclishUtil.constructSucessfulResponse(null);
		} catch (TopiclishCustomException e) {
			log.error(e.getMessage(),e);
			resp = topiclishUtil.constructErrorResponse(e.getMessage());
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		return resp;
	}
}
