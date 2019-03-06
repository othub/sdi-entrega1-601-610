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
public class BuyOfferTests {

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
    public void Prueba23() {
	PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
	PO_LoginView.fillForm(driver, "alg4@gmail.com", "123456");
	PO_View.checkElement(driver, "text", "Ofertas del usuario");

	List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
		PO_View.getTimeout());
	assertTrue(elementos.size() == 5);

	PO_View.checkElement(driver, "text", "100.0 €"); // el saldo de 100 euros

	// buscar oferta
	PO_SearchOffersView.search(driver, "patin"); // buscamos un patinete
	PO_View.checkElement(driver, "text", "patinete electrico"); // su precio es 40

	By boton = By.className("btn-info"); // el boton de comprar
	driver.findElement(boton).click();

	// despues de comprar debe salir el boton de comprado.
	List<WebElement> btnComprado = driver.findElements(By.className("btn-danger"));
	assertFalse(btnComprado.isEmpty());
	// NO APARECE EL BOTON DE COMPRAR AHORA
	List<WebElement> btnComprar = driver.findElements(By.className("btn-info"));
	assertTrue(btnComprar.isEmpty());

	// se actualiza el precio a 60
	PO_View.checkElement(driver, "text", "60.0 €");
	assertFalse(driver.getPageSource().contains("100.0 €")); // el saldo se ha cambiado

	// desconectamos
	PO_PrivateView.clickOption(driver, "logout", "text", "Email:");
    }

    @Test
    public void Prueba24() {
	PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
	PO_LoginView.fillForm(driver, "alg4@gmail.com", "123456");
	PO_View.checkElement(driver, "text", "Ofertas del usuario");

	List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
		PO_View.getTimeout());
	assertTrue(elementos.size() == 5); // 5 elementos por pagina

	PO_View.checkElement(driver, "text", "60.0 €"); // ahora es 60 porque ya hemos comprado el patinete

	// buscar oferta
	PO_SearchOffersView.search(driver, "volks"); // buscamos un volkswagen

	elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
	assertTrue(elementos.size() == 1); // hay 1 golf en el sistema

	// seleccionamos el golf

	PO_View.checkElement(driver, "text", "golf"); // su precio del coche es 60 (ojala fue verdad)

	By boton = By.className("btn-info"); // seleccionamos el segundo boton

	driver.findElement(boton).click();

	elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());

	// queda uno de cada tipo (mercedes no comprado y golf comprado)
	List<WebElement> btnComprado = driver.findElements(By.className("btn-danger"));
	assertTrue(!btnComprado.isEmpty()); // esta en comprado

	// NO APARECE EL BOTON DE COMPRAR AHORA
	List<WebElement> btnComprar = driver.findElements(By.className("btn-info"));
	assertTrue(btnComprar.isEmpty()); // sigue en comprar

	// se actualiza el precio a 60
	PO_View.checkElement(driver, "text", "0.0 €");

	// desconectamos
	PO_PrivateView.clickOption(driver, "logout", "text", "Email:");
    }

    @Test
    public void Prueba25() {
	PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
	PO_LoginView.fillForm(driver, "alg4@gmail.com", "123456");
	PO_View.checkElement(driver, "text", "Ofertas del usuario");

	List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
		PO_View.getTimeout());
	assertTrue(elementos.size() == 5); // 5 elementos por pagina

	assertTrue(driver.getPageSource().contains("0.0 €")); // estamos sin dinero

	// buscar oferta
	PO_SearchOffersView.search(driver, "mercedes"); // buscamos un mercedes

	elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
	assertTrue(elementos.size() == 1); // hay 1 solo mercedes
	PO_View.checkElement(driver, "text", "220c"); // su precio del coche es 70

	By comprar = By.className("btn-info");
	driver.findElement(comprar).click();

	// queda uno de cada tipo (mercedes no comprado y golf comprado)
	List<WebElement> btnComprado = driver.findElements(By.className("btn-danger"));
	assertTrue(btnComprado.isEmpty()); // NO APARECE EL BOTON COMPRADO

	// NO APARECE EL BOTON DE COMPRAR AHORA
	List<WebElement> btnComprar = driver.findElements(By.className("btn-info"));
	assertTrue(btnComprar.size() == 1); // SIGUE EL BOTON DE COMPRAR

	// se actualiza el precio a 60
	assertTrue(driver.getPageSource().contains("0.0 €")); // no se cambió el saldo del usuario

	// desconectamos
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
