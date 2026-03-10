package io.github.sanudix.automation.ui.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class UploadDownloadPage {

    private final SelenideElement downloadBtn  = $("#downloadButton");
    private final SelenideElement uploadInput  = $("#uploadFile");
    private final SelenideElement uploadedPath = $("#uploadedFilePath");

    @Step("Скачать файл")
    public File download() throws IOException {
        String href = downloadBtn.getAttribute("href");
        String base64 = href.split(",")[1];

        byte[] bytes = Base64.getDecoder().decode(base64);

        File file = new File("build/downloads/downloaded.jpg");
        file.getParentFile().mkdirs();
        Files.write(file.toPath(), bytes);

        return file;
    }

    @Step("Загрузить файл")
    public UploadDownloadPage upload(File file) {
        uploadInput.uploadFile(file);
        return this;
    }

    @Step("Проверить: файл успешно скачан")
    public UploadDownloadPage checkFileDownloaded(File file) {
        if (!file.exists()) {
            throw new AssertionError("Файл должен существовать: " + file.getPath());
        }
        if (file.length() == 0) {
            throw new AssertionError("Файл не должен быть пустым: " + file.getPath());
        }
        return this;
    }

    @Step("Проверить: путь загруженного файла содержит '{expectedFileName}'")
    public UploadDownloadPage checkUploadedPath(String expectedFileName) {
        uploadedPath
                .shouldBe(visible)
                .shouldHave(text(expectedFileName));
        return this;
    }

    @Step("Проверить: кнопка Download отображается с корректными атрибутами")
    public UploadDownloadPage checkDownloadButtonVisible() {
        downloadBtn
                .shouldBe(visible)
                .shouldHave(text("Download"))
                .shouldHave(attribute("download", "sampleFile.jpeg"));
        return this;
    }

    @Step("Проверить: путь не отображается до загрузки")
    public UploadDownloadPage checkUploadedPathNotVisible() {
        uploadedPath.shouldNotBe(visible);
        return this;
    }
}
