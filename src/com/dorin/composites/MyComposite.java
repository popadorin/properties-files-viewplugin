package com.dorin.composites;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;

public class MyComposite extends Composite {

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public MyComposite(Composite parent, int style) {
		super(parent, style);
		
		Label lblLabel = new Label(this, SWT.NONE);
		lblLabel.setBounds(26, 50, 55, 15);
		lblLabel.setText("Label1");
		
		Label lblLabel_1 = new Label(this, SWT.NONE);
		lblLabel_1.setBounds(26, 98, 55, 15);
		lblLabel_1.setText("Label2");

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
