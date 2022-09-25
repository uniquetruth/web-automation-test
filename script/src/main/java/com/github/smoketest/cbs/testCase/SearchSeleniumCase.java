package com.github.smoketest.cbs.testCase;

import org.openqa.selenium.By;

import com.github.uniqueT.webAuto.event.ElementAssertEvent;
import com.github.uniqueT.webAuto.event.ElementEvent;
import com.github.uniqueT.webAuto.event.InputTextEvent;
import com.github.uniqueT.webAuto.event.WindowEvent;
import com.github.uniqueT.webAuto.testScript.AbstractTestCase;
import com.github.uniqueT.webAuto.util.CommonUtil;
import com.github.uniqueT.webAuto.util.testNG.log.Logger;

public class SearchSeleniumCase extends AbstractTestCase{

    public static void doSearch(){
        Logger.stringLog("====== open home page ==========");
		CommonUtil.takeScreenShot();
		Logger.stringLog("====== enter keyword ==========");
		e(InputTextEvent::sendKeys, By.name("q"), "selenium");
		Logger.stringLog("====== click search button ==========");
		e(ElementEvent::click, By.name("btnK"));
		Logger.stringLog("====== wait to take screen shot ==========");
		e(ElementAssertEvent::assertVisible, By.xpath("//a//*[text()='Selenium']"));
		CommonUtil.takeScreenShot();
		Logger.stringLog("====== get our result ==========");
		e(ElementEvent::click, By.xpath("//a//*[text()='Selenium']"));
		CommonUtil.forceWait(3);
		e(WindowEvent::changeTo, "Selenium");
		e(ElementAssertEvent::assertVisible, By.xpath("//*[text()='Getting Started']"), 10);
		CommonUtil.takeScreenShot();
    }
    
}
