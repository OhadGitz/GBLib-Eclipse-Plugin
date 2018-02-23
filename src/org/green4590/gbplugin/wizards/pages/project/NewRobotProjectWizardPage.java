package org.green4590.gbplugin.wizards.pages.project;

import java.util.List;
import java.util.Map;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.internal.actions.ModifyWorkingSetDelegate.NewWorkingSetAction;
import org.green4590.gbplugin.file.project.ProjectType;
import org.green4590.gbplugin.wizards.pages.INewProjectWizardPage;

public class NewRobotProjectWizardPage extends WizardPage implements INewProjectWizardPage {
	
	//TODO Default button selection
	//http://www.java2s.com/Code/Java/SWT-JFace-Eclipse/DefaultButton.html
	Composite container;
	Text projectNameBox;
	Button[] projectTypeButtons;
	
	protected NewRobotProjectWizardPage() {
		super("New Project Wizard");
		setTitle("New Project");
		setDescription("Create a new project");
		
		
		
	}
	
	private void initButtons(){
		ProjectType[] all = ProjectType.values();
		projectTypeButtons = new Button[all.length];
		for(int i = 0; i < all.length; i++){
			projectTypeButtons[i] = new Button(container, SWT.RADIO);;
			projectTypeButtons[i].setText(all[i].getName());
		}
	}

	@Override
	public String getName() {
		return "New robot project wizard page";
	}

	@Override
	public void createControl(Composite parent) {
		container = new Composite(parent, SWT.NONE);
		
		//TODO Show buttons and textbox
	}

	@Override
	public ProjectType getSelection() {
		// TODO Auto-generated method stub
		return null;
	}

}
