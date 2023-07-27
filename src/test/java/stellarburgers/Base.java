package stellarburgers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class Base {

    WebDriver webDriver;
    ChromeOptions options;

    @Before
    public void init() {

        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();

        /* запуск в Firefox
        WebDriverManager.firefoxdriver().clearDriverCache().setup();
        webDriver = new FirefoxDriver();*/

        /* запуск в Yandex Browser
        options = new ChromeOptions();
        options.setBinary("/Applications/Yandex.app/Contents/MacOS/Yandex");
        webDriver = new ChromeDriver(options);*/

        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }
    @After
    public void clean(){
        webDriver.quit();
    }
}

