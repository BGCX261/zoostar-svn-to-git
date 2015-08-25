package net.zoostar.roughcut.web.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.PropertyConfigurator;

public class Log4jServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public void init() {
		configureLog4j(getInitParameter("log4j-config-filename"));
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		//Empty Override
	}

	protected void configureLog4j(String filename) {
		String path = System.getProperty("domain.home");
		
		if(path == null)
			path = System.getenv("DOMAIN_HOME");
		
		if(path == null) {
			System.err.println("Error initializing log4j!");
			return;
		}
		
		if(filename == null)
			return;
		
		path += "/" + filename;
		System.out.println("Loading log4j configuration from: " + path);
		PropertyConfigurator.configure(path);
		System.out.println("Configured log4j from path: " + path);
	}
}
