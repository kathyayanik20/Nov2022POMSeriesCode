package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.aspectj.util.FileUtil;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.exception.FrameworkException;

public class DriverFactory {

	public WebDriver driver;
	public Properties prop;
	public OptionsManager optionsManager;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	public static String highlight;
	
	/**
	 * This method is initializing the driver on the basis of browser
	 * @param browserName
	 * @return this returns the driver
	 */
	public WebDriver initDriver(Properties prop)
	{
		highlight = prop.getProperty("highlight").trim();
		String browserName = prop.getProperty("browser").toLowerCase().trim();
		System.out.println("browser name is : " + browserName);
		optionsManager = new OptionsManager(prop);
		if(browserName.equalsIgnoreCase("chrome"))
		{
			//driver = new ChromeDriver(optionsManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			//driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
		}
		else if(browserName.equalsIgnoreCase("safari"))
		{
			driver = new SafariDriver();
		}
		else if(browserName.equalsIgnoreCase("edge"))
		{
			//driver = new EdgeDriver(optionsManager.getEdgeOptions());
			tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
		}
		else
		{
			System.out.println("Plz pass the right browser name: "+browserName);
			throw new FrameworkException("NO BROWSER FOUND EXCEPTION");
		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url").trim());
		return getDriver();
	}
	
	/**
	 * get the local thread copy of the driver
	 * @return
	 */
	public  synchronized static WebDriver getDriver()
	{
		 return tlDriver.get();
	}
	
	/**
	 * This method reading properties from .properties file
	 * @return
	 */
	public Properties initProp()
	{
		prop = new Properties();
			FileInputStream ip = null;
			String envName = System.getProperty("env");
			System.out.println("Running test cases on Env:" +envName);
			try {
				if(envName == null)
				{
					System.out.println("no env is passed ....Running tests on QA env......");
					ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
				}else
				{
					switch (envName.toLowerCase().trim()) {
					case "qa":
						ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
						break;
					case "stage":
						ip = new FileInputStream("./src/test/resources/config/stage.config.properties");
						break;	
					case "dev":
						ip = new FileInputStream("./src/test/resources/config/dev.config.properties");
						break;	
					case "prod":
						ip = new FileInputStream("./src/test/resources/config/config.properties");
						break;
					default:
						System.out.println("...Wrong env is passed....No need to run the test cases....");
						throw new FrameworkException("WRONG ENV IS PASSED......");
						//break;
					}
				}
			}catch(FileNotFoundException e) {
		
			}
		try
			 {
			prop.load(ip);
			 } catch(IOException e)
		{
			e.printStackTrace();
		}
		
		return prop;
			}
	
	
	public static String getScreenshot()
	{
		File srcFile = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir")+"/screenshot/"+System.currentTimeMillis()+".png";
		File destination = new File(path); 
		try {
			FileUtil.copyFile(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}
	
	

}
