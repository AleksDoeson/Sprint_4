package ru.praktikum.scooter.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.praktikum.scooter.pages.MainPage;

import static org.junit.Assert.*;

public class FAQTest {
    private WebDriver driver;
    private MainPage mainPage;

    @Before
    public void setUp() {
        driver = new ChromeDriver(); // Инициализация драйвера
        mainPage = new MainPage(driver); // Инициализация страницы
        driver.get("https://qa-scooter.praktikum-services.ru/"); // Переход на сайт
    }

    @Test
    public void testFaqDropdownOpens() {
        // Явное ожидание, что стрелочка будет доступна для клика
        WebDriverWait wait = new WebDriverWait(driver, 10);  // Используем число вместо Duration
        wait.until(ExpectedConditions.elementToBeClickable(mainPage.getFaqDropdownArrow(0)));

        // Клик по стрелочке
        mainPage.clickOnDropdownArrow(0);

        // Проверка видимости FAQ
        assertTrue("FAQ должен быть видимым", mainPage.isFaqVisible(0));

        // Получение текста ответа
        String faqText = mainPage.getFaqText(0);
        assertNotNull("Текст ответа не должен быть null", faqText);
        assertFalse("Текст ответа не должен быть пустым", faqText.isEmpty());
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // Закрытие драйвера
        }
    }
}
