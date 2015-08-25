package net.zoostar.timesheet.service;

import net.zoostar.timesheet.domain.Timesheet;
import net.zoostar.timesheet.service.TimesheetManager;


public class TimesheetManagerImpl implements TimesheetManager {

	public void submit(Timesheet timesheet) throws StateException {
		timesheet.getState().submit(timesheet);
	}

	public void approve(Timesheet timesheet) throws StateException {
		timesheet.getState().approve(timesheet);
	}

	public void reject(Timesheet timesheet) throws StateException {
		timesheet.getState().reject(timesheet);
	}
}
