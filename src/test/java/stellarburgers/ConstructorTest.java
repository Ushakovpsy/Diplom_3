package stellarburgers;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import stellarburgers.pom.MainPage;

import static org.junit.Assert.*;

public class ConstructorTest extends Base {

    MainPage mainPage;

    @Test
    @DisplayName("Переход к разделу <Соусы>")
    public void checkGoToSauces() {
        mainPage = new MainPage(webDriver);

        mainPage.openPage();
        mainPage.clickSauceTab();
        assertEquals("Соусы", mainPage.getTextFromSelectedMenu());
    }

    @Test
    @DisplayName("Переход к разделу <Начинки>")
    public void checkGoToFillings() {
        mainPage = new MainPage(webDriver);

        mainPage.openPage();
        mainPage.clickFillingsTab();
        assertEquals("Начинки", mainPage.getTextFromSelectedMenu());
    }

    @Test
    @DisplayName("Переход к разделу <Булки>")
    public void checkGoToBuns() {
        mainPage = new MainPage(webDriver);
        mainPage.openPage();
        mainPage.clickFillingsTab();
        mainPage.clickBunsTab();
        assertEquals("Булки", mainPage.getTextFromSelectedMenu());
    }
}