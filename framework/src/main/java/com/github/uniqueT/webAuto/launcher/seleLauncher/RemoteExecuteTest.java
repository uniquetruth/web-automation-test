package com.github.uniqueT.webAuto.launcher.seleLauncher;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.github.uniqueT.webAuto.event.BaseEvent;
import com.github.uniqueT.webAuto.launcher.BaseLauncher;
import com.github.uniqueT.webAuto.launcher.options.ChromeOpt;
import com.github.uniqueT.webAuto.launcher.options.IEOpt;

/**
 * remote test launcher, start selenium like {@code WebDriver driver = new RemoteWebDriver(url, capabilities)}
 * @author uniqueT
 *
 */
public class RemoteExecuteTest extends BaseLauncher {
	
	/**
	 * initializing driver
	 */
	@Override
	public void initDriver() throws MalformedURLException {
		if("chrome".equals(getBrowser())) {
			//remote start doesn't require the file path of driver, here driverFile is just a filling
			driver = initChromeDirver(driverFile);
		}else if("ie".equals(getBrowser())){
			driver = initIEDirver(driverFile);
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
		initContext("");
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
		initContext("");
		doTest();
	}
	
	/**
	 * invoke super.quit()
	 */
	@AfterTest
	public void quit() {
		super.quit();
	}
	
	private WebDriver initChromeDirver(String driverFile) throws MalformedURLException {
		//DesiredCapabilities dc = DesiredCapabilities.chrome();
		ChromeOptions opts = ChromeOpt.getOption(driverFile);
		WebDriver driver = new RemoteWebDriver(new URL("http://"+getRemoteIp()+":"+getRemotePort()+"/wd/hub"), opts);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(BaseEvent.DEFAULT_WAIT_SECONDS));
		driver.manage().window().maximize();
		return driver;
	}
	
	private WebDriver initIEDirver(String driverFile) throws MalformedURLException {
		InternetExplorerOptions opts = IEOpt.getOption(driverFile);
		WebDriver driver = new RemoteWebDriver(new URL("http://"+getRemoteIp()+":"+getRemotePort()+"/wd/hub"), opts);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(BaseEvent.DEFAULT_WAIT_SECONDS));
		driver.manage().window().maximize();
		return driver;
	}

}
