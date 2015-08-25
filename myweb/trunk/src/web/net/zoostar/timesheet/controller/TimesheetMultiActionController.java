package net.zoostar.timesheet.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.zoostar.timesheet.domain.Timesheet;
import net.zoostar.timesheet.service.StateException;
import net.zoostar.timesheet.service.TimesheetState;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

@Controller
public class TimesheetMultiActionController extends MultiActionController {

	@RequestMapping("/handleTimesheetSubmit.do")
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
		TimesheetState state = (TimesheetState)getApplicationContext().getBean(currentState);
		Timesheet timesheet = new Timesheet();
		timesheet.setState(state);
		try {
			timesheet.getState().submit(timesheet);
			model.put("timesheet", timesheet);
		} catch (StateException e) {
			e.printStackTrace();
		}
		return model;
	}
	protected String getHandleTimesheetSubmitView(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> model) {
		if(model == null)
			return getErrorView();
		else
			return getCommonView();
	}
	
	@RequestMapping("/handleTimesheetApprove.do")
	public final ModelAndView handleTimesheetApprove(HttpServletRequest request,
			HttpServletResponse response) {
		String view = getCommonView();
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("timesheet", new Timesheet());
		return new ModelAndView(view, "model", model);
	}
	
	protected String getCommonView() {
		return "timesheetstateconfirmation";
	}
	protected String getErrorView() {
		return "error";
	}
}
