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
import com.uniovi.tests.pageobjects.PO_View;
import com.uniovi.utils.SeleniumUtils;

//Ordenamos las pruebas por el nombre del método
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AdminDeletionTests {

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
    public void Prueba13() {
	PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
	PO_LoginView.fillForm(driver, "admin@email.com", "123456");
	PO_View.checkElement(driver, "text", "Bienvenido Administrador");

	List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'users-menu')]/a");

	elementos.get(0).click();
	elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'user/list')]");
	elementos.get(0).click();

	// hay 8 usuarios en el sistema
	elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
	assertTrue(elementos.size() == 14);

	elementos = PO_View.checkElement(driver, "free",
		"//td[contains(text(), 'akran1@gmail.com')]/following-sibling::*/a[contains(@href, 'user/delete')]");
	elementos.get(0).click();

	elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());

	// No existe el email, se ha borrado
	assertFalse(driver.getPageSource().contains("akran1@gmail.com"));
	// desconectamos
	PO_PrivateView.clickOption(driver, "logout", "text", "Email:");

    }

    @Test
    public void Prueba14() {
	PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
	PO_LoginView.fillForm(driver, "admin@email.com", "123456");
	PO_View.checkElement(driver, "text", "Bienvenido Administrador");

	List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'users-menu')]/a");

	elementos.get(0).click();
	elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'user/list')]");
	elementos.get(0).click();

	elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
	assertTrue(elementos.size() == 13);

	elementos = PO_View.checkElement(driver, "free",
		"//td[contains(text(), 'akran5@gmail.com')]/following-sibling::*/a[contains(@href, 'user/delete')]");
	elementos.get(0).click();

	elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());

	// No existe el email, se ha borrado
	assertFalse(driver.getPageSource().contains("akran5@gmail.com"));
	// desconectamos
	PO_PrivateView.clickOption(driver, "logout", "text", "Email:");

    }

    @Test
    public void Prueba15() {
	PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
	PO_LoginView.fillForm(driver, "admin@email.com", "123456");
	PO_View.checkElement(driver, "text", "Bienvenido Administrador");

	List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'users-menu')]/a");

	elementos.get(0).click();
	elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'user/list')]");
	elementos.get(0).click();

	elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
	assertTrue(elementos.size() == 12);

	clickThreeCheckboxes();

	driver.findElement(By.className("btn-danger")).click();

	// No existe el email, se ha borrado
	assertFalse(driver.getPageSource().contains("akran1@gmail.com"));
	assertFalse(driver.getPageSource().contains("akran2@gmail.com"));
	assertFalse(driver.getPageSource().contains("akran3@gmail.com"));

	elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
	assertTrue(elementos.size() == 9);

	assertTrue(driver.getPageSource().contains("algo1@gmail.com"));
	// y los demas
	assertTrue(driver.getPageSource().contains("admin@email.com"));

	// desconectamos
	PO_PrivateView.clickOption(driver, "logout", "text", "Email:");

    }

    private void clickThreeCheckboxes() {
	int count = 3;

	for (WebElement e : driver.findElements(By.xpath("//input[@type='checkbox']"))) {
	    if (!e.isSelected() && count > 0 && !e.getAttribute("id").equals("checkAll")
		    && !e.getText().equals("admin@email.com")) {
		e.click();
		count -= 1;
	    }
	}
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
