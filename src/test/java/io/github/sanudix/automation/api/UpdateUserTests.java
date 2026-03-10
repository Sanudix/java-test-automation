package io.github.sanudix.automation.api;

import io.github.sanudix.automation.api.models.UpdateUserResponse;
import io.github.sanudix.automation.base.BaseApiTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@Epic("reqres.in API")
@Feature("PUT & PATCH /users/{id} — обновление пользователя")
@DisplayName("Тесты обновления пользователя")
public class UpdateUserTests extends BaseApiTest {

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("PUT обновление пользователя возвращает 200")
    void putUserReturns200() {
        given()
                .body(Map.of("name", "Ivan Updated", "job", "Senior QA"))
                .when()
                .put("/users/2")
                .then()
                .statusCode(200);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("PUT — name и job в ответе обновились")
    void putUpdatesNameAndJob() {
        UpdateUserResponse response = given()
                .body(Map.of("name", "Morpheus", "job", "zion resident"))
                .when()
                .put("/users/2")
                .then()
                .statusCode(200)
                .extract().as(UpdateUserResponse.class);

        assertThat(response.getName()).isEqualTo("Morpheus");
        assertThat(response.getJob()).isEqualTo("zion resident");
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("PUT — updatedAt присутствует в ответе")
    void putResponseContainsUpdatedAt() {
        UpdateUserResponse response = given()
                .body(Map.of("name", "Test", "job", "Test"))
                .when()
                .put("/users/2")
                .then()
                .statusCode(200)
                .extract().as(UpdateUserResponse.class);

        assertThat(response.getUpdatedAt()).isNotBlank();
    }

    @ParameterizedTest(name = "PUT обновление пользователя {0}")
    @ValueSource(ints = {1, 2, 3})
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("PUT — обновить нескольких пользователей")
    void putSeveralUsers(int userId) {
        UpdateUserResponse response = given()
                .body(Map.of("name", "User" + userId, "job", "Job" + userId))
                .when()
                .put("/users/" + userId)
                .then()
                .statusCode(200)
                .extract().as(UpdateUserResponse.class);

        assertThat(response.getName()).isEqualTo("User" + userId);
        assertThat(response.getUpdatedAt()).isNotBlank();
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("PATCH только job возвращает 200")
    void patchOnlyJobReturns200() {
        UpdateUserResponse response = given()
                .body(Map.of("job", "Patched Job"))
                .when()
                .patch("/users/2")
                .then()
                .statusCode(200)
                .extract().as(UpdateUserResponse.class);

        assertThat(response.getJob()).isEqualTo("Patched Job");
        assertThat(response.getUpdatedAt()).isNotBlank();
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("PATCH только name возвращает 200")
    void patchOnlyNameReturns200() {
        UpdateUserResponse response = given()
                .body(Map.of("name", "Patched Name"))
                .when()
                .patch("/users/2")
                .then()
                .statusCode(200)
                .extract().as(UpdateUserResponse.class);

        assertThat(response.getName()).isEqualTo("Patched Name");
        assertThat(response.getUpdatedAt()).isNotBlank();
    }

    @Test
    @Severity(SeverityLevel.MINOR)
    @DisplayName("PATCH и PUT оба возвращают updatedAt")
    void patchAndPutBothReturnUpdatedAt() {
        UpdateUserResponse putResponse = given()
                .body(Map.of("name", "A", "job", "B"))
                .when()
                .put("/users/2")
                .then()
                .statusCode(200)
                .extract().as(UpdateUserResponse.class);

        UpdateUserResponse patchResponse = given()
                .body(Map.of("name", "A", "job", "B"))
                .when()
                .patch("/users/2")
                .then()
                .statusCode(200)
                .extract().as(UpdateUserResponse.class);

        assertThat(putResponse.getUpdatedAt()).isNotBlank();
        assertThat(patchResponse.getUpdatedAt()).isNotBlank();
    }
}
