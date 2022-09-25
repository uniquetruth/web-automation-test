package com.github.uniqueT.webAuto.testScript;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.github.uniqueT.webAuto.event.invoke.CaseBiConsumer;
import com.github.uniqueT.webAuto.event.invoke.CaseConsumer;
import com.github.uniqueT.webAuto.event.invoke.CaseRunnable;
import static com.github.uniqueT.webAuto.event.BaseEvent.getDriver;
import static com.github.uniqueT.webAuto.event.invoke.Invoker.ca5e;

/**
 * real business test scene should extend this class, start writing steps in {@link #doTest()}<br>
 * This class wrapped case methods in {@linkplain com.github.uniqueT.webAuto.event.invoke.Invoker Invoker}, provides some simple methods to script writers.<br>
 * You can use c() method to invoke test case
 * <p>e.g. <br>{@code c(HomePageCase::login, "root", "root1234");}<br>
 * HomePageCase is s sub class of {@linkplain com.github.uniqueT.webAuto.testScript.AbstractTestCase AbstractTestCase},
 * login if the name of a method in HomePageCase, it needs 2 String parameters.</p>
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
	public final void doTest(ITestResult tr) {
		Reporter.setCurrentTestResult(tr);
		driver = getDriver();
		doTest();
	}
	
	/**
	 * execute after doTest, even exceptions are thrown in doTest
	 * this method does nothing, sub class can override it as their requirement
	 */
	@Override
	public void doLast() {
		//do nothing
	}
	
	/**
	 * invoke non parameter case
	 * @param f case method
	 */
	protected static void c(CaseRunnable f){
		ca5e(f);
	}
	
	/**
	 * invoke non parameter case
	 * @param f case method
	 * @param frame if not null, change to this <b>frame</b> before executing steps
	 */
	protected static void c(CaseRunnable f, String frame){
		ca5e(f, frame);
	}
	
	/**
	 * invoke non parameter case
	 * @param f case method
	 * @param frame if not null, change to this <b>frame</b> before executing steps
	 * @param window if not null, change to window with this <b>window</b> title before executing steps
	 */
	protected static void c(CaseRunnable f, String frame, String window){
		ca5e(f, frame, window);
	}
	
	/**
	 invoke one parameter case
	 * @param f case method
	 * @param param parameter 1
	 */
	protected static <T> void c(CaseConsumer<T> f, T param){
		ca5e(f, param);
	}
	
	/**
	 * invoke one parameter case
	 * @param f case method
	 * @param param parameter 1
	 * @param frame if not null, change to this <b>frame</b> before executing steps
	 */
	protected static <T> void c(CaseConsumer<T> f, T param, String frame) {
		ca5e(f, param, frame);
	}
	
	/**
	 * invoke one parameter case
	 * @param f case method
	 * @param param parameter 1
	 * @param frame if not null, change to this <b>frame</b> before executing steps
	 * @param window if not null, change to window with this <b>window</b> title before executing steps
	 */
	protected static <T> void c(CaseConsumer<T> f, T param, String frame, String window){
		ca5e(f, param, frame, window);
	}
	
	/**
	 * nvoke two parameters case
	 * @param f case method
	 * @param param1 parameter 1
	 * @param param2 parameter 2
	 */
	protected static <T, V> void c(CaseBiConsumer<T, V> f, T param1, V param2){
		ca5e(f, param1, param2);
	}
	
	/**
	 * invoke two parameters case
	 * @param f case method
	 * @param param1 parameter 1
	 * @param param2 parameter 2
	 * @param frame if not null, change to this <b>frame</b> before executing steps
	 */
	protected static <T, V> void c(CaseBiConsumer<T, V> f, T param1, V param2, String frame){
		ca5e(f, param1, param2, frame);
	}
	
	/**
	 *  invoke two parameters case
	 * @param f case method
	 * @param param1 parameter 1
	 * @param param2 parameter 2
	 * @param frame if not null, change to this <b>frame</b> before executing steps
	 * @param window if not null, change to window with this <b>window</b> title before executing steps
	 */
	protected static <T, V> void c(CaseBiConsumer<T, V> f, T param1, V param2, String frame, String window){
		ca5e(f, param1, param2, frame, window);
	}

}
