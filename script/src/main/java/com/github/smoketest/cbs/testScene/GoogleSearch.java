package com.github.smoketest.cbs.testScene;

import com.github.smoketest.cbs.testCase.SearchSeleniumCase;
import com.github.uniqueT.webAuto.testScript.AbstractTestScene;
import com.github.uniqueT.webAuto.testScript.anno.SceneComment;

@SceneComment("Google_search_test")
public class GoogleSearch extends AbstractTestScene {

	@Override
	public void doTest() {
		c(SearchSeleniumCase::doSearch);
	}

}
