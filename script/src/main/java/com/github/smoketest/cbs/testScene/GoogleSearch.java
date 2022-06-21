package com.github.smoketest.cbs.testScene;

import org.openqa.selenium.By;

import com.github.smoketest.event.ElementAssertEvent;
import com.github.smoketest.event.ElementEvent;
import com.github.smoketest.event.InputTextEvent;
import com.github.smoketest.event.WindowEvent;
import com.github.smoketest.event.invoke.Invoker;
import com.github.smoketest.testScene.AbstractTestScene;
import com.github.smoketest.util.CommonUtil;
import com.github.smoketest.util.testNG.log.Logger;

public class GoogleSearch extends AbstractTestScene {

	@Override
	public void doTest() {
		Logger.customLog("====== open home page ==========");
		CommonUtil.takeScreenShot(driver);
		Logger.customLog("====== enter keyword ==========");
		Invoker.i(InputTextEvent::sendKeys, By.name("q"), "selenium");
		Logger.customLog("====== click search button ==========");
		Invoker.i(ElementEvent::click, By.name("btnK"));
		Logger.customLog("====== wait to take screen shot ==========");
		Invoker.i(ElementAssertEvent::assertVisible, By.xpath("//a//*[text()='Selenium']"));
		CommonUtil.takeScreenShot(driver);
		Logger.customLog("====== get our result ==========");
		Invoker.i(ElementEvent::click, By.xpath("//a//*[text()='Selenium']"));
		CommonUtil.forceWait(3);
		Invoker.i(WindowEvent::changeTo, "Selenium");
		Invoker.i(ElementAssertEvent::assertVisible, By.xpath("//*[text()='Getting Started']"), 10);
		CommonUtil.takeScreenShot(driver);
	}
	
	@Override
	public String getDisplayName() {
		return "Google search test";
	}

}
