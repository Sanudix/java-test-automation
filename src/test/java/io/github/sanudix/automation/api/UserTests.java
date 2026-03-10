package io.github.sanudix.automation.api;

import io.github.sanudix.automation.api.models.User;
import io.github.sanudix.automation.api.models.UserListResponse;
import io.github.sanudix.automation.base.BaseApiTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@Epic("reqres.in API")
@Feature("Общие CRUD-тесты")
@DisplayName("reqres.in API tests")
public class UserTests extends BaseApiTest {

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("GET список пользователей — страница 2")
    void getUserListTest() {
        UserListResponse response = given()
                .queryParam("page", 2)
                .when()
                .get("/users")
                .then()
                .extract().as(UserListResponse.class);

        assertThat(response.getData()).isNotEmpty();
        assertThat(response.getPage()).isEqualTo(2);
        assertThat(response.getData())
                .allMatch(u -> u.getEmail().contains("@"));
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("GET один пользователь — существующий")
    void getSingleUserTest() {
        User user = given()
                .when()
                .get("/users/2")
                .then()
                .extract().jsonPath().getObject("data", User.class);

        assertThat(user.getId()).isEqualTo(2);
        assertThat(user.getEmail()).isNotBlank();
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("POST создание пользователя")
    void createUserTest() {
        var body = Map.of("name", "Ivan", "job", "QA Engineer");

        given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .body("name", equalTo("Ivan"))
                .body("id", notNullValue());
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("POST логин — успешный")
    void loginSuccessTest() {
        var body = Map.of(
                "email", "eve.holt@reqres.in",
                "password", "cityslicka"
        );

        String token = given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/login")
                .then()
                .extract().jsonPath().getString("token");

        assertThat(token).isNotBlank();
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("POST логин — без пароля, 400")
    void loginFailTest() {
        var body = Map.of("email", "test@test.com");

        given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/login")
                .then()
                .statusCode(400)
                .body("error", equalTo("Missing password"));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("DELETE пользователь — 204")
    void deleteUserTest() {
        given()
                .when()
                .delete("/users/2")
                .then()
                .statusCode(204);
    }
}
