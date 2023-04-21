package com.qa.opencart.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtil {

    private WebDriver driver;
    
    public JavaScriptUtil(WebDriver driver)
    {
    	this.driver = driver;
    }
    public String getTitleByJS()
    {
    	JavascriptExecutor js = (JavascriptExecutor) driver;
    	return js.executeScript("return document.title;").toString();
    }
    public void generateAlert(String message)
    {
    	JavascriptExecutor js = (JavascriptExecutor) driver;
    	js.executeScript("alert(' "+message+ " ')");
    }
    public void generateconfirmpopup(String message)
    {
    	JavascriptExecutor js = (JavascriptExecutor) driver;
    	js.executeScript("confirm(' "+message+ " ')");
    }
    public void refreshBrowserByJS()
    {
    	JavascriptExecutor js = (JavascriptExecutor) driver;
    	js.executeScript("history.go(0);");	//here inside method semicolon is optional
    }
    public void goBackByJS()
    {
    	JavascriptExecutor js = (JavascriptExecutor) driver;
    	js.executeScript("history.go(-1)");	//here inside method semicolon is optional
    }
    public void goForwardByJS()
    {
    	JavascriptExecutor js = (JavascriptExecutor) driver;
    	js.executeScript("history.go(1)");	//here inside method semicolon is optional
    }
    public String getPageInnerText()
    {
    	JavascriptExecutor js = (JavascriptExecutor) driver;
    	 return js.executeScript("return document.documentElement.innerText").toString();	//here inside method semicolon is optional
    }
    
    public void clickElementByJS(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}
    
    public void sendKeysUsingWithId(String id, String value) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.getElementById('" + id + "').value='" + value + "'");
						  //document.getElementById('input-email').value ='tom@gmail.com'
	}
    
    public void drawBorder(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.border='3px solid red'", element);
	}
    public void flash(WebElement element) {
		String bgcolor = element.getCssValue("backgroundColor");
		for (int i = 0; i < 10; i++) {
			changeColor("rgb(0,200,0)", element);// 1
			changeColor(bgcolor, element);// 2
		}
	}

	private void changeColor(String color, WebElement element) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].style.backgroundColor = '" + color + "'", element);

		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
		}
	}
	public void scrollPageDown() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	public void scrollPageDown(String height) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, '" + height + "')");
	}

	public void scrollPageUp() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(document.body.scrollHeight, 0)");
	}
	
	public void scrollPageDownMiddlepage() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight/2)");
	}
	
	public void scrollIntoView(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}
    
    
	

}
