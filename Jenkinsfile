pipeline {
    agent any

    parameters {
        choice(name: 'TAG', choices: ['', 'api', 'ui', 'mobile'], description: 'Тег тестов (пусто = все)')
        choice(name: 'BROWSER', choices: ['chrome', 'firefox'], description: 'Браузер для UI')
        string(name: 'BROWSER_VERSION', defaultValue: '127.0', description: 'Версия браузера в Selenoid')
        choice(name: 'BROWSER_SIZE', choices: ['1920x1080', '1366x768'], description: 'Разрешение')
        string(name: 'THREADS', defaultValue: '2', description: 'Потоки')
        string(name: 'REMOTE_URL', defaultValue: 'http://selenoid:4444/wd/hub', description: 'Selenoid URL')
    }

    stages {
        stage('Запуск тестов') {
            steps {
                withCredentials([string(credentialsId: 'REQRES_API_KEY', variable: 'API_KEY')]) {
                    sh 'chmod +x gradlew'
                    sh """
                        ./gradlew clean test \
                            -Dtag=${params.TAG} \
                            -Dthreads=${params.THREADS} \
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
    }

    post {
        always {
            cleanWs()
        }
    }
}