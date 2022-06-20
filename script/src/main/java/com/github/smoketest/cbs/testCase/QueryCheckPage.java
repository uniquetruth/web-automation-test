package com.github.smoketest.cbs.testCase;

import com.github.smoketest.event.invoke.Invoker;

import org.openqa.selenium.By;

import com.github.smoketest.event.ElementAssertEvent;
import com.github.smoketest.event.ElementEvent;
import com.github.smoketest.event.InputTextEvent;

public class QueryCheckPage {
	
	public static void searchPolicy(String policyCode) {
		Invoker.i(InputTextEvent::clear, By.name("start_time"));
		//手工关闭日历控件，否则后续操作会出现错误
		Invoker.i(ElementEvent::click, By.name("q_agent_code"));
		Invoker.i(InputTextEvent::sendKeys, By.name("q_send_code"), policyCode);
		Invoker.i(ElementEvent::click, By.xpath("//input[@name='pool_state' and @value='2']/following-sibling::div[1]"));
		Invoker.i(ElementEvent::click, By.name("search2"));
		//断言检索结果
		Invoker.i(ElementAssertEvent::assertExists, By.xpath("//a[contains(text(),'" + policyCode + "')]"));
	}

}
