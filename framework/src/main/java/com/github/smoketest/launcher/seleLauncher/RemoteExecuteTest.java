package com.github.smoketest.launcher.seleLauncher;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.github.smoketest.event.BaseEvent;
import com.github.smoketest.launcher.BaseLauncher;

/**
 * remote test launcher, start selenium like {@code WebDriver driver = new RemoteWebDriver(url, dc)}
 * @author uniqueT
 *
 */
public class RemoteExecuteTest extends BaseLauncher {
	
	@Override
	public void initDriver() throws MalformedURLException {
		if("chrome".equals(getBrowser())) {
			driver = initChromeDirver();
		}else if("ie".equals(getBrowser())){
			driver = initIEDirver();
		}
		driver.get(getInitURL());
	}
	
	/**
	 * entrance of chrome execution, parameters are all from xml
	 * @param webroot
	 * @param scenes
	 * @param remoteIp
	 * @param remotePort
	 * @param defaultWaitSeconds
	 * @param publicDriver
	 * @throws MalformedURLException if anything wrong in run.xml
	 */
	@Test
	@Parameters({"webroot","scenes","remoteIp","remotePort","default_wait_seconds","public_driver"})
	public void chrome(String webroot,String scenes, String remoteIp, String remotePort, @Optional String defaultWaitSeconds, @Optional String publicDriver) throws MalformedURLException {
		initParameters(webroot, scenes, "chrome", defaultWaitSeconds, publicDriver);
		initRemoteParams(remoteIp, remotePort);
		initContext();
		doTest();
	}
	
	/**
	 * entrance of ie execution, parameters are all from xml
	 * @param webroot
	 * @param scenes
	 * @param remoteIp
	 * @param remotePort
	 * @param defaultWaitSeconds
	 * @param publicDriver
	 * @throws MalformedURLException if anything wrong in run.xml
	 */
	@Test
	@Parameters({"webroot","scenes","remoteIp","remotePort","default_wait_seconds","public_driver"})
	public void ie(String webroot,String scenes, String remoteIp, String remotePort, @Optional String defaultWaitSeconds, @Optional String publicDriver) throws MalformedURLException {
		initParameters(webroot, scenes, "ie", defaultWaitSeconds, publicDriver);
		initRemoteParams(remoteIp, remotePort);
		initContext();
		doTest();
	}
	
	/**
	 * invoke super.quit()
	 */
	@AfterTest
	public void quit() {
		super.quit();
	}
	
	private WebDriver initChromeDirver() throws MalformedURLException {
		DesiredCapabilities dc = DesiredCapabilities.chrome();
		WebDriver driver = new RemoteWebDriver(new URL("http://"+getRemoteIp()+":"+getRemotePort()+"/wd/hub"), dc);
		driver.manage().timeouts().implicitlyWait(BaseEvent.DEFAULT_WAIT_SECONDS, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		return driver;
	}
	
	private WebDriver initIEDirver() throws MalformedURLException {
		DesiredCapabilities dc = DesiredCapabilities.internetExplorer();
		WebDriver driver = new RemoteWebDriver(new URL("http://"+getRemoteIp()+":"+getRemotePort()+"/wd/hub"), dc);
		driver.manage().timeouts().implicitlyWait(BaseEvent.DEFAULT_WAIT_SECONDS, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		return driver;
	}

}
