package ru.praktikum.scooter.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.praktikum.scooter.pages.OrderPage;

import static org.junit.Assert.*;

public class OrderTest {

    private WebDriver driver;
    private OrderPage orderPage;

    @Before
    public void setUp() {
        // Инициализация WebDriver (можно указать путь к драйверу в зависимости от настроек)
        driver = new ChromeDriver();
        orderPage = new OrderPage(driver);
        driver.get("https://qa-scooter.praktikum-services.ru"); // URL страницы заказа
    }

    @Test
    public void testOrderForm() {
        // Закрыть баннер cookie, если он появляется
        orderPage.closeCookieBanner();
        orderPage.clickOrderButton();
        // Заполнение первой формы (имя, фамилия, адрес, метро, телефон)
        String firstName = "Иван";
        String lastName = "Иванов";
        String address = "Москва, ул. Тестовая, 1";
        String phoneNumber = "+79161234567";
        String subway = "Киевская";

        // Заполнение формы заказа
        orderPage.fillOrderForm(firstName, lastName, address, subway, phoneNumber);

        // Нажатие на кнопку "Далее" после первого набора данных
        orderPage.clickNextButton();

        // Заполнение второго набора данных (когда привезти самокат, срок аренды, цвет и комментарий)
        // 1. Заполнение поля "Когда привезти самокат"
        orderPage.fillDateField("01.05.2025");

        // 2. Выбор срока аренды
        orderPage.selectRentalPeriod("сутки");

        // 3. Выбор цвета
        orderPage.selectColor("black");

        // 4. Заполнение комментария
        String comment = "Нужна доставка на утро.";
        orderPage.fillComment(comment);

        // Завершаем заказ
        orderPage.submitOrder();
        orderPage.confirmOrder();
        System.out.println("Получилось создать заказ");

        // Получаем номер заказа и выводим его в консоль
        String orderNumber = orderPage.getOrderNumber();
        System.out.println("Номер заказа: " + orderNumber);

        // Проверка, что номер заказа отображается
        assertNotNull("Номер заказа не был получен", orderNumber);
    }

    @After
    public void tearDown() {
        // Закрытие браузера после теста
        if (driver != null) {
            driver.quit();
        }
    }
}








