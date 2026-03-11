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
                withCredentials([
                    string(credentialsId: 'TELEGRAM_BOT_TOKEN', variable: 'TG_TOKEN'),
                    string(credentialsId: 'TELEGRAM_CHAT_ID', variable: 'TG_CHAT')
                ]) {
                    sh '''
                        FILE=allure-notifications-4.6.0.jar
                        if [ ! -f "$FILE" ]; then
                            wget -q https://github.com/qa-guru/allure-notifications/releases/download/4.6.0/allure-notifications-4.6.0.jar
                        fi
                    '''
                    sh """
                        java \
                            "-DconfigFile=notifications/config.json" \
                            "-Dnotifications.telegram.token=${TG_TOKEN}" \
                            "-Dnotifications.telegram.chat=${TG_CHAT}" \
                            "-Dnotifications.base.reportLink=${BUILD_URL}" \
                            -jar allure-notifications-4.6.0.jar
                    """
                }
            }
        }
    }

    post {
        always {
            cleanWs()
        }
    }
}