package com.uniovi.tests.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * 
 * @version $Id$
 */
public class PO_SearchOffersView extends PO_NavView {

	/**
	 * @param driver
	 * @param string
	 * @param string2
	 * @param i
	 */
	public static void search(WebDriver driver, String text) {
		WebElement title = driver.findElement(By.name("searchText"));
		title.click();
		title.clear();
		title.sendKeys(text);
		
		By boton = By.className("btn-outline-success"); // el boton de buscar
		driver.findElement(boton).click();
	}
}