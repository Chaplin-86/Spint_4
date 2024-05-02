package ru.yandex.praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.praktikum.EnvConfig;

import java.time.Duration;

import static ru.yandex.praktikum.EnvConfig.DEFAULT_TIMEOUT;

public class ConfirmOrderPane {

    private final WebDriver driver;

    private final By yesButton = By.xpath(".//button[text()='Да']");
    private final By orderModalPane = By.className("Order_Modal__YZ-d3");
    private final By scooterIsOrdered = By.xpath(".//div[text()='Заказ оформлен']");

    public ConfirmOrderPane(WebDriver driver) {
        this.driver = driver;
    }


    public ConfirmOrderPane  clickYesButton() {
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(orderModalPane));
        driver.findElement(yesButton).click();

        return this;
    }

    public boolean isScooterOrdered() {
        return driver.findElement(scooterIsOrdered).isDisplayed();
    }
}



