package stellarburgers;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import stellarburgers.api.client.UserClient;
import stellarburgers.api.model.User;
import stellarburgers.pom.LoginPage;
import stellarburgers.pom.MainPage;
import stellarburgers.pom.RecoverPasswordPage;
import stellarburgers.pom.RegistrationPage;
import stellarburgers.api.util.UserGenerator;

public class LoginTests extends Base {

    private UserClient userClient;
    private User user;

    MainPage mainPage;
    LoginPage loginPage;
    RegistrationPage registerPage;
    RecoverPasswordPage recoverPasswordPage;

    @Test
    @DisplayName("Вход через кнопку <Войти в аккаунт> на главной странице")
    public void loginUserLoginButton() {
        String accessToken;

        userClient = new UserClient();
        user = UserGenerator.getUser();
        ValidatableResponse createResponse = userClient.createUser(user);
        accessToken = createResponse.extract().path("accessToken");

        mainPage = new MainPage(webDriver);
        mainPage.openPage();
        mainPage.clickLoginButton();

        loginPage = new LoginPage(webDriver);
        loginPage.setLoginForm(user.getEmail(), user.getPassword());
        loginPage.singInButtonClick();
        mainPage.isOrderButtonDisplayed();
        userClient.deleteUser(accessToken);
    }

    @Test
    @DisplayName("Вход через кнопку <Личный кабинет>")
    public void loginUserPersonalAccountButton() {
        String accessToken;

        userClient = new UserClient();
        user = UserGenerator.getUser();
        ValidatableResponse createResponse = userClient.createUser(user);
        accessToken = createResponse.extract().path("accessToken");

        mainPage = new MainPage(webDriver);
        mainPage.openPage();
        mainPage.clickPersonalAccountButton();

        loginPage = new LoginPage(webDriver);
        loginPage.setLoginForm(user.getEmail(), user.getPassword());
        loginPage.singInButtonClick();

        mainPage.isOrderButtonDisplayed();

        userClient.deleteUser(accessToken);
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void loginUserRegistrationForm() {
        String accessToken;

        userClient = new UserClient();
        user = UserGenerator.getUser();
        ValidatableResponse createResponse = userClient.createUser(user);
        accessToken = createResponse.extract().path("accessToken");

        mainPage = new MainPage(webDriver);
        mainPage.openPage();
        mainPage.clickLoginButton();

        loginPage = new LoginPage(webDriver);
        loginPage.registerButtonClick();
        registerPage = new RegistrationPage(webDriver);
        registerPage.clickSingInButton();

        loginPage.setLoginForm(user.getEmail(), user.getPassword());
        loginPage.singInButtonClick();

        mainPage.isOrderButtonDisplayed();

        userClient.deleteUser(accessToken);
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void loginUserRecoverPasswordButton() {
        String accessToken;

        userClient = new UserClient();
        user = UserGenerator.getUser();
        ValidatableResponse createResponse = userClient.createUser(user);
        accessToken = createResponse.extract().path("accessToken");

        mainPage = new MainPage(webDriver);
        mainPage.openPage();
        mainPage.clickLoginButton();
        loginPage = new LoginPage(webDriver);
        loginPage.clickRecoverPasswordButton();
        recoverPasswordPage = new RecoverPasswordPage(webDriver);
        recoverPasswordPage.clickLoginButton();

        loginPage.setLoginForm(user.getEmail(), user.getPassword());
        loginPage.singInButtonClick();

        mainPage.isOrderButtonDisplayed();

        userClient.deleteUser(accessToken);
    }
}