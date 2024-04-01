package steps;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;

public class AddRemoveElementsSteps {

    @Step("Переход на страницу AddRemoteElements")
    public void navigateToAddRemoveElementsPage(){
        $x("//a[@href='/add_remove_elements/']").click();
    }

    @Step("Добавление элементов: {count}")
    public void addElements(int count) {
        SelenideElement buttonAddElement = $x("//button[@onclick='addElement()']");
        for (int i = 0; i < count; i++) {
            buttonAddElement.click();
        }
    }

    @Step("Удаление элементов: {count}")
    public void removeElements(int count) {
        SelenideElement buttonDeleteElement = $x("//button[@onclick='deleteElement()']");
        for (int i = 0; i < count; i++) {
            buttonDeleteElement.click();
        }
    }

    @Step("Проверка количества оставшихся кнопок удаления")
    public int getRemainingDeleteButtonsCount() {
        return $$x("//button[text()='Delete']").size();
    }

    @Step("Нажать кнопку Назад в браузере")
    public void navigateBack() {
        back();
    }

}
