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
	private By coordinates = By.xpath("//span[@class='geo-dec']");
	
	// Element methods 
	public WebElement getWikiSearchBox() {
		return driver.findElement(wikiSearchBox);
	}
	
	public WebElement getCoordinates() {
		return driver.findElement(coordinates);
	}
	
	public String getLogisticsPara() {
		String logistics="";
		for(int i=18;i<=19;i++) {
			logistics = logistics.concat(driver.findElement(By.xpath("//*[@id='mw-content-text']/div[1]/p["+i+"]")).getText());
		}
		return logistics;
	}
	
	public String getSiteConcernsPara() {
		String siteConcerns = "";
		for(int i=20; i<=24; i++) {
			 siteConcerns = siteConcerns.concat(driver.findElement(By.xpath("//*[@id='mw-content-text']/div[1]/p["+i+"]")).getText());
		}
		return siteConcerns;
	}
}
