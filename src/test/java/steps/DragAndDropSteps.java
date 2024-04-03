package steps;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.interactions.Actions;

import static com.codeborne.selenide.Selenide.$x;

public class DragAndDropSteps {
    SelenideElement elementA = $x("//div[@id='column-a']");
    SelenideElement elementB = $x("//div[@id='column-b']");

    @Step("Переход на страницу Drag and drop")
    public void navigateToDragAndDropPage(){
        $x("//a[@href='/drag_and_drop']").click();
    }

    @Step("Ператащить элемент А на элемент В")
    public void dragAndDropElement(){
        Actions actions = new Actions(WebDriverRunner.getWebDriver());
        actions.clickAndHold(elementA).moveToElement(elementB).release().build().perform();
    }

    @Step("Проверка, что элементы А и В поменялись местами")
    public void verifyElementsSwapped(){
        Assertions.assertThat(elementA.text()).isEqualTo("B");
        Assertions.assertThat(elementB.text()).isEqualTo("A");
    }
}
