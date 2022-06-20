package com.github.smoketest.cbs.testCase;

import com.github.smoketest.event.ElementEvent;
import com.github.smoketest.event.cbs.FrameCBSEvent;
import com.github.smoketest.event.invoke.Invoker;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class MenuCase {
	
	public static void openMenu(String menuPath) {
		String[] menus = menuPath.split("\\.");
		int i=0;
		for(; i<menus.length-1; i++) {
			String menu = menus[i];
			String subMenu = menus[i+1];
			WebElement m = ElementEvent.getElement(By.xpath("//a[@title=' "+subMenu+"']"));
			//如果下级菜单不可见，则需要展开当前级的菜单
			if(!m.isDisplayed()) {
				Invoker.i(ElementEvent::onClick, By.xpath("//a[@title=' "+menu+"']"));
			}
		}
		//点击最后一级菜单
		Invoker.i(ElementEvent::onClick, By.xpath("//a[@title=' "+menus[i]+"']"));
		//切换iframe
		Invoker.i(FrameCBSEvent::toCurrentDisplayFrame);
	}

}
