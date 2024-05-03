package ru.yandex.praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.praktikum.EnvConfig;

import java.time.Duration;

import static ru.yandex.praktikum.EnvConfig.DEFAULT_TIMEOUT;

public class AboutRent {

    private final WebDriver driver;
    private final By aboutRentHeading = By.className("Order_Header__BZXOb");
    private final By nextButton = By.xpath(".//button[text()='Далее']");
    private final By deliveryDateField = By.xpath(".//input[contains(@placeholder, '* Когда привезти самокат')]");
    private final By calendar = By.className("react-datepicker__month-container");

    private final By rentTermField = By.className("Dropdown-placeholder");
    private final By dropdownMenu = By.className("Dropdown-menu");

    private final By colourBlack = By.id("black");

    private final By colourGray = By.id("grey");
    private final By commentForCourierField = By.xpath(".//input[contains(@placeholder, 'Комментарий для курьера')]");
    private final By orderButton = By.xpath(".//div[@class = 'Order_Buttons__1xGrp']/button[text()='Заказать']");

    public AboutRent(WebDriver driver) {

        this.driver = driver;
    }

    public AboutRent waitForAboutRentPageLoading() {
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(aboutRentHeading));

        return this;
    }

    public AboutRent enterDate(String dayNumber) {
        driver.findElement(deliveryDateField).click();
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(calendar));

        driver.findElement(By.xpath(".//div[@class = 'react-datepicker__day react-datepicker__day--" + dayNumber + "']")).click();

        return this;
    }

    public AboutRent enterRentTerm(String rentTerm) {
        driver.findElement(rentTermField).click();
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(dropdownMenu));
        driver.findElement(By.xpath(".//div[text()='" + rentTerm + "']")).click();

        return this;
    }


    public AboutRent pickColour(String scooterColour) {
        driver.findElement(By.id(scooterColour)).click();

        return this;
    }


    public AboutRent enterComment(String comment) {
        driver.findElement(commentForCourierField).sendKeys(comment);

        return this;
    }

    public AboutRent clickOnOrderButton() {
        driver.findElement(orderButton).click();


        return this;
    }

    public void fillInAboutRentInfo(String dayNumber, String rentTerm, String scooterColour, String comment) {
        waitForAboutRentPageLoading();
        enterDate(dayNumber);
        enterRentTerm(rentTerm);
        pickColour(scooterColour);
        enterComment(comment);
        clickOnOrderButton();

    }


}
