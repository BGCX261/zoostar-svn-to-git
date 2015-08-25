package net.zoostar.myweb.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public abstract class MyWebAbstractController extends MultiActionController {

	static final Logger log = Logger.getLogger(MyWebAbstractController.class);
	
	protected final ModelAndView handleMyWebRequest(HttpServletRequest request,
			HttpServletResponse response) {
		log.debug("MyWebAbstractController.handleMyWebRequest.begin");
		Map<String, Object> model = getModel(request, response);
		String view = getView(request, response, model);
		log.debug("MyWebAbstractController.handleMyWebRequest.end");
		return new ModelAndView(view, "model", model);
	}
	protected final Map<String, Object> getModel(HttpServletRequest request, HttpServletResponse response) {
		log.debug("MyWebAbstractController.getModel.begin");
		Map<String, Object> model = newMap(request, response);
		processModel(request, response, model);
		log.debug("MyWebAbstractController.getModel.end");
		return model;
	}
	protected Map<String, Object> newMap(HttpServletRequest request, HttpServletResponse response) {
		log.debug("returning HashMap as MODEL by default");
		return new HashMap<String, Object>();
	}
	protected abstract void processModel(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model);
	
	protected String getView(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model) {
		log.debug("MyWebAbstractController.getView.begin");
		String view = getViewName(request, response, model);
		if(model == null) {
			log.warn("MODEL is NULL");
			view = getErrorViewName(request, response, model);
		}
		log.info("Returning view: " + view);
		log.debug("MyWebAbstractController.getView.end");
		return view;
	}
	protected abstract String getViewName(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model);
	protected String getErrorViewName(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model) {
		return "error";
	}
}
