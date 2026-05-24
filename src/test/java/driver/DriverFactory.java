package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import constants.FrameworkConstants;

public class DriverFactory {
	
	public static WebDriver initDriver(String browsername) {
		
		WebDriver driver;
		
		switch (browsername) {
		case FrameworkConstants.CHROME_BROWSER:
			driver = new ChromeDriver();
			break;
			
		case FrameworkConstants.EDGE_BROWSER:
			driver = new EdgeDriver();
			break;

		default:
			throw new IllegalStateException("Invalid browser name");
		}
		
		return driver;
		
	}
}
