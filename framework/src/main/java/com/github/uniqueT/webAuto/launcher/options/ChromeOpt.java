package com.github.uniqueT.webAuto.launcher.options;

import org.openqa.selenium.chrome.ChromeOptions;

/**
 * config for chrome driver
 * @author uniqueT
 *
 */
public class ChromeOpt {
	
	/**
	 * get the config of chrome driver
	 * @param driverFile the driver's location
	 * @return ChromeOptions
	 */
	public static ChromeOptions getOption(String driverFile) {
		System.setProperty("webdriver.chrome.driver", driverFile);
		//System.setProperty("webdriver.chrome.driver", "/home/user/work/WebUIAutoamtion/drivers/chromedriver");
		
		ChromeOptions opts = new ChromeOptions();
		//opts.setBinary("/opt/apps/cn.google.chrome/files/google-chrome");
		//opts.setBinary("/usr/bin/browser");
		
		return opts;
	}

}
