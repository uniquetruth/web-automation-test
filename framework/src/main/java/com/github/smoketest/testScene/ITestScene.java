package com.github.smoketest.testScene;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

/**
 * <p>scene entrance interface, should only be used by framework</p>
 * <p>real business scene should extend {@link AbstractTestScene}</p>
 * @author uniqueT
 *
 */
public interface ITestScene {
	
	/**
	 * entrance of real business test scene
	 */
	public void doTest();

	/**
	 * invoked by framework
	 * @param driver WebDriver
	 * @param tr result of A single test scene
	 */
	public void doTest(WebDriver driver, ITestResult tr);
	
	/**
	 * get name of test scene, will be displayed in report file
	 * @return
	 */
	public String getDisplayName();

}
