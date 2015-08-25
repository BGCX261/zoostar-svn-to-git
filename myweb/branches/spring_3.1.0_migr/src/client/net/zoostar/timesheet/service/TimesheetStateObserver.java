package net.zoostar.timesheet.service;

import java.util.Observable;
import java.util.Observer;

import org.apache.log4j.Logger;

import net.zoostar.timesheet.domain.Timesheet;

public class TimesheetStateObserver implements Observer {

	static final Logger log = Logger.getLogger(TimesheetStateObserver.class);
	
	public void update(Observable observable, Object object) {
		if(observable instanceof TimesheetState && object instanceof Timesheet) {
			Timesheet timesheet = (Timesheet)object;
			try {
				timesheet.getCurrentState().onUpdate(timesheet);
			} catch (Exception e) {
				log.error(e.getMessage(), e.fillInStackTrace());
			}
		}
	}
}
