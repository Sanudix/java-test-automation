package io.github.sanudix.automation.api;

import io.github.sanudix.automation.api.models.User;
import io.github.sanudix.automation.base.BaseApiTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@Epic("reqres.in API")
@Feature("GET /users/{id} — один пользователь")
@DisplayName("Тесты одного пользователя")
public class SingleUserTests extends BaseApiTest {

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Пользователь 2 — данные совпадают с ожидаемыми")
    void user2HasCorrectData() {
        User user = given()
                .when()
                .get("/users/2")
                .then()
                .statusCode(200)
                .extract().jsonPath().getObject("data", User.class);

        assertThat(user.getId()).isEqualTo(2);
        assertThat(user.getEmail()).isEqualTo("janet.weaver@reqres.in");
        assertThat(user.getFirstName()).isEqualTo("Janet");
        assertThat(user.getLastName()).isEqualTo("Weaver");
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Несуществующий пользователь 23 возвращает 404")
    void nonExistentUserReturns404() {
        given()
                .when()
                .get("/users/23")
                .then()
                .statusCode(404);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Тело ответа 404 пустое {}")
    void notFoundResponseBodyIsEmpty() {
        String body = given()
                .when()
                .get("/users/23")
                .then()
                .statusCode(404)
                .extract().asString();

        assertThat(body).isEqualTo("{}");
    }

    @ParameterizedTest(name = "Пользователь {0} существует и возвращает 200")
    @ValueSource(ints = {1, 2, 3, 4, 5, 6})
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Все пользователи 1-6 существуют")
    void allUsersFrom1to6Exist(int userId) {
        User user = given()
                .when()
                .get("/users/" + userId)
                .then()
                .statusCode(200)
                .extract().jsonPath().getObject("data", User.class);

        assertThat(user.getId()).isEqualTo(userId);
        assertThat(user.getEmail()).isNotBlank();
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Ответ содержит поле support с url и text")
    void responseContainsSupportBlock() {
        given()
                .when()
                .get("/users/1")
                .then()
                .statusCode(200)
                .body("support.url", equalTo("https://reqres.in/#support-heading"))
                .body("support.text", notNullValue());
    }
}
