package com.github.smoketest.cbs.testCase;

import com.github.uniqueT.webAuto.testScript.AbstractTestCase;

import org.openqa.selenium.By;

import com.github.uniqueT.webAuto.event.ElementAssertEvent;
import com.github.uniqueT.webAuto.event.ElementEvent;
import com.github.uniqueT.webAuto.event.InputTextEvent;

public class QueryCheckPage extends AbstractTestCase{
	
	public static void searchPolicy(String policyCode) {
		e(InputTextEvent::clear, By.name("start_time"));
		//手工关闭日历控件，否则后续操作会出现错误
		e(ElementEvent::click, By.name("q_agent_code"));
		e(InputTextEvent::sendKeys, By.name("q_send_code"), policyCode);
		e(ElementEvent::click, By.xpath("//input[@name='pool_state' and @value='2']/following-sibling::div[1]"));
		e(ElementEvent::click, By.name("search2"));
		//断言检索结果
		e(ElementAssertEvent::assertExists, By.xpath("//a[contains(text(),'" + policyCode + "')]"));
	}

}
