package ru.yandex.praktikum;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.pages.AboutRent;
import ru.yandex.praktikum.pages.ConfirmOrderPane;
import ru.yandex.praktikum.pages.MainPage;
import ru.yandex.praktikum.pages.OrderForm;

import static org.junit.Assert.assertTrue;
import static ru.yandex.praktikum.EnvConfig.BASE_URL;

@RunWith( Parameterized.class )

public class OrderTest {
    private final DriverRule driverRule = new DriverRule();
    private final String orderButton;
    private final String name;
    private final String surname;
    private final String address;
    private final String stationId;
    private final String phoneNumber;
    private final String dayNumber;
    private final String rentTerm;
    private final String scooterColour;
    private final String comment;


    public OrderTest(String orderButton, String name, String surname, String address, String stationId, String phoneNumber, String dayNumber, String rentTerm, String scooterColour, String comment) {
        this.orderButton = orderButton;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.stationId = stationId;
        this.phoneNumber = phoneNumber;
        this.dayNumber = dayNumber;
        this.rentTerm = rentTerm;
        this.scooterColour = scooterColour;
        this.comment = comment;
    }

    @Rule
    public DriverRule driver = new DriverRule();


    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][]{
                {"Top", "Гарри", "Поттер", "Тисовая улица", "10", "+79152776620", "017", "сутки", "black", "Алохомора"},
                {"Bottom", "Фродо", "Беггинс", "Торба-на-круче", "12", "+79266111436", "018", "двое суток", "grey", "Скажи: 'Друг' и войди"}
        };
    }


    @Test
    public void orderTest() {
        WebDriver driver = driverRule.getDriver();
        driver.get(BASE_URL);

        new MainPage(driver)
                .waitForHeaderToLoad()
                .acceptCookies()
                .clickOnOrderButton(orderButton);

        new OrderForm(driver)
                .fillInPersonalInfo(name, surname, address, stationId, phoneNumber);

        new AboutRent(driver)
                .fillInAboutRentInfo(dayNumber, rentTerm, scooterColour, comment);

        Boolean actual = new ConfirmOrderPane(driver)
                .clickYesButton()
                .isScooterOrdered();

        assertTrue("Заказ оформлен", actual);

        new DriverRule()
                .after();

    }

}
