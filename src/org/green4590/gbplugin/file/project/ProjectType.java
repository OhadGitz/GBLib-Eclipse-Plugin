package org.green4590.gbplugin.file.project;

import org.eclipse.core.resources.IProject;

/**
 * Interface for project types
 * @author Programmer
 *
 */
interface ICreateableProjecType {
	IProject create(String projectName);
}

public enum ProjectType implements ICreateableProjecType {
	EMPTY(){

		@Override
		public IProject create(String projectName) {
			
			return ProjectUtils.createProject(projectName);
			
		}
		
	};
}
