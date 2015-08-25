package net.zoostar.timesheet.domain.spring;

import net.zoostar.timesheet.domain.TimesheetStateApproved;
import net.zoostar.timesheet.domain.TimesheetStateSubmitted;
import net.zoostar.util.Messages;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;


public class SpringTimesheetStateSubmitted extends TimesheetStateSubmitted
implements ApplicationContextAware {

	private ApplicationContext applicationContext;

	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}

	protected TimesheetStateApproved getTimesheetStateApproved() {
		return (TimesheetStateApproved) getApplicationContext().getBean(Messages.getString(approveMessage()));
	}

	protected String approveMessage() {
		return "TimesheetStateSubmitted.timesheetStateApproved"; //$NON-NLS-1$
	}
}
