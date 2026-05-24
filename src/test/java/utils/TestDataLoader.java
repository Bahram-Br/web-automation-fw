package utils;

import java.util.Properties;

import constants.FrameworkConstants;

public class TestDataLoader {

	private static TestDataLoader testDataLoader;
	
	public static TestDataLoader getInstance() {
		
		if (testDataLoader == null) {
			testDataLoader = new TestDataLoader();
		}
		
		return testDataLoader;
	}
	
	Properties prop;
	
	private TestDataLoader() {
		
		switch (FrameworkConstants.ENVIRONMENT) {
		case "QA":
			prop = 
			PropertyUtils.
			propertyUtils(FrameworkConstants.QA_TEST_DATA_FILE);
			break;

		case "Prod":
			prop = 
			PropertyUtils.
			propertyUtils(FrameworkConstants.PROD_TEST_DATA_FILE);
			break;
			
		default:
			break;
		}
		
	}
	
	public String getAppURL() {
		
		return prop.getProperty("appurl");
	}
	
}
