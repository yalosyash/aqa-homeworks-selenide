import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

class TestV1 {
    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    private String getDate(int countOfDays) {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return currentDate.plusDays(countOfDays).format(pattern);
    }

    private void cleanForm(SelenideElement element) {
        $(element).sendKeys(Keys.chord(Keys.CONTROL, "a"));
        $(element).sendKeys(Keys.BACK_SPACE);
    }

    private final SelenideElement inputCity = $("[data-test-id=city] .input__control");
    private final ElementsCollection selectOfCity = $$(".menu-item");
    private final SelenideElement inputDate = $("[data-test-id=date] .input__control");
    private final SelenideElement inputName = $("[data-test-id=name] .input__control");
    private final SelenideElement inputPhone = $("[data-test-id=phone] .input__control");
    private final SelenideElement checkboxAgreement = $("[data-test-id=agreement]");
    private final SelenideElement submit = $(".button_theme_alfa-on-white");
    private final SelenideElement calendar = $(".icon-button_theme_alfa-on-white");
    private final ElementsCollection weekRight = $$(".calendar__arrow");
    private final SelenideElement selectDate = $("[data-day]");
    private final SelenideElement notification = $("[data-test-id=notification]");
    private final SelenideElement notificationData = $("[class=notification__content]");

    //Задание 1 --------------------------------------------------------------------------------------------------------
    @Test
    void shouldSuccess() {

        $(inputCity).setValue("Москва");
        cleanForm(inputDate);
        $(inputDate).setValue(getDate(4));
        $(inputName).setValue("Иванов Иван");
        $(inputPhone).setValue("+12345678901");
        $(checkboxAgreement).click();
        $(submit).click();
        $(notification).shouldBe(visible, Duration.ofSeconds(15));
        $(notificationData).shouldHave(exactText("Встреча успешно забронирована на " + getDate(4)));
    }

    //Задание 2 пока откладывается --------------------------------------------------------------------------------------------------------
//    @Test
//    void shouldSuccessWithHint() {
//
//        $(inputCity).setValue("бу");
//        $$(selectOfCity).find(exactText("Оренбург")).click();
//        $(calendar).click();
//        $$(weekRight).get(3).click();
//        $(selectDate).click();
//        $(inputName).setValue("Иванов Иван");
//        $(inputPhone).setValue("+12345678901");
//        $(checkboxAgreement).click();
//        $(submit).click();
//        $(notification).shouldBe(visible, Duration.ofSeconds(15));
//        $(notificationData).shouldHave(exactText("Встреча успешно забронирована на " + getDate(4)));
//    }
}