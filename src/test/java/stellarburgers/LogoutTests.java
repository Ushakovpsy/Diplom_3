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

public class LogoutTests extends Base {

    private UserClient userClient;
    private User user;

    MainPage mainPage;
    LoginPage loginPage;
    AccountPage accountPage;

    @Test
    @DisplayName("Выход по кнопке <Выйти> в личном кабинете")
    public void profileExitButtonTest() {

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

        mainPage.clickPersonalAccountButton();

        accountPage = new AccountPage(webDriver);
        accountPage.clickLogOutButton();

        loginPage.isSingInButtonDisplayed();

        userClient.deleteUser(accessToken);
    }
}