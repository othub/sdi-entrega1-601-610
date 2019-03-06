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

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.uniovi.tests.pageobjects.PO_NavView;
import com.uniovi.tests.pageobjects.PO_PrivateView;
import com.uniovi.tests.pageobjects.PO_Properties;
import com.uniovi.tests.pageobjects.PO_RegisterView;
import com.uniovi.tests.pageobjects.PO_View;

//Ordenamos las pruebas por el nombre del método
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SignUpTests {

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
    public void Prueba1() {
	PO_NavView.clickOption(driver, "signup", "class", "btn btn-primary");
	PO_RegisterView.fillForm(driver, "alguien88@example.org", "Josefo", "Perez", "123456", "123456");
	PO_View.checkElement(driver, "text", "Las Ofertas actuales en my Wallapop son las siguientes :");

	// desconectamos
	PO_PrivateView.clickOption(driver, "logout", "text", "Email:");

    }

    @Test
    public void Prueba2() {
	PO_NavView.clickOption(driver, "signup", "class", "btn btn-primary");
	// Email vacio
	PO_RegisterView.fillForm(driver, "", "Josefo", "Perez", "77777", "77777");
	PO_View.getP();
	// vuelve a su estado inicial
	PO_View.checkElement(driver, "text", "Email:");
	// nombre vacio
	PO_RegisterView.fillForm(driver, "alguien@example.com", "", "Perez", "77777", "77777");
	PO_View.getP();
	// vuelve a su estado inicial
	PO_View.checkElement(driver, "text", "Email:");
	// apellido vacio
	PO_RegisterView.fillForm(driver, "alguien@example.com", "Juanes", "", "77777", "77777");
	PO_View.getP();
	// vuelve a su estado inicial
	PO_View.checkElement(driver, "text", "Email:");
    }

    @Test
    public void Prueba3() {
	PO_NavView.clickOption(driver, "signup", "class", "btn btn-primary");

	PO_RegisterView.fillForm(driver, "alguien@example.com", "Josefo", "Perez", "77777", "77776");
	PO_View.getP();
	// queda en la misma página
	PO_View.checkElement(driver, "text", "Email:");

    }

    @Test
    public void Prueba4() {
	PO_NavView.clickOption(driver, "signup", "class", "btn btn-primary");
	// Rellenamos el formulario.
	PO_RegisterView.fillForm(driver, "alguien88@example.org", "Josefo", "Perez", "77777", "77777");
	PO_View.getP();
	// COmprobamos el error de email repetido.
	PO_RegisterView.checkKey(driver, "Error.signup.email.duplicate", PO_Properties.getSPANISH());

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
