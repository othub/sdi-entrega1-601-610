package com.uniovi.tests.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * 
 * @version $Id$
 */
public class PO_AddOfferView extends PO_NavView {

    /**
     * @param driver
     * @param string
     * @param string2
     * @param i
     */
    public static void fillForm(WebDriver driver, String titlef, String descriptionf, double amountf) {
	WebElement title = driver.findElement(By.name("title"));
	title.click();
	title.clear();
	title.sendKeys(titlef);
	WebElement description = driver.findElement(By.id("descripcion"));
	description.click();
	description.clear();
	description.sendKeys(descriptionf);
	WebElement amount = driver.findElement(By.name("amount"));
	amount.click();
	amount.clear();
	amount.sendKeys(String.valueOf(amountf));
	By boton = By.className("btn");
	driver.findElement(boton).click();
    }
}