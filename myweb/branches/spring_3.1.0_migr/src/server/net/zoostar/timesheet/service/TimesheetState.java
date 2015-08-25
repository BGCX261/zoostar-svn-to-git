package net.zoostar.timesheet.service;

import net.zoostar.timesheet.domain.Timesheet;


public interface TimesheetState {
	public void submit(Timesheet timesheet) throws StateException;
	public void approve(Timesheet timesheet) throws StateException;
	public void reject(Timesheet timesheet) throws StateException;
	public void onUpdate(Timesheet timesheet) throws Exception;
}
