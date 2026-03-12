pipeline {
    agent any

    parameters {
        choice(name: 'TAG', choices: ['', 'api', 'ui'], description: 'Тег тестов (пусто = все)')
        choice(name: 'BROWSER', choices: ['chrome', 'firefox'], description: 'Браузер для UI')
        string(name: 'BROWSER_VERSION', defaultValue: '', description: 'Версия браузера (пусто = текущая в контейнере)')
        choice(name: 'BROWSER_SIZE', choices: ['1920x1080', '1366x768'], description: 'Разрешение')
        string(name: 'THREADS', defaultValue: '1', description: 'Потоки')
        string(name: 'REMOTE_URL', defaultValue: 'http://host.docker.internal:4444/wd/hub', description: 'Selenium Grid URL')
    }

    stages {
        stage('Запуск тестов') {
            steps {
                withCredentials([
                    string(credentialsId: 'REQRES_API_KEY', variable: 'API_KEY')
                ]) {
                    sh 'chmod +x gradlew'
                    sh """
                        ./gradlew clean test \
                            -Dtag=${params.TAG} \
                            -Dthreads=${params.THREADS} \
                            -Denv=remote \
                            -DwebIsRemote=true \
                            -DwebRemoteUrl=${params.REMOTE_URL} \
                            -DwebBrowserName=${params.BROWSER} \
                            -DwebBrowserVersion=${params.BROWSER_VERSION} \
                            -DwebBrowserSize=${params.BROWSER_SIZE} \
                            -DapiKey=${API_KEY} \
                            || true
                    """
                }
            }
        }

        stage('Allure отчёт') {
            steps {
                allure([
                    includeProperties: false,
                    jdk: '',
                    results: [[path: 'build/allure-results']]
                ])
            }
        }

        stage('Telegram уведомление') {
            steps {
                writeFile file: 'notifications/config.json', text: """{
          "base": {
            "logo": "",
            "project": "${JOB_BASE_NAME}",
            "environment": "Ubuntu 24.04.4 LTS | Java 21 | Chrome 145 | Selenide 7.14.0 | Rest Assured 5.4.0 | JUnit 5.10",
            "comment": "UI + API automation tests | Build #${BUILD_NUMBER}",
            "reportLink": "${BUILD_URL}allure/",
            "language": "ru",
            "allureFolder": "allure-report/",
            "enableChart": true
          },
          "telegram": {
            "token": "8699034330:AAG6uZ22MDA6FF73y19A73TgPA2L79Z9dW4",
            "chat": "1308302374",
            "topic": "",
            "replyTo": ""
          }
        }"""
                sh '''
                    FILE=allure-notifications-4.6.0.jar
                    if [ ! -f "$FILE" ]; then
                        wget -q https://github.com/qa-guru/allure-notifications/releases/download/4.6.0/allure-notifications-4.6.0.jar
                    fi
                '''
                sh 'java "-DconfigFile=notifications/config.json" -jar allure-notifications-4.6.0.jar'
            }
        }
    }

    post {
        always {
            cleanWs()
        }
    }
}