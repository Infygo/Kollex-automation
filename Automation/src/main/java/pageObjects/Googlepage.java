package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Googlepage {
	public WebDriver driver;

	public Googlepage(WebDriver driver) {
		this.driver = driver;
	}

	// pageproperties
	private By searchBox = By.xpath("//input[@title='Search']");
	private By wikiLink = By.xpath("//span[text() = 'Wikipedia']");

	// Element methods
	public WebElement getSearchBox() {
		return driver.findElement(searchBox);
	}
	
	public Wikipage getWikiPage() {
		driver.findElement(wikiLink).click();
		Wikipage wp = new Wikipage(driver);
		return wp;
	}

}
