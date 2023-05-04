package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
	
  private WebDriver driver;
  private ElementUtil eleUtil;
  
	//1.private by locators
	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginbtn = By.xpath("//input[@value = 'Login']");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By logoImage = By.xpath("//img[@title ='naveenopencart']");
	private By headerText = By.xpath("//input[@id='input-email']/ancestor::div/h2");
	private By customerServiceFooterLink = By.xpath("(//footer//h5)[2]");
	private By registerLink = By.linkText("Register");
	
	//2.Page constructor 
	public LoginPage(WebDriver driver)
	{
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
    //3. Page Actions/Methods:
	@Step("....getting the login page Title.....")
	public String getLoginPageTitle()
	{
	 //String title = driver.getTitle();
	 String title = eleUtil.waitForTitleIsAndFetch(AppConstants.DEFAULT_SHORT_TIME_OUT, AppConstants.LOGIN_PAGE_TITLE_VALUE);
	 System.out.println("Login Page Title is :" +title);
	 return title;
	}
	@Step("....getting the login page url.....")
	public String getLoginPageURL()
	{
	 //String url = driver.getCurrentUrl();
	 String url = eleUtil.waitForURLContainsAndFetch(AppConstants.DEFAULT_SHORT_TIME_OUT, AppConstants.LOGIN_PAGE_URL_FRACTION_VALUE);
	 System.out.println("Login Page URL is :" +url);
	 return url;
	 }
	@Step("....getting the forgot password link....")
	public boolean isForgotPwdLinkExist()
	{
		//return driver.findElement(forgotPwdLink).isDisplayed();
		return eleUtil.waitForElementVisible(forgotPwdLink, AppConstants.DEFAULT_MEDIUM_TIME_OUT).isDisplayed();
	}
	@Step("login with username :{0} and password: {1}")
	public AccountsPage doLogin(String un,String pwd)
	{
//		driver.findElement(emailId).sendKeys(un);
//		driver.findElement(password).sendKeys(pwd);
//		driver.findElement(loginbtn).click();
		System.out.println("App credentials are :"+un +":"+pwd);
		eleUtil.waitForElementVisible(emailId, AppConstants.DEFAULT_MEDIUM_TIME_OUT).sendKeys(un);
		eleUtil.doSendkeys(password, pwd);
		eleUtil.doClick(loginbtn);
		
		return new AccountsPage(driver);
	}
	@Step("navigating to register page")
	public RegisterPage navigateToRegisterPage()
	{
		eleUtil.doClick(registerLink);
		return new RegisterPage(driver);
	}
	public boolean isLogoExist()
	{
		//return driver.findElement(logoImage).isDisplayed();
		return eleUtil.waitForElementVisible(logoImage, AppConstants.DEFAULT_MEDIUM_TIME_OUT).isDisplayed();
	}
	public String getHeaderText()
	{
		//return driver.findElement(headerText).getText();
		return eleUtil.waitForElementVisible(headerText, AppConstants.DEFAULT_MEDIUM_TIME_OUT).getText();
	}
	public boolean isFooterLinkDisplayed()
	{
		//return driver.findElement(customerServiceFooterLink).isDisplayed();
		return eleUtil.waitForElementVisible(customerServiceFooterLink, AppConstants.DEFAULT_MEDIUM_TIME_OUT).isDisplayed();
	}
	
	
	
	
	
	
}
