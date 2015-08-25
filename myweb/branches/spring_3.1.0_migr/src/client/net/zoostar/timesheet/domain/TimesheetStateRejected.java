package net.zoostar.timesheet.domain;

public class TimesheetStateRejected extends TimesheetStateCreated {

	@Override
	public void onUpdate(Timesheet timesheet) throws Exception {
		System.out.println("Timesheet rejected!");
	}
}
