package ru.yandex.praktikum.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.praktikum.EnvConfig;

import java.time.Duration;

public class MainPage {
    private final WebDriver driver;
    private final By homeHeader = By.className("Home_Header__iJKdX");
    private final By cookieButton = By.id("rcc-confirm-button");
    private final By orderButtonTop = By.className("Button_Button__ra12g");
    private final By orderButtonBottom = By.cssSelector(".Button_Middle__1CSJM");


    public MainPage(WebDriver driver) {

        this.driver = driver;
    }

    public MainPage waitForHeaderToLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(homeHeader));

        return this;
    }

    public MainPage acceptCookies() {
        waitForCookiesFloater();
        driver.findElement(cookieButton).click();
        waitForCookiesFloaterToDisappear();

        return this;
    }

    public void waitForCookiesFloater() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(cookieButton));

    }

    public void waitForCookiesFloaterToDisappear() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.invisibilityOfElementLocated(cookieButton));

    }

    public void clickOnOrderButton(String orderButton) {
        switch (orderButton) {
            case "Top":
                driver.findElement(orderButtonTop).click();
                break;
            case "Bottom":
                WebElement element = driver.findElement(orderButtonBottom);
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
                driver.findElement(orderButtonBottom).click();
                break;
        }

        new MainPage(driver);
    }


}
