package com.github.uniqueT.webAuto.launcher.seleLauncher;

import java.net.MalformedURLException;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.github.uniqueT.webAuto.event.BaseEvent;
import com.github.uniqueT.webAuto.launcher.BaseLauncher;
import com.github.uniqueT.webAuto.launcher.options.ChromeOpt;
import com.github.uniqueT.webAuto.launcher.options.IEOpt;

/**
 * local test launcher, start selenium like {@code WebDriver driver = new ChromeDriver()}
 * @author uniqueT
 *
 */
public class LocalExecuteTest extends BaseLauncher {
	
	/**
	 * initializing driver
	 */
	@Override
	public void initDriver(){
		if("chrome".equals(getBrowser())) {
			driver = initChromeDirver(driverFile);
		}else if("ie".equals(getBrowser())){
			driver = initIEDirver(driverFile);
		}
		driver.get(getInitURL());
	}
	
	/**
	 * entrance of chrome execution, parameters are all from xml
	 * @param webroot from xml
	 * @param scenes from xml
	 * @param chromedriver path of chrome driver
	 * @param defaultWaitSeconds from xml
	 * @param publicDriver from xml
	 * @throws MalformedURLException if anything wrong in run.xml
	 */
	@Test
	@Parameters({"webroot","scenes","chromedriver","default_wait_seconds","public_driver"})
	public void chrome(String webroot, String scenes, String chromedriver, @Optional String defaultWaitSeconds, @Optional String publicDriver) throws MalformedURLException {
		initParameters(webroot, scenes, "chrome", defaultWaitSeconds, publicDriver);
		initContext(chromedriver);
		doTest();
	}
	
	/**
	 * entrance of ie execution, parameters are all from xml
	 * @param webroot from xml
	 * @param scenes from xml
	 * @param iedriver path of ie driver
	 * @param defaultWaitSeconds from xml
	 * @param publicDriver from xml
	 * @throws MalformedURLException if anything wrong in run.xml
	 */
	@Test
	@Parameters({"webroot","scenes","iedriver","default_wait_seconds","public_driver"})
	public void ie(String webroot, String scenes, String iedriver, @Optional String defaultWaitSeconds, @Optional String publicDriver) throws MalformedURLException {
		initParameters(webroot, scenes, "ie", defaultWaitSeconds, publicDriver);
		initContext(iedriver);
		doTest();
	}
	
	/**
	 * invoke super.quit()
	 */
	@AfterTest
	public void quit() {
		super.quit();
	}
	
	private WebDriver initChromeDirver(String driverFile) {
		WebDriver driver = new ChromeDriver(ChromeOpt.getOption(driverFile));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(BaseEvent.DEFAULT_WAIT_SECONDS));
		driver.manage().window().maximize();
		return driver;
	}
	
	private WebDriver initIEDirver(String driverFile) {
		WebDriver driver = new InternetExplorerDriver(IEOpt.getOption(driverFile));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(BaseEvent.DEFAULT_WAIT_SECONDS));
		driver.manage().window().maximize();
		return driver;
	}

}
