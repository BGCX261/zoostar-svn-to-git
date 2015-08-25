package net.zoostar.myweb.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

public class MessageController extends MyWebAbstractController {

	public ModelAndView handleMessage(HttpServletRequest request, HttpServletResponse response) {
		return super.handleMyWebRequest(request, response);
	}
	
	protected void processModel(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model) {
		model.put("message", getMessage(request, response, model));
	}
	protected String getMessage(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model) {
		return "Hello World";
	}
	protected String getViewName(HttpServletRequest request, HttpServletResponse response,
			Map<String, Object> model) {
		return "message";
	}
}
