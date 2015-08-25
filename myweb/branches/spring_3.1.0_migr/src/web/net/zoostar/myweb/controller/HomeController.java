package net.zoostar.myweb.controller;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.zoostar.myweb.WebConstants;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;

public class HomeController extends MyWebAbstractController {

	private final Date lastAccessed = new Date();
	private long counter = 0;
	

	public long getCounter() {
		return counter;
	}

	public Date getLastAccessed() {
		return lastAccessed;
	}

	public final ModelAndView handleHome(HttpServletRequest request,
			HttpServletResponse response) {
		return super.handleMyWebRequest(request, response);
	}
	protected void processModel(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model) {
		model.put("message", "Welcome to my homepage!");
		model.put("authType", request.getAuthType());
		model.put("characterEncoding", request.getCharacterEncoding());
		model.put("contentLength", request.getContentLength());
		model.put("contentPath", request.getContentType());
		model.put("contextPath", request.getContextPath());
		model.put("localAddr", request.getLocalAddr());
		model.put("locale", request.getLocale());
		model.put("localName", request.getLocalName());
		model.put("localPort", request.getLocalPort());
		model.put("method", request.getMethod());
		model.put("pathInfo", request.getPathInfo());
		model.put("protocol", request.getProtocol());
		model.put("queryString", request.getQueryString());
		model.put("remoteAddr", request.getRemoteAddr());
		model.put("remoteHost", request.getRemoteHost());
		model.put("remotePort", request.getRemotePort());
		model.put("remoteUser", request.getRemoteUser());
		model.put("sessionId", request.getRequestedSessionId());
		model.put("requestURI", request.getRequestURI());
		model.put("serverName", request.getServerName());
		model.put("serverPort", request.getServerPort());
		model.put("servletPath", request.getServletPath());
		counter++;
		if(log.isDebugEnabled()) {
			for(String key : model.keySet()) {
				log.debug(key + ": " + model.get(key));
			}
			log.debug("Accessed " + getCounter() + " time(s) since " + getLastAccessed());
		}
	}
	
	@Override
	protected String getViewName(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model) {
		return StringUtils.isBlank(request.getParameter("view")) ? WebConstants.DEFAULT_VIEW :
			request.getParameter("view");
	}
}
