package com.dorin.myplugin.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import com.dorin.composites.PropertiesFilesComposite;

public class PropertiesFilesView extends ViewPart {
	
	private PropertiesFilesComposite composite;
	
	public PropertiesFilesView() {
		System.out.println("The constructor is called in the SampleView");
	}

	public void createPartControl(Composite parent) {
		System.out.println("The createPartControl method is called");
		composite = new PropertiesFilesComposite(parent, SWT.RESIZE);
	}
	
	public void setFocus() {
		System.out.println("The setFocus is called");
		refreshComposite();
	}
	
	private void refreshComposite() {
		Composite parent = composite.getParent();
		composite.dispose();
		createPartControl(parent);
		composite.getParent().layout();
	}
}
