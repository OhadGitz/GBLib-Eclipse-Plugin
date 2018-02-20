package org.green4590.gbplugin.widgets;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.swt.widgets.Shell;
import org.green4590.gbplugin.activator.Activator;

public class GBPopups {
	
	/**
	 * Throw an error message.
	 */
	public static void Message(Throwable t) {
		Message(IStatus.ERROR, "ERROR", t.getClass().getName(), t.getMessage(), (Object[]) t.getStackTrace());
	}

	/**
	 * Pops a message.
	 */
	public static void Message(int severity, String title, String message, String reason, Object... description) {
		List<IStatus> children = new ArrayList<>();

		for (Object o : description) {
			Status child = new Status(severity, Activator.PLUGIN_ID, o.toString());
			children.add(child);
		}

		MultiStatus ms = new MultiStatus(Activator.PLUGIN_ID, severity, children.toArray(new Status[] {}), reason,
				null);

		ErrorDialog.openError(new Shell(), title, message, ms);
	}

}
