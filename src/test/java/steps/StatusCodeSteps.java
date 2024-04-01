package steps;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class StatusCodeSteps {

    @Step("Переход на страницу Status code")
    public void navigateToStatusCodePage() {
        $x("//a[@href='/status_codes']").click();
    }

    @Step("Переход на страницу со статус кодом {code}")
    public void navigateToStatusCode(String code) {
        $x("//a[@href='status_codes/" + code + "']").click();
    }

    @Step("Проверка, что текст кода существует")
    public void verifyStatusCodeTextIsPresent() {
        $x("//p").shouldBe(Condition.visible);
    }

    @Step("Получение текста статус кода")
    public String getStatusCodeText() {
        return $x("//p").getText();
    }
}
