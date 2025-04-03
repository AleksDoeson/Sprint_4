package ru.praktikum.scooter.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import org.openqa.selenium.JavascriptExecutor;

public class MainPage {
    private WebDriver driver;

    // Локаторы для вопросов и ответов в разделе "Вопросы о важном"
    private By faqDropdownArrows = By.cssSelector(".accordion__button");
    private By faqText = By.cssSelector(".accordion__panel");


    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    // Метод для клика по стрелочке определенного вопроса
    public void clickOnDropdownArrow(int index) {
        List<WebElement> arrows = driver.findElements(faqDropdownArrows);
        if (index < 0 || index >= arrows.size()) {
            throw new IllegalArgumentException("Некорректный индекс: " + index);
        }

        WebElement arrow = arrows.get(index);

        // Прокрутка до элемента, если он не виден
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", arrow);

        arrow.click(); // Клик по стрелочке
    }

    // Метод для получения текста ответа
    public String getFaqText(int index) {
        List<WebElement> texts = driver.findElements(faqText);
        if (index < 0 || index >= texts.size()) {
            throw new IllegalArgumentException("Некорректный индекс: " + index);
        }
        return texts.get(index).getText().trim();
    }

    // Метод для проверки, виден ли ответ (используется в тестах)
    public boolean isFaqVisible(int index) {
        List<WebElement> texts = driver.findElements(faqText);
        if (index < 0 || index >= texts.size()) {
            return false;
        }
        return texts.get(index).isDisplayed();
    }

    // Метод для получения стрелочки FAQ по индексу
    public WebElement getFaqDropdownArrow(int index) {
        List<WebElement> arrows = driver.findElements(faqDropdownArrows);
        if (index < 0 || index >= arrows.size()) {
            throw new IllegalArgumentException("Некорректный индекс: " + index);
        }
        return arrows.get(index);
    }

}


