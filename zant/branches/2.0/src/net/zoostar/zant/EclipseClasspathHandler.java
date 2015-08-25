package net.zoostar.zant;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class EclipseClasspathHandler extends DefaultHandler {
	private EclipseClasspath eclipseClasspath;

	public EclipseClasspathHandler() {
		this(null);
	}
	
	public EclipseClasspathHandler(EclipseClasspath eclipseClasspath) {
		setEclipseClasspath(eclipseClasspath);
	}
	
	public EclipseClasspath getEclipseClasspath() {
		return eclipseClasspath;
	}

	public void setEclipseClasspath(EclipseClasspath eclipseClasspath) {
		this.eclipseClasspath = eclipseClasspath;
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if(qName.equals("classpathentry")) {
			String type = attributes.getValue("kind");
			String value = attributes.getValue("path");
			if(type.equals("src")) {
				if(value.startsWith("/")) {
					eclipseClasspath.getSubprojects().add(value);
				} else {
					eclipseClasspath.getSources().add(value);
				}
			} else if(type.equals("lib")) {
				if(value.startsWith("/"))
					value = ".." + value;
				eclipseClasspath.getLibraries().add(value);
			} else if(type.equals("output")) {
				eclipseClasspath.setOutput(value);
			}
		}
	}
}
