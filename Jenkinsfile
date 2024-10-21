properties([
            disableConcurrentBuilds(),
            buildDiscarder(logRotator(numToKeepStr: '5')),
            parameters([
                    booleanParam(name: 'EnableANSIColor', defaultValue: true),
                    choice(name: 'browser', choices: ['chrome', 'opera']),
                    string(name: "browser_version", defaultValue: "127.0", trim: true),
                    string(name: "selenoid_url", defaultValue: "http://192.168.1.124/wd/hub", trim: true),
                    string(name: "image_version", defaultValue: "1.0.0", trim: true)
            ])
])

pipeline {

    agent {
        label 'docker-node-1'
    }

    options {
        timeout(time: 15, unit: 'MINUTES')
    }

    stages {
        stage('Prepare parameters') {
            steps {
                sh "echo BROWSER=$params.browser > ./.env"
                sh "echo BROWSER_VER=$params.browser_version >> ./.env"
                sh "echo REMOTE_URL=$params.selenoid_url >> ./.env"
            }
        }

        stage('Pull test image') {
            steps {
                sh "docker pull localhost:5000/ui-tests:$params.image_version"
            }
        }

        stage('Run test image') {
            steps {
                sh "docker run --rm \
                -v /home/konstantin/jenkins/jenkins/jenkins_home/workspace/ui-tests/allure-results:/ui-tests/target/allure-results \
                --env-file ./.env \
                localhost:5000/ui-tests:$params.image_version"
            }
        }

        stage('Generate report') {
            steps {
                allure([
                        includeProperties: false,
                        jdk: '',
                        properties: [],
                        reportBuildPolicy: 'ALWAYS',
                        results: [[path: "allure-results"]]
                ])
            }
        }
    }

    post {
      success { buildMessageAndSendTelegram('🚀', 'Success') }
      unstable { buildMessageAndSendTelegram('💣', 'Unstable') }
      failure { buildMessageAndSendTelegram('💥', 'Failure') }
      aborted { buildMessageAndSendTelegram('😥', 'Aborted') }
    }

}



def buildMessageAndSendTelegram(smiley, status) {
    message = "${smiley}\n Job Name: ${JOB_NAME} \nBuild: ${BUILD_DISPLAY_NAME} \nStatus: ${status}"
    message += "\nLog: ${env.BUILD_URL}console"
    if (status.equals("Success") || status.equals("Unstable")) {
        message += "\nAllure report: ${BUILD_URL}allure" + buildAllureResults()
    }
    sendTelegram(message)
    cleanWs()
}

def sendTelegram(message) {
    withCredentials([
        string(credentialsId: 'tg_key', variable: 'TG_KEY'),
        string(credentialsId: 'tg_chart_id', variable: 'TG_CHART_ID')
    ]) {
    sh """
      curl -X POST -H 'Content-Type: application/json' -d '{
          "chat_id": ${TG_CHART_ID},
          "text": "${message}",
          "disable_notification": false
      }' https://api.telegram.org/bot${TG_KEY}/sendMessage
      """
    }
}

def buildAllureResults(){
    def summaryFile = "allure-report/widgets/summary.json"
    if (fileExists(summaryFile)) {
        def summary = sh(script: "cat ${summaryFile}", returnStdout: true).trim()
        def jsonSlurper = new groovy.json.JsonSlurper()
        def summaryJson = jsonSlurper.parseText(summary)
        def passed = summaryJson.statistic.passed
        def failed = summaryJson.statistic.failed
        def skipped = summaryJson.statistic.skipped
        def total = summaryJson.statistic.total
        def error = total - passed - failed - skipped
        "\n     Passed: ${passed}\n     Failed: ${failed}\n     Error: ${error}\n     Skipped: ${skipped}\n     Total: ${total}"
    } else {
        "\nSummary report not found: ${summaryFile}"
    }
}