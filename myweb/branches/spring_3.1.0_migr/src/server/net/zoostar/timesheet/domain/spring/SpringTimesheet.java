package net.zoostar.timesheet.domain.spring;

import net.zoostar.timesheet.domain.Timesheet;
import net.zoostar.timesheet.service.TimesheetState;
import net.zoostar.util.Messages;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringTimesheet extends Timesheet implements ApplicationContextAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4750921017597714704L;
	
	private ApplicationContext applicationContext;

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}

	public void afterPropertiesSet() throws Exception {
		TimesheetState state = (TimesheetState) applicationContext
				.getBean(afterPropertiesSetMessage()); //$NON-NLS-1$
		setCurrentState(state);
	}
	protected String afterPropertiesSetMessage() {
		return Messages.getString("Timesheet.timesheetStateCreated");
	}
}
