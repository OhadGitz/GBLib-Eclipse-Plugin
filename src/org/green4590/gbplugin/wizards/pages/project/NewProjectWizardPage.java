package org.green4590.gbplugin.wizards.pages.project;

import java.util.List;
import java.util.Map;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.green4590.gbplugin.wizards.pages.INewProjectWizardPage;

public class NewProjectWizardPage extends WizardPage implements INewProjectWizardPage {
	
	//TODO Default button selection
	//http://www.java2s.com/Code/Java/SWT-JFace-Eclipse/DefaultButton.html
	Composite container;
	Text projectNameBox;
	
	protected NewProjectWizardPage() {
		super("New Project Wizard");
		setTitle("New Project");
		setDescription("Create a new project");
	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public List<Map<String, String>> getProperties() {
		return null;
	}

	@Override
	public void createControl(Composite parent) {
		
	}

}
