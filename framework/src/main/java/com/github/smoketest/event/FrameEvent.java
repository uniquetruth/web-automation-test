package com.github.smoketest.event;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.github.smoketest.util.testNG.log.EventLog;

/**
 * change iframe
 * @author uniqueT
 *
 */
public class FrameEvent extends BaseEvent {
	
	/**
	 * change to a visible iframe
	 * @param iframeName iframe's name or id
	 * @return event log
	 */
	public static EventLog toDisplayFrame(String iframeName) {
		String xpath = "//iframe[@name='"+iframeName+"' or @id='" + iframeName +"'] | "
				+ "//frame[@name='"+iframeName+"' or @id='" + iframeName +"']";
		WebElement iframe = getVisiableElement(By.xpath(xpath));
		driver.switchTo().frame(iframe);
		return EventLog.getInstance("changed to iframe " + iframeName);
	}
	
	/**
	 * change to an iframe
	 * @param iframeName iframe's name or id
	 * @return event log
	 */
	public static EventLog toFrame(String iframeName) {
		String xpath = "//iframe[@name='"+iframeName+"' or @id='" + iframeName +"'] | "
				+ "//frame[@name='"+iframeName+"' or @id='" + iframeName +"']";
		WebElement iframe = getElement(By.xpath(xpath));
		driver.switchTo().frame(iframe);
		return EventLog.getInstance("changed to iframe " + iframeName);
	}
	
	/**
	 * change to a visible iframe
	 * @param by By of an iframe
	 * @return event log
	 */
	public static EventLog toDisplayFrame(By by) {
		WebElement iframe = getVisiableElement(by);
		driver.switchTo().frame(iframe);
		return EventLog.getInstance("changed to iframe " + by.toString());
	}
	
	/**
	 * change to an iframe
	 * @param by By of an iframe
	 * @return event log
	 */
	public static EventLog toFrame(By by) {
		WebElement iframe = getElement(by);
		driver.switchTo().frame(iframe);
		return EventLog.getInstance("changed to iframe " + by.toString());
	}
	
	/**
	 * to default content
	 * @return event log
	 */
	public static EventLog toRootFrame() {
		driver.switchTo().defaultContent();
		return EventLog.getInstance("changed to default frame");
	}

}
