package org.green4590.gbplugin.wizards.pages;

import java.util.Map;

import org.eclipse.jface.wizard.IWizardPage;

/**
 * A wizard page for pages for creating files.
 * @author Programmer
 *
 */
public interface INewFileWizardPage extends IWizardPage {

	String getPackage();

	String getFileName();

	Map<String, String> getProperties();

}
