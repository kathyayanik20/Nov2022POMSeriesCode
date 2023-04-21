package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By productHeader = By.tagName("h1"); 
	private By productImages = By.cssSelector("ul.thumbnails img");
	//diff cssselector for the same locator
	//private By productImages = By.cssSelector("div#content img");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=1]/li");
	private By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=2]/li");
	
	private By quantity = By.id("input-quantity");
	private By addToCartBtn = By.id("button-cart");
	private By cartSuccessMessage = By.cssSelector("div.alert.alert-success");
	
	private Map<String ,String> productInfoMap;

	
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver; 
		eleUtil = new ElementUtil(driver);
	}
	public String getProductHeaderValue()
	{
		String productHeaderVal = eleUtil.doElementGetText(productHeader);
		System.out.println("product header: " +productHeaderVal);
		return productHeaderVal;
	}
	public int getProductImagesCount()
	{
		int imagesCount = eleUtil.waitForElementsVisible(productImages, AppConstants.DEFAULT_MEDIUM_TIME_OUT).size();
		System.out.println("product images count: " +imagesCount);
		return imagesCount;
	}
	
	public void enterQuantity(int qty)
	{
		eleUtil.doSendkeys(quantity, String.valueOf(qty));
	}
	
	public String addProductToCart()
	{
		eleUtil.doClick(addToCartBtn);
		String SuccessMessg  = eleUtil.waitForElementVisible(cartSuccessMessage, AppConstants.DEFAULT_SHORT_TIME_OUT).getText();
		StringBuilder sb = new StringBuilder(SuccessMessg);
		String mesg = sb.substring(0, SuccessMessg.length()-1).replace("\n", "").toString();
		System.out.println("Cart Success Message:" +mesg); 
		return mesg;
	}
	
	public Map<String, String> getProductInfo()
	{
		//productInfoMap = new HashMap<String ,String>();
    	//productInfoMap = new LinkedHashMap<String ,String>();
		productInfoMap = new TreeMap<String ,String>();
		//header
		productInfoMap.put("productname", getProductHeaderValue());
		getProductMetaData();
		getProductPriceData();
		System.out.println(productInfoMap);
		
		return productInfoMap;
	}
	
	//fetching meta data
	private void getProductMetaData()
	{
//		Brand: Apple
//		Product Code: Product 18
//		Reward Points: 800
//		Availability: In Stock
		//meta data:
				List<WebElement> metaList =  eleUtil.getElements(productMetaData);
				for(WebElement e : metaList)
				{
					String meta =  e.getText();
					String metaInfo[] =  meta.split(":");
					String key = metaInfo[0].trim();
					String value = metaInfo[1].trim();
					productInfoMap.put(key, value);
				}
		
	}
	
	//fetching price data
	private void getProductPriceData()
	{
		//price
//		$2,000.00  // 0-location
//		Ex Tax: $2,000.00  // 1-location
		List<WebElement> priceList =  eleUtil.getElements(productPriceData);
		String price = priceList.get(0).getText();
		String exTax = priceList.get(1).getText();
		//String extaxVal[] = exTax.split(":");  //this will give you total value Ex Tax: $2,000.00
		String extaxVal = exTax.split(":")[1].trim();  //this will give you only price $2,000.00
		
		productInfoMap.put("productprice",price);
		productInfoMap.put("exTax",extaxVal);
	}

}
