package net.zoostar.timesheet.domain;

import net.zoostar.timesheet.service.TimesheetState;

public class TimesheetStateSubmitted extends AbstractTimesheetState {

	public void approve(Timesheet timesheet) {
		TimesheetState state = getTimesheetStateApproved();
		updateState(timesheet, state);
	}
	protected TimesheetStateApproved getTimesheetStateApproved() {
		return new TimesheetStateApproved();
	}

	public void reject(Timesheet timesheet) {
		TimesheetState state = getTimesheetStateRejected();
		updateState(timesheet, state);
	}
	protected TimesheetStateRejected getTimesheetStateRejected() {
		return new TimesheetStateRejected();
	}
	
	public void onUpdate(Timesheet timesheet) throws Exception {
		System.out.println("Timesheet submitted.");
	}
}
