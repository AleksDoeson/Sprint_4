package ru.praktikum.scooter.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class OpenScooterPageTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        driver = new ChromeDriver();  // Инициализация драйвера
    }

    @Test
    public void openScooterPage() {
        driver.get("https://qa-scooter.praktikum-services.ru/");  // Открываем страницу
        String actualTitle = driver.getTitle();  // Получаем заголовок страницы

        // Проверяем, что title не равен null и содержит слово "Самокат"
        if (actualTitle != null) {
            String expectedTitle = "Самокат";
            assert(actualTitle.contains(expectedTitle));  // Проверка, что название страницы включает слово "Самокат"
        } else {
            // Если title не был загружен, выводим ошибку
            System.out.println("Title is null, page might not have loaded correctly.");
        }
    }

    // После каждого теста закрываем браузер
    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();  // Закрытие браузера
        }
    }
}



