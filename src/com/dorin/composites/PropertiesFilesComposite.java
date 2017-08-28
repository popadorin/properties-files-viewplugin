package com.dorin.composites;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.PartInitException;

import com.dorin.models.PropertiesFile;
import org.eclipse.swt.layout.FillLayout;

public class PropertiesFilesComposite extends Composite {
	private Table table;
	private String[] titles;
	private List<PropertiesFile> files = new ArrayList<>();
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public PropertiesFilesComposite(Composite parent, int style) {
		super(parent, style);
		loadFiles();
		setLayout(new FillLayout(SWT.HORIZONTAL));
		
		table = new Table(this, SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent event) {
				openFileOn(event);
			}
		});
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		titles = new String[] {"File name", "Path (project-relative)", "Project"};
	    for (int i = 0; i < titles.length; i++) {
	      TableColumn column = new TableColumn(table, SWT.NONE);
	      column.setText(titles[i]);
	    }
	    
	    for (int i = 0; i < files.size(); i++) {
	        TableItem item = new TableItem(table, SWT.NONE);
	        item.setText(0, files.get(i).getName());
	        item.setText(1, files.get(i).getProjectRelativePath());
	        item.setText(2, files.get(i).getProjectName());
	    }
	    
	    for (int i = 0; i < titles.length; i++) {
	        table.getColumn(i).pack();
	    }
	
	}

	private void loadFiles() {
		try {
			// Iterate through files and get the ones which have extension .properties
			ResourcesPlugin.getWorkspace().getRoot().accept(new IResourceVisitor() {
				
				@Override
				public boolean visit(IResource resource) throws CoreException {
					if (resource.getName().endsWith(".properties")) {
						files.add(new PropertiesFile(resource));
					}
					
					return true;
				}
				
			}, 2, false);
			
		} catch (Exception e) {
			System.out.println("Failed to load the files!\n");
			e.printStackTrace();
		}
	}
	
	private void openFileOn(MouseEvent event) {
		Point pt = new Point(event.x, event.y);
        TableItem item = table.getItem(pt);
        if (item == null)
        	return;
		
	  	Rectangle rect = item.getBounds(0);
	  
	  	if (rect.contains(pt)) {  
			for (PropertiesFile file : files) {
				if (selectedItemIsFile(item, file)) {
					try {
						file.open();
					} catch (PartInitException e) {
						System.out.println("Failed to open file: " + file.getName());
						e.printStackTrace();
					}
				}
			}
	  	}

	}
	
	private boolean selectedItemIsFile(TableItem item, PropertiesFile file) {
		return file.getName().equals(item.getText(0)) && 
				file.getProjectName().equals(item.getText(2));
	}
	
	
}
