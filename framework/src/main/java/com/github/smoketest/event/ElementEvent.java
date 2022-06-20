package com.github.smoketest.event;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.github.smoketest.util.testNG.log.EventLog;

/**
 * <p>opration of general html elements(e.g. click,js onclick, setValue...)</p>
 * <p>also can be used to obtain an element</p>
 * @author uniqueT
 *
 */
public class ElementEvent extends BaseEvent {
	
	/**
	 * click a visible element
	 * @param by locate By
	 * @return event log
	 */
	public static EventLog click(By by) {
		WebElement element = getVisiableElement(by);
		element.click();
		return EventLog.getInstance(by);
	}
	
	/**
	 * <p>set value by JavaScript</p>
	 * @param by locate By
	 * @param value specific value
	 * @return event log
	 */
	public static EventLog setValue(By by, String value) {
		WebElement element = getElement(by);
		String js = "arguments[0].value=arguments[1]";
		((JavascriptExecutor)driver).executeScript(js, element, value);
		return EventLog.getInstance(by, value);
	}
	
	/**
	 * <p>click an element as it in dom</p>
	 * @param by locate By
	 * @return event log
	 */
	public static EventLog onClick(By by) {
		WebElement element = getElement(by);
		String js = "arguments[0].click()";
		((JavascriptExecutor)driver).executeScript(js, element);
		return EventLog.getInstance(by);
	}
	
	/**
	 * locate an element in timeout
	 * @param by locate By
	 * @param timeseconds timeout in seconds
	 * @return WebElement
	 */
	public static WebElement getElement(By by, int timeseconds) {
		existsWait(by, timeseconds);
		return driver.findElement(by);
	}
	
	/**
	 * locate an element in defauls timeout({@link BaseEvent#DEFAULT_WAIT_SECONDS})
	 * @param by locate By
	 * @return WebElement
	 */
	public static WebElement getElement(By by) {
		return getElement(by, DEFAULT_WAIT_SECONDS);
	}

}
