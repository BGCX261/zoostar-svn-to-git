package net.zoostar.timesheet.domain;

import java.util.Observable;

import net.zoostar.timesheet.service.StateException;
import net.zoostar.timesheet.service.TimesheetState;
import net.zoostar.util.Messages;

import org.apache.log4j.Logger;

public abstract class AbstractTimesheetState extends Observable
implements TimesheetState {

	static final Logger log = Logger.getLogger(AbstractTimesheetState.class);
	
	private String type;

	public AbstractTimesheetState() {
		this.type = getClass().getName();
	}

	public String getType() {
		return type;
	}

	public void updateState(Timesheet timesheet, TimesheetState state) {
		timesheet.setState(state);
		setChanged();
		notifyObservers(timesheet);
	}
	
	public void submit(Timesheet timesheet) throws StateException {
		throw new StateException(Messages.getString(timesheetExceptionMessage()));
	}

	public void approve(Timesheet timesheet) throws StateException {
		throw new StateException(Messages.getString(timesheetExceptionMessage()));
	}

	public void reject(Timesheet timesheet) throws StateException {
		throw new StateException(Messages.getString(timesheetExceptionMessage()));
	}
	
	protected String timesheetExceptionMessage() {
		return "AbstractTimesheetState.0"; //$NON-NLS-1$
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractTimesheetState other = (AbstractTimesheetState) obj;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return type;
	}
}
