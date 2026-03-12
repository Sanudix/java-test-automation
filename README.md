# Java Test Automation Framework

<p align="center">
  <img src="https://img.shields.io/badge/Java-17-orange?logo=openjdk&logoColor=white" />
  <img src="https://img.shields.io/badge/Gradle-8.14-02303A?logo=gradle&logoColor=white" />
  <img src="https://img.shields.io/badge/JUnit5-5.10-25A162?logo=junit5&logoColor=white" />
  <img src="https://img.shields.io/badge/Selenide-7.14.0-D82C7D" />
  <img src="https://img.shields.io/badge/REST--Assured-5.4.0-6DB33F" />
  <img src="https://img.shields.io/badge/Allure-2.32.0-FF8C00?logo=qameta&logoColor=white" />
  <img src="https://img.shields.io/badge/Jenkins-CI-D24939?logo=jenkins&logoColor=white" />
  <img src="https://img.shields.io/badge/Docker-Selenoid-2496ED?logo=docker&logoColor=white" />
  <img src="https://img.shields.io/badge/Telegram-Bot-26A5E4?logo=telegram&logoColor=white" />
</p>

Фреймворк для автоматического тестирования **UI** и **API**, построенный на Java 17. Включает интеграцию с Jenkins, Selenoid (Docker), Allure-отчёты и Telegram-уведомления.

---

## Общая информация о проекте

| Что тестируем | Стенд | Тип тестов |
|---|---|---|
| Web UI — Elements ([demoqa.com](https://demoqa.com)) | Selenoid в Docker | Smoke, Regression |
| REST API ([reqres.in](https://reqres.in)) | Прямые HTTP-запросы | CRUD, Contract, Auth |

---

## Технологический стек

<p align="center">
  <a href="https://www.java.com"><img width="40" title="Java" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg" /></a>
  &nbsp;&nbsp;
  <a href="https://gradle.org"><img width="40" title="Gradle" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/gradle/gradle-original.svg" /></a>
  &nbsp;&nbsp;
  <a href="https://junit.org/junit5"><img width="40" title="JUnit 5" src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/junit/junit-original.svg" /></a>
  &nbsp;&nbsp;
  <a href="https://selenide.org"><img width="40" title="Selenide" src="https://avatars.githubusercontent.com/u/43955696?s=200&v=4" /></a>
  &nbsp;&nbsp;
  <a href="https://rest-assured.io"><img width="40" title="REST Assured" src="https://avatars.githubusercontent.com/u/19369327?s=200&v=4" /></a>
  &nbsp;&nbsp;
  <a href="https://allurereport.org"><img width="40" title="Allure Report" src="https://avatars.githubusercontent.com/u/5879127?s=200&v=4" /></a>
  &nbsp;&nbsp;
  <a href="https://www.docker.com"><img width="40" title="Docker" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/docker/docker-original.svg" /></a>
  &nbsp;&nbsp;
  <a href="https://www.jenkins.io"><img width="40" title="Jenkins" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/jenkins/jenkins-original.svg" /></a>
  &nbsp;&nbsp;
  <a href="https://aerokube.com/selenoid"><img width="40" title="Selenoid" src="https://aerokube.com/img/aerokube_logo.svg" /></a>
  &nbsp;&nbsp;
  <a href="https://github.com/qa-guru/allure-notifications"><img width="40" title="Telegram Bot" src="https://upload.wikimedia.org/wikipedia/commons/8/82/Telegram_logo.svg" /></a>
</p>

| Инструмент | Назначение |
|---|---|
| `Java 17` | Язык проекта |
| `Gradle 8.14` | Сборка и управление зависимостями |
| `JUnit 5` | Тестовый фреймворк |
| `Selenide 7.14.0` | UI-автотесты (обёртка над Selenium) |
| `REST Assured 5.4.0` | API-автотесты |
| `Allure Report` | Формирование отчётов о прогоне |
| `Jenkins` | CI/CD — запуск тестов |
| `Docker + Selenoid` | Запуск браузеров в контейнерах |
| `Owner` | Управление конфигурацией через property-файлы |
| `Lombok` | Генерация boilerplate-кода (модели) |
| `AssertJ` | Гибкие assertions |
| `Allure Notifications` | Telegram-уведомления с результатами |

---

## Покрытый функционал

### UI — Web-приложение [demoqa.com](https://demoqa.com)

> Автотесты покрывают раздел **Elements** — 9 страниц, **48 тестов**

| Страница | Проверки |
|---|---|
| Text Box | Ввод данных, валидация email, спецсимволы, пустая форма, отображение полей |
| Check Box | Выбор/снятие чекбоксов, каскадный выбор, indeterminate-состояние |
| Radio Button | Выбор опций, disabled-состояние, переключение |
| Web Tables | Добавление записи, поиск, отображение дефолтных данных |
| Buttons | Обычный / двойной / правый клик, E2E через навигацию |
| Links | Simple/Dynamic ссылки, API-ссылки (201, 204, 301, 400, 401, 403, 404) |
| Upload and Download | Скачивание и загрузка файлов, проверка атрибутов |
| Broken Links - Images | Валидные/битые изображения и ссылки |
| Dynamic Properties | Ожидание появления, активации кнопки, смена CSS-класса |

### API — Сервис [reqres.in](https://reqres.in)

> REST API тесты — **59 тестов** по всем CRUD-операциям

| Эндпоинт | Проверки |
|---|---|
| `GET /users` | Список пользователей, пагинация, структура ответа |
| `GET /users/{id}` | Получение одного пользователя, несуществующий id → 404 |
| `POST /users` | Создание пользователя, валидация полей, пустое тело |
| `PUT /users/{id}` | Полное обновление, проверка updatedAt |
| `PATCH /users/{id}` | Частичное обновление |
| `DELETE /users/{id}` | Удаление, повторное удаление, несуществующий id |
| `POST /register` | Успешная регистрация, без пароля → 400 |
| `POST /login` | Успешный логин, неверный пароль → 400 |
| `GET /unknown` | Ресурсы, структура, несуществующий ресурс → 404 |
| Контрактные тесты | Content-Type, время ответа, JSON-схема |

---

## Запуск тестов из терминала

### Локальный запуск с property-файлом

```bash
gradle clean test -Denv={имя property файла в папке test.resources}
```

### Локальный запуск (конфиг по умолчанию — test)

```bash
gradle clean test
```

### Описание параметров для запуска тестов

| Параметр | Описание |
|---|---|
| `-Dtag` | Тег тестов для запуска (`ui`, `api` или пусто = все) |
| `-Dthreads` | Количество потоков для параллельного запуска |
| `-Denv` | Имя property-файла конфигурации (`local`, `remote`) |
| `-DwebBrowserName` | Название браузера (`chrome`, `firefox`) |
| `-DwebBrowserVersion` | Версия браузера |
| `-DwebBrowserSize` | Разрешение браузера (`1920x1080`, `1366x768`) |
| `-DwebIsRemote` | Запуск через remote-сервис (`true`/`false`) |
| `-DwebRemoteUrl` | URL remote-сервиса (Selenoid) |
| `-DwebIsHeadless` | Запуск в headless-режиме |

### Пример удалённого запуска

```bash
gradle clean test \
  -Denv=remote \
  -Dtag=ui \
  -Dthreads=2 \
  -DwebIsRemote=true \
  -DwebRemoteUrl=http://localhost:4444/wd/hub \
  -DwebBrowserName=chrome \
  -DwebBrowserSize=1920x1080
```

---

## 🚀 Удалённый запуск тестов в Jenkins

Для запуска тестов используется параметризованная сборка:

<p align="center">
  <em>Параметры сборки в Jenkins</em>
</p>

| Параметр | Описание | Значения |
|---|---|---|
| `TAG` | Тег тестов (пусто = все) | `""`, `api`, `ui` |
| `BROWSER` | Браузер для UI | `chrome`, `firefox` |
| `BROWSER_VERSION` | Версия браузера | Пусто = текущая в контейнере |
| `BROWSER_SIZE` | Разрешение | `1920x1080`, `1366x768` |
| `THREADS` | Потоки | `1` по умолчанию |
| `REMOTE_URL` | Selenium Grid URL | `http://host.docker.internal:4444/wd/hub` |

---

## 📊 Allure Report

После завершения сборки формируется Allure-отчёт с детальной информацией по каждому тесту.

### Главная страница отчёта

> Общая статистика: passed / failed / broken / skipped, тренд, длительность

### Группировка тестов по Suites

> Тесты сгруппированы по Epic → Feature → Story с аннотациями `@DisplayName`

### Детали теста

> Каждый тест содержит: шаги выполнения, Severity, тэги, вложения (скриншоты, логи)

---

## 📬 Уведомления в Telegram

После завершения сборки специальный бот, созданный в `Telegram`, автоматически обрабатывает результаты и отправляет сообщение с отчётом о прогоне.

Информация по настройке и использованию бота: [allure-notifications](https://github.com/qa-guru/allure-notifications)

> Сообщение содержит: пай-чарт с результатами, окружение, количество тестов, процент прохождения и ссылку на Allure-отчёт

---

## Структура проекта

```
java-test-automation/
├── src/
│   ├── main/java/io/github/sanudix/automation/
│   │   ├── config/              # Конфигурация (Owner-интерфейсы)
│   │   ├── ui/pages/            # Page Objects для UI-тестов
│   │   ├── api/models/          # POJO-модели для API-ответов
│   │   └── mobile/              # Mobile (screens, drivers)
│   └── test/
│       ├── java/io/github/sanudix/automation/
│       │   ├── base/            # Базовые классы тестов (BaseWebTest, BaseApiTest)
│       │   ├── ui/              # UI-тесты (9 классов, 48 тестов)
│       │   └── api/             # API-тесты (10 классов, 59 тестов)
│       └── resources/config/    # Property-файлы (local, remote)
├── notifications/               # Конфигурация Telegram-бота
├── Jenkinsfile                  # Pipeline для Jenkins
├── build.gradle.kts             # Зависимости и настройки сборки
└── docker-compose.yml           # Selenoid + Selenoid UI
```
