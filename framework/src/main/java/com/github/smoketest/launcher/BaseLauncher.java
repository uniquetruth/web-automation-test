package com.github.smoketest.launcher;

import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.internal.TestResult;

import com.github.smoketest.event.BaseEvent;
import com.github.smoketest.testScene.ITestScene;
import com.github.smoketest.util.CommonUtil;
import com.github.smoketest.util.testNG.log.Logger;
import com.github.smoketest.util.testNG.report.SceneResultList;

/**
 * Base class of real Launcher。
 * @author uniqueT
 *
 */
public abstract class BaseLauncher {
	
	/**
	 * Should be created by sub class, test scene can use it directly
	 */
	protected WebDriver driver = null;
	private String initURL;
	private String sceneListStr;
	private String remoteIp;
	private String remotePort;
	private String browser;
	private boolean publicDriver;
	
	/**
	 * Main logic of this framework. invoke all test scenes and write record
	 * @throws MalformedURLException
	 */
	public void doTest() throws MalformedURLException {
		BaseEvent.initDriver(driver);
		List<String> sceneList = Arrays.asList(sceneListStr.split(";"));
		Iterator<String> it = sceneList.iterator();
		while(it.hasNext()) {
			String scene = it.next().trim();
			if(scene==null || "".equals(scene)) {
				continue;
			}
			TestResult tr = new TestResult();
			try {
				ITestScene testScene = getSceneIntance(scene);
				tr.setTestName(testScene.getDisplayName());
				//testNG under 7.0, use attribute to store start time
				tr.setAttribute("startTime", System.currentTimeMillis());
				testScene.doTest(driver, tr);
				tr.setStatus(ITestResult.SUCCESS);
			}catch (Exception e) {
				tr.setStatus(ITestResult.FAILURE);
				CommonUtil.takeScreenShot(driver);
				e.printStackTrace();
				Logger.exceptionLog(e);
				if(it.hasNext()) {
					quit();
					initContext();
				}
			}
			tr.setEndMillis(System.currentTimeMillis());
			SceneResultList.addTestReault(tr);
			if(!publicDriver && it.hasNext()) {
				quit();
				initContext();
			}
		}
	}
	
	/**
	 * create instance of driver
	 * @throws MalformedURLException
	 */
	protected abstract void initDriver() throws MalformedURLException;
	
	/**
	 * initialize context and driver
	 * @throws MalformedURLException
	 */
	public void initContext() throws MalformedURLException {
		initDriver();
		BaseEvent.initDriver(driver);
	}

	private ITestScene getSceneIntance(String scene) throws ReflectiveOperationException{
		Class<?> sceneClass = Class.forName(scene);
		Class<?> paraTypes[] = {};
		Object[] para = {};
		Object o = sceneClass.getConstructor(paraTypes).newInstance(para);
		if(o instanceof ITestScene) {
			return (ITestScene)o;
		}else {
			throw new ReflectiveOperationException(scene + "is not an instance of ITestScene");
		}
	}

	/**
	 * store basic information from TestNG .xml
	 * @param _initURL home page of application
	 * @param _sceneListStr scenes' list
	 * @param _browser
	 * @param defaultWaitSeconds
	 * @param _publicDriver
	 */
	public void initParameters(String _initURL,String _sceneListStr, String _browser, String defaultWaitSeconds, String _publicDriver) {
		setInitURL(_initURL);
		setSceneListStr(_sceneListStr);
		setBrowser(_browser);
		if(defaultWaitSeconds!=null && !"".equals(defaultWaitSeconds)) {
			BaseEvent.DEFAULT_WAIT_SECONDS = Integer.parseInt(defaultWaitSeconds);
		}
		publicDriver = !"false".equals(_publicDriver);
	}
	
	/**
	 * store remote information from TestNG .xml
	 * @param _remoteIp
	 * @param _remotePort
	 */
	public void initRemoteParams(String _remoteIp,String _remotePort) {
		setRemoteIp(_remoteIp);
		setRemotePort(_remotePort);
	}
	
	/**
	 * invoke at last of framework
	 */
	public void quit() {
		if(driver!=null) {
			driver.quit();
		}
	}

	/**
	 * webroot in .xml
	 * @return
	 */
	public String getInitURL() {
		return initURL;
	}

	private void setInitURL(String initURL) {
		this.initURL = initURL;
	}

	/**
	 * scenes in .xml
	 * @return
	 */
	public String getSceneListStr() {
		return sceneListStr;
	}

	private void setSceneListStr(String sceneListStr) {
		this.sceneListStr = sceneListStr;
	}

	/**
	 * remoteIp in .xml
	 * @return
	 */
	public String getRemoteIp() {
		return remoteIp;
	}

	private void setRemoteIp(String remoteIp) {
		this.remoteIp = remoteIp;
	}

	/**
	 * remotePort in .xml
	 * @return
	 */
	public String getRemotePort() {
		return remotePort;
	}

	private void setRemotePort(String remotePort) {
		this.remotePort = remotePort;
	}

	/**
	 * browser in .xml
	 * @return
	 */
	public String getBrowser() {
		return browser;
	}

	private void setBrowser(String browser) {
		this.browser = browser;
	}

}
