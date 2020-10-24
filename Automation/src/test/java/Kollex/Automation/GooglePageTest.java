package Kollex.Automation;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageObjects.Googlepage;
import resources.Initialisation;

public class GooglePageTest extends Initialisation {
	public WebDriver driver;
	Googlepage gp;
	private static Logger log = LogManager.getLogger(GooglePageTest.class.getName());
	File screenShotFolder = new File(System.getProperty("user.dir") + "\\src\\main\\java\\screenshots");

	@Parameters("browser")
	@BeforeMethod(alwaysRun = true)
	public void setUp(String browser) throws IOException, InterruptedException {
		driver = intialiseDriver(browser);
		driver.get(props.getProperty("googleurl"));
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	// Test1 - google page to Wikipedia
	// page transition happens here
	@Test
	public void googleToWiki() throws IOException {
		FileUtils.cleanDirectory(screenShotFolder);
		gp = new Googlepage(driver);
		String wikiurl = props.getProperty("wikiurl");
		gp.getSearchBox().sendKeys(wikiurl);
		gp.getSearchBox().sendKeys(Keys.ENTER);
		gp.getWikiPage();
		takeScreenShot(driver, "Test1_Wikipage.png");
		Assert.assertEquals(driver.getTitle(), "Wikipedia");
		log.info("Google page to Wikipedia page - Test success");
	}
	
	@AfterMethod(alwaysRun = true)
	public void tearDown(){
		driver.quit();
	}
    
	/*
	public static void main(String[] args) throws IOException, InterruptedException {
		GooglePageTest gpt = new GooglePageTest();
		gpt.setUp("chrome");
		gpt.googleToWiki();
	}
	*/

}
