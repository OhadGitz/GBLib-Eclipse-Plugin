package org.green4590.gbplugin.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.internal.resources.Folder;
import org.eclipse.core.internal.resources.Workspace;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.OperationCanceledException;
import org.green4590.gbplugin.logger.GBLog;
import org.green4590.gbplugin.widgets.GBPopups;

/**
 * Class for general purpose files and template handling
 * 
 * @author Ohad Gitz
 *
 */
@SuppressWarnings("all")
public class FileUtils {
	
	/* should not be called */
	private FileUtils() {}
	
	private static final char separator = File.separatorChar; // im lazy
	
	/**
	 * the standard GBLib directory at C:\User\$User\GBLib\
	 */
	public static String GBLibDirectory = System.getProperty("user.home") + separator + "GBLib" + separator;
	
	/**
	 * a map for containing and managing all directories
	 * TODO abstract this shit
	 */
	public static final Map<String, String> DIRS = new HashMap<>();

	/**
	 * Creates all the GBLib resources folders and files
	 */
	public static void setupGBLibFiles() {
		DIRS.put("gblib", GBLibDirectory);
		DIRS.put("logs", DIRS.get("gblib") + "logger" + separator );
		DIRS.put("resources", DIRS.get("gblib") + "resources" + separator );
		DIRS.put("thumbnails", DIRS.get("resources") + "thumbnails" + separator );
		DIRS.put("templates", DIRS.get("resources") + "templates" + separator );
		
		for(String folder : DIRS.values()){
			createFolder(folder);
		}
	}
	
	/**
	 * Create a folder inside a project.
	 * relative path should not contain the \src\ folder so plz.
	 */
	public static File createFolder(IProject project, String relative){
		File ret = createFolder(project.getLocation().toString() + File.separator + "src" + File.separator + relative);
		try {
			project.refreshLocal(IResource.DEPTH_INFINITE, null);
		} catch (CoreException e) {
			GBLog.getLogger().warning("Failed to refresh after folder creation " + e.toString());
			e.printStackTrace();
			GBPopups.Message(e);
		}
		return ret;
	}
	
	/**
	 * Create a file inside a project.
	 * relative path should not contain the \src\ folder so plz.
	 * 
	 * will create all parent directories.
	 */
	public static File createFile(IProject project, String relative){
		File file = new File(project.getLocation().toString() + File.separator + "src" + File.separator + relative);
		createFolder(file.getParent());
		File ret = createFile(file.getPath());
		try {
			project.refreshLocal(IResource.DEPTH_INFINITE, null);
		} catch (CoreException e) {
			GBLog.getLogger().warning("Failed to refresh after folder creation " + e.toString());
			e.printStackTrace();
			GBPopups.Message(e);
		}
		return ret;
	}
	
	/**
	 * Creates a folder in a path.
	 */
	public static File createFolder(String path){
		GBLog.getLogger().fine("Trying to create Folder " + path);
		
		if(Files.exists(Paths.get(path))){
			GBLog.getLogger().fine(path + " exists");
			return new File(path);
		}
		
		try {
			Files.createDirectories(Paths.get(path));
		} catch (IOException e) {
			GBLog.getLogger().severe(e.toString());
			GBPopups.Message(e);
			e.printStackTrace();
			return null;
		}
		
		GBLog.getLogger().fine("Created " + path);
		return new File(path);
	}
	
	/**
	 * Create a file in a path. will create all parent folders.
	 */
	public static File createFile(String path){
		
		File file = new File(path);
		
		GBLog.getLogger().fine("Trying to create file " + path);
		
		if(Files.exists(Paths.get(path))){
			GBLog.getLogger().fine(path + " exists");
			return file;
		}
		
		try {
			createFolder(file.getParent());
			Files.createFile(Paths.get(path));
			
		} catch (IOException e) {
			GBLog.getLogger().severe(e.toString());
			GBPopups.Message(e);
			e.printStackTrace();
			return null;
		}
		
		GBLog.getLogger().fine("Created " + path);
		return file;
	}

}
