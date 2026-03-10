package io.github.sanudix.automation.api;

import io.github.sanudix.automation.api.models.CreateUserResponse;
import io.github.sanudix.automation.base.BaseApiTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.Instant;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@Epic("reqres.in API")
@Feature("POST /users — создание пользователя")
@DisplayName("Тесты создания пользователя")
public class CreateUserTests extends BaseApiTest {

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Создание с name и job возвращает 201")
    void createUserReturns201() {
        given()
                .body(Map.of("name", "Ivan", "job", "QA Engineer"))
                .when()
                .post("/users")
                .then()
                .statusCode(201);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("В ответе есть id и createdAt")
    void responseContainsIdAndCreatedAt() {
        CreateUserResponse response = given()
                .body(Map.of("name", "Ivan", "job", "QA Engineer"))
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .extract().as(CreateUserResponse.class);

        assertThat(response.getId()).isNotBlank();
        assertThat(response.getCreatedAt()).isNotBlank();
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("name и job в ответе совпадают с отправленными")
    void responseNameAndJobMatchRequest() {
        CreateUserResponse response = given()
                .body(Map.of("name", "Olga", "job", "Developer"))
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .extract().as(CreateUserResponse.class);

        assertThat(response.getName()).isEqualTo("Olga");
        assertThat(response.getJob()).isEqualTo("Developer");
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("createdAt — валидная дата ISO 8601")
    void createdAtIsValidDate() {
        CreateUserResponse response = given()
                .body(Map.of("name", "Test", "job", "Tester"))
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .extract().as(CreateUserResponse.class);

        assertThatCode(() -> Instant.parse(response.getCreatedAt()))
                .doesNotThrowAnyException();
    }

    @Test
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Создание только с name (без job) возвращает 201")
    void createUserWithoutJobReturns201() {
        CreateUserResponse response = given()
                .body(Map.of("name", "NoJob"))
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .extract().as(CreateUserResponse.class);

        assertThat(response.getName()).isEqualTo("NoJob");
        assertThat(response.getId()).isNotBlank();
    }

    @ParameterizedTest(name = "Создание пользователя name={0}, job={1}")
    @CsvSource({
            "Alice, Manager",
            "Bob, Designer",
            "Charlie, DevOps",
            "Diana, Analyst"
    })
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Параметризованное создание разных пользователей")
    void createVariousUsers(String name, String job) {
        CreateUserResponse response = given()
                .body(Map.of("name", name, "job", job))
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .extract().as(CreateUserResponse.class);

        assertThat(response.getName()).isEqualTo(name);
        assertThat(response.getJob()).isEqualTo(job);
        assertThat(response.getId()).isNotBlank();
    }
}
