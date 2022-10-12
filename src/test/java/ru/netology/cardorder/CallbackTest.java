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


import java.lang.module.Configuration;
import java.sql.Driver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CallbackTest {
    private WebDriver driver;

    @BeforeAll
    static void setupAll() {

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
    void shouldTestCorrectName1() {

        driver.findElement(By.cssSelector("[type=\"text\"]")).sendKeys("Иванов Иван");
        driver.findElement(By.cssSelector("[type=\"tel\"]")).sendKeys("+79871234567");
        driver.findElement(By.xpath("//label[@data-test-id = 'agreement']")).click();
        driver.findElement(By.cssSelector(".button__text")).click();
        String text = driver.findElement(By.xpath("//p[@data-test-id = 'order-success']")).getText();
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text.trim());
    }

    @Test
    void shouldTestCorrectName2() {

        driver.findElement(By.cssSelector("[type=\"text\"]")).sendKeys("Иванов-Петров Иван");
        driver.findElement(By.cssSelector("[type=\"tel\"]")).sendKeys("+79871234567");
        driver.findElement(By.xpath("//label[@data-test-id = 'agreement']")).click();
        driver.findElement(By.cssSelector(".button__text")).click();
        String text = driver.findElement(By.xpath("//p[@data-test-id = 'order-success']")).getText();
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text.trim());
    }

    @Test
    void shouldTestNotValidName() {
        driver.findElement(By.cssSelector("[type=\"text\"]")).sendKeys("Ivanov Ivan ");
        driver.findElement(By.cssSelector("[type=\"tel\"]")).sendKeys("+78766842649");
        driver.findElement(By.xpath("//label[@data-test-id = 'agreement']")).click();
        driver.findElement(By.cssSelector(".button__text")).click();
        String text = driver.findElement(By.xpath("//span[@data-test-id = 'name' and contains(@class,'input_invalid')]//span[@class='input__sub']")).getText();
        assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", text.trim());
    }

    @Test
    void shouldTestNotValidPhone() {
        driver.findElement(By.cssSelector("[type=\"text\"]")).sendKeys("Иванов-Петров Иван");
        driver.findElement(By.cssSelector("[type=\"tel\"]")).sendKeys("88766842649");
        driver.findElement(By.xpath("//label[@data-test-id = 'agreement']")).click();
        driver.findElement(By.cssSelector(".button__text")).click();
        String text = driver.findElement(By.xpath("//span[@data-test-id = 'phone' and contains(@class,'input_invalid')]//span[@class='input__sub']")).getText();
        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", text.trim());
    }

    @Test
    void shouldTestEmptyName() {

        driver.findElement(By.cssSelector("[type=\"tel\"]")).sendKeys("88766842649");
        driver.findElement(By.xpath("//label[@data-test-id = 'agreement']")).click();
        driver.findElement(By.cssSelector(".button__text")).click();
        String text = driver.findElement(By.xpath("//span[@data-test-id = 'name' and contains(@class,'input_invalid')]//span[@class='input__sub']")).getText();
        assertEquals("Поле обязательно для заполнения", text.trim());
    }

    @Test
    void shouldTestEmptyPhone() {
        driver.findElement(By.cssSelector("[type=\"text\"]")).sendKeys("Иванов-Петров Иван");
        driver.findElement(By.xpath("//label[@data-test-id = 'agreement']")).click();
        driver.findElement(By.cssSelector(".button__text")).click();
        String text = driver.findElement(By.xpath("//span[@data-test-id = 'phone' and contains(@class,'input_invalid')]//span[@class='input__sub']")).getText();
        assertEquals("Поле обязательно для заполнения", text.trim());
    }

    @Test
    void shouldTestEmptyCheckBox() {

        driver.findElement(By.cssSelector("[type=\"text\"]")).sendKeys("Иванов-Петров Иван");
        driver.findElement(By.cssSelector("[type=\"tel\"]")).sendKeys("+78766842649");
        driver.findElement(By.cssSelector(".button__text")).click();
        assertTrue(driver.findElement(By.xpath("//label[@data-test-id='agreement' and contains(@class, 'input_invalid')]")).isDisplayed());
    }
}





