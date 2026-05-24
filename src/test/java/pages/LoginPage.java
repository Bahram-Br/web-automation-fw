package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver d) {
		super(d);
		PageFactory.initElements(d, this);
	}
	
	@FindBy(name = "username")
	private WebElement userName;
	
	@FindBy(name ="password")
	private WebElement password;
	
	@FindBy(xpath ="//button[@type='submit']")
	private WebElement login;
	
	public void enterUserName(String value) {
		enterValue(userName, value);
	}
	
	public void enterPassword(String value) {
		enterValue(password, value);
	}
	
	public void clickLogin() {
		click(login);
	}
}
