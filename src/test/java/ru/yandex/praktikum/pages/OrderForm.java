package ru.yandex.praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.praktikum.EnvConfig;

import java.time.Duration;

public class OrderForm {
    private final WebDriver driver;

    private final By orderFormHeading = By.xpath(".//div[text()='Для кого самокат']");
    private final By nameField = By.xpath(".//input[contains(@placeholder, '* Имя')]");
    private final By surnameField = By.xpath(".//input[contains(@placeholder, '* Фамилия')]");
    private final By addressField = By.xpath(".//input[contains(@placeholder, '* Адрес: куда привезти заказ')]");
    private final By metroStationField = By.xpath(".//input[contains(@placeholder, '* Станция метро')]");

    private final By phoneNumberField = By.xpath(".//input[contains(@placeholder, '* Телефон: на него позвонит курьер')]");
    private final By nextButton = By.xpath(".//button[text()='Далее']");


    public OrderForm(WebDriver driver) {

        this.driver = driver;
    }

    public OrderForm  waitForOrderFormLoading() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(orderFormHeading));

        return this;
    }


    public OrderForm enterName(String name) {

        driver.findElement(nameField).sendKeys(name);

        return this;
    }

    public OrderForm enterSurname(String surname) {

        driver.findElement(surnameField).sendKeys(surname);

        return this;
    }

    public OrderForm enterAddress(String address) {

        driver.findElement(addressField).sendKeys(address);

        return this;
    }

    public OrderForm pickMetroStation(String stationId) {
        driver.findElement(metroStationField).click();
        driver.findElement(By.xpath(".//button[@value=" + stationId + "]" )).click();

        return this;
    }

    public OrderForm enterPhoneNumber(String phoneNumber) {

        driver.findElement(phoneNumberField).sendKeys(phoneNumber);

        return this;

    }



    public OrderForm clickNextButton() {
        driver.findElement(nextButton).click();

        return this;

    }


public OrderForm fillInPersonalInfo(String name, String surname, String address, String stationId, String phoneNumber) {
        waitForOrderFormLoading();
        enterName(name);
        enterSurname(surname);
        enterAddress(address);
        pickMetroStation(stationId);
        enterPhoneNumber(phoneNumber);
        clickNextButton();

        return this;
    }



}
