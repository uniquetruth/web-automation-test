package com.github.uniqueT.webAuto.launcher.options;

import org.openqa.selenium.ie.InternetExplorerOptions;

/**
 * config for IE driver
 * @author uniqueT
 *
 */
public class IEOpt {
	
	/**
	 * get the config of ie driver
	 * @param driverFile the driver's location
	 * @return InternetExplorerOptions
	 */
	public static InternetExplorerOptions getOption(String driverFile) {
		System.setProperty("webdriver.ie.driver", driverFile);
		
		InternetExplorerOptions opts = new InternetExplorerOptions();
		//opts.setCapability(capabilityName, value);
		
		return opts;
	}

}
