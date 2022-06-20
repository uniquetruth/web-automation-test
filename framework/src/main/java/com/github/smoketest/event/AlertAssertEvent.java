package com.github.smoketest.event;

import org.openqa.selenium.Alert;

import com.github.smoketest.util.testNG.log.EventLog;

/**
 * alert assertion class
 * @author uniqueT
 *
 */
public class AlertAssertEvent extends BaseEvent {
	
	/**
	 * assert text in alert
	 * @param expectValue expected text
	 * @param matchExact 0 - contains 1 - equals
	 * @return EventLog
	 */
	public static EventLog assertText(String expectValue, int matchExact) {
		Alert alert = driver.switchTo().alert();
		String actualText = alert.getText();
		boolean result = false;
		if(expectValue==null) {
			if(actualText == null) {
				result = true;
			}
		}else {
			if(actualText != null) {
				if(actualText.equals(expectValue) && matchExact == 1) {
					result = true;
				}else if(actualText.contains(expectValue) && matchExact == 0) {
					result = true;
				}
			}
		}
		if(!result) throw new RuntimeException("element text don't equal to expect, expectValue is ["
				+ expectValue + "] actual value is [" + actualText + "]");
		return EventLog.getInstance("assert alert text success, expectValue=["+expectValue+"]");
	}

}
