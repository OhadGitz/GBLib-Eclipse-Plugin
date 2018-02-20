package org.green4590.gbplugin.wizards.pages.file;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.green4590.gbplugin.wizards.pages.INewFileWizardPage;

public class NewSubsystemWizardPage extends WizardPage implements INewFileWizardPage {

	Composite container;
	Label className;
	Text classNameTextbox;
	Label packageName;
	Text packageNameTextbox;

	public NewSubsystemWizardPage() {
		super("New Subsystem Page");
		setTitle("Create Subsystem");
		setDescription("Create a new Subsystem class file");
	}

	@Override
	public void createControl(Composite parent) {
		container = new Composite(parent, SWT.BORDER);
		
		container.setLayout(new GridLayout(2, false));
		
		className = new Label(container, SWT.NONE);
		className.setText("Class name: ");
		className.setLayoutData(new GridData());

		classNameTextbox = new Text(container, SWT.BORDER);
		//classNameTextbox.setText("NewSubsystem");
		classNameTextbox.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

		packageName = new Label(container, SWT.NONE);
		packageName.setText("Package: ");
		packageName.setLayoutData(new GridData());

		packageNameTextbox = new Text(container, SWT.BORDER);
		//packageNameTextbox.setText("src.");
		packageNameTextbox.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		setControl(container);
	}

	@Override
	public String getPackage() {
		return packageNameTextbox.getText();
	}

	@Override
	public String getFileName() {
		return classNameTextbox.getText();
	}

	@Override
	public Map<String, String> getProperties() {
		Map<String, String> ret = new HashMap<>();
		ret.put("class", getFileName());
		ret.put("package", getPackage());
		return ret;
	}

}
