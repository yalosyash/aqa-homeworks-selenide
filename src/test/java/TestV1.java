import com.codeborne.selenide.ElementsCollection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

class TestV1 {
    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    private String getDate() {
        return getDate(3);
    }

    private String getDate(int countOfDays) {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return currentDate.plusDays(countOfDays).format(pattern);
    }

    private void cleanForm(WebElement element) {
        $(element).sendKeys(Keys.chord(Keys.CONTROL, "a"));
        $(element).sendKeys(Keys.BACK_SPACE);
    }

    private final WebElement inputCity = $("[data-test-id=city] .input__control");
    private final ElementsCollection selectOfCity = $$(".menu-item");
    private final WebElement inputDate = $("[data-test-id=date] .input__control");
    private final WebElement inputName = $("[data-test-id=name] .input__control");
    private final WebElement inputPhone = $("[data-test-id=phone] .input__control");
    private final WebElement checkboxAgreement = $("[data-test-id=agreement]");
    private final WebElement submit = $(".button_theme_alfa-on-white");
    private final WebElement calendar = $(".icon-button_theme_alfa-on-white");
    private final ElementsCollection weekRight = $$(".calendar__arrow");
    private final WebElement selectDate = $("[data-day]");
    private final WebElement notification = $("[data-test-id=notification]");
    private final WebElement textNotice = $(byText("Успешно!"));

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
        $(textNotice).shouldBe(visible);
    }

    //Задание 2 --------------------------------------------------------------------------------------------------------
    @Test
    void shouldSuccessWithHint() {

        $(inputCity).setValue("бу");
        $$(selectOfCity).get(2).click();
        $(calendar).click();
        $$(weekRight).get(3).click();
        $(selectDate).click();
        $(inputName).setValue("Иванов Иван");
        $(inputPhone).setValue("+12345678901");
        $(checkboxAgreement).click();
        $(submit).click();
        $(notification).shouldBe(visible, Duration.ofSeconds(15));
        $(textNotice).shouldBe(visible);
    }
}