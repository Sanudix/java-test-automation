package io.github.sanudix.automation.ui;

import io.github.sanudix.automation.base.BaseWebTest;
import io.github.sanudix.automation.ui.pages.CheckBoxPage;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Epic("Elements")
@Feature("Check Box")
@DisplayName("Elements → Check Box")
public class CheckBoxTest extends BaseWebTest {

    // --- Позитивные ---

    @Test
    @Story("Выбор нескольких элементов")
    @Severity(SeverityLevel.CRITICAL)
    @Tag("smoke")
    @DisplayName("Выбор Angular, Public, Private")
    void selectSeveralOptions() {
        openPage("checkbox");
        new CheckBoxPage()
                .openGeneralDropDownMenu()
                .openDropDownDocuments()
                .openWorkSpace()
                .checkAngular()
                .openOffice()
                .checkPublic()
                .checkPrivate()
                .checkSelectedItems("angular", "public", "private");
    }

    @Test
    @Story("Выбор родительской папки")
    @Severity(SeverityLevel.CRITICAL)
    @Tag("smoke")
    @DisplayName("Выбор родительской папки Desktop → выбираются все дочерние")
    void selectParentFolderSelectsAllChildren() {
        openPage("checkbox");
        new CheckBoxPage()
                .openGeneralDropDownMenu()
                .openDropDownDesktop()
                .checkDesktop()
                .checkSelectedItems("desktop", "notes", "commands");
    }

    @Test
    @Story("Выбор без раскрытия")
    @Severity(SeverityLevel.NORMAL)
    @Tag("regression")
    @DisplayName("Выбор папки Downloads без раскрытия")
    void selectFolderWithoutExpand() {
        openPage("checkbox");
        new CheckBoxPage()
                .openGeneralDropDownMenu()
                .checkDownloads()
                .checkSelectedItems("downloads", "wordFile", "excelFile");
    }

    @Test
    @Story("Снятие чекбокса")
    @Severity(SeverityLevel.NORMAL)
    @Tag("regression")
    @DisplayName("Снятие чекбокса Angular")
    void unselectAngular() {
        openPage("checkbox");
        new CheckBoxPage()
                .openGeneralDropDownMenu()
                .openDropDownDocuments()
                .openWorkSpace()
                .checkAngular()
                .checkSelectedItems("angular")
                .checkAngular()
                .checkNoItemsSelected();
    }

    @Test
    @Story("Выбор одного файла")
    @Severity(SeverityLevel.NORMAL)
    @Tag("smoke")
    @DisplayName("Выбор Word File (smoke)")
    void selectWordFile() {
        openPage("checkbox");
        new CheckBoxPage()
                .openGeneralDropDownMenu()
                .openDropDownDownloads()
                .checkWordFile()
                .checkSelectedItems("wordFile");
    }

    // --- Негативные ---

    @Test
    @Story("Пустой выбор")
    @Severity(SeverityLevel.NORMAL)
    @Tag("regression")
    @DisplayName("Ничего не выбрано → результатов нет")
    void nothingSelected() {
        openPage("checkbox");
        new CheckBoxPage()
                .checkNoItemsSelected();
    }

    // --- UI State ---

    @Test
    @Story("Indeterminate состояние")
    @Severity(SeverityLevel.NORMAL)
    @Tag("regression")
    @DisplayName("Unselect one child → parent indeterminate (Desktop)")
    void unselectChildMakesParentIndeterminate() {
        openPage("checkbox");
        new CheckBoxPage()
                .openGeneralDropDownMenu()
                .openDropDownDesktop()
                .checkDesktop()
                .checkNotes()
                .checkDesktopIndeterminate()
                .checkSelectedItems("commands");
    }
}
