package io.github.sanudix.automation.ui;

import io.github.sanudix.automation.base.BaseWebTest;
import io.github.sanudix.automation.ui.pages.UploadDownloadPage;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.File;

@Epic("Elements")
@Feature("Upload and Download")
@DisplayName("Elements → Upload and Download")
public class UploadDownloadTest extends BaseWebTest {

    @Test
    @Story("Скачивание файла")
    @Severity(SeverityLevel.CRITICAL)
    @Tag("smoke")
    @DisplayName("Файл успешно скачивается")
    void downloadFile() throws Exception {
        openPage("upload-download");
        UploadDownloadPage page = new UploadDownloadPage();

        File downloaded = page.download();
        page.checkFileDownloaded(downloaded);
    }

    @Test
    @Story("Загрузка файла")
    @Severity(SeverityLevel.CRITICAL)
    @Tag("smoke")
    @DisplayName("Файл успешно загружается")
    void uploadFile() {
        File testFile = new File("src/test/resources/testfile.txt");
        openPage("upload-download");
        new UploadDownloadPage()
                .upload(testFile)
                .checkUploadedPath("testfile.txt");
    }

    @Test
    @Story("Кнопка Download")
    @Severity(SeverityLevel.MINOR)
    @Tag("regression")
    @DisplayName("Кнопка Download отображается с корректными атрибутами")
    void downloadButtonIsVisible() {
        openPage("upload-download");
        new UploadDownloadPage()
                .checkDownloadButtonVisible();
    }

    @Test
    @Story("Состояние до загрузки")
    @Severity(SeverityLevel.MINOR)
    @Tag("regression")
    @DisplayName("Путь не отображается до загрузки файла")
    void noPathBeforeUpload() {
        openPage("upload-download");
        new UploadDownloadPage()
                .checkUploadedPathNotVisible();
    }
}
