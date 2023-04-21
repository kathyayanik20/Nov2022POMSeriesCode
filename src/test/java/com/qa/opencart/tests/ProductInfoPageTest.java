package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class ProductInfoPageTest extends BaseTest {
	
	//precondition to go to accounts page
	
		@BeforeClass
		public void accPageSetup()
		{
			accPage =loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		}
		
		@DataProvider
		public Object[][] getProductImagesTestData()
		{
			return new Object[][]
				{
				{"Macbook","MacBook Pro",4},
				{"iMac","iMac",3},
				{"Apple","Apple Cinema 30\"",6},
				{"Samsung","Samsung SyncMaster 941BW",1},
				{"Samsung","Samsung Galaxy Tab 10.1",7},
			};
		}
		@Test(dataProvider = "getProductImagesTestData")
		public void productImagesCountTest(String searchKey,String productName,int imagesCount)
		{
			searchPage = accPage.performSearch(searchKey);
			productInfoPage = searchPage.selectProduct(productName);
			int actImagesCount = productInfoPage.getProductImagesCount();
			Assert.assertEquals(actImagesCount, imagesCount);
		}

		@Test
		public void productInfoTest()
		{
			searchPage = accPage.performSearch("Macbook");
			productInfoPage = searchPage.selectProduct("MacBook Pro");
			Map<String, String> actProductInfoMap = productInfoPage.getProductInfo();
			softAssert.assertEquals(actProductInfoMap.get("Brand"), "Apple");
			softAssert.assertEquals(actProductInfoMap.get("Product Code"), "Product 18");
			softAssert.assertEquals(actProductInfoMap.get("productname"), "MacBook Pro");
			softAssert.assertEquals(actProductInfoMap.get("productprice"), "$2000.00");
			
			softAssert.assertAll();
		}
		
		@DataProvider
		public Object[][] addToCartTestData()
		{
			return new Object[][]
				{
				{"Macbook","MacBook Pro"},
				{"iMac","iMac"},
				{"Apple","Apple Cinema 30\""},
				{"Samsung","Samsung SyncMaster 941BW"},
				{"Samsung","Samsung Galaxy Tab 10.1"},
			};
		}
		
		@Test(dataProvider = "addToCartTestData")
		public void addToCartTest(String searchKey,String productName )
		{
			searchPage = accPage.performSearch(searchKey);
			productInfoPage = searchPage.selectProduct(productName);
			productInfoPage.enterQuantity(2);
			String actCartMesg = productInfoPage.addProductToCart();
			softAssert.assertTrue(actCartMesg.indexOf("Success")>=0);
			softAssert.assertTrue(actCartMesg.indexOf(productName)>=0);
			
			softAssert.assertEquals(actCartMesg, "Success: You have added " +productName+ " to your shopping cart!");
			softAssert.assertAll();
			
			
		}

}
