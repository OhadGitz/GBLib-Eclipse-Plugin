package org.green4590.gbplugin.file.project;

import org.eclipse.core.resources.IProject;

/**
 * Interface for project types
 * @author Programmer
 *
 */
interface ICreateableProjectType {
	String getName();
	IProject create(String projectName);
}

public enum ProjectType implements ICreateableProjectType {
	EMPTY(){

		@Override
		public IProject create(String projectName) {
			return ProjectUtils.createProject(projectName);
		}

		@Override
		public String getName() {
			// TODO Auto-generated method stub
			return "Empty project";
		}
		
	},
	
	TEST(){

		@Override
		public IProject create(String projectName) {
			IProject project = ProjectUtils.createProject(projectName);
			//project.cr
			return null;
		}

		@Override
		public String getName() {
			// TODO Auto-generated method stub
			return "Test robot project";
		}
		
	};
}
