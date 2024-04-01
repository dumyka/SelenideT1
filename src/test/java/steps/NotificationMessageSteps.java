package steps;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class NotificationMessageSteps {
    @Step("Переход на страницу NotificationMessage")
    public void navigateToNotificationMessagePage() {
        $x("//a[@href='/notification_message']").click();
    }

    @Step("Клик на кнопку 'Click here'")
    public void clickNotificationButton() {
        SelenideElement buttonNotificationMessage = $x("//a[@href='/notification_message']");
        buttonNotificationMessage.click();
    }

    @Step("Получение текста сообщения")
    public String getMessageText() {
        SelenideElement textActionSuccessful = $x("//div[@id='flash'][contains(text(), 'Action successful')]");
        return textActionSuccessful.getText();
    }
}
