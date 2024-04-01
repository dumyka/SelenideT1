package steps;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static org.assertj.core.api.Assertions.assertThat;

public class HoversSteps {

    @Step("Переход на страницу Hover")
    public void navigateToHoverPage(){
        $x("//a[@href='/hovers']").click();
    }

    @Step("Навести курсор на аватар {element} и проверить текст")
    public void hoverAndCheckText(HoverElement element) {
        SelenideElement avatar = getAvatarElement(element);
        avatar.hover();
        SelenideElement textAvatar = getTextAvatarElement(element);
        String expectedText = getExpectedText(element);
        String actualText = textAvatar.getText();
        // Добавить проверку текста
        assertThat(actualText)
                .as("Текст не соответствует элементу " + element.name())
                .isEqualTo(expectedText);
        System.out.println("Текст соответствует элементу " + element.name() + ": " + actualText);
    }

    private SelenideElement getAvatarElement(HoverElement element) {
        int index = element.ordinal() + 1;
        return $x("//div[@class='figure'][" + index + "]");
    }

    private SelenideElement getTextAvatarElement(HoverElement element) {
        int index = element.ordinal() + 1;
        return $x("//h5[text()='name: user" + index + "']");
    }

    private String getExpectedText(HoverElement element) {
        int index = element.ordinal() + 1;
        return "name: user" + index;
    }
}
