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
import org.openqa.selenium.support.ui.Select;

import com.uniovi.tests.pageobjects.PO_LoginView;
import com.uniovi.tests.pageobjects.PO_NavView;
import com.uniovi.tests.pageobjects.PO_PrivateView;
import com.uniovi.tests.pageobjects.PO_SendMessageView;
import com.uniovi.tests.pageobjects.PO_View;
import com.uniovi.utils.SeleniumUtils;

//Ordenamos las pruebas por el nombre del método
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ListMessageTests {

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
    public void Prueba31() {
	PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
	PO_LoginView.fillForm(driver, "algo1@gmail.com", "123456");
	PO_View.checkElement(driver, "text", "Las ofertas actuales en My Wallapop son las siguientes :");

	List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'messages-menu')]/a");
	elementos.get(0).click();
	elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'message/chat')]");
	elementos.get(0).click();

	Select dropdown = new Select(driver.findElement(By.id("user")));
	// seleccionamos el quinto
	dropdown.selectByIndex(5);

	PO_View.checkElement(driver, "text", "Tu mensaje");

	// enviamos un mensaje
	PO_SendMessageView.fillForm(driver, "Cuantos años lo tienes");

	// comprobamos que se actualizó en la lista de mensajes
	elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'messages-menu')]/a");
	elementos.get(0).click();
	elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'message/list')]");
	elementos.get(0).click();

	elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
	assertTrue(!elementos.isEmpty());

	PO_View.checkElement(driver, "text", "Cuantos años lo tienes");
	// the concerned users
	PO_View.checkElement(driver, "text", "algo1@gmail.com");
	PO_View.checkElement(driver, "text", "algo2@gmail.com");

	// otro mensaje a otra oferta
	elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'messages-menu')]/a");
	elementos.get(0).click();
	elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'message/chat')]");
	elementos.get(0).click();

	dropdown = new Select(driver.findElement(By.id("user")));
	// seleccionamos el quinto
	dropdown.selectByIndex(8);

	PO_View.checkElement(driver, "text", "Tu mensaje");

	// enviamos un mensaje
	PO_SendMessageView.fillForm(driver, "Puedes bajar el precio");

	// comprobamos que se actualizó en la lista de mensajes
	elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'messages-menu')]/a");
	elementos.get(0).click();
	elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'message/list')]");
	elementos.get(0).click();

	elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());

	PO_View.checkElement(driver, "text", "Puedes bajar el precio");
	// the concerned users
	PO_View.checkElement(driver, "text", "algo1@gmail.com");
	PO_View.checkElement(driver, "text", "algo3@gmail.com");

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
