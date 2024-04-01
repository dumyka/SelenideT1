package steps;

import io.qameta.allure.Step;
import org.assertj.core.api.Assertions;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$x;

public class DisappearingElementsSteps {
    @Step("Переход на страницу Disappearing Elements")
    public void navigateToDisappearingElementsPage(){
        $x("//a[@href='/disappearing_elements']").click();
    }

    @Step("Проверка наличия 5 элементов на странице")
    public void checkElementsCount(int expectedCount) {
        Assertions.assertThat($$("ul>li").size()).isEqualTo(expectedCount);
    }
}
