package Kollex.Automation;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageObjects.Googlepage;
import pageObjects.Wikipage;
import resources.Initialisation;

public class WikipageTest extends Initialisation {
	public WebDriver driver;
	private static Logger log = LogManager.getLogger(WikipageTest.class.getName());
	File screenShotFolder = new File(System.getProperty("user.dir") + "\\src\\main\\java\\screenshots");
	Googlepage gp;
	Wikipage wp;

	@Parameters("browser")
	@BeforeMethod(alwaysRun = true)
	public void setUp(String browser) throws IOException, InterruptedException {
		driver = intialiseDriver(browser);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		wp = new Wikipage(driver);
	}

	// Test2 Giga berlin Wikipedia 
	@Test(priority = 0)
	public void searchGigaBerlin() throws IOException {
		wp = googleWikiNavigate();
		WebDriverWait w = new WebDriverWait(driver, 10);
		String wikiSearch = props.getProperty("wikisearchtext");
		wp.getWikiSearchBox().sendKeys(wikiSearch);
		wp.getWikiSearchBox().sendKeys(Keys.ENTER);
		w.until(ExpectedConditions.titleContains("Giga Berlin"));
		takeScreenShot(driver, "Test2_Gigaberlin.png");
		Assert.assertTrue(driver.getTitle().contains("Giga Berlin"));
		log.info("Wikipage Giga Berlin- Test success ");
	}

	// Test3 - Get Coordinate , Logistics, Site concerns, Open maps with coordinates 
	@Test(priority = 1)
	public void getGigaBerlinData() throws InterruptedException, IOException {
		wp = googleWikiNavigate();
		String wikiSearch = props.getProperty("wikisearchtext");
		String mapsUrl = props.getProperty("googlemaps");

		wp.getWikiSearchBox().sendKeys(wikiSearch);
		wp.getWikiSearchBox().sendKeys(Keys.ENTER);
		String gigaBerlin = driver.getTitle();
		WebDriverWait w = new WebDriverWait(driver, 10);

		String coordinates = wp.getCoordinates().getText();
		takeScreenShot(driver, "Test3_Coordinates.png");

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", wp.getLogisticHeader());
		String logisticsData = wp.getLogisticsPara();
		takeScreenShot(driver, "Test3_Logistics.png");

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", wp.getSiteConcerHeader());
		String siteConcernsData = wp.getSiteConcernsPara();
		takeScreenShot(driver, "Test3_Siteconcerns.png");

		// Open google Maps in new tab and check the coordinates
		((JavascriptExecutor) driver).executeScript("window.open()");
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		driver.get(mapsUrl);
		wp.getMapSearchBox().sendKeys(coordinates);
		wp.getMapSearchBox().sendKeys(Keys.ENTER);
		w.until(ExpectedConditions.visibilityOf(wp.getLocation()));
		Assert.assertTrue(wp.getLocation().getText().contains("Grünheide"));
		copyToCsv(gigaBerlin, coordinates, logisticsData, siteConcernsData);
		log.info("Giga Berlin data retrieve - Test success");

	}

	private Wikipage googleWikiNavigate() {
		driver.get(props.getProperty("googleurl"));
		gp = new Googlepage(driver);
		String wikiurl = props.getProperty("wikiurl");
		gp.getSearchBox().sendKeys(wikiurl);
		gp.getSearchBox().sendKeys(Keys.ENTER);
		return gp.getWikiPage();
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}

}
