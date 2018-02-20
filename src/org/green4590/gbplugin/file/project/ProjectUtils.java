package org.green4590.gbplugin.file.project;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.green4590.gbplugin.logger.GBLog;
import org.green4590.gbplugin.widgets.GBPopups;

public class ProjectUtils {
	
	private static IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
	
	/* just don't */
	private ProjectUtils(){}
	
	protected static IProject generateProject(String name){
		return root.getProject(name);
	}
	
	public static IProject createProject(String name){
		IProject ret = generateProject(name);
		
		IProjectDescription desc = root.getWorkspace().newProjectDescription(name);
		
		desc.setLocation( root.getLocation() );
		
		try {
			ret.create(null);
			
			ret.open(null); //TODO progress monitor
			
		} catch (CoreException e) {
			GBLog.getLogger().severe("Failed to create a project " + name + " " + e.toString());
			e.printStackTrace();
			GBPopups.Message(e);
		}
		
		return ret;
	}
	
}
