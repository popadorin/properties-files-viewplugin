package com.dorin.models;

import org.eclipse.core.resources.IResource;

public class PropertyFile {
	private IResource resource;
	private String name;
	private String projectName;
	
	public PropertyFile(String name, String projectName) {
		this.name = name;
		this.projectName = projectName;
	}
	
	public PropertyFile(IResource resource) {
		this.resource = resource;
		this.name = resource.getName();
		this.projectName = resource.getProject().getName();
	}

	public IResource getResource() {
		return resource;
	}
	
	public String getName() {
		return name;
	}

	public String getProjectName() {
		return projectName;
	}
		

}
