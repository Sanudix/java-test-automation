package io.github.sanudix.automation.api;

import io.github.sanudix.automation.api.models.User;
import io.github.sanudix.automation.api.models.UserListResponse;
import io.github.sanudix.automation.base.BaseApiTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@Epic("reqres.in API")
@Feature("GET /users — список пользователей")
@DisplayName("Тесты списка пользователей")
public class UserListTests extends BaseApiTest {

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Страница 1 возвращает 6 пользователей")
    void page1Returns6Users() {
        UserListResponse response = given()
                .queryParam("page", 1)
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .extract().as(UserListResponse.class);

        assertThat(response.getData()).hasSize(6);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Страница 2 возвращает данные")
    void page2ReturnsData() {
        UserListResponse response = given()
                .queryParam("page", 2)
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .extract().as(UserListResponse.class);

        assertThat(response.getData()).isNotEmpty();
        assertThat(response.getPage()).isEqualTo(2);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("total_pages совпадает с реальным количеством страниц")
    void totalPagesIsCorrect() {
        UserListResponse page1 = given()
                .queryParam("page", 1)
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .extract().as(UserListResponse.class);

        int expectedTotalPages = (int) Math.ceil((double) page1.getTotal() / page1.getPerPage());
        assertThat(page1.getTotalPages()).isEqualTo(expectedTotalPages);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("У каждого пользователя есть id, email, first_name, last_name, avatar")
    void everyUserHasRequiredFields() {
        UserListResponse response = given()
                .queryParam("page", 1)
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .extract().as(UserListResponse.class);

        for (User user : response.getData()) {
            assertThat(user.getId()).isPositive();
            assertThat(user.getEmail()).isNotBlank();
            assertThat(user.getFirstName()).isNotBlank();
            assertThat(user.getLastName()).isNotBlank();
            assertThat(user.getAvatar()).isNotBlank();
        }
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Email каждого пользователя содержит @reqres.in")
    void everyUserEmailContainsDomain() {
        UserListResponse response = given()
                .queryParam("page", 1)
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .extract().as(UserListResponse.class);

        assertThat(response.getData())
                .allMatch(u -> u.getEmail().contains("@reqres.in"));
    }

    @Test
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Несуществующая страница возвращает пустой data[]")
    void nonExistentPageReturnsEmptyData() {
        UserListResponse response = given()
                .queryParam("page", 999)
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .extract().as(UserListResponse.class);

        assertThat(response.getData()).isEmpty();
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("per_page соответствует количеству элементов в data")
    void perPageMatchesDataSize() {
        UserListResponse response = given()
                .queryParam("page", 1)
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .extract().as(UserListResponse.class);

        assertThat(response.getData()).hasSize(response.getPerPage());
    }

    @Test
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Avatar каждого пользователя — валидный URL с https://")
    void everyUserAvatarIsValidUrl() {
        UserListResponse response = given()
                .queryParam("page", 1)
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .extract().as(UserListResponse.class);

        assertThat(response.getData())
                .allMatch(u -> u.getAvatar().startsWith("https://"));
    }
}
