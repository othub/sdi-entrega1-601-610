package com.uniovi.tests.pageobjects;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.uniovi.utils.SeleniumUtils;

public class PO_NavView extends PO_View {

    public static void clickOption(WebDriver driver, String textOption, String criterio, String textoDestino) {
	// CLickamos en la opción de registro y esperamos a que se cargue el enlace de
	// Registro.
	List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "@href", textOption, getTimeout());

	// Tiene que haber un sólo elemento.
	assertTrue(elementos.size() == 1);

	// Ahora lo clickamos
	elementos.get(0).click();

	// Esperamos a que sea visible un elemento concreto
	elementos = SeleniumUtils.EsperaCargaPagina(driver, criterio, textoDestino, getTimeout());

	// Tiene que haber un sólo elemento.
	assertTrue(elementos.size() == 1);
    }

    /**
     * Selecciona el enlace de idioma correspondiente al texto textLanguage
     * 
     * @param driver: apuntando al navegador abierto actualmente.
     * @param textLanguage: el texto que aparece en el enlace de idioma ("English" o
     *        "Spanish")
     */
    public static void changeIdiom(WebDriver driver, String textLanguage) {
	List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "id", "btnLanguage", getTimeout());

	elementos.get(0).click();
	elementos = SeleniumUtils.EsperaCargaPagina(driver, "id", "languageDropdownMenuButton", getTimeout());
	elementos = SeleniumUtils.EsperaCargaPagina(driver, "id", textLanguage, getTimeout());
	elementos.get(0).click();
    }
}
