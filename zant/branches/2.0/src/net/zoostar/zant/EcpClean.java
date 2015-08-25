package net.zoostar.zant;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.taskdefs.Delete;
import org.xml.sax.SAXException;

public class EcpClean extends Delete {

	private EclipseClasspath eclipseClasspath;
	
	public EcpClean() {
		this(new EclipseClasspath());
	}
	
	public EcpClean(EclipseClasspath eclipseClasspath) {
		setEclipseClasspath(eclipseClasspath);
	}
	
	public EclipseClasspath getEclipseClasspath() {
		return eclipseClasspath;
	}

	public void setEclipseClasspath(EclipseClasspath eclipseClasspath) {
		this.eclipseClasspath = eclipseClasspath;
	}

	public void execute() {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser = null;
		try {
			saxParser = factory.newSAXParser();
			saxParser.parse(new File(getProject().getBaseDir(), ".classpath"),
					new EclipseClasspathHandler(getEclipseClasspath()));
			for(String strSubproject : getEclipseClasspath().getSubprojects()) {
				Project subproject = new Project();
				subproject.setBaseDir(new File(getProject().getBaseDir().getParent(), strSubproject));
				EcpClean task = new EcpClean();
				task.setProject(subproject);
				task.execute();
			}
			
			File dirOutput = new File(getProject().getBaseDir(), getEclipseClasspath().getOutput());
			setDir(dirOutput);
			
			super.execute();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Project project = new Project();
		project.setBaseDir(new File("../oneTradeApp"));
		Task task = new EcpClean();
		task.setProject(project);
		task.execute();
	}

}
