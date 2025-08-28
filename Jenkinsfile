pipeline {
    agent any

    tools {
        git 'Git'
        maven 'maven'        // the Maven name you configured in Jenkins
        jdk 'jdk17 local'    // the JDK name you configured in Jenkins
    }

    stages {
        stage('Checkout') {
            steps { 
                // Pull code from SCM (GitHub, GitLab, etc.)
                checkout scm
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean install -DskipTests'  // compiles code, skip tests
            }
        }

        stage('Test') {
            steps {
                withCredentials([
                    usernamePassword(
                        credentialsId: 'mysql-db-connection',
                        usernameVariable: 'DB_USER',
                        passwordVariable: 'DB_PASS'
                    ),
                    usernamePassword(
                        credentialsId: 'ui-creds',
                        usernameVariable: 'UI_USER',
                        passwordVariable: 'UI_PASS'
                    )
                ]) {
                    bat 'mvn test'   // runs your TestNG tests
                }
            }
        }
    }

    post {
        always {
            // Publish standard JUnit test results
            junit 'target/surefire-reports/*.xml'

            // Publish ExtentReports HTML
            publishHTML([
                allowMissing: false,
                alwaysLinkToLastBuild: true,
                keepAll: true,
                reportDir: 'test-output/extent-reports',
                reportFiles: 'ExtentReport.html',
                reportName: 'Extent Report'
            ])
        }
    }
}