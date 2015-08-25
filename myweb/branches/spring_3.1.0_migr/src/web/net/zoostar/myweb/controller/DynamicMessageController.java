package net.zoostar.myweb.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;

public class DynamicMessageController extends MessageController {

	public final ModelAndView handleDynamicMessage(HttpServletRequest request, HttpServletResponse response) {
		return super.handleMessage(request, response);
	}
	
	protected String getMessage(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model) {
		String message = request.getParameter("message");
		if(StringUtils.isBlank(message))
			log.warn("message is NULL in DynamicMessageController.getMessage()");
		
		return message;
	}
}
