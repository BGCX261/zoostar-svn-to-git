package net.zoostar.timesheet.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.zoostar.timesheet.domain.Timesheet;
import net.zoostar.timesheet.domain.spring.SpringTimesheet;
import net.zoostar.timesheet.service.StateException;
import net.zoostar.timesheet.service.TimesheetState;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

@Controller
public class TimesheetMultiActionController extends MultiActionController {

	static final String KEY_ERROR_MSG = "errorMessage";
	static final Logger log = Logger.getLogger(TimesheetMultiActionController.class);
	
	@RequestMapping("/handleTimesheetSubmit.html")
	public final ModelAndView handleTimesheetSubmit(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> model = getHandleTimesheetSubmitModel(request, response);
		String view = getHandleTimesheetSubmitView(request, response, model);
		return new ModelAndView(view, "model", model);
	}
	protected Map<String, Object> getHandleTimesheetSubmitModel(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> model = new HashMap<String, Object>();
		String currentState = request.getParameter("currentTimesheetState"); //eg. TimesheetStateCreated
		if(StringUtils.isNotBlank(currentState)) {
			TimesheetState state = (TimesheetState)getApplicationContext().getBean(currentState);
			Timesheet timesheet = new SpringTimesheet();
			timesheet.setCurrentState(state);
			try {
				timesheet.getCurrentState().submit(timesheet);
				model.put("timesheet", timesheet);
			} catch (StateException e) {
				model.put(KEY_ERROR_MSG, e.getMessage());
				log.error(e.getMessage(), e.fillInStackTrace());
			}
		} else {
			model.put(KEY_ERROR_MSG, "request.getParameter(\"currentTimesheetState\") is NULL!");
			log.error("request.getParameter(\"currentTimesheetState\") is NULL!");
		}
		return model;
	}
	protected String getHandleTimesheetSubmitView(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model) {
		if(model == null || StringUtils.isNotBlank((String)model.get(KEY_ERROR_MSG)))
			return getErrorView();
		else
			return getCommonView();
	}
	
	@RequestMapping("/handleTimesheetApprove.html")
	public final ModelAndView handleTimesheetApprove(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> model = getHandleTimesheetApproveModel(request, response);
		String view = getHandleTimesheetApproveView(request, response, model);
		return new ModelAndView(view, "model", model);
	}
	protected Map<String, Object> getHandleTimesheetApproveModel(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> model = new HashMap<String, Object>();
		String currentState = request.getParameter("currentTimesheetState"); //eg. TimesheetStateCreated
		if(StringUtils.isNotBlank(currentState)) {
			TimesheetState state = (TimesheetState)getApplicationContext().getBean(currentState);
			Timesheet timesheet = new SpringTimesheet();
			timesheet.setCurrentState(state);
			try {
				timesheet.getCurrentState().submit(timesheet);
				model.put("timesheet", timesheet);
			} catch (StateException e) {
				log.error(e.getMessage(), e.fillInStackTrace());
			}
		} else {
			log.error("request.getParameter(\"currentTimesheetState\") is NULL!");
			model = null;
		}
		return model;
	}
	protected String getHandleTimesheetApproveView(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model) {
		if(model == null)
			return getErrorView();
		else
			return getCommonView();
	}
	
	protected String getCommonView() {
		return "timesheetstateconfirmation";
	}
	protected String getErrorView() {
		return "error";
	}
}
