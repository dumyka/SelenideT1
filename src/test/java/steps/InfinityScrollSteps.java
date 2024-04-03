package steps;

import io.qameta.allure.Step;
import org.openqa.selenium.interactions.Actions;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class InfinityScrollSteps {

    @Step("Переход на страницу Infinity Scroll")
    public void navigateToInfinityScrollPage() {
        $x("//a[@href='/infinite_scroll']").click();
    }

    @Step("Скролл страницы до текста {text}")
    public void scrollToText(String text) {
        Actions actions = new Actions(getWebDriver());
        actions.scrollToElement($x("//*[contains(text(), '" + text + "')]")).perform();
    }

    @Step("Проверить, что текст {text} присутствует в зоне видимости")
    public void verifyTextIsVisible(String text) {
        $x("//*[contains(text(), '" + text + "')]").shouldBe(visible);
        System.out.println(text);
    }
}
