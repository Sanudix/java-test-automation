package io.github.sanudix.automation.ui.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class TextBoxPage {

    private final SelenideElement fullName         = $("input[placeholder='Full Name']");
    private final SelenideElement email            = $("input[placeholder='name@example.com']");
    private final SelenideElement currentAddress   = $("textarea[placeholder='Current Address']");
    private final SelenideElement permanentAddress = $("textarea#permanentAddress.form-control");
    private final SelenideElement submit           = $("button#submit.btn");

    private final SelenideElement outputBlock            = $("#output");
    private final SelenideElement outputName             = $("#name");
    private final SelenideElement outputEmail            = $("#email");
    private final SelenideElement outputCurrentAddress   = $("#currentAddress.mb-1");
    private final SelenideElement outputPermanentAddress = $("#permanentAddress.mb-1");

    @Step("Заполнить форму и отправить")
    public TextBoxPage insertData() {
        fullName.setValue("Иван Петров");
        email.setValue("ivan@example.com");
        currentAddress.setValue("Москва, ул. Тестовая, д. 1");
        permanentAddress.setValue("Санкт-Петербург, пр. Невский, д. 10");
        submit.click();
        return this;
    }

    @Step("Ввести email '{emailValue}' и отправить")
    public TextBoxPage insertDataWithEmail(String emailValue) {
        email.setValue(emailValue);
        submit.click();
        return this;
    }

    @Step("Ввести имя '{name}' и отправить")
    public TextBoxPage insertDataWithName(String name) {
        fullName.setValue(name);
        submit.click();
        return this;
    }

    @Step("Ввести адрес и отправить")
    public TextBoxPage insertDataWithCurrentAddress(String address) {
        currentAddress.setValue(address);
        submit.click();
        return this;
    }

    @Step("Отправить пустую форму")
    public TextBoxPage submitEmpty() {
        submit.click();
        return this;
    }

    @Step("Проверить: блок вывода не отображается")
    public TextBoxPage checkOutputNotVisible() {
        outputBlock.shouldNotBe(visible);
        return this;
    }

    @Step("Проверить: блок вывода отображается")
    public TextBoxPage checkOutputVisible() {
        outputBlock.shouldBe(visible);
        return this;
    }

    @Step("Проверить: имя в выводе — '{expectedName}'")
    public TextBoxPage checkOutputName(String expectedName) {
        outputName.shouldHave(text(expectedName));
        return this;
    }

    @Step("Проверить: email в выводе — '{expectedEmail}'")
    public TextBoxPage checkOutputEmail(String expectedEmail) {
        outputEmail.shouldHave(text(expectedEmail));
        return this;
    }

    @Step("Проверить: Current Address в выводе — '{expectedAddress}'")
    public TextBoxPage checkOutputCurrentAddress(String expectedAddress) {
        outputCurrentAddress.shouldHave(text(expectedAddress));
        return this;
    }

    @Step("Проверить: Permanent Address в выводе — '{expectedAddress}'")
    public TextBoxPage checkOutputPermanentAddress(String expectedAddress) {
        outputPermanentAddress.shouldHave(text(expectedAddress));
        return this;
    }
}