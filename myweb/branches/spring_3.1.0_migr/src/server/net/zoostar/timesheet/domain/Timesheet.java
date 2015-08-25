package net.zoostar.timesheet.domain;

import java.io.Serializable;

import net.zoostar.timesheet.service.StateException;
import net.zoostar.timesheet.service.TimesheetState;

public class Timesheet implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2924283074239386294L;

	private TimesheetState previousState;
	private TimesheetState currentState;

	public Timesheet() {
		this.previousState = null;
		this.currentState = new TimesheetStateCreated();
	}
	
	public Timesheet(TimesheetState currentState) {
		this.currentState = currentState;
	}
	
	public TimesheetState getCurrentState() {
		return currentState;
	}

	public void setCurrentState(TimesheetState currentState) {
		if(!currentState.equals(previousState))
			setPreviousState(this.currentState);
		this.currentState = currentState;
	}
	
	public TimesheetState getPreviousState() {
		return previousState;
	}

	protected void setPreviousState(TimesheetState previousState) {
		this.previousState = previousState;
	}

	public static void main(String[] args) throws StateException {
		Timesheet timesheet = new Timesheet();
		timesheet.getCurrentState().submit(timesheet);
		timesheet.getCurrentState().approve(timesheet);
		timesheet.getCurrentState().reject(timesheet);
	}
}
