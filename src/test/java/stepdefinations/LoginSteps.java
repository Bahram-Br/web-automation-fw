package stepdefinations;

import org.testng.Assert;

import hooks.Hooks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pages.LoginPage;

public class LoginSteps {

	LoginPage loginPage ;
	
	@Given("user enters valid username")
	public void user_enters_valid_username() {
		
	   loginPage = new LoginPage(Hooks.driver);
	   loginPage.enterUserName("Admin");
	   
	}

	@And("user enters valid password")
	public void user_enters_valid_password() {
		
	    loginPage.enterPassword("admin123");
	    
	}

	@And("user clicks on login button")
	public void user_clicks_on_login_button() {
	    
		loginPage.clickLogin();
	}

	@Then("home page should be displayed")
	public void home_page_should_be_displayed() {
	    
		String actualURL = Hooks.driver.getCurrentUrl();
		String expectedURL = "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";
		Assert.assertEquals(actualURL, expectedURL);
	}

}