package stellarburgers.pom;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage {

    private WebDriver driver;
    //Поле ввода имени
    private By nameFieldInput = By.xpath("//label[text()='Имя']//following-sibling::input");
    //Поле ввода email
    private By emailFieldInput = By.xpath("//label[text()='Email']//following-sibling::input");
    //Поле ввода пароля
    private By passwordFieldInput = By.xpath("//input[@type='password']");
    //Кнопка "Зарегистрироваться"
    private By registerButton = By.xpath(".//button[contains(text(),'Зарегистрироваться')]");
    //Кнопка "Войти"
    private By singInButton = By.xpath("//a[text()='Войти']");
    //Локатор некорректного пароля
    private By textError = By.xpath(".//p[@class='input__error text_type_main-default']");

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Заполнение поля Имя")
    public void setName(String name) {
        driver.findElement(nameFieldInput).sendKeys(name);
    }

    @Step("Заполнение поля Email")
    public void setEmail(String email) {
        driver.findElement(emailFieldInput).sendKeys(email);
    }

    @Step("Заполнение поля Пароль")
    public void setPassword(String password) {
        driver.findElement(passwordFieldInput).sendKeys(password);
    }

    @Step("Клик по кнопке Зарегистрироваться")
    public void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }

    @Step("Регистрация")
    public void setFieldsForRegistration(String name, String email, String password) {
        setName(name);
        setEmail(email);
        setPassword(password);
        clickRegisterButton();
    }

    @Step("Проверка отображения ошибки некорректного пароля")
    public boolean getTextError() {
        return driver.findElement(textError).isDisplayed();
    }

    @Step("Клик по кнопке Войти")
    public void clickSingInButton() {
        driver.findElement(singInButton).click();
    }
}