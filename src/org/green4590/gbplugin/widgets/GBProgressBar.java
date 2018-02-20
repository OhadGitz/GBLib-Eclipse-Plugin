package org.green4590.gbplugin.widgets;

import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Image;
import org.green4590.gbplugin.file.FileUtils;

public class GBProgressBar {
	
	private Image guy;
	private String guyImage = FileUtils.DIRS.get("resources") + "guy.jpg";
	
	public GBProgressBar(Device device){
		guy = new Image(device, guyImage);
	}
	
}
