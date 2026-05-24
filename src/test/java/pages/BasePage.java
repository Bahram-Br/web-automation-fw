package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

	private WebDriverWait wait;
	
	public WebDriver driver;
	
	public BasePage(WebDriver d) {
		
		driver = d;
		
		wait = new WebDriverWait(d,Duration.ofSeconds(10));
		
		PageFactory.initElements(d, this);
	}
	
	public void click(WebElement element) {
		
		waitForClicking(element).click();
		
	}
	
	public void enterValue(WebElement element,String value) {
		
		waitforElementToBEVisible(element);
		element.clear();
		element.sendKeys(value);
	}
	
	private WebElement waitForClicking(WebElement element) {
		
		return wait.until(ExpectedConditions.elementToBeClickable(element));
		
	}
	
	private WebElement waitforElementToBEVisible(WebElement element) {
		
		return wait.until(ExpectedConditions.visibilityOf(element));
	}
}
