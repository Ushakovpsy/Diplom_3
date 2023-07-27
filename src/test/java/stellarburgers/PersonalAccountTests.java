package stellarburgers;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import stellarburgers.api.client.UserClient;
import stellarburgers.api.model.User;
import stellarburgers.api.util.UserGenerator;
import stellarburgers.pom.AccountPage;
import stellarburgers.pom.LoginPage;
import stellarburgers.pom.MainPage;

public class PersonalAccountTests extends Base {

    private UserClient userClient;
    private User user;
    private String accessToken;

    MainPage mainPage;
    LoginPage loginPage;
    AccountPage accountPage;

    @Test
    @DisplayName("Переход по клику на <Личный кабинет>")
    public void loginUserPersonalAccountButton() {
        mainPage = new MainPage(webDriver);
        mainPage.openPage();
        mainPage.clickPersonalAccountButton();
        loginPage = new LoginPage(webDriver);
        loginPage.isSingInButtonDisplayed();
    }

    @Test
    @DisplayName("Переход по клику на <Конструктор>")
    public void checkGoToConstructor() {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.openPage();
        mainPage.clickLoginButton();

        userClient = new UserClient();
        user = UserGenerator.getUser();
        ValidatableResponse createResponse = userClient.createUser(user);
        accessToken = createResponse.extract().path("accessToken");

        loginPage = new LoginPage(webDriver);
        loginPage.setLoginForm(user.getEmail(), user.getPassword());
        loginPage.singInButtonClick();

        mainPage.clickPersonalAccountButton();
        accountPage = new AccountPage(webDriver);
        accountPage.clickCreateBurgerButton();
        mainPage.isOrderButtonDisplayed();
        userClient.deleteUser(accessToken);
    }

    @Test
    @DisplayName("Переход по клику на логотипу Stellar Burgers")
    public void checkOutFromPersonalAccount() {
        mainPage = new MainPage(webDriver);
        mainPage.openPage();
        mainPage.clickLoginButton();

        userClient = new UserClient();
        user = UserGenerator.getUser();
        ValidatableResponse createResponse = userClient.createUser(user);
        accessToken = createResponse.extract().path("accessToken");

        loginPage = new LoginPage(webDriver);
        loginPage.setLoginForm(user.getEmail(), user.getPassword());
        loginPage.singInButtonClick();
        mainPage.clickPersonalAccountButton();
        accountPage = new AccountPage(webDriver);
        accountPage.clickStellarBurgerLogoButton();
        mainPage.isOrderButtonDisplayed();
        userClient.deleteUser(accessToken);
    }
}