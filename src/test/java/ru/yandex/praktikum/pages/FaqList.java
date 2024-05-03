package ru.yandex.praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.praktikum.EnvConfig;

import java.time.Duration;

public class FaqList {

    private final WebDriver driver;
    private final By cookieButton = By.id("rcc-confirm-button");
    private final String questionsIdPrefix = "accordion__heading-";
    private final String answersIdPrefix = "accordion__panel-";

    public FaqList(WebDriver driver) {
        this.driver = driver;
    }

    public FaqList open() {
        driver.get(EnvConfig.BASE_URL);

        return this;
    }

//    public FaqList acceptCookies() {
//        waitForCookiesFloater();
//        driver.findElement(cookieButton).click();
//        waitForCookiesFloaterToDisappear();
//
//        return this;
//    }
//
//    private void waitForCookiesFloater() {
//        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
//                .until(ExpectedConditions.visibilityOfElementLocated(cookieButton));
//    }
//
//    private void waitForCookiesFloaterToDisappear() {
//        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
//                .until(ExpectedConditions.invisibilityOfElementLocated(cookieButton));
//    }

    public FaqList clickOnQuestion(String id) {
        driver.findElement(By.id(questionsIdPrefix + id)).click();

        return this;
    }

    public FaqList waitForAnswer(String id, String answerText) {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id(answersIdPrefix + id)));

        return this;
    }

    public FaqList checkAnswerIsInvisible(String id) {
        assert !driver.findElement(By.id(answersIdPrefix + id)).isDisplayed();

        return this;
    }

}

