package steps;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.openqa.selenium.interactions.Actions;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.switchTo;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.assertj.core.api.Assertions.assertThat;

public class ContextMenuSteps {

    static final String ALERT_TEXT = "You selected a context menu";

    @Step("Переход на страницу Context menu")
    public void navigateToContextMenuPage() {
        $x("//a[@href='/context_menu']").click();
    }

    @Step("Нажать правой кнопкой мыши по отмеченной области и проверить текст алерта")
    public void rightClickZoneAndVerifyAlertText() {
        SelenideElement zone = $x("//div[@id='hot-spot']");
        Actions actions = new Actions(getWebDriver());
        actions.contextClick(zone).perform();

        Alert alert = switchTo().alert();
        String actualAlertText = alert.getText();
        alert.accept();

        assertThat(actualAlertText).isEqualTo(ALERT_TEXT);
    }
}
