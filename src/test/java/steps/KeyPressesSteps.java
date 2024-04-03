package steps;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$x;

public class KeyPressesSteps {
    private final String[] symbols = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};
    private final Keys[] specialKeys = {Keys.ENTER, Keys.CONTROL, Keys.ALT, Keys.TAB};

    @Step("Переход на страницу Key Presses")
    public void navigateToKeyPressesPage() {
        $x("//a[@href='/key_presses']").click();
    }

    @Step("Ввод значения в поле и проверка, что результат вывода соответствует конкретной клавиши")
    public void inputValueClickAndCheckText() {
        SelenideElement input = $x("//input[@id='target']");
        SelenideElement result = $x("//p[@id='result']");
        for (String symbol : symbols) {
            input.sendKeys(symbol);
            Assertions.assertThat(result.getText()).isEqualTo("You entered: " + symbol.toUpperCase());
            System.out.println(result.getText());
            input.clear();
        }

            for (Keys specialKey : specialKeys) {
                input.sendKeys(specialKey);
                Assertions.assertThat(result.getText().endsWith(specialKey.toString()));
                System.out.println(result.getText());
            }
        }
    }
