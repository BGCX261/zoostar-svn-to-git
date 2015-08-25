package net.zoostar.zant.taskdefs;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import net.zoostar.zant.EclipseClasspathVO;
import net.zoostar.zant.utils.EclipseClasspathHandler;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.taskdefs.Delete;
import org.xml.sax.SAXException;

public class EcpClean extends Delete {

	public void execute() {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = null;
		try {
			File basedir = getProject().getBaseDir();
			EclipseClasspathVO eclipseClasspath = new EclipseClasspathVO(basedir);
			
			parser = factory.newSAXParser();
			parser.parse(new File(basedir, ".classpath"), new EclipseClasspathHandler(eclipseClasspath));
			
			for(String subprojectBasedir : eclipseClasspath.getSubprojects()) {
				Project subproject = new Project();
				subproject.setBaseDir(new File(basedir.getParentFile(), subprojectBasedir));
				EcpClean task = getEcpClean();
				task.setProject(subproject);
				task.execute();
			}
			
			File dirOutput = new File(getProject().getBaseDir(), eclipseClasspath.getOutput());
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
	
	protected EcpClean getEcpClean() {
		return new EcpClean();
	}
	
	public static void main(String[] args) {
		Project project = new Project();
		project.setBaseDir(new File("../oneTradeApp"));
		Task task = new EcpClean();
		task.setProject(project);
		task.execute();
	}

}
