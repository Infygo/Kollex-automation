package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import resources.Initialisation;

public class Wikipage extends Initialisation {
	public WebDriver driver ;
	
	public Wikipage(WebDriver driver) {
		this.driver = driver;
	}
	
	// pageproperties 
	private By wikiSearchBox = By.xpath("//input[@id='searchInput']");
	
	// Element methods 
	public WebElement getWikiSearchBox() {
		return driver.findElement(wikiSearchBox);
	}
}
