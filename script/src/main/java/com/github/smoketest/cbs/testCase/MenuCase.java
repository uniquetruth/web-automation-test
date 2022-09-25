package com.github.smoketest.cbs.testCase;

import com.github.uniqueT.webAuto.event.ElementEvent;
import com.github.uniqueT.webAuto.event.cbs.FrameCBSEvent;
import com.github.uniqueT.webAuto.testScript.AbstractTestCase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class MenuCase extends AbstractTestCase {
	
	public static void openMenu(String menuPath) {
		String[] menus = menuPath.split("\\.");
		int i=0;
		for(; i<menus.length-1; i++) {
			String menu = menus[i];
			String subMenu = menus[i+1];
			WebElement m = ElementEvent.getElement(By.xpath("//a[@title=' "+subMenu+"']"));
			//如果下级菜单不可见，则需要展开当前级的菜单
			if(!m.isDisplayed()) {
				e(ElementEvent::onClick, By.xpath("//a[@title=' "+menu+"']"));
			}
		}
		//点击最后一级菜单
		e(ElementEvent::onClick, By.xpath("//a[@title=' "+menus[i]+"']"));
		//切换iframe
		e(FrameCBSEvent::toCurrentDisplayFrame);
	}

}
