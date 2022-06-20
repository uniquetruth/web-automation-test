package com.github.smoketest.event;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.github.smoketest.util.testNG.log.EventLog;

/**
 * general operations of select element
 * @author uniqueT
 *
 */
public class SelectEvent extends ElementEvent {
	
	/**
	 * select an option of specific value
	 * @param by locate By
	 * @param value value to be selected
	 * @return event log
	 */
	public static EventLog selectValue(By by, String value) {
		WebElement element = getVisiableElement(by);
		Select s = new Select(element);
		s.selectByValue(value);
		return EventLog.getInstance(by, value);
	}
	
	/**
	 * select an option of specific text
	 * @param by locate By
	 * @param text text to be selected
	 * @return event log
	 */
	public static EventLog selectText(By by, String text) {
		WebElement element = getVisiableElement(by);
		Select s = new Select(element);
		s.selectByVisibleText(text);
		return EventLog.getInstance(by, text);
	}

}
