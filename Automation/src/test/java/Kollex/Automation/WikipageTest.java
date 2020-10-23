package Kollex.Automation;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

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
	}

	// Test2 Giga berlin
	public void searchGigaBerlin() {
		googleWikiNavigate();
		wp = new Wikipage(driver);
		String wikiSearch = props.getProperty("wikisearchtext");
		wp.getWikiSearchBox().sendKeys(wikiSearch);
		wp.getWikiSearchBox().sendKeys(Keys.ENTER);
		Assert.assertTrue(driver.getTitle().contains("Giga Berlin"));
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
		wpt.searchGigaBerlin();
	}

}
