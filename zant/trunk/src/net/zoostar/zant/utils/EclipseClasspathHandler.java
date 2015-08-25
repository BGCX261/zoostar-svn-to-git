package net.zoostar.zant.utils;

import net.zoostar.zant.EclipseClasspathVO;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class EclipseClasspathHandler extends DefaultHandler {
	private EclipseClasspathVO eclipseClasspath;

	public EclipseClasspathHandler() {
		this(new EclipseClasspathVO());
	}
	
	public EclipseClasspathHandler(EclipseClasspathVO eclipseClasspath) {
		setEclipseClasspath(eclipseClasspath);
	}
	
	public EclipseClasspathVO getEclipseClasspath() {
		return eclipseClasspath;
	}

	public void setEclipseClasspath(EclipseClasspathVO eclipseClasspath) {
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
					eclipseClasspath.putSubproject(value);
				} else {
					eclipseClasspath.putSource(value);
				}
			} else if(type.equals("lib")) {
				if(value.startsWith("/"))
					value = ".." + value;
				eclipseClasspath.putLibrary(value);
			} else if(type.equals("output")) {
				eclipseClasspath.setOutput(value);
			}
		}
	}
}
