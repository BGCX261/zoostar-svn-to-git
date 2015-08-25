package net.zoostar.timesheet.domain;

public class TimesheetStateApproved extends AbstractTimesheetState {

	public void onUpdate(Timesheet timesheet) throws Exception {
		System.out.println("Timesheet approved!");
	}

}
