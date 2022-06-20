package com.github.smoketest.event;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.github.smoketest.util.testNG.log.EventLog;

/**
 * general operations of input element
 * @author uniqueT
 *
 */
public class InputTextEvent extends ElementEvent {

	/**
	 * <p>equals to Selenium's element.sendKeys(value)</p>
	 * @param by locate By
	 * @param value input value
	 * @return event log
	 */
	public static EventLog sendKeys(By by, String value) {
		WebElement element = getVisiableElement(by);
		element.sendKeys(value);
		return EventLog.getInstance(by, value);
	}
	
	/**
	 * <p>equals to Selenium's element.clear()</p>
	 * @param by 定位方式
	 * @return event日志
	 */
	public static EventLog clear(By by) {
		WebElement element = getVisiableElement(by);
		element.clear();
		return EventLog.getInstance(by);
	}

}
