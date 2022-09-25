package com.github.uniqueT.webAuto.event.cbs;

import org.openqa.selenium.By;
import com.github.uniqueT.webAuto.event.FrameEvent;
import com.github.uniqueT.webAuto.util.testNG.log.EventLog;

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
