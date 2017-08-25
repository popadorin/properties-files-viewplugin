package com.dorin.models;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

public class PropertyFile {
	private IFile file;
	private String name;
	private String projectName;
	
	public PropertyFile(String name, String projectName) {
		this.name = name;
		this.projectName = projectName;
	}
	
	public PropertyFile(IResource resource) {
		this.file = (IFile) resource;
		this.name = resource.getName();
		this.projectName = resource.getProject().getName();
	}

	public void open() {
	    IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
	    try {
			IDE.openEditor(page, file);
		} catch (PartInitException e) {
			System.out.println("The partInitException occured");
			e.printStackTrace();
		}
		    
	} 
	
	public IResource getResource() {
		return file;
	}
	
	public String getName() {
		return name;
	}

	public String getProjectName() {
		return projectName;
	}

}
