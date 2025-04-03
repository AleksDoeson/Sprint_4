package ru.praktikum.scooter.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class OrderPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Локаторы для полей ввода
    private By firstNameField = By.cssSelector("input[placeholder='* Имя']");  // Локатор для поля ввода имени
    private By lastNameField = By.cssSelector("input[placeholder='* Фамилия']");  // Локатор для поля ввода фамилии
    private By addressField = By.cssSelector("input[placeholder='* Адрес: куда привезти заказ']");  // Локатор для поля ввода адреса
    private By phoneField = By.cssSelector("input[placeholder='* Телефон: на него позвонит курьер']");  // Локатор для поля ввода телефона

    // Локаторы для кнопок
    private By orderButton = By.cssSelector(".Button_Button__ra12g");  // Локатор для кнопки отправки заказа
    private By nextButton = By.cssSelector(".Button_Middle__1CSJM");  // Локатор для кнопки "Далее"
    private By cookieBannerCloseButton = By.cssSelector(".App_CookieButton__3cvqF");  // Локатор для кнопки закрытия баннера cookie
    private By yesButton = By.cssSelector(".Button_Middle__1CSJM");
    // Локаторы для других элементов формы
    private By rentalPeriodArrow = By.xpath("//div[contains(@class, 'Dropdown-arrow-wrapper')]//span");
    private By commentInput = By.cssSelector("input[placeholder='Комментарий для курьера']");  // Локатор для поля ввода комментария
    // Универсальный локатор для чекбокса с параметризированным id
    private By colorCheckbox(String colorId) {
        return By.id(colorId);  // Формируем локатор по переданному id
    }

    private By modalWindow = By.cssSelector(".Order_Modal__YZ-d3");  // Локатор для модального окна с подтверждением заказа
    private By orderNumber = By.cssSelector(".Order_Text__2broi");  // Локатор для текста с номером заказа
    private By subwayInputField = By.cssSelector("input.select-search__input");  // Локатор для поля ввода станции метро
    private By subwayOption = By.cssSelector(".select-search__option");  // Локатор для вариантов метро в выпадающем списке
    private By dateInputField = By.cssSelector("input[placeholder='* Когда привезти самокат']");  // Локатор для поля даты

    public OrderPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    // Метод для закрытия баннера cookie
    public void closeCookieBanner() {
        try {
            WebElement cookieButton = driver.findElement(cookieBannerCloseButton);
            cookieButton.click();  // Закрытие баннера
            System.out.println("Баннер cookie закрыт");
        } catch (NoSuchElementException e) {
            System.out.println("Баннер cookie не найден");
        }
    }

    // Метод для клика по кнопке "Заказать"
    public void clickOrderButton() {
        WebElement button = driver.findElement(orderButton);
        button.click();
    }

    // Метод для клика по кнопке "Далее"
    public void clickNextButton() {
        WebElement button = driver.findElement(nextButton);
        button.click();
    }

    // Метод для заполнения поля даты
    public void fillDateField(String date) {
        WebElement dateField = driver.findElement(dateInputField);
        dateField.sendKeys(date);
    }

    // Заполнение формы заказа
    public void fillOrderForm(String firstName, String lastName, String address, String subway, String phoneNumber) {
        fillFirstNameField(firstName);
        fillLastNameField(lastName);
        fillAddressField(address);
        fillPhoneField(phoneNumber);
        fillSubwayField(subway);
    }

    // Заполнение поля имени
    public void fillFirstNameField(String firstName) {
        WebElement firstNameInput = driver.findElement(firstNameField);
        firstNameInput.sendKeys(firstName);
    }

    // Заполнение поля фамилии
    public void fillLastNameField(String lastName) {
        WebElement lastNameInput = driver.findElement(lastNameField);
        lastNameInput.sendKeys(lastName);
    }

    // Заполнение поля адреса
    public void fillAddressField(String address) {
        WebElement addressInput = driver.findElement(addressField);
        addressInput.sendKeys(address);
    }

    // Заполнение поля телефона
    public void fillPhoneField(String phoneNumber) {
        WebElement phoneInput = driver.findElement(phoneField);
        phoneInput.sendKeys(phoneNumber);
    }

    // Заполнение поля станции метро
    public void fillSubwayField(String subwayName) {
        WebElement subwayInput = driver.findElement(subwayInputField);
        subwayInput.sendKeys(subwayName);

        // Ожидание появления списка вариантов
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(subwayOption));

        // Выбор нужной станции метро из списка
        List<WebElement> options = driver.findElements(subwayOption);
        for (WebElement option : options) {
            if (option.getText().equalsIgnoreCase(subwayName)) {
                option.click();
                break;
            }
        }
    }

    // Универсальный метод для выбора срока аренды
    public void selectRentalPeriod(String period) {
        // Кликаем по стрелочке для открытия выпадающего списка
        WebElement dropdownArrow = driver.findElement(rentalPeriodArrow);
        dropdownArrow.click();

        // Локатор для выбора варианта (например, "1 день")
        WebElement option = driver.findElement(By.xpath("//div[contains(@class, 'Dropdown-menu')]//div[text()='" + period + "']"));
        option.click();  // Выбираем нужный вариант
    }


    // Заполнение комментария
    public void fillComment(String comment) {
        WebElement commentInputElement = driver.findElement(commentInput);
        commentInputElement.sendKeys(comment);
    }

    // Метод для выбора чекбокса по переданному id
    public void selectColor(String colorId) {
        WebElement checkbox = driver.findElement(colorCheckbox(colorId));  // Находим элемент по динамическому id
        if (!checkbox.isSelected()) {
            checkbox.click();  // Если чекбокс не выбран, выбираем его
        }
    }

    // Отправка формы
    public void submitOrder() {
        WebElement submitBtn = driver.findElement(orderButton);
        submitBtn.click();
    }

    // Проверка видимости модального окна подтверждения
    public boolean isModalVisible() {
        WebElement modal = driver.findElement(modalWindow);
        return modal.isDisplayed();
    }

    // Подтверждение заказа в модальном окне
    public void confirmOrder() {
        WebElement confirmBtn = driver.findElement(yesButton);
        confirmBtn.click();

    }

    // Проверка видимости окна с номером заказа
    public boolean isOrderModalVisible() {
        return driver.findElement(orderNumber).isDisplayed();
    }

    // Получение номера заказа
    public String getOrderNumber() {
        return driver.findElement(orderNumber).getText().trim();
    }
}



