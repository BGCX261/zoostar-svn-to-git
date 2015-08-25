package net.zoostar.zant;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Javac;
import org.apache.tools.ant.types.Path;
import org.xml.sax.SAXException;

public class EcpCompile extends Javac {

	private EclipseClasspath eclipseClasspath;
	
	public EcpCompile() {
		this(new EclipseClasspath());
	}
	
	public EcpCompile(EclipseClasspath eclipseClasspath) {
		setEclipseClasspath(eclipseClasspath);
	}
	
	public EclipseClasspath getEclipseClasspath() {
		return eclipseClasspath;
	}

	public void setEclipseClasspath(EclipseClasspath eclipseClasspath) {
		this.eclipseClasspath = eclipseClasspath;
	}

	@Override
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
				EcpCompile task = new EcpCompile();
				task.setProject(subproject);
				task.execute();
				
				getEclipseClasspath().getLibraries().add(task.getDestdir().toString());
			}

			for(String source : getEclipseClasspath().getSources()) {
				setSrcdir(new Path(getProject(), source));
			}
			
			for(String library : getEclipseClasspath().getLibraries()) {
				setClasspath(new Path(getProject(), library));
			}
			
			File dirOutput = new File(getProject().getBaseDir(), getEclipseClasspath().getOutput());
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
	
	public static void main(String[] args) {
		Project project = new Project();
		project.setBaseDir(new File("../oneTradeApp"));
		EcpCompile task = new EcpCompile();
		task.setProject(project);
		task.execute();
	}
}
