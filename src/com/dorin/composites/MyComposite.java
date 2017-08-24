package com.dorin.composites;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

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
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				loadFiles();
			}
		});
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

	private void loadFiles() {
		try {
			// Iterate through files and get the ones which have extension .properties
			ResourcesPlugin.getWorkspace().getRoot().accept(new IResourceVisitor() {
				
				@Override
				public boolean visit(IResource resource) throws CoreException {
					if (resource.getName().endsWith(".properties")) {
						System.out.println("resource name: " + resource.getName());
						System.out.println("resource project: " + resource.getProject());
						IFile file = (IFile) resource;
						openFile(file);
					}
					
					return true;
				}
				
			}, 2, false);
			
		} catch (Exception e) {
			System.out.println("Exception bad");
			e.printStackTrace();
		}
		System.out.println("finished checking files");
	}
	
	private void openFile(IFile file) {
	    IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
	    try {
			IDE.openEditor(page, file);
		} catch (PartInitException e) {
			System.out.println("The partInitException occured");
			e.printStackTrace();
		}
		    
	}
	
}
