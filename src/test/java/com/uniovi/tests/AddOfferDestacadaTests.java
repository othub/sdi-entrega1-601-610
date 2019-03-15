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

import com.uniovi.tests.pageobjects.PO_AddOfferView;
import com.uniovi.tests.pageobjects.PO_LoginView;
import com.uniovi.tests.pageobjects.PO_NavView;
import com.uniovi.tests.pageobjects.PO_PrivateView;
import com.uniovi.tests.pageobjects.PO_View;
import com.uniovi.utils.SeleniumUtils;

//Ordenamos las pruebas por el nombre del método
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AddOfferDestacadaTests {

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
    public void Prueba36() {
	PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
	PO_LoginView.fillForm(driver, "algo5@gmail.com", "123456");
	PO_View.checkElement(driver, "text", "Las ofertas actuales en My Wallapop son las siguientes :");

	List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'offers-menu')]/a");
	elementos.get(0).click();
	elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'offer/list')]");
	elementos.get(0).click();

	// hay 3 ofertas de este usuario
	elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
	assertTrue(elementos.size() == 4);

	elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'offers-menu')]/a");
	elementos.get(0).click();

	elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'offer/add')]");
	elementos.get(0).click();

	PO_View.checkElement(driver, "text", "Agregar una oferta");

	PO_AddOfferView.fillFormHighlighted(driver, "Apple", "iMac 2009", 4500);

	PO_View.checkElement(driver, "text", "iMac 2009");

	elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
	assertTrue(elementos.size() == 5);

	PO_View.checkElement(driver, "text", "80.0 €");
	assertFalse(driver.getPageSource().contains("100.0 €")); // el saldo se ha cambiado
	PO_View.checkElement(driver, "text", "Destacado");

	// vamos a la pagina de todas las ofertas
	elementos = PO_View.checkElement(driver, "free", "// *[@id=\"myNavbar\"]/ul[1]/li[1]/a");
	elementos.get(0).click();
	PO_View.checkElement(driver, "text", "Las ofertas actuales en My Wallapop son las siguientes :");

	// aparece en la primera vista y en primera columna
	PO_View.checkElement(driver, "text", "iMac 2009");
	PO_View.checkElement(driver, "text", "Destacado");
	PO_View.checkElement(driver, "text", "80.0 €");
	assertFalse(driver.getPageSource().contains("100.0 €")); // el saldo se ha cambiado

	// desconectamos
	PO_PrivateView.clickOption(driver, "logout", "text", "Correo :");
    }

    @Test
    public void Prueba37() {
	// el mismo usuario
	PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
	PO_LoginView.fillForm(driver, "algo5@gmail.com", "123456");
	PO_View.checkElement(driver, "text", "Las ofertas actuales en My Wallapop son las siguientes :");

	List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'offers-menu')]/a");
	elementos.get(0).click();
	elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'offer/list')]");
	elementos.get(0).click();

	// hay 3 ofertas de este usuario
	elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
	assertTrue(elementos.size() == 5); // tiene cuatro

	// click en la oferta de un Coche
	By boton = By.xpath("//*[@id=\"highlightButton24\"]");
	driver.findElement(boton).click();

	PO_View.checkElement(driver, "text", "60.0 €");
	assertFalse(driver.getPageSource().contains("80.0 €")); // el saldo se ha cambiado

	// vamos a la pagina de todas las ofertas
	elementos = PO_View.checkElement(driver, "free", "// *[@id=\"myNavbar\"]/ul[1]/li[1]/a");
	elementos.get(0).click();
	PO_View.checkElement(driver, "text", "Las ofertas actuales en My Wallapop son las siguientes :");

	// aparece en la primera vista y en primera columna
	PO_View.checkElement(driver, "text", "Coche");
	PO_View.checkElement(driver, "text", "Destacado");
	PO_View.checkElement(driver, "text", "60.0 €");
	assertFalse(driver.getPageSource().contains("80.0 €")); // el saldo se ha cambiado

	// desconectamos
	PO_PrivateView.clickOption(driver, "logout", "text", "Correo :");
    }

    @Test
    public void Prueba38() {
	// entramos con alg4 que no tiene saldo
	PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
	PO_LoginView.fillForm(driver, "alg4@gmail.com", "123456");
	PO_View.checkElement(driver, "text", "Las ofertas actuales en My Wallapop son las siguientes :");

	List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'offers-menu')]/a");
	elementos.get(0).click();
	elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'offer/list')]");
	elementos.get(0).click();

	assertTrue(driver.getPageSource().contains("0.0 €")); // estamos sin dinero

	// click en la oferta de un Coche
	By boton = By.xpath("//*[@id=\"highlightButton20\"]");
	driver.findElement(boton).click();

	PO_View.checkElement(driver, "text", "0.0 €");

	// no se cambia el boton a destacado
	List<WebElement> btnDestacado = driver.findElements(By.className("btn-warning"));
	assertTrue(btnDestacado.isEmpty()); // NO APARECE EL BOTON DESTACADO

	assertTrue(driver.getPageSource().contains("0.0 €")); // estamos sin dinero :(

	// desconectamos
	PO_PrivateView.clickOption(driver, "logout", "text", "Correo :");
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
