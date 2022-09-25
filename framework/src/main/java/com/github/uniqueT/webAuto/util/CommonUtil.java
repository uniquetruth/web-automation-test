package com.github.uniqueT.webAuto.util;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import static com.github.uniqueT.webAuto.event.BaseEvent.getDriver;
import com.github.uniqueT.webAuto.util.testNG.log.Logger;

/**
 * common utilities
 * @author uniqueT
 *
 */
public class CommonUtil {
	
	/**
	 * temporary name of screenshot
	 */
	public final static String ScreenShotTMP = "screenshot_tmp";
	
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
	 */
	public static void takeScreenShot() {
		String path = CommonUtil.class.getClassLoader().getResource("").getPath();
		String dirPath = path + File.separator + ScreenShotTMP;
		File dir = new File(dirPath);
		if(!dir.exists()) {
			dir.mkdir();
		}
		String filename = null;
		try {
			File src = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
			filename = String.valueOf(System.currentTimeMillis()) + ".png";
			FileUtils.copyFile(src, new File(dirPath + File.separator + filename));
		} catch (Exception e) {
			Logger.exceptionLog(e);
		}
		Logger.screenShotLog(filename);
	}
	
	/**
	 * take screen shot, if page is too high, scroll automatically and take more screen shots
	 */
	public static void fullScreenShot() {
		JavascriptExecutor executor = (JavascriptExecutor)getDriver();
		long pageHeight = (long) executor.executeScript("return document.body.scrollHeight");
		long viewHeight = (long) executor.executeScript("return window.innerHeight");
		executor.executeScript("window.scrollTo(0, 0)");
		takeScreenShot();
		long jump = viewHeight;
		while(jump < pageHeight) {
			executor.executeScript("window.scrollTo(0, arguments[0])", jump);
			takeScreenShot();
			jump += viewHeight;
		}
	}

}
