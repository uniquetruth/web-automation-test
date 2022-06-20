package com.github.smoketest.cbs.testCase;

import org.openqa.selenium.By;

import com.github.smoketest.event.AlertEvent;
import com.github.smoketest.event.ElementEvent;
import com.github.smoketest.event.FrameEvent;
import com.github.smoketest.event.InputTextEvent;
import com.github.smoketest.event.WindowEvent;
import com.github.smoketest.event.invoke.Invoker;
import com.github.smoketest.util.CommonUtil;

public class LoginPage {
	
	public static void login(String username, String password) {
		Invoker.i(InputTextEvent::sendKeys, By.id("username"), username);
		Invoker.i(InputTextEvent::sendKeys, By.id("password"), password);
		Invoker.i(ElementEvent::click, By.xpath("//a[text()='login']"));
		if(AlertEvent.ifAlertInSeconds(5)) {
			Invoker.i(AlertEvent::cancel);
		}
		Invoker.i(WindowEvent::changeTo, "用户选择");
	}
	
	public static void selectCBSUser(String userName) {
		Invoker.i(ElementEvent::setValue, By.name("userName"), userName);
		//这里ie用click会点不到登录按钮
		Invoker.i(ElementEvent::onClick, By.id("vaeyo-login-submit"));
	}
	
	public static void logout() {
		Invoker.i(FrameEvent::toRootFrame);
		Invoker.i(ElementEvent::click, By.xpath("//a[@title='退出']"));
		//CommonUtil.forceWait(2);
		//Invoker.i(WindowEvent::toDefaultWindow);
		Invoker.i(WindowEvent::changeTo, "new_tab_window");
		Invoker.i(ElementEvent::click, By.xpath("//a[text()='目标服务']"));
	}

}
