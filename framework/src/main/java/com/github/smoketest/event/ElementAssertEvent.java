package com.github.smoketest.event;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.github.smoketest.util.testNG.log.EventLog;

/**
 * <p>Assertion about element</p>
 * @author uniqueT
 *
 */
public class ElementAssertEvent extends BaseEvent {
	
	/**
	 * assert if an element was in the dom
	 * @param by locate By
	 * @return EventLog
	 */
	public static EventLog assertExists(By by) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				  .withTimeout(Duration.ofSeconds(1))
				  .ignoring(NoSuchElementException.class);
		wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(by, 0));
		return EventLog.getInstance(by);
	}
	
	/**
	 * assert if an element was in the dom in timeout seconds
	 * @param by locate By
	 * @param seconds timeout in seconds
	 * @return EventLog
	 */
	public static EventLog assertExists(By by, int seconds) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				  .withTimeout(Duration.ofSeconds(seconds))
				  .ignoring(NoSuchElementException.class);
		wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(by, 0));
		return EventLog.getInstance(by);
	}
	
	/**
	 * assert if an element was visible
	 * @param by locate By
	 * @return EventLog
	 */
	public static EventLog assertVisible(By by) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				  .withTimeout(Duration.ofSeconds(1))
				  .ignoring(NoSuchElementException.class);
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		return EventLog.getInstance(by);
	}
	
	/**
	 * assert if an element was visible in timeout seconds
	 * @param by locate By
	 * @param seconds timeout in seconds
	 * @return EventLog
	 */
	public static EventLog assertVisible(By by, int seconds) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				  .withTimeout(Duration.ofSeconds(1))
				  .ignoring(NoSuchElementException.class);
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		return EventLog.getInstance(by);
	}
	
	/**
	 * assert if an element's value equals to expectValue
	 * @param by locate By
	 * @param expectValue
	 * @return EventLog
	 */
	public static EventLog assertValue(By by, String expectValue) {
		WebElement ele = getElement(by);
		String actualValue = ele.getAttribute("value");
		boolean result = false;
		if(expectValue==null) {
			if(actualValue == null) {
				result = true;
			}
		}else {
			if(expectValue.equals(actualValue)) {
				result = true;
			}
		}
		if(!result) throw new RuntimeException("element value don't equal to expect, expectValue is ["
				+ expectValue + "] actual value is [" + actualValue + "]");
		return EventLog.getInstance(by, expectValue);
	}
	
	/**
	 * assert if an attribute of an element equals to expectValue
	 * @param by locate By
	 * @param attrName attribute name
	 * @param expectValue
	 * @return EventLog
	 */
	public static EventLog assertAttribute(By by, String attrName, String expectValue) {
		WebElement ele = getElement(by);
		String actualValue = ele.getAttribute(attrName);
		boolean result = false;
		if(expectValue==null) {
			if(actualValue == null) {
				result = true;
			}
		}else {
			if(expectValue.equals(actualValue)) {
				result = true;
			}
		}
		if(!result) throw new RuntimeException("element attribute don't equal to expect, expectValue is ["
				+ expectValue + "] actual value is [" + actualValue + "]");
		return EventLog.getInstance(by, expectValue);
	}
	
	/**
	 * assert if an element's text equals to expectValue
	 * @param by locate By
	 * @param expectValue
	 * @param matchExact 0 - contains 1 - equals
	 * @return EventLog
	 */
	public static EventLog assertText(By by, String expectValue, int matchExact) {
		if(matchExact !=0 && matchExact != 1) {
			throw new RuntimeException("matchExact can only be 0 or 1");
		}
		WebElement ele = getElement(by);
		String actualValue = ele.getText();
		boolean result = false;
		if(expectValue==null) {
			if(actualValue == null) {
				result = true;
			}
		}else {
			if(actualValue != null) {
				if(actualValue.equals(expectValue) && matchExact == 1) {
					result = true;
				}else if(actualValue.contains(expectValue) && matchExact == 0) {
					result = true;
				}
			}
		}
		if(!result) throw new RuntimeException("element text don't equal to expect, expectValue is ["
				+ expectValue + "] actual value is [" + actualValue + "]");
		return EventLog.getInstance(by, expectValue);
	}

}
