package net.zoostar.zant.taskdefs;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import net.zoostar.zant.EclipseClasspathVO;
import net.zoostar.zant.utils.EclipseClasspathHandler;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Javac;
import org.apache.tools.ant.types.Path;
import org.xml.sax.SAXException;

public class EcpCompile extends Javac {

	@Override
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
				EcpCompile task = getEcpCompile();
				task.setProject(subproject);
				task.execute();
				
				eclipseClasspath.getLibraries().add(task.getDestdir().toString());
			}

			for(String source : eclipseClasspath.getSources()) {
				setSrcdir(new Path(getProject(), source));
			}
			
			for(String library : eclipseClasspath.getLibraries()) {
				setClasspath(new Path(getProject(), library));
			}
			
			File dirOutput = new File(getProject().getBaseDir(), eclipseClasspath.getOutput());
			if(!dirOutput.exists())
				dirOutput.mkdirs();
			setDestdir(dirOutput);
			
			super.execute();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected EcpCompile getEcpCompile() {
		return new EcpCompile();
	}
	
	public static void main(String[] args) {
		Project project = new Project();
		project.setBaseDir(new File("../oneTradeApp"));
		EcpCompile task = new EcpCompile();
		task.setProject(project);
		task.execute();
	}
}
