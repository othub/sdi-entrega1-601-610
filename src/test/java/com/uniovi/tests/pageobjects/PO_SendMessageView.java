package com.uniovi.tests.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * 
 * @version $Id$
 */
public class PO_SendMessageView extends PO_NavView {

	/**
	 * @param driver
	 * @param string
	 * @param string2
	 * @param i
	 */
	public static void fillForm(WebDriver driver, String msgf) {
		WebElement title = driver.findElement(By.name("messageText"));
		title.click();
		title.clear();
		title.sendKeys(msgf);
		
		By boton = By.className("btn");
		driver.findElement(boton).click();
	}
}
