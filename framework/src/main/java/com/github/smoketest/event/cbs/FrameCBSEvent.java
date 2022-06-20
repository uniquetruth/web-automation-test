package com.github.smoketest.event.cbs;

import org.openqa.selenium.By;
import com.github.smoketest.event.FrameEvent;
import com.github.smoketest.util.testNG.log.EventLog;

/**
 * cbs iframe event
 * @author uniqueT
 *
 */
public class FrameCBSEvent extends FrameEvent {
	
	/**
	 * change current iframe
	 * @return event log
	 */
	public static EventLog toCurrentDisplayFrame() {
		String xpath = "//div[contains(@class,'layui-show')]/iframe";
		return toDisplayFrame(By.xpath(xpath));
	}

}
