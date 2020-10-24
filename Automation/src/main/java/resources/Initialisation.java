package resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Initialisation {
	public WebDriver driver;
	public Properties props;

	public WebDriver intialiseDriver(String browserName) throws IOException {
		props = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\resources\\data.properties");
		props.load(fis);
		if (browserName.contains("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\src\\main\\java\\resources\\chromedriver.exe");
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("profile.default_content_setting_values.cookies", 2);
			prefs.put("network.cookie.cookieBehavior", 2);
			prefs.put("profile.block_third_party_cookies", true);

			ChromeOptions chOptions = new ChromeOptions();
			chOptions.setExperimentalOption("prefs", prefs);

			if (browserName.contains("headless")) {
				chOptions.addArguments("headless");
			}
			driver = new ChromeDriver(chOptions);

		} else if (browserName.contains("firefox")) {
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + "\\src\\main\\java\\resources\\geckodriver.exe");

			DesiredCapabilities dc = DesiredCapabilities.firefox();
			FirefoxProfile profile = new FirefoxProfile();
			profile.setPreference("network.cookie.cookieBehavior", 2);
			dc.setCapability(FirefoxDriver.PROFILE, profile);
			FirefoxOptions fo = new FirefoxOptions();
			fo.merge(dc);
			driver = new FirefoxDriver(fo);
		}
		return driver;
	}

}
