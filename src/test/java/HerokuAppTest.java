import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;


public class HerokuAppTest {

    @BeforeEach
    void setUp() {
        open("https://the-internet.herokuapp.com/");
    }

    @Test
    public void checkboxesTest() {
        $x("//a[@href='/checkboxes']").click();
        $x("//input[@type='checkbox'][1]").click();
        $x("//input[@type='checkbox'][2]").click();
        System.out.println("Checkbox 1 checked: " + $x("//input[@type='checkbox'][1]").isSelected());
        System.out.println("Checkbox 2 checked: " + $x("//input[@type='checkbox'][2]").isSelected());
    }

    @Test
    public void DropdownTest() {
        $x("//a[@href='/dropdown']").click();
        SelenideElement dropdown = $x("//select[@id='dropdown']");
        dropdown.selectOptionByValue("1");
        System.out.println("Text element: " + dropdown.getSelectedOptionText());
        dropdown.selectOptionByValue("2");
        System.out.println("Text element: " + dropdown.getSelectedOptionText());
    }

    @Test
    public void disappearingElementsTest() {
        $x("//a[@href='/disappearing_elements']").click();
        int count = 0;
        int maxAttempts = 10;

        while (count != 5 && maxAttempts > 0) {
            count = $$("ul>li").size();
            if (count != 5) {
                refresh();
                maxAttempts--;
            }

            if (count == 5) {
                System.out.println("Тест пройден: на странице отображены 5 элементов.");
            } else {
                System.out.println("Тест провален: не удалось достичь отображения 5 элементов за 10 попыток.");
            }
        }
    }

    @Test
    public void InputsTest() {
        $x("//a[@href='/inputs']").click();
        SelenideElement input = $x("//input[@type='number']");
        input.sendKeys("777");
        System.out.println(input.getValue());
    }

    @Test
    public void HoversTest() {
        $x("//a[@href='/hovers']").click();

        for (int i = 1; i <= 3; i++) {
            SelenideElement avatar = $x("//div[@class='figure'][" + i + "]");
            avatar.hover();
            SelenideElement textAvatar = $x("//h5[text()='name: user" + i + "']");
            System.out.println(textAvatar.getText());
        }
    }

    @Test
    public void NotificationMessage() {
        SelenideElement buttonNotificationMessage = $x("//a[@href='/notification_message']");
        SelenideElement textActionSuccessful = $x("//div[@id='flash'][contains(text(), 'Action successful')]");
        SelenideElement buttonCloseMessage = $x("//a[@class= 'close']");

        buttonNotificationMessage.click();
        do {
            buttonNotificationMessage.click();
            if (!textActionSuccessful.exists()) {
                buttonCloseMessage.click();
            }
        } while (!textActionSuccessful.exists());
    }

    @Test
    public void addRemoveElementsTest() {
        SelenideElement buttonAddElement = $x("//button[@onclick='addElement()']");
        SelenideElement buttonDeleteElement = $x("//button[@onclick='deleteElement()']");
        SelenideElement nameElement = $x("//button[text()='Delete']");

        $x("//a[@href='/add_remove_elements/']").click();
        for (int i = 1; i <= 5; i++) {
            buttonAddElement.click();
            System.out.println("Element text: " + nameElement.getText());
        }
        for (int i = 0; i < 3; i++) {
            buttonDeleteElement.click();
        }
        int remainingDeleteButtons = $$x("//button[text()='Delete']").size();
        System.out.println("Remaining Delete buttons: " + remainingDeleteButtons);

        for (int i = 1; i <= remainingDeleteButtons; i++) {
            System.out.println("Element text: " + nameElement.getText());
        }
    }

    @Test
    public void statusCodesTest() {
        SelenideElement buttonStatusCode = $x("//a[@href='/status_codes']");
        buttonStatusCode.click();

        String[] statusCodes = {"200", "301", "404", "500"};

        for (String code : statusCodes) {
            navigateToStatusCode(code);
            System.out.println(getStatusCodeText());
            buttonStatusCode.click();
        }
    }

    void navigateToStatusCode(String code) {
        $x("//a[@href='status_codes/" + code + "']").click();
    }

    String getStatusCodeText() {
        return $x("//p").getText();
    }
}




