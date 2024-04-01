package steps;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.assertj.core.api.Assertions;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.back;

public class InputsSteps {
    @Step("Переход на страницу Inputs")
    public void navigateToInputsPage() {
        $x("//a[@href='/inputs']").click();
    }

    @Step("Ввод числа {number} в поле ввода")
    public void enterNumber(String number) {
        SelenideElement input = $x("//input[@type='number']");
        input.sendKeys(number);

    }

    @Step("Получение значения из поля ввода")
    public String getInputValue() {
        return $x("//input[@type='number']")
                .getValue();
    }

    @Step("Проверка значения в поле ввода")
    public void checkInputValue(String expectedValue) {
        String actualValue = getInputValue();
        if (actualValue.equals(expectedValue)) {
            System.out.println("Значение в поле совпадает с ожидаемым: " + actualValue);
        } else {
            System.out.println("Поле доступно только для ввода чисел");
        }
        back();
        Assertions.assertThat(actualValue).isEqualTo(expectedValue);
    }

}
