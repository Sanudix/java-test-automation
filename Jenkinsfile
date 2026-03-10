pipeline {
    agent any

    parameters {
        choice(name: 'TAG', choices: ['', 'api', 'ui', 'mobile'], description: 'Тег тестов (пусто = все)')
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
                    string(credentialsId: 'REQRES_API_KEY', variable: 'API_KEY'),
                    string(credentialsId: 'BS_USER', variable: 'BS_USER'),
                    string(credentialsId: 'BS_KEY', variable: 'BS_KEY')
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
                            -DbsUser=${BS_USER} \
                            -DbsKey=${BS_KEY} \
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
    }

    post {
        always {
            cleanWs()
        }
    }
}