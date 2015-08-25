package net.zoostar.zant.taskdefs;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import net.zoostar.zant.EclipseClasspathVO;
import net.zoostar.zant.utils.EclipseClasspathHandler;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Jar;
import org.xml.sax.SAXException;

public class EcpJar extends Jar {

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
				EcpJar task = getEcpJar();
				task.setProject(subproject);
				task.execute();
			}

			super.execute();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected EcpJar getEcpJar() {
		return new EcpJar();
	}
}
