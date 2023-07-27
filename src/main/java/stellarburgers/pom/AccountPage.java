package stellarburgers.pom;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountPage {

    private WebDriver driver;
    //Кнопка "Конструктор"
    private By createBurgerButton = By.xpath("//p[text()='Конструктор']");
    //Логотип "Stellar Burgers"
    private By logoStellarBurgerButton = By.xpath("//div[@class='AppHeader_header__logo__2D0X2']/a");
    //Кнопка "Выход"
    private By logOutButton = By.xpath("//button[text()='Выход']");

    public AccountPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Клик по кнопке Конструктор")
    public void clickCreateBurgerButton() {
        driver.findElement(createBurgerButton).click();
    }

    @Step("Клик по логотипу Stellar Burgers")
    public void clickStellarBurgerLogoButton() {
        driver.findElement(logoStellarBurgerButton).click();
    }

    @Step("Клик по кнопке Выйти")
    public void clickLogOutButton() {
        driver.findElement(logOutButton).click();
    }
}