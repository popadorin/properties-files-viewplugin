package com.dorin.myplugin.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import com.dorin.composites.MyComposite;


public class SampleView extends ViewPart {

	public SampleView() {
		System.out.println("The constructor is called in the SampleView");
	}

	public void createPartControl(Composite parent) {
		System.out.println("The createPartControl method is called");
		
		new MyComposite(parent, SWT.RESIZE);

	}
	
	public void setFocus() {
		System.out.println("The setFocus is called");
	}
}
