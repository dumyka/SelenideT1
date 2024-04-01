package tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;
import steps.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;


public class HerokuAppTest {
    @BeforeEach
    void setUp() {
        open("https://the-internet.herokuapp.com/");
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @ParameterizedTest
    @ValueSource(strings = {"checkbox 1", "checkbox 2", "checkbox 2", "checkbox 1"})
    public void checkboxesTest(String checkboxName) {
        CheckboxSteps checkboxSteps = new CheckboxSteps();
        checkboxSteps.navigateToCheckboxPage();
        // Проверка состояния чекбоксов перед нажатием
        checkboxSteps.checkElements();
        checkboxSteps.clickCheckbox(checkboxName);
        checkboxSteps.checkCheckboxStateAfterClick(checkboxName);
    }

    @Test
    public void DropdownTest() {
        DropdownSteps dropdownSteps = new DropdownSteps();
        dropdownSteps.navigateToDropdownPage();
        dropdownSteps.selectElement("1");
        dropdownSteps.checkElement("Option 1");
        dropdownSteps.selectElement("2");
        dropdownSteps.checkElement("Option 2");
    }

    @RepeatedTest(10)
    public void disappearingElementsTest() {
        DisappearingElementsSteps disappearingElementsSteps = new DisappearingElementsSteps();
        disappearingElementsSteps.navigateToDisappearingElementsPage();
        disappearingElementsSteps.checkElementsCount(5);
        System.out.println("Тест пройден: на странице отображены 5 элементов.");
    }

    @TestFactory
    public List<DynamicTest> InputsTest() {
        List<String> inputValues = Arrays.asList("111", "222", "333", "777" , "dada", "666", "455"," 123", "456 ", "abc", "!@#");

        List<DynamicTest> dynamicTests = new ArrayList<>();
        InputsSteps steps = new InputsSteps();

        for (String value : inputValues) {
            final String inputValue = value.trim();
            String testName = "Test input value: " + inputValue;

            DynamicTest dynamicTest = DynamicTest.dynamicTest(testName, () -> {

                steps.navigateToInputsPage();
                steps.enterNumber(inputValue);
                steps.checkInputValue(inputValue);
            });

            dynamicTests.add(dynamicTest);
        }

        return dynamicTests;
    }



    @ParameterizedTest
    @EnumSource(HoverElement.class)
    public void HoversTest(HoverElement avatar) {
        HoversSteps hoversSteps = new HoversSteps();
        hoversSteps.navigateToHoverPage();
        hoversSteps.hoverAndCheckText(avatar);
    }

    @RepeatedTest(10)
    public void NotificationMessage() {
        NotificationMessageSteps notificationMessageSteps = new NotificationMessageSteps();
        notificationMessageSteps.navigateToNotificationMessagePage();
        notificationMessageSteps.clickNotificationButton();
        String message = notificationMessageSteps.getMessageText();

        // Проверяем, содержит ли сообщение текст 'Action successful', если нет - тест проваливается
        if (!message.contains("Action successful")) {
            fail("Уведомление не содержит статус 'Action successful'");
        }
    }

    @TestFactory
    public List<DynamicTest> addRemoveElementsTest() {
        AddRemoveElementsSteps steps = new AddRemoveElementsSteps();
        List<DynamicTest> dynamicTests = new ArrayList<>();

        // Тест на 2 добавления, 1 удаление
        dynamicTests.add(DynamicTest.dynamicTest("2 added, 1 remove", () -> {
            steps.navigateToAddRemoveElementsPage();
            steps.addElements(2);
            assertThat(steps.getRemainingDeleteButtonsCount())
                    .as("Неправильное количество кнопок после добавления")
                    .isEqualTo(2);
            steps.removeElements(1);
            assertThat(steps.getRemainingDeleteButtonsCount())
                    .as("Неправильное количество кнопок после удаления")
                    .isEqualTo(1);
        }));

        // Тест на 5 добавлений, 2 удаления
        dynamicTests.add(DynamicTest.dynamicTest("5 added, 2 remove", () -> {
            steps.navigateBack();
            steps.navigateToAddRemoveElementsPage();
            steps.addElements(5);
            assertThat(steps.getRemainingDeleteButtonsCount())
                    .as("Неправильное количество кнопок после добавления")
                    .isEqualTo(5);
            steps.removeElements(2);
            assertThat(steps.getRemainingDeleteButtonsCount())
                    .as("Неправильное количество кнопок после удаления")
                    .isEqualTo(3);
        }));

        // Тест на 1 добавление, 3 удаления
        dynamicTests.add(DynamicTest.dynamicTest("1 added, 3 remove", () -> {
            steps.navigateBack();
            steps.navigateToAddRemoveElementsPage();
            steps.addElements(1);
            assertThat(steps.getRemainingDeleteButtonsCount())
                    .as("Неправильное количество кнопок после добавления")
                    .isEqualTo(1);
            steps.removeElements(3);
            assertThat(steps.getRemainingDeleteButtonsCount())
                    .as("Неправильное количество кнопок после удаления")
                    .isEqualTo(0);
        }));

        return dynamicTests;
    }

    @Test
    public void statusCodesTest() {
        StatusCodeSteps steps = new StatusCodeSteps();
        String[] statusCodes = {"200", "301", "404", "500"};
        steps.navigateToStatusCodePage();
        for (String code : statusCodes) {
            steps.navigateToStatusCode(code);
            steps.verifyStatusCodeTextIsPresent();
            steps.getStatusCodeText();
            steps.navigateToStatusCodePage();
        }
    }
}




