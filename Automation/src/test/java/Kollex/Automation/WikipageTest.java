package Kollex.Automation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import pageObjects.Googlepage;
import pageObjects.Wikipage;
import resources.Initialisation;

public class WikipageTest extends Initialisation {
	public WebDriver driver;
	Googlepage gp;
	Wikipage wp;

	public void setUp(String browser) throws IOException, InterruptedException {
		driver = intialiseDriver(browser);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		wp = new Wikipage(driver);
	}

	// Test2 Giga berlin
	public void searchGigaBerlin() {
		wp = googleWikiNavigate();
		String wikiSearch = props.getProperty("wikisearchtext");
		wp.getWikiSearchBox().sendKeys(wikiSearch);
		wp.getWikiSearchBox().sendKeys(Keys.ENTER);
		Assert.assertTrue(driver.getTitle().contains("Giga Berlin"));
		System.out.println("Test passed");
	}

	// Test3 - Get Coordinate , Logistics, Site concerns
	public void getGigaBerlinData() {
		wp = googleWikiNavigate();
		String wikiSearch = props.getProperty("wikisearchtext");
		String mapsUrl = props.getProperty("googlemaps");
		wp.getWikiSearchBox().sendKeys(wikiSearch);
		wp.getWikiSearchBox().sendKeys(Keys.ENTER);

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

	public static void main(String[] args) throws IOException, InterruptedException {
		WikipageTest wpt = new WikipageTest();
		wpt.setUp("chrome");
		// wpt.searchGigaBerlin();
		wpt.getGigaBerlinData();
	}

}
