package com.github.smoketest.util.testNG.report;

import java.util.ArrayList;
import java.util.List;

import org.testng.ITestResult;

/**
 * use a list to record all test results
 * @author uniqueT
 *
 */
public class SceneResultList {
	
	private static List<ITestResult> list = new ArrayList<ITestResult>();

	/**
	 * add a test result into the list
	 * @param tr test result
	 */
	public static void addTestReault(ITestResult tr) {
		list.add(tr);
	}
	
	/**
	 * get the list
	 * @return the list
	 */
	public static List<ITestResult> getResultList(){
		return list;
	}

}
