package org.green4590.gbplugin.logger;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.green4590.gbplugin.file.FileUtils;
import org.green4590.gbplugin.widgets.GBPopups;

/**
 * General logging class for the good of <s>Imanity</s> Humanity.
 * 
 * @author Ohad Gitz
 *
 */
public class GBLog {

	private static Logger logger = Logger.getLogger("GBLogger");
	private static boolean hasInitiated = false;

	/**
	 * Initializes all the logger related bullshit.
	 */
	private static void init() {
		hasInitiated = true;

		setupLogger();

		logger.setLevel(Level.ALL);

		logger.fine("Logger ready");
	}

	/**
	 * Sets up all the resources for the logger.
	 */
	private static void setupLogger() {
		String logFile = FileUtils.createFile(FileUtils.DIRS.get("logs") + getFormattedDate() + ".log").toString();

		try {
			logger.addHandler(new FileHandler(logFile));

			logger.fine("Logger resources ready - " + logFile);
		} catch (SecurityException | IOException e) {
			GBPopups.Message(e);
			e.printStackTrace();
		}

	}

	/**
	 * @return returns the logger instance.
	 */
	public static Logger getLogger() {
		if (!hasInitiated)
			init();

		return logger;
	}
	
	/**
	 * @return - the current time in YYYY-MM-DD HH-MM-SS.MMMM 
	 */
	private static String getFormattedDate() {
		return (new Timestamp(new Date().getTime()) + "").replace(':', '-');
	}
}
