package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("EPIC - 100 : design login for open cart app")
@Story("US-Login:101: design login page features for open cart")
public class LoginPageTest  extends BaseTest{
	
	@Severity(SeverityLevel.TRIVIAL) 
	@Description("....checking the title of the page......tester :Kathyayani")
	@Test(priority = 1)
	public void loginPageTitleTest()
	{
		String actualTitle = loginPage.getLoginPageTitle();
		Assert.assertEquals(actualTitle, AppConstants.LOGIN_PAGE_TITLE_VALUE);
	}
	@Severity(SeverityLevel.NORMAL) 
	@Description("....checking the URL of the page......tester :Kathyayani")
	@Test(priority = 2)
	public void checking()
	{
		String actualURL = loginPage.getLoginPageURL();
		Assert.assertTrue(actualURL.contains(AppConstants.LOGIN_PAGE_URL_FRACTION_VALUE));
	}
	@Severity(SeverityLevel.CRITICAL) 
	@Description("....checking forgot pwd link exist......tester :Kathyayani")
	@Test(priority = 3)
	public void forgotPwdLinkExistTest()
	{
		
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());
	}
	
	@Test(priority = 4)
	public void isImageLogoExistTest()
	{
		
		Assert.assertTrue(loginPage.isLogoExist());
	}
	@Test(priority = 5)
	public void loginPageHeaderTest()
	{
		String text = loginPage.getHeaderText();
		Assert.assertEquals(text, "Returning Customer");
	}
	//Non priority test cases will execute first
	@Test
	public void isFooterlinkExistTest()
	{
		
		Assert.assertTrue(loginPage.isFooterLinkDisplayed());
	}
	@Severity(SeverityLevel.CRITICAL) 
	@Description("....checking user is bale to login to app with correct username and password ......tester :Kathyayani")
	@Test(priority = 7)
	public void loginTest()
	{
		accPage =loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());	
		Assert.assertTrue(accPage.isLogoutLinkExist());
		Assert.assertEquals(accPage.getAccPageTitle(),"My Account");
		
	}

	

	

}
