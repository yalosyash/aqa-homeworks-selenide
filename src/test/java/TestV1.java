import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

class TestV1 {
    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    private String getDate () {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return currentDate.plusDays(16).format(pattern);
    }

    private final WebElement inputCity = $("[data-test-id=city] .input__control");
    private final WebElement inputDate = $("[data-test-id=date] .input__control");
    private final WebElement inputName = $("[data-test-id=name] .input__control");
    private final WebElement inputPhone = $("[data-test-id=phone] .input__control");



    //Задание 1 --------------------------------------------------------------------------------------------------------
    @Test
    void shouldOpen() throws InterruptedException {

        $$(".input__control").first().setValue("Москва");

        $("[data-test-id=date] .input__control").sendKeys(Keys.chord(Keys.CONTROL, "a"));
        $("[data-test-id=date] .input__control").sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] .input__control").setValue(getDate());

        $("[data-test-id=name] .input__control").setValue("Иванов Иван");

        $("[data-test-id=phone] .input__control").setValue("+12345678901");

        $("[data-test-id=agreement]").click();
        $(".button_theme_alfa-on-white").click();
        $("[data-test-id=notification]").shouldBe(visible, Duration.ofSeconds(15));
        $(byText("Успешно!")).shouldBe(visible);
    }
}