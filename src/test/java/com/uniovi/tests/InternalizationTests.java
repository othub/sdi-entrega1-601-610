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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.uniovi.tests.pageobjects.PO_AddOfferView;
import com.uniovi.tests.pageobjects.PO_HomeView;
import com.uniovi.tests.pageobjects.PO_LoginView;
import com.uniovi.tests.pageobjects.PO_NavView;
import com.uniovi.tests.pageobjects.PO_PrivateView;
import com.uniovi.tests.pageobjects.PO_Properties;
import com.uniovi.tests.pageobjects.PO_View;
import com.uniovi.utils.SeleniumUtils;

//Ordenamos las pruebas por el nombre del método
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class InternalizationTests {

    // En Windows (Debe ser la versión 65.0.1 y desactivar las actualizacioens
    // automáticas)):
    static String PathFirefox65 = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
    // CHANGE THIS
    static String Geckdriver024 = "D:\\Documents\\S6\\SDI\\Practicas\\PL-SDI-Sesion5-material\\PL-SDI-Sesión5-material\\geckodriver024win64.exe";

    static WebDriver driver = getDriver(PathFirefox65, Geckdriver024);
    static String URL = "http://localhost:8090";

    static List<WebElement> elementos;

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
    public void Prueba27() {
	// cambiando las idiomas en index.html <-> home
	homeTest();

	// login spanish -> english -> spanish
	loginUserTest();

	// estamos conectados con el usuario con correo algo5@gmail.com
	// click en menu y hacemos click en agregar oferta
	elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'offers-menu')]/a");
	elementos.get(0).click();
	elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'offer/add')]");
	elementos.get(0).click();
	// la agregamos
	addOfferTest();

	// rellenamos el formulario
	PO_AddOfferView.fillForm(driver, "Coche a vender", "Polo 2009", 4500);
	// comprobamos que exista la oferta que acabamos de agregar
	PO_View.checkElement(driver, "text", "Polo 2009");

	// nos dirige a la pagina de offer/list
	listOffersTest();

	// disconnect
	PO_PrivateView.clickOption(driver, "logout", "text", "Email:");

	// login con admin
	loginAdminTest();

	// ahora estamos como admin y vamos a mostrar la lista de usuarios
	// menu -> ver usuarios
	elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'users-menu')]/a");
	elementos.get(0).click();
	elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'user/list')]");
	elementos.get(0).click();

	listUsersTest();

	PO_PrivateView.clickOption(driver, "logout", "text", "Email:");

    }

    /**
     * 
     */
    private void listUsersTest() {
	// hay 7 usuarios en el sistema
	elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
	assertTrue(elementos.size() == 9);
	// estamos en spanish, probamos que si
	PO_View.checkElement(driver, "text", "Los usuarios de myWallaPop son los siguientes:");
	// changing to english
	PO_NavView.changeIdiom(driver, "English");
	PO_View.checkElement(driver, "text", "MyWallapop current users are :");
	// changing to spanish again
	PO_NavView.changeIdiom(driver, "Spanish");
	PO_View.checkElement(driver, "text", "eliminar");
    }

    /**
     * 
     */
    private void loginAdminTest() {
	PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
	PO_LoginView.checkElement(driver, "text", "Identificate");
	PO_LoginView.fillForm(driver, "admin@email.com", "123456");
	PO_View.checkElement(driver, "text", "Bienvenido Administrador");
	// disconnect
	PO_PrivateView.clickOption(driver, "logout", "text", "Email:");

	// changing to english
	PO_NavView.changeIdiom(driver, "English");
	PO_LoginView.checkElement(driver, "text", "Login");
	PO_LoginView.fillForm(driver, "admin@email.com", "123456");
	PO_View.checkElement(driver, "text", "Welcome dear Administrator");
	// disconnect
	PO_PrivateView.clickOption(driver, "logout", "text", "Email:");

	// changing to spanish again
	PO_NavView.changeIdiom(driver, "Spanish");
	PO_LoginView.checkElement(driver, "text", "Identificate");
	PO_LoginView.fillForm(driver, "admin@email.com", "123456");
	PO_View.checkElement(driver, "text", "Bienvenido Administrador");
    }

    /**
     * 
     */
    private void addOfferTest() {
	PO_View.checkElement(driver, "text", "Agregar una Oferta");
	PO_View.checkElement(driver, "text", "Email y Nombre :");
	PO_View.checkElement(driver, "text", "Titulo de Oferta :");
	// changing to english
	PO_NavView.changeIdiom(driver, "English");
	PO_View.checkElement(driver, "text", "Your Email and Full Name :");
	PO_View.checkElement(driver, "text", "Price you want :");
	// changing to spanish again
	PO_NavView.changeIdiom(driver, "Spanish");
	PO_View.checkElement(driver, "text", "Email y Nombre :");
	PO_View.checkElement(driver, "text", "Titulo de Oferta :");
    }

    /**
     * 
     */
    private void listOffersTest() {
	// estamos en spanish, probamos que si
	PO_View.checkElement(driver, "text", "Precio");
	PO_View.checkElement(driver, "text", "Titulo");
	// changing to english
	PO_NavView.changeIdiom(driver, "English");
	PO_View.checkElement(driver, "text", "Amount");
	PO_View.checkElement(driver, "text", "Date of Creation");
	// changing to spanish again
	PO_NavView.changeIdiom(driver, "Spanish");
	PO_View.checkElement(driver, "text", "Precio");
	PO_View.checkElement(driver, "text", "Fecha de creacion");
    }

    /**
     * 
     */
    private void loginUserTest() {
	// login con usuario estandar spanish
	PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
	PO_LoginView.checkElement(driver, "text", "Identificate");
	PO_LoginView.fillForm(driver, "algo5@gmail.com", "123456");
	PO_View.checkElement(driver, "text", "Las Ofertas actuales en my Wallapop son las siguientes :");
	// disconnect
	PO_PrivateView.clickOption(driver, "logout", "text", "Email:");

	// changing to english
	PO_NavView.changeIdiom(driver, "English");
	PO_LoginView.checkElement(driver, "text", "Login");
	PO_LoginView.fillForm(driver, "algo5@gmail.com", "123456");
	PO_View.checkElement(driver, "text", "The current offers in myWallapop are the following :");
	// disconnect
	PO_PrivateView.clickOption(driver, "logout", "text", "Email:");

	// changing to spanish again
	PO_NavView.changeIdiom(driver, "Spanish");
	PO_LoginView.checkElement(driver, "text", "Identificate");
	PO_LoginView.fillForm(driver, "algo5@gmail.com", "123456");
	PO_View.checkElement(driver, "text", "Las Ofertas actuales en my Wallapop son las siguientes :");
    }

    /**
     * 
     */
    private void homeTest() {
	// pagina principal en spanish
	PO_HomeView.checkWelcome(driver, PO_Properties.getSPANISH());
	PO_NavView.changeIdiom(driver, "English");
	PO_HomeView.checkWelcome(driver, PO_Properties.getENGLISH());
	PO_NavView.changeIdiom(driver, "Spanish");
	PO_HomeView.checkWelcome(driver, PO_Properties.getSPANISH());
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
