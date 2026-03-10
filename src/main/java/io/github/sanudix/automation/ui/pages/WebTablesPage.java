package io.github.sanudix.automation.ui.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class WebTablesPage {

    private final SelenideElement addBtn    = $("#addNewRecordButton");
    private final SelenideElement submitBtn = $("#submit");

    private final SelenideElement searchInput     = $("#searchBox");
    private final SelenideElement firstNameInput  = $("#firstName");
    private final SelenideElement lastNameInput   = $("#lastName");
    private final SelenideElement emailInput      = $("#userEmail");
    private final SelenideElement ageInput        = $("#age");
    private final SelenideElement salaryInput     = $("#salary");
    private final SelenideElement departmentInput = $("#department");

    private final ElementsCollection rows   = $$("table tbody tr");

    @Step("Открыть форму добавления записи")
    public WebTablesPage openAddForm() {
        addBtn.click();
        return this;
    }

    @Step("Заполнить форму: {firstName} {lastName}, {email}")
    public WebTablesPage fillForm(String firstName, String lastName, String email,
                                  String age, String salary, String department) {
        firstNameInput.setValue(firstName);
        lastNameInput.setValue(lastName);
        emailInput.setValue(email);
        ageInput.setValue(age);
        salaryInput.setValue(salary);
        departmentInput.setValue(department);
        return this;
    }

    @Step("Отправить форму")
    public WebTablesPage submitForm() {
        submitBtn.click();
        return this;
    }

    @Step("Поиск: '{query}'")
    public WebTablesPage search(String query) {
        searchInput.setValue(query);
        return this;
    }

    @Step("Проверить: запись с текстом '{searchText}' существует")
    public WebTablesPage checkRowExists(String searchText) {
        rows.filterBy(text(searchText)).shouldHave(size(1));
        return this;
    }

    @Step("Проверить: запись с '{searchText}' содержит данные")
    public WebTablesPage checkRowContains(String searchText, String... expectedTexts) {
        SelenideElement row = rows.filterBy(text(searchText)).first();
        for (String expected : expectedTexts) {
            row.shouldHave(text(expected));
        }
        return this;
    }

    @Step("Проверить: таблица ничего не показывает")
    public WebTablesPage checkNoRowsFound() {
        rows.shouldHave(size(0));
        return this;
    }

    @Step("Проверить: в таблице {expectedCount} записей с данными")
    public WebTablesPage checkTotalDataRows(int expectedCount) {
        rows.filterBy(text("@")).shouldHave(size(expectedCount));
        return this;
    }
}
