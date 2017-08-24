package com.dorin.composites;

import org.eclipse.swt.widgets.Composite;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Button;

public class MyComposite extends Composite {
	private Table table;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public MyComposite(Composite parent, int style) {
		super(parent, style);
		
		Button btnNewButton = new Button(this, SWT.NONE);
		btnNewButton.setBounds(10, 25, 75, 25);
		btnNewButton.setText("New file");
		
		table = new Table(this, SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(10, 56, 200, 200);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		
		String[] titles = { "File name", "Project", "Action"};
	    for (int i = 0; i < titles.length; i++) {
	      TableColumn column = new TableColumn(table, SWT.NONE);
	      column.setText(titles[i]);
	    }
	    
	    for (int i = 0; i < 10; i++) {
	        TableItem item = new TableItem(table, SWT.NONE);
	        item.setText(0, "filename");
	        item.setText(1, "project name");
	        item.setText(2, "actions");
	    }
	    
	    for (int i=0; i<titles.length; i++) {
	        table.getColumn(i).pack();
	    }   
		
	    table.setSize(table.computeSize(SWT.DEFAULT, 200));
	    
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	
}
