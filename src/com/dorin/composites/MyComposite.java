package com.dorin.composites;

import java.util.ArrayList;
import java.util.List;

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

import com.dorin.models.PropertyFile;

public class MyComposite extends Composite {
	private Table table;
	private List<PropertyFile> files = new ArrayList<>();
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public MyComposite(Composite parent, int style) {
		super(parent, style);
		loadFiles();
		
		Button btnNewButton = new Button(this, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
//				loadFiles();
			}
		});
		btnNewButton.setBounds(10, 25, 75, 25);
		btnNewButton.setText("New file");
		
		table = new Table(this, SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(10, 56, 200, 200);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		
		String[] titles = { "File name", "Project"};
	    for (int i = 0; i < titles.length; i++) {
	      TableColumn column = new TableColumn(table, SWT.NONE);
	      column.setText(titles[i]);
	    }
	    
	    for (int i = 0; i < files.size(); i++) {
	        TableItem item = new TableItem(table, SWT.NONE);
	        item.setText(0, files.get(i).getName());
	        item.setText(1, files.get(i).getProjectName());
//	        item.setText(2, "actions");
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
						files.add(new PropertyFile(resource));
						
						System.out.println("resource name: " + resource.getName());
						System.out.println("resource project: " + resource.getProject());
						IFile file = (IFile) resource;
//						openFile(file);
					}
					
					return true;
				}
				
			}, 2, false);
			
		} catch (Exception e) {
			System.out.println("Failed to load the files!\n");
			e.printStackTrace();
		}
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
