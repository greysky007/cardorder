package ru.netology.cardorder;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;


import static java.nio.channels.FileChannel.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CallbackTest {
    private WebDriver driver;

    @BeforeAll
    static void setupAll() {
        // System.setProperty("webdriver.chrome.driver", "driver/win/chromedriver.exe");
        WebDriverManager.chromedriver().setup();

    }

    @BeforeEach
    void setup() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.get("http://localhost:9999");
    }

    @AfterEach
    void tearsDown() {
        driver.quit();
        driver = null;
    }

    @Test
    void shouldTestSomething() {

        driver.findElement(By.cssSelector("[type=\"text\"]")).sendKeys("Иван");
        driver.findElement(By.cssSelector("[type=\"tel\"]")).sendKeys("+79871234567");
        driver.findElement(By.cssSelector(".checkbox__box")).click();
        driver.findElement(By.cssSelector(".button__text")).click();
        String text = driver.findElement(By.className("paragraph")).getText();
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text.trim());
    }
}
   /*@Test
   void shouldSubmitRequest() {
       open("http://localhost:9999");
       SelenideElement form = $("[data-test-id=callback-form]");
       form.$("[data-test-id=name] input").setValue("Василий");
       form.$("[data-test-id=phone] input").setValue("+79270000000");
       form.$("[data-test-id=agreement]").click();
       form.$("[data-test-id=submit]").click();
       $(".alert-success").shouldHave(exactText("Ваша заявка успешно
               отправлена!"));
   }*/




