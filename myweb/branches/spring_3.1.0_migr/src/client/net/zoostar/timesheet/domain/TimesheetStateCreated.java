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
	
	public void onUpdate(Timesheet timesheet) throws Exception {
		System.out.println("Timesheet created.");
	}
	
	public static void main(String[] args) {
		TimesheetState stateCreated1 = new TimesheetStateCreated();
		TimesheetState stateCreated2 = new TimesheetStateCreated();
		TimesheetState stateApproved1 = new TimesheetStateApproved();
		log.info("stateCreated1.equals(stateCreated2): ", stateCreated1.equals(stateCreated2));
		log.info("stateCreated1.equals(stateApproved1): ", stateCreated1.equals(stateApproved1));
	}
}
