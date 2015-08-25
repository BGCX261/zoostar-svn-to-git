package net.zoostar.zant;

import java.io.File;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class EclipseClasspathVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3860101086758301288L;

	private File basedir;
	private Set<String> sources;
	private Set<String> libraries;
	private String output;
	
	private Set<String> subprojects;

	public EclipseClasspathVO() {
		this("");
	}

	public EclipseClasspathVO(String basedir) {
		this(new File(basedir));
	}

	public EclipseClasspathVO(File basedir) {
		this.setBasedir(basedir);
		this.setSources(new HashSet<String>());
		this.setLibraries(new HashSet<String>());
		this.setOutput(null);
		this.setSubprojects(new HashSet<String>());
	}
	
	public File getBasedir() {
		return basedir;
	}

	public void setBasedir(File basedir) {
		this.basedir = basedir;
	}

	public Set<String> getSubprojects() {
		return subprojects;
	}

	public void setSubprojects(Set<String> subprojects) {
		this.subprojects = subprojects;
	}
	
	public void putSubproject(String subproject) {
		this.subprojects.add(subproject);
	}

	public Set<String> getSources() {
		return sources;
	}
	
	public void setSources(Set<String> sources) {
		this.sources = sources;
	}

	public void putSource(String source) {
		this.sources.add(source);
	}
	
	public Set<String> getLibraries() {
		return libraries;
	}

	public void setLibraries(Set<String> libraries) {
		this.libraries = libraries;
	}

	public void putLibrary(String library) {
		this.libraries.add(library);
	}
	
	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}
}
