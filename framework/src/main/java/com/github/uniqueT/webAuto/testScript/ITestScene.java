package com.github.uniqueT.webAuto.testScript;

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
	 * @param tr result of A single test scene
	 */
	public void doTest(ITestResult tr);
	
	/**
	 * execute after doTest, even exceptions are thrown in doTest
	 */
	public void doLast();

}
