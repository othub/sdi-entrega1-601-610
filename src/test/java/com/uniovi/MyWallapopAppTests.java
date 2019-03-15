package com.uniovi;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.uniovi.entities.Offer;
import com.uniovi.entities.User;
import com.uniovi.repositories.UsersRepository;
import com.uniovi.services.RolesService;
import com.uniovi.services.UsersService;
import com.uniovi.tests.pageobjects.PO_AddOfferView;
import com.uniovi.tests.pageobjects.PO_HomeView;
import com.uniovi.tests.pageobjects.PO_LoginView;
import com.uniovi.tests.pageobjects.PO_NavView;
import com.uniovi.tests.pageobjects.PO_PrivateView;
import com.uniovi.tests.pageobjects.PO_Properties;
import com.uniovi.tests.pageobjects.PO_RegisterView;
import com.uniovi.tests.pageobjects.PO_SearchOffersView;
import com.uniovi.tests.pageobjects.PO_SendMessageView;
import com.uniovi.tests.pageobjects.PO_View;
import com.uniovi.utils.SeleniumUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MyWallapopAppTests {

    @Autowired
    private UsersService usersService;
    @Autowired
    private RolesService rolesService;
    @Autowired
    private UsersRepository usersRepository;

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
	initdb();
	driver.navigate().to(URL);
    }

    /**
     * 
     */
    @SuppressWarnings("serial")
    private void initdb() {

	// Borramos todas las entidades.
	usersRepository.deleteAll();

	User user1 = new User("algo@gmail.com", "Pedro", "Pascal");
	user1.setPassword("123456");
	user1.setRole(rolesService.getRoles()[0]);
	User user2 = new User("algo1@gmail.com", "Lucas", "Hernandez");
	user2.setPassword("123456");
	user2.setRole(rolesService.getRoles()[0]);
	User user3 = new User("algo2@gmail.com", "Iker", "Casillas");
	user3.setPassword("123456");
	user3.setRole(rolesService.getRoles()[0]);
	User user4 = new User("algo3@gmail.com", "Xavi", "Hernandez");
	user4.setPassword("123456");
	user4.setRole(rolesService.getRoles()[0]);
	User user5 = new User("alg4@gmail.com", "Pedro", "Rodriguez");
	user5.setPassword("123456");
	user5.setRole(rolesService.getRoles()[0]);
	User user6 = new User("algo5@gmail.com", "Leo", "Messi");
	user6.setPassword("123456");
	user6.setRole(rolesService.getRoles()[0]);

	User user7 = new User("algo7@gmail.com", "Neymar", "Jr");
	user7.setPassword("123456");
	user7.setRole(rolesService.getRoles()[0]);

	User admin = new User("admin@email.com", "Othmane", "Bakhtaoui");
	admin.setPassword("123456");
	admin.setRole(rolesService.getRoles()[1]);

	Set<Offer> user2Offers = new HashSet<Offer>() {
	    {
		add(new Offer("Patinete 2019", "patinete electrico", 40, user2));
		add(new Offer("title22", "descr22", 22, user2));
		add(new Offer("Samsung note", "samsung a vender", 88, user2));

	    }
	};
	user2.setOffers(user2Offers);

	Set<Offer> user4Offers = new HashSet<Offer>() {
	    {
		add(new Offer("title41", "descr41", 41, user4));
		add(new Offer("title42", "descr42", 42, user4));
		add(new Offer("Apple Iphone X", "iphone 256gb", 90, user4));
	    }
	};
	user4.setOffers(user4Offers);

	Set<Offer> user3Offers = new HashSet<Offer>() {
	    {
		add(new Offer("title31", "descr31", 31, user3));
		add(new Offer("title32", "descr32", 32, user3));
		add(new Offer("iPhone XS", "iphone 128gb", 80, user3));
	    }
	};
	user3.setOffers(user3Offers);

	Set<Offer> user7Offers = new HashSet<Offer>() {
	    {
		add(new Offer("title71", "descr71", 71, user7));
		add(new Offer("title72", "descr72", 72, user7));
		add(new Offer("iMac 2017", "iMac 2017 a vender", 65, user7));

	    }
	};
	user7.setOffers(user7Offers);

	Set<Offer> user6Offers = new HashSet<Offer>() {
	    {
		add(new Offer("Coche VolksWagen", "golf", 60, user6));
		add(new Offer("Coche Mercedes", "220c", 70, user6));
		add(new Offer("Yoga", "Lenovo Yoga 2018, 1Tb", 99, user6));
	    }
	};
	user6.setOffers(user6Offers);

	Set<Offer> user5Offers = new HashSet<Offer>() {
	    {
		add(new Offer("title51", "descr51", 51, user5));
		add(new Offer("title52", "descr52", 52, user5));
		add(new Offer("XPS 2017", "Dell XPS 16 1 tb, 16go ram", 90, user5));

	    }
	};
	user5.setOffers(user5Offers);

	User delete1 = new User("akran1@gmail.com", "Pedro", "Pascal");
	delete1.setPassword("123456");
	delete1.setRole(rolesService.getRoles()[0]);
	User delete2 = new User("akran2@gmail.com", "Lucas", "Hernandez");
	delete2.setPassword("123456");
	delete2.setRole(rolesService.getRoles()[0]);
	User delete3 = new User("akran3@gmail.com", "Iker", "Casillas");
	delete3.setPassword("123456");
	delete3.setRole(rolesService.getRoles()[0]);
	User delete4 = new User("akran4@gmail.com", "Xavi", "Hernandez");
	delete4.setPassword("123456");
	delete4.setRole(rolesService.getRoles()[0]);
	User delete5 = new User("akran5@gmail.com", "Pedro", "Rodriguez");
	delete5.setPassword("123456");
	delete5.setRole(rolesService.getRoles()[0]);

	// FOR ADMIN DELETE
	usersService.addUser(delete1);
	usersService.addUser(delete2);
	usersService.addUser(delete3);
	usersService.addUser(delete4);
	usersService.addUser(delete5);

	// for normal use
	usersService.addUser(user1);
	usersService.addUser(user2);
	usersService.addUser(user3);
	usersService.addUser(user4);
	usersService.addUser(user5);
	usersService.addUser(user6);
	usersService.addUser(user7);
	usersService.addUser(admin);
    }

    // Después de cada prueba se borran las cookies del navegador
    @After
    public void tearDown() {
	driver.manage().deleteAllCookies();
    }

    @Test
    public void contextLoads() {
	Prueba01();
	Prueba02();
	Prueba03();
	Prueba04();
	Prueba05();
	Prueba06();
	Prueba07();
	Prueba08();
	Prueba09();
	Prueba10();
	Prueba11();
	Prueba12();
	Prueba13();
	Prueba14();
	Prueba15();
	Prueba16();
	Prueba17();
	Prueba18();
	Prueba19();
	Prueba20();
	Prueba21();
	Prueba22();
	Prueba23();
	Prueba24();
	Prueba25();
	Prueba26();
	Prueba27();
	Prueba28();
	Prueba29();
	Prueba30();
	Prueba31();
	Prueba32();
	Prueba33();
	Prueba34();
	Prueba35();
	Prueba36();
	Prueba37();
	Prueba38();
    }

    public void Prueba01() {
	PO_NavView.clickOption(driver, "signup", "class", "btn btn-primary");
	PO_RegisterView.fillForm(driver, "alguien88@example.org", "Josefo", "Perez", "123456", "123456");
	PO_View.checkElement(driver, "text", "Las ofertas actuales en My Wallapop son las siguientes :");

	// desconectamos
	PO_PrivateView.clickOption(driver, "logout", "text", "Correo :");

    }

    public void Prueba02() {
	PO_NavView.clickOption(driver, "signup", "class", "btn btn-primary");
	// Email vacio
	PO_RegisterView.fillForm(driver, "", "Josefo", "Perez", "77777", "77777");
	PO_View.getP();
	// vuelve a su estado inicial
	PO_View.checkElement(driver, "text", "Correo :");
	// nombre vacio
	PO_RegisterView.fillForm(driver, "alguien@example.com", "", "Perez", "77777", "77777");
	PO_View.getP();
	// vuelve a su estado inicial
	PO_View.checkElement(driver, "text", "Correo :");
	// apellido vacio
	PO_RegisterView.fillForm(driver, "alguien@example.com", "Juanes", "", "77777", "77777");
	PO_View.getP();
	// vuelve a su estado inicial
	PO_View.checkElement(driver, "text", "Correo :");

    }

    public void Prueba03() {
	PO_NavView.clickOption(driver, "signup", "class", "btn btn-primary");

	PO_RegisterView.fillForm(driver, "alguien@example.com", "Josefo", "Perez", "77777", "77776");
	PO_View.getP();
	// queda en la misma página
	PO_View.checkElement(driver, "text", "Correo :");

    }

    public void Prueba04() {

	PO_NavView.clickOption(driver, "signup", "class", "btn btn-primary");
	// Rellenamos el formulario.
	PO_RegisterView.fillForm(driver, "alguien88@example.org", "Josefo", "Perez", "77777", "77777");
	PO_View.getP();
	// COmprobamos el error de email repetido.
	PO_RegisterView.checkKey(driver, "Error.signup.email.duplicate", PO_Properties.getSPANISH());

    }

    public void Prueba05() {
	PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
	PO_LoginView.fillForm(driver, "admin@email.com", "123456");
	PO_View.checkElement(driver, "text", "Bienvenido Administrador");
	PO_PrivateView.clickOption(driver, "logout", "text", "Correo :");
    }

    public void Prueba06() {
	PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
	PO_LoginView.fillForm(driver, "algo@gmail.com", "123456");
	PO_View.checkElement(driver, "text", "Las ofertas actuales en My Wallapop son las siguientes :");
	PO_PrivateView.clickOption(driver, "logout", "text", "Correo :");
    }

    public void Prueba07() {
	PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
	// email vacio
	PO_LoginView.fillForm(driver, "", "123456");
	// no cambiamos de pagina
	PO_View.checkElement(driver, "text", "Correo :");
	PO_View.checkElement(driver, "text", "Password :");

	// contraseña vacia
	PO_LoginView.fillForm(driver, "algo@gmail.com", "");
	// no cambiamos de pagina
	PO_View.checkElement(driver, "text", "Correo :");
	PO_View.checkElement(driver, "text", "Password :");

    }

    public void Prueba08() {
	PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
	// contraseña vacia
	PO_LoginView.fillForm(driver, "algo@gmail.com", "9999999999");
	// no cambiamos de pagina
	PO_View.checkElement(driver, "text", "Correo :");
	PO_View.checkElement(driver, "text", "Password :");
    }

    public void Prueba09() {
	PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
	// contraseña vacia
	PO_LoginView.fillForm(driver, "no_existente@gmail.com", "123456");
	// no cambiamos de pagina
	PO_View.checkElement(driver, "text", "Correo :");
	PO_View.checkElement(driver, "text", "Password :");

    }

    public void Prueba10() {
	PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
	PO_LoginView.fillForm(driver, "algo@gmail.com", "123456");
	// disconnect
	PO_PrivateView.clickOption(driver, "logout", "text", "Correo :");
    }

    public void Prueba11() {
	PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
	PO_LoginView.fillForm(driver, "algo@gmail.com", "123456");
	PO_View.checkElement(driver, "text", "Las ofertas actuales en My Wallapop son las siguientes :");

	PO_PrivateView.clickOption(driver, "logout", "text", "Correo :");

	// No existe el boton disconnect
	List<WebElement> disconnect = driver.findElements(By.className("btn-disconnect"));
	assertTrue(disconnect.isEmpty());

	PO_LoginView.fillForm(driver, "algo@gmail.com", "123456");
	PO_View.checkElement(driver, "text", "Las ofertas actuales en My Wallapop son las siguientes :");
	PO_PrivateView.clickOption(driver, "logout", "text", "Correo :");

    }

    public void Prueba12() {
	PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
	PO_LoginView.fillForm(driver, "admin@email.com", "123456");
	PO_View.checkElement(driver, "text", "Bienvenido Administrador");

	List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'users-menu')]/a");

	elementos.get(0).click();
	elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'user/list')]");
	elementos.get(0).click();

	// hay 8 usuarios en el sistema incluyendo el admin
	elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
	assertTrue(elementos.size() == 14);

	PO_PrivateView.clickOption(driver, "logout", "text", "Correo :");
    }

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
	PO_PrivateView.clickOption(driver, "logout", "text", "Correo :");

    }

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
	PO_PrivateView.clickOption(driver, "logout", "text", "Correo :");

    }

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
	PO_PrivateView.clickOption(driver, "logout", "text", "Correo :");

    }

    public void Prueba16() {
	PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
	PO_LoginView.fillForm(driver, "algo2@gmail.com", "123456");
	PO_View.checkElement(driver, "text", "Las ofertas actuales en My Wallapop son las siguientes :");

	List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'offers-menu')]/a");
	elementos.get(0).click();
	elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'offer/list')]");
	elementos.get(0).click();

	// hay 2 ofertas de este usuario
	elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
	assertTrue(elementos.size() == 3);

	elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'offers-menu')]/a");
	elementos.get(0).click();

	elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'offer/add')]");
	elementos.get(0).click();

	PO_View.checkElement(driver, "text", "Agregar una oferta");

	PO_AddOfferView.fillForm(driver, "Coche a vender", "Polo 2009", 4500);

	PO_View.checkElement(driver, "text", "Polo 2009");

	elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
	assertTrue(elementos.size() == 4);

	// desconectamos
	PO_PrivateView.clickOption(driver, "logout", "text", "Correo :");
    }

    public void Prueba17() {
	PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
	PO_LoginView.fillForm(driver, "algo2@gmail.com", "123456");
	PO_View.checkElement(driver, "text", "Las ofertas actuales en My Wallapop son las siguientes :");

	List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'offers-menu')]/a");
	elementos.get(0).click();
	elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'offer/list')]");
	elementos.get(0).click();

	// hay 2 ofertas de este usuario
	elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
	assertTrue(elementos.size() == 4);

	elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'offers-menu')]/a");
	elementos.get(0).click();

	elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'offer/add')]");
	elementos.get(0).click();

	PO_View.checkElement(driver, "text", "Agregar una oferta");

	// titulo vacio
	PO_AddOfferView.fillForm(driver, "", "Polo 2009", 4500);
	// no cambiamos de pagina
	PO_View.checkElement(driver, "text", "Agregar una oferta");

	// descripcion vacia
	PO_AddOfferView.fillForm(driver, "Coche 2", "", 4500);
	// no cambiamos de pagina
	PO_View.checkElement(driver, "text", "Agregar una oferta");

	elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'offers-menu')]/a");
	elementos.get(0).click();
	elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'offer/list')]");
	elementos.get(0).click();

	// hay 2 ofertas de este usuario
	elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
	assertTrue(elementos.size() == 4);

	// desconectamos
	PO_PrivateView.clickOption(driver, "logout", "text", "Correo :");

    }

    public void Prueba18() {
	PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
	PO_LoginView.fillForm(driver, "algo3@gmail.com", "123456");
	PO_View.checkElement(driver, "text", "Las ofertas actuales en My Wallapop son las siguientes :");

	List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'offers-menu')]/a");
	elementos.get(0).click();
	elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'offer/list')]");
	elementos.get(0).click();

	// hay 2 ofertas de este usuario
	elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
	assertTrue(elementos.size() == 3);

	elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'offers-menu')]/a");
	elementos.get(0).click();

	elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'offer/add')]");
	elementos.get(0).click();

	// add iferta
	PO_View.checkElement(driver, "text", "Agregar una oferta");

	PO_AddOfferView.fillForm(driver, "Coche a vender", "Golf 2009", 4500);
	// comprobar que existe en offer/list
	PO_View.checkElement(driver, "text", "Golf 2009");

	// se ha actualizado
	elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
	assertTrue(elementos.size() == 4);

	// desconectamos
	PO_PrivateView.clickOption(driver, "logout", "text", "Correo :");

    }

    public void Prueba19() {
	PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
	PO_LoginView.fillForm(driver, "algo7@gmail.com", "123456");
	PO_View.checkElement(driver, "text", "Las ofertas actuales en My Wallapop son las siguientes :");

	List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'offers-menu')]/a");
	elementos.get(0).click();
	elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'offer/list')]");
	elementos.get(0).click();

	// hay 2 ofertas de este usuario
	elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
	assertTrue(elementos.size() == 3);

	// tiene tres ofertas

	clickOneCheckbox();

	driver.findElement(By.className("btn-danger")).click(); // borrar la oferta

	elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
	assertTrue(elementos.size() == 2);

	// desconectamos
	PO_PrivateView.clickOption(driver, "logout", "text", "Correo :");

    }

    public void Prueba20() {
	PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
	PO_LoginView.fillForm(driver, "algo7@gmail.com", "123456");
	PO_View.checkElement(driver, "text", "Las ofertas actuales en My Wallapop son las siguientes :");

	List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'offers-menu')]/a");
	elementos.get(0).click();
	elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'offer/list')]");
	elementos.get(0).click();

	// hay 1 oferta de este usuario
	elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
	assertTrue(elementos.size() == 2);

	clickOneCheckbox();

	driver.findElement(By.className("btn-danger")).click(); // borrar la oferta

	elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
	assertTrue(elementos.size() == 1);

	// desconectamos
	PO_PrivateView.clickOption(driver, "logout", "text", "Correo :");

    }

    public void Prueba21() {
	PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
	PO_LoginView.fillForm(driver, "alg4@gmail.com", "123456");
	PO_View.checkElement(driver, "text", "Las ofertas actuales en My Wallapop son las siguientes :");

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
	PO_PrivateView.clickOption(driver, "logout", "text", "Correo :");
    }

    public void Prueba22() {
	PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
	PO_LoginView.fillForm(driver, "alg4@gmail.com", "123456");
	PO_View.checkElement(driver, "text", "Las ofertas actuales en My Wallapop son las siguientes :");

	List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
		PO_View.getTimeout());
	assertTrue(elementos.size() == 5); // de momento en la pagina uno tenemos 5

	// buscar oferta
	PO_SearchOffersView.search(driver, "telefono"); // buscamos un telefono
	// no existe ningun telefono y la tabla esta vacia
	List<WebElement> lista = driver.findElements(By.xpath("//tbody/tr"));
	assertTrue(lista.size() == 0);

	// nos desconectamos
	PO_PrivateView.clickOption(driver, "logout", "text", "Correo :");
    }

    public void Prueba23() {
	PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
	PO_LoginView.fillForm(driver, "alg4@gmail.com", "123456");
	PO_View.checkElement(driver, "text", "Las ofertas actuales en My Wallapop son las siguientes :");

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
	PO_PrivateView.clickOption(driver, "logout", "text", "Correo :");
    }

    public void Prueba24() {
	PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
	PO_LoginView.fillForm(driver, "alg4@gmail.com", "123456");
	PO_View.checkElement(driver, "text", "Las ofertas actuales en My Wallapop son las siguientes :");

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

	// se actualiza el precio a 00
	PO_View.checkElement(driver, "text", "0.0 €");

	// desconectamos
	PO_PrivateView.clickOption(driver, "logout", "text", "Correo :");
    }

    public void Prueba25() {
	PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
	PO_LoginView.fillForm(driver, "alg4@gmail.com", "123456");
	PO_View.checkElement(driver, "text", "Las ofertas actuales en My Wallapop son las siguientes :");

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

	// se queda el mismo precio
	assertTrue(driver.getPageSource().contains("0.0 €")); // no se cambió el saldo del usuario

	// desconectamos
	PO_PrivateView.clickOption(driver, "logout", "text", "Correo :");
    }

    public void Prueba26() {
	PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
	PO_LoginView.fillForm(driver, "algo3@gmail.com", "123456");
	PO_View.checkElement(driver, "text", "Las ofertas actuales en My Wallapop son las siguientes :");

	// entramos para comprobar que la lista de ofertas compradas por algo3 es vacía
	List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'offers-menu')]/a");
	elementos.get(0).click();
	elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'offer/bought')]");
	elementos.get(0).click();

	// hay 0 ofertas compradas por este usuario
	List<WebElement> lista = driver.findElements(By.xpath("//tbody/tr"));
	assertTrue(lista.size() == 0);

	// volvemos a home
	driver.navigate().back();

	// el numero de articulos que aparecen son 5
	elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
	assertTrue(elementos.size() == 5);

	assertTrue(driver.getPageSource().contains("100.0 €")); // estamos sin dinero

	// buscar oferta
	PO_SearchOffersView.search(driver, "mercedes"); // buscamos un mercedes

	elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
	assertTrue(elementos.size() == 1); // hay 1 solo mercedes
	PO_View.checkElement(driver, "text", "220c"); // su precio del coche es 70

	By comprar = By.className("btn-info");
	driver.findElement(comprar).click();

	// se actualiza el precio a 30
	assertTrue(driver.getPageSource().contains("30.0 €")); // no se cambió el saldo del usuario

	// VAMOS AHORA A LA LISTA de OFERTAS COMPRADAS

	elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'offers-menu')]/a");
	elementos.get(0).click();
	elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'offer/bought')]");
	elementos.get(0).click();

	// hay 1 oferta que es mercedes
	elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
	assertTrue(elementos.size() == 1);

	// comprobamos que es un mercedes
	PO_View.checkElement(driver, "text", "220c");
	PO_View.checkElement(driver, "text", "Coche");

	// desonectamos
	PO_PrivateView.clickOption(driver, "logout", "text", "Correo :");

    }

    public void Prueba27() {
	// index.html
	String url = driver.getCurrentUrl();
	String newUrl = url.replace("/login?logout", "");
	driver.get(newUrl);
	// cambiando las idiomas en index.html <-> home
	homeTest();

	// login spanish -> english -> spanish
	loginUserTest();

	// estamos conectados con el usuario con correo algo5@gmail.com
	// click en menu y hacemos click en agregar oferta
	List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'offers-menu')]/a");
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
	PO_PrivateView.clickOption(driver, "logout", "text", "Correo :");

	// login con admin
	loginAdminTest();

	// ahora estamos como admin y vamos a mostrar la lista de usuarios
	// menu -> ver usuarios
	elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'users-menu')]/a");
	elementos.get(0).click();
	elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'user/list')]");
	elementos.get(0).click();

	listUsersTest(elementos);

	PO_PrivateView.clickOption(driver, "logout", "text", "Correo :");

    }

    /**
     * 
     */
    private void listUsersTest(List<WebElement> elementos) {
	// hay 7 usuarios en el sistema
	elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
	assertTrue(elementos.size() == 9);
	// estamos en spanish, probamos que si
	PO_View.checkElement(driver, "text", "Los usuarios de My Wallapop son los siguientes:");
	// changing to english
	PO_NavView.changeIdiom(driver, "English");
	PO_View.checkElement(driver, "text", "My Wallapop current users are :");
	// changing to spanish again
	PO_NavView.changeIdiom(driver, "Spanish");
	PO_View.checkElement(driver, "text", "Eliminar");
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
	PO_PrivateView.clickOption(driver, "logout", "text", "Correo :");

	// changing to english
	PO_NavView.changeIdiom(driver, "English");
	PO_LoginView.checkElement(driver, "text", "Log in");
	PO_LoginView.fillForm(driver, "admin@email.com", "123456");
	PO_View.checkElement(driver, "text", "Welcome dear Administrator");
	// disconnect
	PO_PrivateView.clickOption(driver, "logout", "text", "Correo :");

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
	PO_View.checkElement(driver, "text", "Agregar una oferta");
	PO_View.checkElement(driver, "text", "Correo y Nombre :");
	PO_View.checkElement(driver, "text", "Titulo de oferta :");
	// changing to english
	PO_NavView.changeIdiom(driver, "English");
	PO_View.checkElement(driver, "text", "Email and Name :");
	PO_View.checkElement(driver, "text", "Description :");
	// changing to spanish again
	PO_NavView.changeIdiom(driver, "Spanish");
	PO_View.checkElement(driver, "text", "Correo y Nombre :");
	PO_View.checkElement(driver, "text", "Titulo de oferta :");
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
	PO_View.checkElement(driver, "text", "Date of creation");
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
	PO_View.checkElement(driver, "text", "Las ofertas actuales en My Wallapop son las siguientes :");
	// disconnect
	PO_PrivateView.clickOption(driver, "logout", "text", "Correo :");

	// changing to english
	PO_NavView.changeIdiom(driver, "English");
	PO_LoginView.checkElement(driver, "text", "Log in");
	PO_LoginView.fillForm(driver, "algo5@gmail.com", "123456");
	PO_View.checkElement(driver, "text", "The current offers in My Wallapop are the following :");
	// disconnect
	PO_PrivateView.clickOption(driver, "logout", "text", "Correo :");

	// changing to spanish again
	PO_NavView.changeIdiom(driver, "Spanish");
	PO_LoginView.checkElement(driver, "text", "Identificate");
	PO_LoginView.fillForm(driver, "algo5@gmail.com", "123456");
	PO_View.checkElement(driver, "text", "Las ofertas actuales en My Wallapop son las siguientes :");
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

    public void Prueba28() {
	// index.html
	String url = driver.getCurrentUrl();
	String newUrl = url.replace("/login?logout", "");
	driver.get(newUrl);
	// nos dirigimos a user/list sin login
	url = driver.getCurrentUrl();
	newUrl = url + "user/list";
	driver.get(newUrl);
	// nos dirige a la pagina de login
	PO_LoginView.checkElement(driver, "text", "Identificate");
	// login con admin
	PO_LoginView.fillForm(driver, "admin@email.com", "123456");
	// vemos los usuarios del sistema
	PO_View.checkElement(driver, "text", "Los usuarios de My Wallapop son los siguientes:");

	// desconectamos
	PO_PrivateView.clickOption(driver, "logout", "text", "Correo :");

    }

    public void Prueba29() {
	// index.html
	String url = driver.getCurrentUrl();
	String newUrl = url.replace("/login?logout", "");
	driver.get(newUrl);
	// nos dirigimos a offer/list sin login
	url = driver.getCurrentUrl();
	newUrl = url + "offer/list";
	driver.get(newUrl);
	// nos dirige a la pagina de login
	PO_LoginView.checkElement(driver, "text", "Identificate");
	// login con user
	PO_LoginView.fillForm(driver, "algo5@gmail.com", "123456");
	// vea sus ofertas
	PO_View.checkElement(driver, "text", "Sus ofertas publicadas en My Wallapop son las siguientes :");

	// desconectamos
	PO_PrivateView.clickOption(driver, "logout", "text", "Correo :");

    }

    public void Prueba30() {
	// index.html
	String url = driver.getCurrentUrl();
	String newUrl = url.replace("/login?logout", "");
	driver.get(newUrl);
	// login con usuario estandar
	PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
	PO_LoginView.fillForm(driver, "algo@gmail.com", "123456");
	PO_View.checkElement(driver, "text", "Las ofertas actuales en My Wallapop son las siguientes :");
	// cambiamos el home
	url = driver.getCurrentUrl().replace("home", "");
	newUrl = url + "user/list";
	// nos vamos a user/list
	driver.get(newUrl);
	// error 403
	assertTrue(driver.getPageSource().contains("HTTP Status 403 – Forbidden"));
	// asegurar que no se ven la pagina de usuarios gestionada por el admin
	assertFalse(driver.getPageSource().contains("Los usuarios de My Wallapop son los siguientes:"));
	// no estamos conectados entonces.
	driver.navigate().back();
	PO_PrivateView.clickOption(driver, "logout", "text", "Correo :");
    }

    public void Prueba31() {
	PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
	PO_LoginView.fillForm(driver, "algo5@gmail.com", "123456");
	PO_View.checkElement(driver, "text", "Las ofertas actuales en My Wallapop son las siguientes :");

	List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'messages-menu')]/a");
	elementos.get(0).click();
	elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'message/chat')]");
	elementos.get(0).click();

	// enviamos un mensaje al usuario cuyo oferta es Samsung note y email
	// algo1@gmail.com

	Select dropdown = new Select(driver.findElement(By.id("user")));
	dropdown.selectByIndex(2);

	PO_View.checkElement(driver, "text", "Tu mensaje");

	// enviamos un mensaje
	PO_SendMessageView.fillForm(driver, "Cuantos años la tienes");

	elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
	assertTrue(elementos.size() == 1);

	PO_View.checkElement(driver, "text", "Cuantos años la tienes");

	// comprobamos que se actualizó en la lista de mensajes
	elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'messages-menu')]/a");
	elementos.get(0).click();
	elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'message/list')]");
	elementos.get(0).click();

	elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
	assertTrue(!elementos.isEmpty());

	PO_View.checkElement(driver, "text", "Cuantos años la tienes");
	PO_View.checkElement(driver, "text", "algo1@gmail.com");
	PO_View.checkElement(driver, "text", "algo5@gmail.com");

	// desconectamos
	PO_PrivateView.clickOption(driver, "logout", "text", "Correo :");
    }

    public void Prueba32() {
	PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
	PO_LoginView.fillForm(driver, "algo5@gmail.com", "123456");
	PO_View.checkElement(driver, "text", "Las ofertas actuales en My Wallapop son las siguientes :");

	List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'messages-menu')]/a");
	elementos.get(0).click();
	elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'message/chat')]");
	elementos.get(0).click();

	// enviamos un mensaje al usuario cuyo oferta es Samsung note y email
	// algo1@gmail.com

	Select dropdown = new Select(driver.findElement(By.id("user")));
	dropdown.selectByIndex(1);

	PO_View.checkElement(driver, "text", "Tu mensaje");

	// enviamos un mensaje
	PO_SendMessageView.fillForm(driver, "Quiero verlo en persona porfa");

	elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
	assertTrue(elementos.size() == 2);

	PO_View.checkElement(driver, "text", "Cuantos años la tienes");
	PO_View.checkElement(driver, "text", "Quiero verlo en persona porfa");

	// comprobamos que se actualizó en la lista de mensajes
	elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'messages-menu')]/a");
	elementos.get(0).click();
	elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'message/list')]");
	elementos.get(0).click();

	elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
	assertTrue(!elementos.isEmpty());

	PO_View.checkElement(driver, "text", "Cuantos años la tienes");
	PO_View.checkElement(driver, "text", "Quiero verlo en persona porfa");
	PO_View.checkElement(driver, "text", "algo1@gmail.com");
	PO_View.checkElement(driver, "text", "algo5@gmail.com");

	// desconectamos
	PO_PrivateView.clickOption(driver, "logout", "text", "Correo :");
    }

    public void Prueba33() {
	PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
	PO_LoginView.fillForm(driver, "algo5@gmail.com", "123456");
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
	PO_View.checkElement(driver, "text", "algo5@gmail.com");
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

    public void Prueba34() {
	PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
	PO_LoginView.fillForm(driver, "algo5@gmail.com", "123456");
	PO_View.checkElement(driver, "text", "Las ofertas actuales en My Wallapop son las siguientes :");

	List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'messages-menu')]/a");
	elementos.get(0).click();
	elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'message/chat')]");
	elementos.get(0).click();

	// enviamos un mensaje al usuario cuyo oferta es Samsung note y email
	// algo1@gmail.com

	Select dropdown = new Select(driver.findElement(By.id("user")));
	dropdown.selectByIndex(9);

	PO_View.checkElement(driver, "text", "Tu mensaje");

	// enviamos un mensaje
	PO_SendMessageView.fillForm(driver, "Quiero verlo");

	elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
	// tiene tres mensajes
	assertTrue(elementos.size() == 5);

	PO_View.checkElement(driver, "text", "Quiero verlo");

	// comprobamos que se actualizó en la lista de mensajes
	elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'messages-menu')]/a");
	elementos.get(0).click();
	elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'message/delete')]");
	elementos.get(0).click();

	PO_View.checkElement(driver, "text", "Cuantos años lo tienes");
	PO_View.checkElement(driver, "text", "Puedes bajar el precio");
	PO_View.checkElement(driver, "text", "Quiero verlo");

	// borramos el primero
	clickOneCheckbox();

	driver.findElement(By.className("btn-danger")).click(); // borrar cuantos años...

	// desconectamos
	PO_PrivateView.clickOption(driver, "logout", "text", "Correo :");
    }

    public void Prueba35() {
	PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
	PO_LoginView.fillForm(driver, "algo5@gmail.com", "123456");
	PO_View.checkElement(driver, "text", "Las ofertas actuales en My Wallapop son las siguientes :");

	List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'messages-menu')]/a");
	elementos.get(0).click();
	elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'message/chat')]");
	elementos.get(0).click();

	elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
	// tiene tres mensajes
	assertTrue(elementos.size() == 4); // ahora tenemos solamente 2 mensajes

	// comprobamos que se actualizó en la lista de mensajes
	elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'messages-menu')]/a");
	elementos.get(0).click();
	elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'message/delete')]");
	elementos.get(0).click();

	// borramos el siguiente
	clickOneCheckbox();

	driver.findElement(By.className("btn-danger")).click(); //

	elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'messages-menu')]/a");
	elementos.get(0).click();
	elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'message/chat')]");
	elementos.get(0).click();

	elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
	// tiene un mensaje
	assertTrue(elementos.size() == 3); // ahora tenemos solamente 1 msg

	// desconectamos
	PO_PrivateView.clickOption(driver, "logout", "text", "Correo :");
    }

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
	By boton = By.xpath("//*[@id=\"highlightButton57\"]");
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
	By boton = By.xpath("//*[@id=\"highlightButton53\"]");
	driver.findElement(boton).click();

	PO_View.checkElement(driver, "text", "0.0 €");

	// no se cambia el boton a destacado
	List<WebElement> btnDestacado = driver.findElements(By.className("btn-warning"));
	assertTrue(btnDestacado.isEmpty()); // NO APARECE EL BOTON DESTACADO

	assertTrue(driver.getPageSource().contains("0.0 €")); // estamos sin dinero :(

	// desconectamos
	PO_PrivateView.clickOption(driver, "logout", "text", "Correo :");
    }

    private void clickOneCheckbox() {
	int count = 1;
	for (WebElement e : driver.findElements(By.xpath("//input[@type='checkbox']"))) {
	    if (!e.isSelected() && count > 0 && !e.getAttribute("id").equals("checkAll")) {
		e.click();
		count -= 1;
	    }
	}
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
