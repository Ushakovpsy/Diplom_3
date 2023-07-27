package stellarburgers.pom;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private WebDriver driver;
    //Кнопка "восстановить пароль"
    private By recoverPasswordButton = By.xpath(".//a[text()='Восстановить пароль']");
    //Кнопка "зарегистрироваться"
    private By registerButton = By.xpath(".//a[text()='Зарегистрироваться']");
    //Кнопка "Войти"
    private By singInButton = By.xpath(".//button[contains(text(),'Войти')]");
    //Поле ввода email
    private By emailInput = By.xpath(".//input[@name='name']");
    //Поле ввода пароля
    private By passwordInput = By.xpath(".//input[@name='Пароль']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Заполнение формы входа")
    public void setLoginForm(String email, String password) {
        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(passwordInput).sendKeys(password);
    }

    @Step("Клик по кнопке зарегистрироваться")
    public void registerButtonClick() {
        driver.findElement(registerButton).click();
    }

    @Step("Клик по кнопке Войти")
    public void singInButtonClick() {
        driver.findElement(singInButton).click();
    }

    @Step("Клик по кнопке восстановить пароль")
    public void clickRecoverPasswordButton() {
        driver.findElement(recoverPasswordButton).click();
    }

    @Step("Проверка отображения кнопки Войти")
    public boolean isSingInButtonDisplayed() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOf(driver.findElement(singInButton)));
        return driver.findElement(singInButton).isDisplayed();
    }
}