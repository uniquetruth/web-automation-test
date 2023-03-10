package com.github.uniqueT.webAuto.launcher;

import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.internal.TestResult;

import com.github.uniqueT.webAuto.event.BaseEvent;
import com.github.uniqueT.webAuto.testScript.ITestScene;
import com.github.uniqueT.webAuto.testScript.anno.SceneComment;
import com.github.uniqueT.webAuto.util.CommonUtil;
import com.github.uniqueT.webAuto.util.testNG.log.Logger;
import com.github.uniqueT.webAuto.util.testNG.report.SceneResultList;

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
	/**
	 * driver's path, get from the xml file
	 */
	protected String driverFile;
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
		List<String> sceneList = Arrays.asList(sceneListStr.split(";"));
		Iterator<String> it = sceneList.iterator();
		while(it.hasNext()) {
			String scene = it.next().trim();
			if(scene==null || "".equals(scene)) {
				continue;
			}
			TestResult tr = TestResult.newEmptyTestResult();
			//TestResult tr = new TestResult();
			ITestScene testScene = null;
			try {
				testScene = getSceneIntance(scene);
				String testName = getDisplayName(testScene);
				if(testName!=null) {
					tr.setTestName(testName);
				}else {
					tr.setTestName(scene);
				}
				tr.setAttribute("testClassName", scene);
				tr.setStatus(TestResult.STARTED);
				//we don't use factory methods of TestResult, so store start time manully
				tr.setAttribute("startTime", System.currentTimeMillis());
				testScene.doTest(tr);
				tr.setStatus(ITestResult.SUCCESS);
			}catch (UnhandledAlertException e) {
				tr.setStatus(ITestResult.FAILURE);
				CommonUtil.takeScreenShot();
				e.printStackTrace();
				Logger.exceptionLog(e);
				driver.switchTo().alert().dismiss();
			}catch (Exception e) {
				tr.setStatus(ITestResult.FAILURE);
				CommonUtil.fullScreenShot();
				e.printStackTrace();
				Logger.exceptionLog(e);
			}
			//isolating doTest and doLast methods
			try {
				testScene.doLast();
			}catch (Exception e) {
				tr.setStatus(ITestResult.FAILURE);
				CommonUtil.fullScreenShot();
				e.printStackTrace();
				Logger.exceptionLog(e);
			}
			tr.setEndMillis(System.currentTimeMillis());
			SceneResultList.addTestReault(tr);
			if(!publicDriver && it.hasNext()) {
				quit();
				initContext();
			}
		}
	}
	
	private String getDisplayName(ITestScene testScene) {
		Class<? extends ITestScene> c = testScene.getClass();
		if(c.isAnnotationPresent(SceneComment.class)) {
			return c.getAnnotation(SceneComment.class).value();
		}else {
			return null;
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
	protected void initContext(String _driverFile) throws MalformedURLException {
		driverFile = _driverFile;
		initContext();
	}
	
	private void initContext() throws MalformedURLException {
		initDriver();
		BaseEvent.initDriver(driver);
	}

	private ITestScene getSceneIntance(String scene) throws ReflectiveOperationException{
		Class<?> sceneClass = null;
		//A scene starts with @, means we need to find test scene by its annotation
		if(scene.startsWith("@")) {
			sceneClass = SceneScanner.getSceneFromComment(scene.substring(1));
		}else {
			sceneClass = Class.forName(scene);
		}
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
