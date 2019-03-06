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

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.uniovi.tests.pageobjects.PO_LoginView;
import com.uniovi.tests.pageobjects.PO_NavView;
import com.uniovi.tests.pageobjects.PO_PrivateView;
import com.uniovi.tests.pageobjects.PO_SearchOffersView;
import com.uniovi.tests.pageobjects.PO_View;
import com.uniovi.utils.SeleniumUtils;

//Ordenamos las pruebas por el nombre del método
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SearchOffersTests {

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
    public void Prueba21() {
	PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
	PO_LoginView.fillForm(driver, "alg4@gmail.com", "123456");
	PO_View.checkElement(driver, "text", "Ofertas del usuario");

	List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
		PO_View.getTimeout());
	assertTrue(elementos.size() == 5); // de momento en la pagina uno tenemos 5

	// buscar oferta
	PO_SearchOffersView.search(driver, "patin"); // buscamos un patinete
	PO_View.checkElement(driver, "text", "patinete electrico"); // hay uno solo

	elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
	assertTrue(elementos.size() == 1); // de momento en la pagina uno tenemos 5

	By boton = By.id("updateButton"); // seleccionamos el boton de actualizar para buscar mas
	driver.findElement(boton).click();

	// buscamos en campo empty
	PO_SearchOffersView.search(driver, "");

	elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
	assertTrue(elementos.size() == 5); // en la pagina 1 hay 5

	// nos movemos a la pagina 2
	elementos = PO_View.checkElement(driver, "free", "//a[contains(@class, 'page-link')]");
	// Nos vamos a la última página
	elementos.get(1).click();

	elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
	assertTrue(elementos.size() == 5); // tambien salen 5

	// y las demas paginas asi

	// nos desconectamos
	PO_PrivateView.clickOption(driver, "logout", "text", "Email:");
    }

    @Test
    public void Prueba22() {
	PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
	PO_LoginView.fillForm(driver, "alg4@gmail.com", "123456");
	PO_View.checkElement(driver, "text", "Ofertas del usuario");

	List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
		PO_View.getTimeout());
	assertTrue(elementos.size() == 5); // de momento en la pagina uno tenemos 5

	// buscar oferta
	PO_SearchOffersView.search(driver, "telefono"); // buscamos un telefono
	// no existe ningun telefono y la tabla esta vacia
	List<WebElement> lista = driver.findElements(By.xpath("//tbody/tr"));
	assertTrue(lista.size() == 0);

	// nos desconectamos
	PO_PrivateView.clickOption(driver, "logout", "text", "Email:");
    }

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
