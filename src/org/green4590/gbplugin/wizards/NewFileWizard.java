package org.green4590.gbplugin.wizards;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.green4590.gbplugin.file.FileUtils;
import org.green4590.gbplugin.file.TemplateUtils;
import org.green4590.gbplugin.logger.GBLog;
import org.green4590.gbplugin.widgets.GBPopups;
import org.green4590.gbplugin.wizards.pages.INewFileWizardPage;

/**
 * A wizard the creates files from templates.
 * @author Programmer
 *
 */
public class NewFileWizard extends Wizard implements INewWizard {

	protected IProject project;
	private INewFileWizardPage page;
	private String templateFile;
	private String name;
	
	private String currentPackage;
	
	/**
	 * 
	 * @param name The name of the wizard.
	 * @param templateFile The name of the template file of the wizard
	 * @param page The page file of the wizard.
	 */
	public NewFileWizard(String name, String templateFile, INewFileWizardPage page) {
		this.page = page;
		this.templateFile = templateFile;
		this.name = name;
	}
	
	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		
		// save the IProject
		
		/*
		 * class org.eclipse.jdt.internal.core.PackageFragment
class org.eclipse.core.internal.resources.Project
class org.eclipse.jdt.internal.core.CompilationUnit

class org.eclipse.jdt.internal.core.PackageFragmentRoot
		 */
		
		//TODO auto package generation
		
		GBLog.getLogger().fine("Selection is " + selection.getFirstElement() + " from type  ");

		Object element = selection.getFirstElement();
		
		System.out.println(element.getClass());
		
		if (element == null) {
			MessageDialog.openError(new Shell(), "PLZ Select",
					"Plz select an element so i wont get NullPointerException");
		}
		if (element instanceof IResource) {
			IResource thing = (IResource) element;
			project = thing.getProject();
			currentPackage = ((IResource) element).getProjectRelativePath().toString();
			
		} else if (element instanceof IPackageFragment) {
			IPackageFragment thing = (IPackageFragment) element;
			project = thing.getJavaProject().getProject();
			currentPackage = thing.getPath().toString();
			
		} else if (element instanceof IPackageFragmentRoot) {
			// oooh thysti lil thang
			IPackageFragmentRoot thang = (IPackageFragmentRoot) element;
			project = thang.getJavaProject().getProject();
			currentPackage = thang.getPath().toString();
			
		} else if (element instanceof ICompilationUnit) {
			ICompilationUnit thang = (ICompilationUnit) element;
			project = thang.getJavaProject().getProject();
			currentPackage = thang.getPath().removeLastSegments(1).toString();
		}
		
		page.setPackageName(currentPackage.replace('/', '.').replaceFirst("." + project.getName() + ".src.", ""));
	}

	@Override
	public void addPages() {
		addPage(page);
	}

	@Override
	public boolean performFinish() {

		String packageName = page.getPackage();
		String fileName = page.getFileName() + ".java";
		Map<String, String> arguments = page.getProperties();

		return doFinish(project, (packageName.replace('.', File.separatorChar) + File.separator + fileName), templateFile, arguments);
	}

	private boolean doFinish(IProject project, String newFilePath, String template, Map<String, String> arguments) {
		try {
			TemplateUtils.createFileFromTemplate(project, newFilePath, FileUtils.DIRS.get("templates") + template,
					arguments);
		} catch (IOException | CoreException e) {
			GBPopups.Message(e);
			e.printStackTrace();
		}

		GBLog.getLogger().fine(name + ": created " + newFilePath + " from " + template);

		return true;
	}

}
