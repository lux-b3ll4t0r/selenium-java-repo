pipeline {
    agent any

    tools {
        maven 'maven'   // the Maven name you configured in Jenkins
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
                sh 'mvn clean install -DskipTests'   // compiles code, skip tests
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'   // runs your TestNG tests
            }
        }
    }

    post {
        always {
            junit 'target/surefire-reports/*.xml'   // publish TestNG/JUnit reports
        }
    }
}