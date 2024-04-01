package steps;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.assertj.core.api.Assertions;

import static com.codeborne.selenide.Selenide.$x;

public class DropdownSteps {
    SelenideElement dropdown = $x("//select[@id='dropdown']");

    @Step("Переход на страницу dropdown")
    public void navigateToDropdownPage() {
        $x("//a[@href='/dropdown']").click();
    }

    @Step("Выбор элемента {value} в dropdown")
    public void selectElement(String value) {
        dropdown.selectOptionByValue(value);
    }

    @Step("Проверка, что выбранный {elementName} отображается в дропдауне")
    public void checkElement(String elementName) {
        Assertions.assertThat(dropdown.getSelectedOptionText()).isEqualTo(elementName);
        System.out.println("Text element: " + dropdown.getSelectedOptionText());
    }
}
