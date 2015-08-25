package net.zoostar.timesheet.domain.spring;

import net.zoostar.timesheet.domain.TimesheetStateCreated;
import net.zoostar.timesheet.domain.TimesheetStateSubmitted;
import net.zoostar.util.Messages;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringTimesheetStateCreated extends TimesheetStateCreated
implements ApplicationContextAware {

	private ApplicationContext applicationContext;

	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}

	protected TimesheetStateSubmitted getTimesheetStateSubmitted() {
		return (TimesheetStateSubmitted) getApplicationContext()
		.getBean(Messages.getString(submitMessage()));
	}

	protected String submitMessage() {
		return "TimesheetStateCreated.timesheetStateSubmitted"; //$NON-NLS-1$
	}
}
