package com.github.uniqueT.webAuto.event;

import java.time.Duration;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.github.uniqueT.webAuto.util.testNG.log.EventLog;

/**
 * alert handle event
 * @author uniqueT
 *
 */
public class AlertEvent extends BaseEvent {
	
	/**
	 * confirm alert
	 * @return event log
	 */
	public static EventLog confirm() {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				  .withTimeout(Duration.ofSeconds(3))
				  .pollingEvery(Duration.ofSeconds(1))
				  .ignoring(NoSuchElementException.class);
		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
		return EventLog.getInstance("alert confirm");
	}
	
	/**
	 * cancel alert
	 * @return event log
	 */
	public static EventLog cancel() {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				  .withTimeout(Duration.ofSeconds(3))
				  .pollingEvery(Duration.ofSeconds(1))
				  .ignoring(NoSuchElementException.class);
		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().dismiss();
		return EventLog.getInstance("alert cancel");
	}
	
	/**
	 * wait if alert appears
	 * @param seconds timeout in seconds
	 * @return if alert appears, return true
	 */
	public static boolean ifAlertInSeconds(int seconds) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				  .withTimeout(Duration.ofSeconds(seconds))
				  .pollingEvery(Duration.ofSeconds(1))
				  .ignoring(NoSuchElementException.class)
				  .ignoring(NoSuchWindowException.class);
		try {
			wait.until(ExpectedConditions.alertIsPresent());
		}catch(TimeoutException e) {
			return false;
		}
		return true;
	}

}
