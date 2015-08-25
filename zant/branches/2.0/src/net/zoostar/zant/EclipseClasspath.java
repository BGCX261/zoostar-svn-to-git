package net.zoostar.zant;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class EclipseClasspath implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3860101086758301288L;

	private Set<String> subprojects;
	private Set<String> sources;
	private Set<String> libraries;
	private String output;
	
	public EclipseClasspath() {
		this.setSubprojects(new HashSet<String>());
		this.setSources(new HashSet<String>());
		this.setLibraries(new HashSet<String>());
		this.setOutput(null);
	}

	public Set<String> getSubprojects() {
		return subprojects;
	}

	public void setSubprojects(Set<String> subprojects) {
		this.subprojects = subprojects;
	}

	public Set<String> getSources() {
		return sources;
	}

	public void setSources(Set<String> sources) {
		this.sources = sources;
	}

	public Set<String> getLibraries() {
		return libraries;
	}

	public void setLibraries(Set<String> libraries) {
		this.libraries = libraries;
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}
}
