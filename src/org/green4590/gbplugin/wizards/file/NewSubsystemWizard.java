package org.green4590.gbplugin.wizards.file;

import org.green4590.gbplugin.wizards.NewFileWizard;
import org.green4590.gbplugin.wizards.pages.file.NewSubsystemWizardPage;

public class NewSubsystemWizard extends NewFileWizard {

	public NewSubsystemWizard() {
		super("Subsystem Wizard", "Subsystem.java", new NewSubsystemWizardPage());
	}

}
