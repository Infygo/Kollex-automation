package Kollex.Automation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

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
	Googlepage gp;
	Wikipage wp;
    
	@Parameters("browser")
	@BeforeMethod(alwaysRun = true)
	public void setUp(String browser) throws IOException, InterruptedException {
		driver = intialiseDriver(browser);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		wp = new Wikipage(driver);
	}

	// Test2 Giga berlin
	@Test
	public void searchGigaBerlin() {
		wp = googleWikiNavigate();
		WebDriverWait w = new WebDriverWait(driver, 10);
		String wikiSearch = props.getProperty("wikisearchtext");
		wp.getWikiSearchBox().sendKeys(wikiSearch);
		wp.getWikiSearchBox().sendKeys(Keys.ENTER);
		w.until(ExpectedConditions.titleContains("Giga Berlin"));
		Assert.assertTrue(driver.getTitle().contains("Giga Berlin"));
		System.out.println("Test passed");
	}

	// Test3 - Get Coordinate , Logistics, Site concerns
	@Test
	public void getGigaBerlinData() throws InterruptedException {
		wp = googleWikiNavigate();
		String wikiSearch = props.getProperty("wikisearchtext");
		String mapsUrl = props.getProperty("googlemaps");
		wp.getWikiSearchBox().sendKeys(wikiSearch);
		wp.getWikiSearchBox().sendKeys(Keys.ENTER);
		WebDriverWait w = new WebDriverWait(driver, 10);

		String coordinates = wp.getCoordinates().getText();
		System.out.println(coordinates);
		String logisticsData = wp.getLogisticsPara();
		String siteConcernsData = wp.getSiteConcernsPara();

		System.out.println(logisticsData);
		System.out.println(siteConcernsData);

		// Open google Maps in new tab and check the coordinates
		((JavascriptExecutor) driver).executeScript("window.open()");
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		driver.get(mapsUrl);
		wp.getMapSearchBox().sendKeys(coordinates);
		wp.getMapSearchBox().sendKeys(Keys.ENTER);
		w.until(ExpectedConditions.visibilityOf(wp.getLocation()));
		Assert.assertTrue(wp.getLocation().getText().contains("Grünheide"));
		System.out.println("Test passed");

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
    
	/*
	public static void main(String[] args) throws IOException, InterruptedException {
		WikipageTest wpt = new WikipageTest();
		wpt.setUp("firefox");
		// wpt.searchGigaBerlin();
		wpt.getGigaBerlinData();
	}
	*/

}
