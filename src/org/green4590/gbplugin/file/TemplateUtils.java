package org.green4590.gbplugin.file;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.SequenceInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;

public class TemplateUtils {
	
	/**
	 * Returns the content of a file as a string 
	 */
	private static String getFileAsString(String template) throws IOException {
		byte[] bytes = Files.readAllBytes(Paths.get(template));
        
		String fileContent = new String(bytes,"UTF-8");
		return fileContent;
	}
	
	/**
	 * formats the content of a template file from the properties of the map.
	 */
	private static String format(String template,
			Map<String, String> arguments) {
		String ret = template;
		
		for (Map.Entry<String, String> entry : arguments.entrySet()){
			String var = "\\$" + entry.getKey() + "\\$";
			ret = ret.replaceAll(var, entry.getValue());
		}
		
		return ret;
	}
	
	/**
	 * Creates a file in a project with the content of a template files.
	 */
	public static void createFileFromTemplate(IProject project, String newFilePath, String templateFilePath,
			Map<String, String> arguments) throws IOException, CoreException {
		
		File file = FileUtils.createFile(project, newFilePath);
		
		String template = null;
		
		template = getFileAsString(templateFilePath);
		
		String newFileContent = format(template, arguments);
		
		System.out.println(file);
		
		Files.write(file.toPath(), newFileContent.getBytes());
	}
}
