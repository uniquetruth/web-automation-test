package com.github.smoketest.launcher.seleLauncher;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.github.smoketest.event.BaseEvent;
import com.github.smoketest.launcher.BaseLauncher;

/**
 * local test launcher, start selenium like {@code WebDriver driver = new ChromeDriver()}
 * @author uniqueT
 *
 */
public class LocalExecuteTest extends BaseLauncher {
	
	@Override
	public void initDriver(){
		if("chrome".equals(getBrowser())) {
			driver = initChromeDirver();
		}else if("ie".equals(getBrowser())){
			driver = initIEDirver();
		}
		driver.get(getInitURL());
	}
	
	/**
	 * entrance of chrome execution, parameters are all from xml
	 * @param webroot from xml
	 * @param scenes from xml
	 * @param defaultWaitSeconds from xml
	 * @param publicDriver from xml
	 * @throws MalformedURLException if anything wrong in run.xml
	 */
	@Test
	@Parameters({"webroot","scenes","default_wait_seconds","public_driver"})
	public void chrome(String webroot, String scenes, @Optional String defaultWaitSeconds, @Optional String publicDriver) throws MalformedURLException {
		initParameters(webroot, scenes, "chrome", defaultWaitSeconds, publicDriver);
		initContext();
		doTest();
	}
	
	/**
	 * entrance of ie execution, parameters are all from xml
	 * @param webroot
	 * @param scenes
	 * @param defaultWaitSeconds
	 * @param publicDriver
	 * @throws MalformedURLException if anything wrong in run.xml
	 */
	@Test
	@Parameters({"webroot","scenes","default_wait_seconds","public_driver"})
	public void ie(String webroot, String scenes, @Optional String defaultWaitSeconds, @Optional String publicDriver) throws MalformedURLException {
		initParameters(webroot, scenes, "ie", defaultWaitSeconds, publicDriver);
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
	
	private WebDriver initChromeDirver() {
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(BaseEvent.DEFAULT_WAIT_SECONDS, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		return driver;
	}
	
	private WebDriver initIEDirver() {
		WebDriver driver = new InternetExplorerDriver();
		driver.manage().timeouts().implicitlyWait(BaseEvent.DEFAULT_WAIT_SECONDS, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		return driver;
	}

}
