package com.github.smoketest.cbs.testScene;

import com.github.smoketest.testScene.AbstractTestScene;
import com.github.smoketest.util.CommonUtil;
import com.github.smoketest.util.testNG.log.Logger;
import com.github.smoketest.cbs.testCase.LoginPage;
import com.github.smoketest.cbs.testCase.MenuCase;
import com.github.smoketest.cbs.testCase.QueryCheckPage;

public class NewCheck extends AbstractTestScene {

	@Override
	public void doTest() {
		//long startTime = System.currentTimeMillis();
		Logger.customLog("====== 开始登录 ==========");
		LoginPage.login("username", "password");
		LoginPage.selectCBSUser("beijing");
		
		Logger.customLog("====== check操作 ==========");
		MenuCase.openMenu("ParentMenu.subMenu");
		QueryCheckPage.searchPolicy("123456987654");
		CommonUtil.takeScreenShot(driver);
		
		Logger.customLog("======= 退出系统 ==========");
		LoginPage.logout();
		CommonUtil.takeScreenShot(driver);
		//Logger.customLog("实际耗时："+((System.currentTimeMillis()-startTime)/1000));
	}

	@Override
	public String getDisplayName() {
		return "newcheck冒烟测试";
	}

}
