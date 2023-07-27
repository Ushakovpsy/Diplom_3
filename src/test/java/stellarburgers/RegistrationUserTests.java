package stellarburgers;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import stellarburgers.api.client.UserClient;
import stellarburgers.api.model.User;
import stellarburgers.api.model.UserCredentials;
import stellarburgers.pom.LoginPage;
import stellarburgers.pom.MainPage;
import stellarburgers.pom.RegistrationPage;
import stellarburgers.api.util.UserGenerator;

import static org.hamcrest.CoreMatchers.equalTo;

public class RegistrationUserTests extends Base {

    private String accessToken;
    private UserClient userClient;

    MainPage mainPage;
    LoginPage loginPage;
    RegistrationPage registerPage;

    @Test
    @DisplayName("Успешная регистрация")
    public void checkRegistration() {
        mainPage = new MainPage(webDriver);
        mainPage.openPage();
        userClient = new UserClient();
        User user = UserGenerator.getUser();
        mainPage.clickLoginButton();

        loginPage = new LoginPage(webDriver);
        loginPage.registerButtonClick();

        registerPage = new RegistrationPage(webDriver);
        registerPage.setFieldsForRegistration(user.getName(), user.getEmail(), user.getPassword());

        UserCredentials userCredentials = new UserCredentials(user.getEmail(), user.getPassword());
        ValidatableResponse loginResponse = userClient.loginUser(userCredentials);
        loginResponse.assertThat()
                .statusCode(200)
                .and()
                .body("success", equalTo(true));
        accessToken = loginResponse.extract().path("accessToken");
        userClient.deleteUser(accessToken);
    }

    @Test
    @DisplayName("Ошибка некорректного пароля")
    public void checkRegistrationWithIncorrectPassword() {
        mainPage = new MainPage(webDriver);
        mainPage.openPage();
        userClient = new UserClient();
        User user = UserGenerator.getIncorrectPasswordUser();
        mainPage.clickLoginButton();

        loginPage = new LoginPage(webDriver);
        loginPage.registerButtonClick();

        registerPage = new RegistrationPage(webDriver);
        registerPage.setFieldsForRegistration(user.getName(), user.getEmail(), user.getPassword());
        registerPage.getTextError();

        UserCredentials userCredentials = new UserCredentials(user.getEmail(), user.getPassword());
        ValidatableResponse loginResponse = userClient.loginUser(userCredentials);
        loginResponse.assertThat()
                .statusCode(401)
                .and()
                .body("message", equalTo("email or password are incorrect"));
    }
}