package com.github.smoketest.util;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.github.smoketest.util.testNG.log.Logger;

/**
 * common utilities
 * @author uniqueT
 *
 */
public class CommonUtil {
	
	/**
	 * temporary name of screenshot
	 */
	public static String ScreenShotTMP = "screenshot";
	
	/**
	 * force thread to wait X seconds
	 * @param seconds
	 */
	public static void forceWait(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			Logger.exceptionLog(e);
		}
	}
	
	/**
	 * take screen shot and write screen shot log
	 * @param driver WebDriver实例
	 */
	public static void takeScreenShot(WebDriver driver) {
		String path = CommonUtil.class.getClassLoader().getResource("").getPath();
		String dirPath = path + File.separator + ScreenShotTMP;
		File dir = new File(dirPath);
		if(!dir.exists()) {
			dir.mkdir();
		}
		String filename = null;
		try {
			File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			filename = String.valueOf(System.currentTimeMillis()) + ".png";
			FileUtils.copyFile(src, new File(dirPath + File.separator + filename));
		} catch (Exception e) {
			Logger.exceptionLog(e);
		}
		Logger.screenShotLog(filename);
	}

}
