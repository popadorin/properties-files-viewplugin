package com.dorin.models;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

public class PropertiesFile {
	private IFile file;
	private String name;
	private String projectRelativePath;
	private String projectName;
	
	public PropertiesFile(String name, String projectName) {
		this.name = name;
		this.projectName = projectName;
	}
	
	public PropertiesFile(IResource resource) {
		this.file = (IFile) resource;
		this.name = resource.getName();
		this.projectRelativePath = "" + resource.getProjectRelativePath();
		this.projectName = resource.getProject().getName();
	}

	public void open() throws PartInitException {
	    IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
	    IDE.openEditor(page, file);
	} 
	
	public IFile getFile() {
		return file;
	}
	
	public String getName() {
		return name;
	}
	
	public String getProjectRelativePath() {
		return projectRelativePath;
	}

	public String getProjectName() {
		return projectName;
	}

}
