package net.zoostar.zant.taskdefs;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import net.zoostar.zant.EclipseClasspathVO;
import net.zoostar.zant.utils.EclipseClasspathHandler;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Copy;
import org.apache.tools.ant.types.FileSet;
import org.xml.sax.SAXException;

public class EcpCopy extends Copy {

	public void execute() {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = null;
		try {
			File basedir = getProject().getBaseDir();
			EclipseClasspathVO eclipseClasspath = new EclipseClasspathVO(basedir);
			
			parser = factory.newSAXParser();
			parser.parse(new File(basedir, ".classpath"), new EclipseClasspathHandler(eclipseClasspath));
			
			FileSet tempFileSet = (FileSet)rcs.remove(0);
			rcs.removeAllElements(); //Reset FileSet
			for(String source : eclipseClasspath.getSources()) {
				FileSet fileSet = (FileSet)tempFileSet.clone();
				tempFileSet.setDir(new File(source));
				addFileset(fileSet);
			}
			
			File dirOutput = new File(getProject().getBaseDir(), eclipseClasspath.getOutput());
			if(!dirOutput.exists())
				dirOutput.mkdirs();
			setTodir(dirOutput);
			
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
		EcpCopy task = new EcpCopy();
		task.setProject(project);
		task.addFileset(new FileSet());
		task.execute();
	}
}
