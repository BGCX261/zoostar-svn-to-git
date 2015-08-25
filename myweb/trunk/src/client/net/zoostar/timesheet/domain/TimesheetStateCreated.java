package net.zoostar.timesheet.domain;

import net.zoostar.timesheet.service.StateException;
import net.zoostar.timesheet.service.TimesheetState;


public class TimesheetStateCreated extends AbstractTimesheetState {

	public void submit(Timesheet timesheet) throws StateException {
		TimesheetState state = getTimesheetStateSubmitted();
		updateState(timesheet, state);
	}
	protected TimesheetStateSubmitted getTimesheetStateSubmitted() {
		return new TimesheetStateSubmitted();
	}
	
	public static void main(String[] args) {
		TimesheetState stateCreated1 = new TimesheetStateCreated();
		TimesheetState stateCreated2 = new TimesheetStateCreated();
		TimesheetState stateApproved1 = new TimesheetStateApproved();
		System.out.println("stateCreated1.equals(stateCreated2): " + stateCreated1.equals(stateCreated2));
		System.out.println("stateCreated1.equals(stateApproved1): " + stateCreated1.equals(stateApproved1));
	}
}
