package org.green4590.gbplugin.wizards;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.green4590.gbplugin.file.project.ProjectType;
import org.green4590.gbplugin.wizards.pages.INewProjectWizardPage;

public class NewProjectWizard extends Wizard implements INewWizard {
	
	private INewProjectWizardPage page;
	private ProjectType projectType;
	
	//TODO doFinish, project properties creation
	
	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		
	}

	
	@Override
	public boolean performFinish() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean createProject(String projectName, ProjectType project){
		
		return true;
	}

}
