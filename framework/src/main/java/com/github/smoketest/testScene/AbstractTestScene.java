package com.github.smoketest.testScene;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;

/**
 * real business test scene should extend this class
 * @author uniqueT
 *
 */
public abstract class AbstractTestScene implements ITestScene {
	
	/**
	 * Created by sub class, test scene can use it directly
	 */
	protected WebDriver driver;
	
	/**
	 * entrance of real business test scene
	 */
	@Override
	public abstract void doTest();

	/**
	 * invoked by framework
	 */
	@Override
	public final void doTest(WebDriver _driver, ITestResult tr) {
		Reporter.setCurrentTestResult(tr);
		driver = _driver;
		doTest();
	};
	
	/**
	 * get name of test scene, will be displayed in report file. Default is testSceneClass.getName()
	 */
	@Override
	public String getDisplayName() {
		return this.getClass().getName();
	}

}
