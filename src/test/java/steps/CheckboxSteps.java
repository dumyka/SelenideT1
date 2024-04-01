package steps;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.assertj.core.api.Assertions;

import static com.codeborne.selenide.Selenide.$x;

public class CheckboxSteps {
    SelenideElement checkbox1 = $x("//input[@type='checkbox'][1]");
    SelenideElement checkbox2 = $x("//input[@type='checkbox'][2]");

    @Step("Переход на страницу Checkbox")
    public void navigateToCheckboxPage() {
        $x("//a[@href='/checkboxes']").click();
    }

    @Step("Проверка состояния элементов на входе")
    public void checkElements() {
        Assertions.assertThat(checkbox1.getAttribute("checked")).isNull();
        Assertions.assertThat(checkbox2.getAttribute("checked")).isEqualTo("true");
    }

    @Step("Нажатие на чекбокс {checkboxName}")
    public void clickCheckbox(String checkboxName) {
        if (checkboxName.equals("checkbox 1")) {
            checkbox1.click();
        } else if (checkboxName.equals("checkbox 2")) {
            checkbox2.click();
        }
    }

    @Step("Проверка состояния чекбоксов после нажатия")
    public void checkCheckboxStateAfterClick(String checkboxName) {
        if (checkboxName.equals("checkbox 1")) {
            Assertions.assertThat(checkbox1.getAttribute("checked")).isEqualTo("true");
            Assertions.assertThat(checkbox2.getAttribute("checked")).isEqualTo("true");
        } else if (checkboxName.equals("checkbox 2")) {
            Assertions.assertThat(checkbox1.getAttribute("checked")).isNull();
            Assertions.assertThat(checkbox2.getAttribute("checked")).isNull();
        }
    }
}
