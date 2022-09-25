package com.github.uniqueT.webAuto.event;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

/**
 * <p>super class of all events, supports some fluent waiting function</p>
 * <p>please extends this class when you want to add an event(operation). please return a 
 * {@linkplain com.github.uniqueT.webAuto.util.testNG.log.EventLog EventLog} in very function</p>
 * <b>Attention</b> in extending this class: don't overload method depending parameters' amount, 
 * otherwise exceptions will be thrown in {@linkplain com.github.uniqueT.webAuto.event.invoke.Invoker Invoker}'s methods
 * @author uniqueT
 *
 */
public abstract class BaseEvent {
	
	/**
	 * a WebDriver provided by framework
	 */
	protected static WebDriver driver;
	
	/**
	 * default waiting time: 10s
	 */
	public static int DEFAULT_WAIT_SECONDS = 10;
	
	/**
	 * initialize driver, invoked by framework
	 * @param _driver WebDriver
	 */
	public static void initDriver(WebDriver _driver) {
		driver = _driver;
	}
	
	/**
	 * get current driver object
	 * @return WebDriver object
	 */
	public static WebDriver getDriver() {
		return driver;
	}
	
	/**
	 * locate visible element
	 * @param by locate By
	 * @param timeseconds timeout in seconds
	 * @return element
	 */
	protected static WebElement getVisibleElement(By by, int timeseconds) {
		visiableWait(by, timeseconds);
		List<WebElement> list = driver.findElements(by);
		WebElement ele = null;
		for(WebElement e : list) {
			if(e.isDisplayed()) {
				ele = e;
				break;
			}
		}
		return ele;
	}
	
	/**
	 * locate visible element, timeout is {@link BaseEvent#DEFAULT_WAIT_SECONDS}
	 * @param by locate By
	 * @return element
	 */
	protected static WebElement getVisibleElement(By by) {
		return getVisibleElement(by, DEFAULT_WAIT_SECONDS);
	}
	
	/**
	 * waiting for an element is visible
	 * @param by locate By
	 * @param timeseconds timeout in seconds
	 */
	protected static void visiableWait(By by, int timeseconds) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				  .withTimeout(Duration.ofSeconds(timeseconds))
				  .pollingEvery(Duration.ofSeconds(3))
				  .ignoring(NoSuchElementException.class);
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}
	
	/**
	 * locate element
	 * @param by locate By
	 * @param timeseconds timeout in seconds
	 * @return element
	 */
	protected static WebElement getElement(By by, int timeseconds) {
		existsWait(by, timeseconds);
		return driver.findElement(by);
	}
	
	/**
	 * locate element, timeout is{@link BaseEvent#DEFAULT_WAIT_SECONDS}
	 * @param by locate By
	 * @return element
	 */
	protected static WebElement getElement(By by) {
		return getElement(by, DEFAULT_WAIT_SECONDS);
	}
	
	/**
	 * waiting for an element is loaded into dom
	 * @param by locate By
	 * @param timeseconds timeout in seconds
	 */
	protected static void existsWait(By by, int timeseconds) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				  .withTimeout(Duration.ofSeconds(timeseconds))
				  .pollingEvery(Duration.ofSeconds(3))
				  .ignoring(NoSuchElementException.class);
		wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(by, 0));
	}
	
	/**
	 * check is an element in the dom
	 * @param by locate By
	 * @return true / false
	 */
	protected static boolean elementExists(By by) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				  .withTimeout(Duration.ofSeconds(1))
				  .ignoring(NoSuchElementException.class);
		try {
			wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(by, 0));
		}catch(TimeoutException e) {
			return false;
		}
		return true;
	}

}
