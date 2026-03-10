package io.github.sanudix.automation.api;

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

@Epic("reqres.in API")
@Feature("DELETE /users/{id} — удаление пользователя")
@DisplayName("Тесты удаления пользователя")
public class DeleteUserTests extends BaseApiTest {

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("DELETE пользователя возвращает 204")
    void deleteUserReturns204() {
        given()
                .when()
                .delete("/users/2")
                .then()
                .statusCode(204);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Тело ответа DELETE пустое")
    void deleteResponseBodyIsEmpty() {
        String body = given()
                .when()
                .delete("/users/2")
                .then()
                .statusCode(204)
                .extract().asString();

        assertThat(body).isEmpty();
    }

    @ParameterizedTest(name = "DELETE пользователя {0} возвращает 204")
    @ValueSource(ints = {1, 2, 3})
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Параметризованное удаление нескольких пользователей")
    void deleteSeveralUsers(int userId) {
        given()
                .when()
                .delete("/users/" + userId)
                .then()
                .statusCode(204);
    }
}
