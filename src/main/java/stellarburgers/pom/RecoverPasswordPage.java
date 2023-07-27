package stellarburgers.pom;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RecoverPasswordPage {

    private WebDriver driver;
    private By loginButton = By.xpath(".//a[text()='Войти']");

    public RecoverPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Клик по кнопке войти")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }
}