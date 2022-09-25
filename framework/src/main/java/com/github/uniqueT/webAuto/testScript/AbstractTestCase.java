package com.github.uniqueT.webAuto.testScript;

import static com.github.uniqueT.webAuto.event.BaseEvent.getDriver;

import com.github.uniqueT.webAuto.event.invoke.CaseBiConsumer;
import com.github.uniqueT.webAuto.event.invoke.CaseConsumer;
import com.github.uniqueT.webAuto.event.invoke.CaseRunnable;
import com.github.uniqueT.webAuto.util.testNG.log.EventLog;
import static com.github.uniqueT.webAuto.event.invoke.Invoker.ca5e;
import static com.github.uniqueT.webAuto.event.invoke.Invoker.event;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import org.openqa.selenium.WebDriver;

/**
 * Business test case should extend this class.<br>
 * This class wrapped case and event methods in {@linkplain com.github.uniqueT.webAuto.event.invoke.Invoker Invoker}, provides some simple methods to script writers.<br>
 * You can use e() method to invoke test event, use c() method to invoke other cases.
 * <p>e.g. <br>{@code e(InputTextEvent::sendKeys, By.id("username"), "root");}<br>
 * InputTextEvent is a sub class of {@linkplain com.github.uniqueT.webAuto.event.BaseEvent BaseEvent},
 * sendKeys is the name of a method in InputTextEvent, it needs a By parameter and a String parameter</p>
 * @author uniqueT
 *
 */
public abstract class AbstractTestCase {
	
	/**
	 * store current driver, test case can use it directly
	 */
	protected static WebDriver driver = getDriver();
	
	/**
	 * invoke non parameter case
	 * @param f case method
	 */
	protected static void c(CaseRunnable f){
		ca5e(f);
	}
	
	/**
	 *invoke non parameter case
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
	 * invoke one parameter case
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
	 * invoke two parameters case
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
	
	/**
	 * Non parameter event
	 * @param f function name
	 */
	protected static void e(Supplier<EventLog> f) {
		event(f);
	}
	
	/**
	 * Non parameter event
	 * @param f function name
	 * @param wait if wait&gt;0, the thread will sleep wait seconds after executing this step
	 */
	protected static void e(Supplier<EventLog> f, int wait) {
		event(f, wait, false);
	}
	
	/**
	 * Non parameter event
	 * @param f function name
	 * @param needScreenShot if true, take a screenshot after this step
	 */
	protected static void e(Supplier<EventLog> f, boolean needScreenShot) {
		event(f, 0, needScreenShot);
	}
	
	/**
	 * Non parameter event
	 * @param f function name
	 * @param wait if wait&gt;0, the thread will sleep wait seconds after executing this step
	 * @param needScreenShot if true, take a screenshot after this step
	 */
	protected static void e(Supplier<EventLog> f, int wait, boolean needScreenShot) {
		event(f, wait, needScreenShot);
	}
	
	/**
	 * One parameter event
	 * @param f function name
	 * @param param parameter 1
	 */
	protected static <T> void e(Function<T, EventLog> f, T param) {
		event(f, param, 0, false);
	}
	
	/**
	 * One parameter event
	 * @param f function name
	 * @param param parameter 1
	 * @param wait if wait&gt;0, the thread will sleep wait seconds after executing this step
	 */
	protected static <T> void e(Function<T, EventLog> f, T param, int wait) {
		event(f, param, wait, false);
	}
	
	/**
	 * One parameter event
	 * @param f function name
	 * @param param parameter 1
	 * @param needScreenShot if true, take a screenshot after this step
	 */
	protected static <T> void e(Function<T, EventLog> f, T param, boolean needScreenShot) {
		event(f, param, 0, needScreenShot);
	}
	
	/**
	 * One parameter event
	 * @param f function name
	 * @param param parameter 1
	 * @param wait if wait&gt;0, the thread will sleep wait seconds after executing this step
	 * @param needScreenShot if true, take a screenshot after this step
	 */
	protected static <T> void e(Function<T, EventLog> f, T param, int wait, boolean needScreenShot) {
		event(f, param, wait, needScreenShot);
	}
	
	/**
	 * Two parameters event
	 * @param f function name
	 * @param param1 parameter 1
	 * @param param2 parameter 2
	 */
	protected static <T, V> void e(BiFunction<T, V, EventLog> f, T param1, V param2) {
		event(f, param1, param2, 0, false);
	}
	
	/**
	 * Two parameters event
	 * @param f function name
	 * @param param1 parameter 1
	 * @param param2 parameter 2
	 * @param wait if wait&gt;0, the thread will sleep wait seconds after executing this step
	 */
	protected static <T, V> void e(BiFunction<T, V, EventLog> f, T param1, V param2, int wait) {
		event(f, param1, param2, wait, false);
	}
	
	/**
	 * Two parameters event
	 * @param f function name
	 * @param param1 parameter 1
	 * @param param2 parameter 2
	 * @param needScreenShot if true, take a screenshot after this step
	 */
	protected static <T, V> void e(BiFunction<T, V, EventLog> f, T param1, V param2, boolean needScreenShot) {
		event(f, param1, param2, 0, needScreenShot);
	}
	
	/**
	 * Two parameters event
	 * @param f function name
	 * @param param1 parameter 1
	 * @param param2 parameter 2
	 * @param wait if wait&gt;0, the thread will sleep wait seconds after executing this step
	 * @param needScreenShot if true, take a screenshot after this step
	 */
	protected static <T, V> void e(BiFunction<T, V, EventLog> f, T param1, V param2, int wait, boolean needScreenShot) {
		event(f, param1, param2, wait, needScreenShot);
	}

}
