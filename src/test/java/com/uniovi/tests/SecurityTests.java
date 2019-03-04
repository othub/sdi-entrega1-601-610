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
package com.uniovi.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.uniovi.tests.pageobjects.PO_LoginView;
import com.uniovi.tests.pageobjects.PO_NavView;
import com.uniovi.tests.pageobjects.PO_View;

//Ordenamos las pruebas por el nombre del método
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SecurityTests {

    // En Windows (Debe ser la versión 65.0.1 y desactivar las actualizacioens
    // automáticas)):
    static String PathFirefox65 = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
    // CHANGE THIS
    static String Geckdriver024 = "D:\\Documents\\S6\\SDI\\Practicas\\PL-SDI-Sesion5-material\\PL-SDI-Sesión5-material\\geckodriver024win64.exe";

    static WebDriver driver = getDriver(PathFirefox65, Geckdriver024);
    static String URL = "http://localhost:8090";

    public static WebDriver getDriver(String PathFirefox, String Geckdriver) {
	System.setProperty("webdriver.firefox.bin", PathFirefox);
	System.setProperty("webdriver.gecko.driver", Geckdriver);
	WebDriver driver = new FirefoxDriver();
	return driver;
    }

    @Before
    public void setUp() {
	driver.navigate().to(URL);
    }

    // Después de cada prueba se borran las cookies del navegador
    @After
    public void tearDown() {
	driver.manage().deleteAllCookies();
    }

    @Test
    public void Prueba28() {
	// nos dirigimos a user/list sin login
	String url = driver.getCurrentUrl();
	String newUrl = url + "user/list";
	driver.get(newUrl);
	// nos dirige a la pagina de login
	PO_LoginView.checkElement(driver, "text", "Identificate");
	// login con admin
	PO_LoginView.fillForm(driver, "admin@email.com", "123456");
	// vemos los usuarios del sistema
	PO_View.checkElement(driver, "text", "Los usuarios de myWallaPop son los siguientes:");
    }

    @Test
    public void Prueba29() {
	// nos dirigimos a offer/list sin login
	String url = driver.getCurrentUrl();
	String newUrl = url + "offer/list";
	driver.get(newUrl);
	// nos dirige a la pagina de login
	PO_LoginView.checkElement(driver, "text", "Identificate");
	// login con user
	PO_LoginView.fillForm(driver, "algo5@gmail.com", "123456");
	// vea sus ofertas
	PO_View.checkElement(driver, "text", "Su ofertas publicadas en myWallapop son las siguientes :");
    }

    @Test
    public void Prueba30() {
	// login con usuario estandar
	PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
	PO_LoginView.fillForm(driver, "algo@gmail.com", "123456");
	PO_View.checkElement(driver, "text", "Ofertas del usuario");
	// cambiamos el home
	String url = driver.getCurrentUrl().replace("home", "");
	String newUrl = url + "user/list";
	// nos vamos a user/list
	driver.get(newUrl);
	// error 403
	assertTrue(driver.getPageSource().contains("HTTP Status 403 – Forbidden"));
	// asegurar que no se ven la pagina de usuarios gestionada por el admin
	assertFalse(driver.getPageSource().contains("Los usuarios de myWallaPop son los siguientes:"));
    }

    // Antes de la primera prueba
    @BeforeClass
    static public void begin() {
    }

    // Al finalizar la última prueba
    @AfterClass
    static public void end() {
	// Cerramos el navegador al finalizar las pruebas
	driver.quit();
    }
}
