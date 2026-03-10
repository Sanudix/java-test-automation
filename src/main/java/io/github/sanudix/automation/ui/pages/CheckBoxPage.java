package io.github.sanudix.automation.ui.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.empty;
import static com.codeborne.selenide.CollectionCondition.textsInAnyOrder;
import static com.codeborne.selenide.Condition.attributeMatching;
import static com.codeborne.selenide.Selenide.*;

public class CheckBoxPage {

    private final SelenideElement generalToggle   = $(".rc-tree-switcher.rc-tree-switcher_close");
    private final SelenideElement desktopToggle   = $x("//span[@aria-label='Select Desktop']/preceding-sibling::span[contains(@class,'rc-tree-switcher')]");
    private final SelenideElement documentsToggle = $x("//span[@aria-label='Select Documents']/preceding-sibling::span[contains(@class,'rc-tree-switcher')]");
    private final SelenideElement downloadsToggle = $x("//span[@aria-label='Select Downloads']/preceding-sibling::span[contains(@class,'rc-tree-switcher')]");
    private final SelenideElement workSpaceToggle = $x("//span[@aria-label='Select WorkSpace']/preceding-sibling::span[contains(@class,'rc-tree-switcher')]");
    private final SelenideElement officeToggle    = $x("//span[@aria-label='Select Office']/preceding-sibling::span[contains(@class,'rc-tree-switcher')]");

    private final SelenideElement desktopCheck   = $("span[aria-label='Select Desktop']");
    private final SelenideElement downloadsCheck = $("span[aria-label='Select Downloads']");
    private final SelenideElement wordFileCheck  = $("span[aria-label='Select Word File.doc']");
    private final SelenideElement angularCheck   = $("span[aria-label='Select Angular']");
    private final SelenideElement publicCheck    = $("span[aria-label='Select Public']");
    private final SelenideElement privateCheck   = $("span[aria-label='Select Private']");
    private final SelenideElement notesCheck     = $("span[aria-label='Select Notes']");

    private final ElementsCollection allResults = $$(".text-success");

    @Step("Раскрыть корневой узел")
    public CheckBoxPage openGeneralDropDownMenu() {
        generalToggle.click();
        return this;
    }

    @Step("Раскрыть Desktop")
    public CheckBoxPage openDropDownDesktop() {
        desktopToggle.click();
        return this;
    }

    @Step("Раскрыть Documents")
    public CheckBoxPage openDropDownDocuments() {
        documentsToggle.click();
        return this;
    }

    @Step("Раскрыть Downloads")
    public CheckBoxPage openDropDownDownloads() {
        downloadsToggle.click();
        return this;
    }

    @Step("Раскрыть WorkSpace")
    public CheckBoxPage openWorkSpace() {
        workSpaceToggle.click();
        return this;
    }

    @Step("Раскрыть Office")
    public CheckBoxPage openOffice() {
        officeToggle.click();
        return this;
    }

    @Step("Выбрать Desktop")
    public CheckBoxPage checkDesktop() {
        desktopCheck.click();
        return this;
    }

    @Step("Выбрать Downloads")
    public CheckBoxPage checkDownloads() {
        downloadsCheck.click();
        return this;
    }

    @Step("Выбрать Angular")
    public CheckBoxPage checkAngular() {
        angularCheck.click();
        return this;
    }

    @Step("Выбрать Public")
    public CheckBoxPage checkPublic() {
        publicCheck.click();
        return this;
    }

    @Step("Выбрать Private")
    public CheckBoxPage checkPrivate() {
        privateCheck.click();
        return this;
    }

    @Step("Выбрать Word File")
    public CheckBoxPage checkWordFile() {
        wordFileCheck.click();
        return this;
    }

    @Step("Выбрать Notes")
    public CheckBoxPage checkNotes() {
        notesCheck.click();
        return this;
    }

    @Step("Проверить: выбраны элементы {expectedItems}")
    public CheckBoxPage checkSelectedItems(String... expectedItems) {
        allResults.shouldHave(textsInAnyOrder(expectedItems));
        return this;
    }

    @Step("Проверить: ни один элемент не выбран")
    public CheckBoxPage checkNoItemsSelected() {
        allResults.shouldBe(empty);
        return this;
    }

    @Step("Проверить: Desktop в состоянии indeterminate")
    public CheckBoxPage checkDesktopIndeterminate() {
        desktopCheck.shouldHave(attributeMatching("class", ".*(indeterminate|half).*"));
        return this;
    }
}
