package net.zoostar.timesheet.domain;

import java.io.Serializable;

import net.zoostar.timesheet.service.StateException;
import net.zoostar.timesheet.service.TimesheetState;

public class Timesheet implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2924283074239386294L;

	private TimesheetState state;

	public Timesheet() {
		this.state = new TimesheetStateCreated();
	}
	
	public Timesheet(TimesheetState state) {
		this.state = state;
	}
	
	public TimesheetState getState() {
		return state;
	}

	public void setState(TimesheetState state) {
		this.state = state;
	}
	
	public static void main(String[] args) throws StateException {
		Timesheet timesheet = new Timesheet();
		timesheet.getState().submit(timesheet);
		timesheet.getState().approve(timesheet);
		timesheet.getState().reject(timesheet);
	}
}
