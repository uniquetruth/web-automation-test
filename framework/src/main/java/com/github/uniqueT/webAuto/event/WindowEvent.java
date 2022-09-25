package com.github.uniqueT.webAuto.event;

import org.openqa.selenium.NoSuchWindowException;

import com.github.uniqueT.webAuto.util.testNG.log.EventLog;

/**
 * change window event
 * @author uniqueT
 *
 */
public class WindowEvent extends BaseEvent {

	/**
	 * change to a window that fuzzy matches the windowName
	 * @param windowName text
	 * @return event log
	 */
	public static EventLog changeTo(String windowName) {
		for(String window : driver.getWindowHandles()) {
			try {
				driver.switchTo().window(window);
				if(driver.getTitle().contains(windowName)) {
					driver.manage().window().maximize();
					return EventLog.getInstance("changed to window " + windowName);
				}
			}catch(NoSuchWindowException e) {
				continue;
			}
		}
		throw new RuntimeException("can not find window with name " + windowName);
	}
	
}
