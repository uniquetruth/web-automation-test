package com.github.smoketest.cbs.testScene;

import com.github.uniqueT.webAuto.testScript.AbstractTestScene;
import com.github.uniqueT.webAuto.testScript.anno.SceneComment;
import com.github.uniqueT.webAuto.util.CommonUtil;
import com.github.uniqueT.webAuto.util.testNG.log.Logger;
import com.github.smoketest.cbs.testCase.LoginPage;
import com.github.smoketest.cbs.testCase.MenuCase;
import com.github.smoketest.cbs.testCase.QueryCheckPage;

@SceneComment("newcheckSmokeTest")
public class NewCheck extends AbstractTestScene {

	@Override
	public void doTest() {
		//long startTime = System.currentTimeMillis();
		Logger.stringLog("====== 开始登录 ==========");
		c(LoginPage::login, "username","password");
		c(LoginPage::selectCBSUser, "beijing");
		
		Logger.stringLog("====== check操作 ==========");
		c(MenuCase::openMenu, "ParentMenu.subMenu");
		c(QueryCheckPage::searchPolicy, "123456987654");
		CommonUtil.takeScreenShot();
	}

	@Override
	public void doLast() {
		Logger.stringLog("======= 退出系统 ==========");
		c(LoginPage::logout);
		CommonUtil.takeScreenShot();
	}

}
