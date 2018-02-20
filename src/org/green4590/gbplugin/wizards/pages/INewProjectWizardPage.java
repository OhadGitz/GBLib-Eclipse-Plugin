package org.green4590.gbplugin.wizards.pages;

import java.util.List;
import java.util.Map;

public interface INewProjectWizardPage {
	
	String getName();
	
	List<Map<String, String>> getProperties();
	
}
