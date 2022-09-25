package com.github.smoketest.cbs.testCase;

import org.openqa.selenium.By;

import com.github.uniqueT.webAuto.event.AlertEvent;
import com.github.uniqueT.webAuto.event.ElementEvent;
import com.github.uniqueT.webAuto.event.FrameEvent;
import com.github.uniqueT.webAuto.event.InputTextEvent;
import com.github.uniqueT.webAuto.event.WindowEvent;
import com.github.uniqueT.webAuto.testScript.AbstractTestCase;

public class LoginPage extends AbstractTestCase{
	
	public static void login(String username, String password) {
		e(InputTextEvent::sendKeys, By.id("username"), username);
		e(InputTextEvent::sendKeys, By.id("password"), password);
		e(ElementEvent::click, By.xpath("//a[text()='login']"));
		if(AlertEvent.ifAlertInSeconds(5)) {
			e(AlertEvent::cancel);
		}
		e(WindowEvent::changeTo, "user select");
	}
	
	public static void selectCBSUser(String userName) {
		e(ElementEvent::setValue, By.name("userName"), userName);
		e(ElementEvent::onClick, By.id("vaeyo-login-submit"));
	}
	
	public static void logout() {
		e(FrameEvent::toRootFrame);
		e(ElementEvent::click, By.xpath("//a[@title='退出']"));
		//CommonUtil.forceWait(2);
		//Invoker.i(WindowEvent::toDefaultWindow);
		e(WindowEvent::changeTo, "new_tab_window");
		e(ElementEvent::click, By.xpath("//a[text()='目标服务']"));
	}

}
