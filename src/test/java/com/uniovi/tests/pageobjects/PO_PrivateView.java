/*
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package com.uniovi.tests.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.uniovi.utils.SeleniumUtils;

public class PO_PrivateView extends PO_NavView {

    static public void fillFormAddMark(WebDriver driver, int userOrder, String descriptionp, String scorep) {
	// Esperamos 5 segundo a que carge el DOM porque en algunos equipos falla
	SeleniumUtils.esperarSegundos(driver, 5);
	// Seleccionamos el alumnos userOrder
	new Select(driver.findElement(By.id("user"))).selectByIndex(userOrder);
	// Rellenemos el campo de descripción
	WebElement description = driver.findElement(By.name("description"));
	description.clear();
	description.sendKeys(descriptionp);
	WebElement score = driver.findElement(By.name("score"));
	score.click();
	score.clear();
	score.sendKeys(scorep);
	By boton = By.className("btn");
	driver.findElement(boton).click();
    }

}
